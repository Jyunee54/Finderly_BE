package com.server.finderly_backend.service;

import com.server.finderly_backend.data.entity.Post;
import com.server.finderly_backend.data.repository.CommentRepository;
import com.server.finderly_backend.data.repository.UserRepository;
import com.server.finderly_backend.dto.ResponseDto;
import com.server.finderly_backend.dto.comment.CommentDto;
import com.server.finderly_backend.dto.post.FilteredPostDto;
import com.server.finderly_backend.dto.post.PostDto;
import com.server.finderly_backend.dto.post.RegisterPostDto;
import com.server.finderly_backend.dto.post.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    //private final UserService userService;
    private final MongoTemplate mongoTemplate;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    // 아이디로 게시글 찾기
    public Boolean existsByPostId(int postCategory, String postId) {
        String collectionName = getCollectionName(postCategory);
        return mongoTemplate.exists(Query.query(Criteria.where("_id").is(postId)),collectionName);
    }
    public Post getPostById(int postCategory, String postId) {
        String collectionName = getCollectionName(postCategory);
        return  mongoTemplate.findById(postId, Post.class, collectionName);
    }

    // 게시글 등록
    public ResponseEntity<?> addPost(RegisterPostDto registerPostDto) {
        if(!userRepository.existsByUserId(registerPostDto.getUserId())){
            return new ResponseEntity<>(new ResponseDto("회원가입되지 않은 사용자입니다."), HttpStatus.BAD_REQUEST);
        }
        String collectionName = getCollectionName(registerPostDto.getPostCategory());
        String postId = mongoTemplate.save(new Post(registerPostDto), collectionName).getPostId();

        return ResponseEntity.ok(new PostResponseDto("게시물 등록 완료", postId));
    }

    private String getCollectionName(int postCategory) {
        if(postCategory==0){
            return "lostPost";
        }else{
            return "foundPost";
        }
    }

    // 게시글 상세 정보 조회
    public ResponseEntity<?> getPost(int category, String postId) {
        Post post = getPostById(category, postId);
        if(post==null){
            return new ResponseEntity<>(new ResponseDto("해당 게시글이 존재하지 않습니다."), HttpStatus.NOT_FOUND);
        }
        List<CommentDto> comments = commentRepository.findCommentsByPostId(postId);
        PostDto postDto = new PostDto(post);
        postDto.setComments(comments);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    // 게시글 리스트 조회
    public ResponseEntity<List<FilteredPostDto>> getAllPostList(int category){
        String collectionName = getCollectionName(category);
        List<Post> posts = mongoTemplate.findAll(Post.class, collectionName);
        List<FilteredPostDto> filteredPosts = posts.stream().map(FilteredPostDto::new).toList();
        if(filteredPosts.isEmpty()){
            return new ResponseEntity<>(filteredPosts, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
    }

    // 사용자가 작성한 게시글 리스트 조회
    public List<FilteredPostDto> getPostListByCategory(int category, String userId){
        String collectionName = getCollectionName(category);
        Query query = Query.query(Criteria.where("userId").is(userId));
        List<Post> posts = mongoTemplate.find(query, Post.class, collectionName);
        List<FilteredPostDto> filteredPosts = posts.stream().map(FilteredPostDto::new).toList();
        return filteredPosts;
    }

    // 게시글 삭제
    public ResponseEntity<ResponseDto> deletePost(int category, String postId) {
        Post post = getPostById(category,postId);
        if(post==null){
            return new ResponseEntity<>(new ResponseDto("게시물이 존재하지 않습니다."), HttpStatus.NOT_FOUND);
        }
        String collectionName = getCollectionName(category);
        Query query = new Query(Criteria.where("_id").is(postId));
        mongoTemplate.remove(query, Post.class, collectionName);
        return new ResponseEntity<>(new ResponseDto("게시물 삭제 성공"), HttpStatus.OK);
    }

    // 게시글 검색
    public ResponseEntity<?> searchByPostName(int category, String postTitle){
        String collectionName = getCollectionName(category);
        Query query = new Query(Criteria.where("postTitle").regex(postTitle,"i"));
        List<Post> posts = mongoTemplate.find(query, Post.class, collectionName);
        List<FilteredPostDto> filteredPosts = posts.stream().map(FilteredPostDto::new).toList();
        if(filteredPosts.isEmpty()){
            //return new ResponseEntity<>(new ResponseDto("검색 결과가 없습니다."), HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(filteredPosts, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
    }

}
