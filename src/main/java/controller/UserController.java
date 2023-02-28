package controller;

import data.dto.SessionInfo;
import data.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.core.BoardService;
import service.core.UserService;
import service.util.HashUtil;
import service.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final BoardService boardService;
    private final HashUtil hashUtil;
    private final SessionUtil sessionUtil;

    @GetMapping("/authentication")
    public String getAuthentication(){
        return "/user/sign-in";
    }

    @PostMapping("/authentication")
    public ModelAndView postAuthentication(@RequestParam String email, @RequestParam String password,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        UserDTO userDTO = userService.signIn(email,password);

        if(userDTO!=null){
            SessionInfo sessionInfo = new SessionInfo(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
            HttpSession httpSession = request.getSession();

            httpSession.setAttribute("session",sessionInfo);
            modelAndView.setViewName("redirect:/");

            return modelAndView;
        }

        modelAndView.setViewName("redirect:/user/authentication");

        return modelAndView;
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "/user/sign-up";
    }

    @PostMapping("/registration")
    public ModelAndView postRegistration(@ModelAttribute final UserDTO userDTO, @RequestParam("checkPwd") String checkPwd){
        ModelAndView modelAndView = new ModelAndView();

        log.info(userDTO.toString()+":::"+checkPwd);
        if(userDTO.getPassword().equals(checkPwd)){
           if(userService.signUp(userDTO)) {
               modelAndView.setViewName("redirect:/user/authentication");
           }

           return modelAndView;
        }

        modelAndView.setViewName("redirect:/user/registration");

        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView postEdit(@ModelAttribute UserDTO userDTO, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        SessionInfo sessionInfo = sessionUtil.getSessionInfo(request);

        if(sessionInfo!=null){
            if(userService.modifyUser(userDTO)){
                modelAndView.setViewName("/user/info");

                return modelAndView;
            }
        }

        modelAndView.setViewName("redirect:/user/edit");

        return modelAndView;
    }

    @GetMapping("/info/{userId}")
    public ModelAndView getInfo(@PathVariable int userId){
        ModelAndView modelAndView = new ModelAndView();

        UserDTO userDTO = userService.getUserInfo(UserDTO.builder().id(userId).build());

        if(userDTO!=null){
            modelAndView.addObject("user",userDTO);
        }

        modelAndView.setViewName("/user/info");

        //Ajax 를 통해 BoardList 혹은 ReplyList 불러오기

        return modelAndView;
    }

    @PostMapping("/withdraw")
    public ModelAndView postWithdraw(@RequestParam String checkPwd, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        SessionInfo sessionInfo = sessionUtil.getSessionInfo(request);

        if (sessionInfo!=null){

            UserDTO userDTO = userService.getUserInfo(UserDTO.builder()
                    .id(sessionInfo.getId())
                    .email(sessionInfo.getEmail())
                    .build());
            String hashedPwd = hashUtil.doHash(checkPwd, userDTO.getSalt());

            if(hashedPwd.equals(userDTO.getPassword())){
                if(userService.deleteUser(userDTO)) {
                    modelAndView.setViewName("redirect:/");

                    return modelAndView;
                    }
                }
            }

        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }

    @GetMapping("/logout")
    public String getLogout(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession(false);

        if(httpSession!=null){
            httpSession.invalidate();
        }
        return "redirect:/";
    }


}
