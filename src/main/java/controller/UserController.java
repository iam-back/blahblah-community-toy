package controller;

import data.dto.SessionInfo;
import data.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.core.UserService;
import service.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SessionUtil sessionUtil;

    @GetMapping("/authentication")
    public String getAuthentication(){
        return "/user/sign-in";
    }

    @PostMapping("/authentication")
    public ModelAndView postAuthentication(@RequestParam String email, @RequestParam String password){
        ModelAndView modelAndView = new ModelAndView();




        return modelAndView;
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "/user/sign-up";
    }

    @PostMapping("/registration")
    public ModelAndView postRegistration(@ModelAttribute UserDTO userDTO, @RequestParam("checkPwd") String checkPwd){
        ModelAndView modelAndView = new ModelAndView();

        if(userDTO.getPassword().equals(checkPwd)){
           if(userService.signUp(userDTO)) {
               modelAndView.setViewName("redirect:/user/sign-in");
           }

           return modelAndView;
        }
        modelAndView.setViewName("/user/sign-up");

        return modelAndView;
    }

    @GetMapping("/info")
    public ModelAndView getInfo(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        SessionInfo sessionInfo = sessionUtil.getSessionInfo(request);
        UserDTO userDTO = UserDTO.builder()
                            .id(sessionInfo.getId())
                            .email(sessionInfo.getEmail())
                            .build();
        if(sessionInfo!=null){
            UserDTO searchedUser = userService.getUser(userDTO);

            modelAndView.addObject("user",userDTO);
            modelAndView.setViewName("/user/info");
        }


        return modelAndView;
    }
}
