package com.ra.service;

import com.ra.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<Users> findAll();

    List<Users> sortByName(String order);

    Page<Users> getPaginate(Pageable pageable);
}
