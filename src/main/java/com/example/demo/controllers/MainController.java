package com.example.demo.controllers;

import java.awt.*;
import java.util.*;
import java.io.File;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping({ "/" })
    public String mainPage() {
        return "redirect:/mainPage";
    }

    @GetMapping({ "/mainPage" })
    public String mainPage(Model model, Principal user) {
        model.addAttribute("benches", getBenches());
        model.addAttribute("swing", getSwing());
        model.addAttribute("tables", getTables());
        getBenches();
        return "mainPage/index";
    }

    private ArrayList<String> getBenches() {
        File benchesPath = new File("../demo/src/main/resources/static/sources/gallery/benches");
        return addStrToStr(benchesPath.list(), "benches/");
    }

    private ArrayList<String> getSwing() {
        File swingPath = new File("../demo/src/main/resources/static/sources/gallery/swing");
        return addStrToStr(swingPath.list(), "swing/");
    }

    private ArrayList<String> getTables() {
        File tablesPath = new File("../demo/src/main/resources/static/sources/gallery/tables");
        return addStrToStr(tablesPath.list(), "tables/");
    }

    private ArrayList<String> addStrToStr(String[] strs, String addStr ){
        ArrayList<String> strList = new ArrayList<>();
        for(String someStr : strs){
            someStr = addStr + someStr;
            strList.add(someStr);
        }
        return strList;
    }

}
