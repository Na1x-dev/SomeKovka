package com.example.demo.controllers.main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.security.Principal;
import java.util.*;

@Controller
public class MainController {

    @GetMapping({"/mainPage/"})
    public String mainPage(Model model, Principal user) {
        return "mainPage/index";
    }

}
