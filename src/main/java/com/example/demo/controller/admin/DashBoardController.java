package com.example.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DashBoardController {

    @RequestMapping("/admin")
    public String getDashBoard(){
        return "admin/dashboard/show";
    }    
}
