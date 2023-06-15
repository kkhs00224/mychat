package com.example.mychat_project.repository;

import com.example.mychat_project.vo.UsersVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersVo, String> {

    UsersVo findUsersVoByUsernameAndPassword(String username, String password);

    int deleteUsersVoByUsernameAndPassword(String username, String password);

    UsersVo findUsersVoByUsername(String username);

}
