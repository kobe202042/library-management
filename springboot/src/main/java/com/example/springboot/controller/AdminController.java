package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.service.IAdminService;
import com.example.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IAdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request){
        LoginDTO login=adminService.login(request);
        return Result.success(login);
    }
    @PostMapping("/save")
    public Result save(@RequestBody Admin obj){

        adminService.save(obj);
        return Result.success();
    }
    @PutMapping ("/update")
    public Result update(@RequestBody Admin obj){

        adminService.update(obj);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<Admin> list= adminService.list();
        return Result.success(list);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        adminService.deleteById(id);
        return Result.success();
    }


    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Admin obj= adminService.getById(id);
        return Result.success(obj);
    }

    @GetMapping("/page")
    public Result page(adminPageRequest pageRequest){
        return Result.success(adminService.page(pageRequest));
    }
}
