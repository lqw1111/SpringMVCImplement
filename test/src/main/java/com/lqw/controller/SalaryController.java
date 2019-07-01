package com.lqw.controller;

import com.lqw.beans.AutoWired;
import com.lqw.service.SalaryService;
import com.lqw.web.mvc.Controller;
import com.lqw.web.mvc.RequestMapping;
import com.lqw.web.mvc.RequestParam;

@Controller
public class SalaryController {

    @AutoWired
    private SalaryService salaryService;

    @RequestMapping("/getSalary.json")
    public Integer getSalary(@RequestParam("name") String name, @RequestParam("experience") String experience){
        System.out.println(name);
        System.out.println(experience);
        return salaryService.calSalary(Integer.parseInt(experience));
    }
}
