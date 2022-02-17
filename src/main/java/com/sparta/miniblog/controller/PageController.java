package com.sparta.miniblog.controller;

import com.sparta.miniblog.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    @GetMapping("/forum/{idx}")
    public String getPostandComments(@PathVariable Long idx, Model model) {
        model.addAttribute("data", idx);
        return "forum";
    }
}
