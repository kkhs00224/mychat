package com.example.mychat_project.service;

import com.example.mychat_project.repository.AuthoritiesRepository;
import com.example.mychat_project.repository.UsersRepository;
import com.example.mychat_project.vo.AuthoritiesVo;
import com.example.mychat_project.vo.AuthorityId;
import com.example.mychat_project.vo.UsersVo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UsersRepository userRepository;
    final AuthoritiesRepository authoritiesRepository;


    @Override
    @Transactional
    public UsersVo userRegister(UsersVo user) {
        UsersVo userTmp;
        userTmp = userRepository.findUsersVoByUsername(user.getUsername());

        if(userTmp == null){
            userTmp = userRepository.save(user);
            AuthoritiesVo authoritiesVo = new AuthoritiesVo();
            AuthorityId authorityId = new AuthorityId();
            authorityId.setUsername(user.getUsername());
            authorityId.setAuthority("ROLE_USER");
            authoritiesVo.setAuthorityId(authorityId);
            authoritiesRepository.save(authoritiesVo);
            return userTmp;
        }else{
            return null;
        }
    }

    @Transactional
    @Override
    public int userRemove(UsersVo user){
        int remove = 0;
        remove += userRepository.deleteUsersVoByUsernameAndPassword(user.getUsername(), user.getPassword());
        return remove;
    }

    @Override
    public UsersVo selectOne(String username){
        return userRepository.findUsersVoByUsername(username);
    }
}
