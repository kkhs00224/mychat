package com.example.mychat_project.repository;

import com.example.mychat_project.vo.AuthoritiesVo;
import com.example.mychat_project.vo.AuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<AuthoritiesVo, String> {
    AuthoritiesVo findByAuthorityId_Username(String username);

    AuthoritiesVo findByAuthorityId_Authority(String authority);

    int deleteAuthoritiesVoByAuthorityId(AuthorityId authorityId);
}
