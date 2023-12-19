package com.ra.controller;

import com.ra.model.Users;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/ti-nua-lam")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String order){
        if(order != null){
            List<Users> list = userService.sortByName(order);
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        List<Users> list = userService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> paginate
            (@RequestParam(defaultValue = "0") int page,
             @RequestParam(defaultValue = "2") int limit){
        Pageable pageable = PageRequest.of(page,limit);
        Page<Users> usersPage = userService.getPaginate(pageable);
        Map<String,Object> data = new HashMap<>();
        data.put("users",usersPage.getContent());
        data.put("total",usersPage.getSize());
        data.put("totalElement",usersPage.getTotalElements());
        data.put("totalPages",usersPage.getTotalPages());
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
}
