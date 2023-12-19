package com.ra.service.impl;

import com.ra.model.Users;
import com.ra.repository.IUserRepository;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Users> sortByName(String order) {
        if(order.equals("desc")){
            return userRepository.findAll(Sort.by("username").descending());
        } else if(order.equals("asc")) {
            return userRepository.findAll(Sort.by("username").ascending());
        }
        return userRepository.findAll();
    }

    @Override
    public Page<Users> getPaginate(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
