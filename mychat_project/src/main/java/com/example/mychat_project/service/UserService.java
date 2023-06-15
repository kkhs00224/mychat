package com.example.mychat_project.service;

import com.example.mychat_project.vo.UsersVo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UsersVo userRegister(UsersVo user);

    int userRemove(UsersVo user);

    UsersVo selectOne(String username);


}
