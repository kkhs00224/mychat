package com.example.mychat_project.controller;

import com.example.mychat_project.service.UserService;
import com.example.mychat_project.vo.UsersVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        if (authentication != null) {
            System.out.println("authentication 정보 : " + authentication.getClass());

            // 세션 객체 반환
            WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
            System.out.println("세션 ID : " + web.getSessionId());
            System.out.println("접속 IP : " + web.getRemoteAddress());
            System.out.println("authentication.getName : " + authentication.getName());

            // UserDetails 객체 반환
            UserDetails userVo = (UserDetails) authentication.getPrincipal();
            System.out.println("ID 정보 : " + userVo.getUsername());
            System.out.println("권한 정보 : " + userVo.getAuthorities());
        }
        return "hello";
    }

    @GetMapping("signup")
    public String signup() {
        return "user/signup";
    }

    @PostMapping("signup")
    public String signup(@RequestParam(name = "username") String username,
                         @RequestParam(name = "password") String password) {
        UsersVo user = new UsersVo();
        user.setUsername(username);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        UsersVo regUser = userService.userRegister(user);
        if (regUser != null && user.getUsername().equals(regUser.getUsername())) {
            return "redirect:/login";
        } else {
            return "redirect:/user/signup";
        }
    }

    @PostMapping("withdrawal")
    public String withdrawal(Authentication authentication,
                             RedirectAttributes redirectAttributes,
                             HttpSession httpSession) {
        int remove = 0;
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UsersVo user = userService.selectOne(userDetails.getUsername());
            remove += userService.userRemove(user);
            if(remove > 0){
                redirectAttributes.addFlashAttribute("msg", "회원탈퇴 성공");
                httpSession.invalidate();
                return "redirect:/";
            }
        }
        return "redirect:hello";
    }
}
