package com.server.finderly_backend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@RestController
@RequestMapping("session")
public class SessionController {
    public static Hashtable<String, HttpSession> sessionList = new Hashtable<>();

    @GetMapping("list")
    @ResponseBody
    public Map<String, String> sessionList() {
        Enumeration<HttpSession> elements = sessionList.elements();
        Map<String, String> lists = new HashMap<>();
        while(elements.hasMoreElements()) {
            HttpSession session = elements.nextElement();
            try{
                if(session !=null && session.getAttribute("userId") != null) {
                    //lists.put(session.getId(), session.getId());
                    lists.put(session.getId(), String.valueOf(session.getAttribute("userId")));
                }
            }catch (IllegalStateException e) {
                e.printStackTrace();
                sessionList.remove(session.getId());
            }
            //lists.put(session.getId(), String.valueOf(session.getAttribute("userId")));
        }
        return lists;
    }
}
