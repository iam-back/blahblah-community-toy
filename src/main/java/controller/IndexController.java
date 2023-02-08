package controller;

import data.dto.SessionInfo;
import data.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import service.core.UserService;
import service.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final SessionUtil sessionUtil;
    private final UserService userService;


    @GetMapping("/")
    public ModelAndView getIndex(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        SessionInfo sessionInfo = sessionUtil.getSessionInfo(request);

        if(sessionInfo!=null){
            /*
                SESSION INFO 를 기반으로 한 USER 별 데이터 불러오기
             */
            UserDTO userDTO = userService.getUser(UserDTO.builder()
                                                .id(sessionInfo.getId())
                                                .email(sessionInfo.getEmail())
                                                .build());

        }

        modelAndView.setViewName("/index");

        return modelAndView;
    }
}
