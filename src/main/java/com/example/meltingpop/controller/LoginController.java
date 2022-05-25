package com.example.meltingpop.controller;

import com.example.meltingpop.entity.User;
import com.example.meltingpop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.meltingpop.session.SessionConst.LOGIN_USER;

@Controller
public class LoginController {
    private UserService userService;
    public LoginController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value="/login")
    public String login(){
        return "static/pages/login";
    }

    @PostMapping(value="/login")
    public String login1(User user, HttpServletRequest request, Model model){
        //Map<String, Object> result = new HashMap<String, Object>();
        System.out.println(user.getUserEmail());
        System.out.println(user.getUserPw());

        User loginUser = userService.login(user.getUserEmail(), user.getUserPw());

        if(loginUser==null){
            System.out.println("로그인 실패");
            return "redirect:/";
        }

        // 세션 매니저를 이용하여 세션 생성과 회원정보를 보관한다.
        // 세션이 있으면 있는 세션을 반환하고, 세션이 없다면 신규 세션을 만들어준다.
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_USER, loginUser);

        if(loginUser.getUserAuthentication()==0){

            //LOGIN_USER로 바인딩 된 객체를 가져온다.

            System.out.println("회원 로그인 성공");
            String currentUserId = ((User)session.getAttribute(LOGIN_USER)).getUserId();
            model.addAttribute("currentUserId",currentUserId);

            return "redirect://localhost:8081";
        }

        else if(loginUser.getUserAuthentication()==1){

            System.out.println("관리자 로그인 성공");
            System.out.println("세션 확인: "+session.getAttribute(LOGIN_USER));
            //LOGIN_USER로 바인딩 된 객체를 가져온다.
            //session.getAttribute(LOGIN_USER)
        }
        System.out.println("세션 재확인: "+session.getAttribute(LOGIN_USER));

        return "redirect://localhost:8081/admin-list";
    }

    // 프론트(메인 페이지)에서 로그인 버튼 누르면 /login으로 넘어가도록 설정
//    @PostMapping("/login")
//    public String login(User user){
//        User userInDB = userService.login(user.getUserId(), user.getUserPw());
//        if(userInDB == null){
//            System.out.println("다시 입력해주세요");
//            return "redirect:/";
//        }
//        return "static/index";
//    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }

        return "redirect://localhost:8081";
    }
}
