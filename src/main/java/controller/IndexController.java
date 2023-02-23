package controller;

import data.dto.BoardDTO;
import data.dto.SessionInfo;
import data.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import service.core.BoardService;
import service.core.UserService;
import service.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final SessionUtil sessionUtil;
    private final UserService userService;
    private final BoardService boardService;

    // 매번 Database 에 접근해야 하지 않으려면?
    // 시간 혹은 날마다 갱신되는 list 는 미리 저장하고 return 하는건 어떨까?
    private static List<BoardDTO> dailyLikeList;
    private static List<BoardDTO> weeklyLikeList;


    @GetMapping("/")
    public ModelAndView getIndex(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        SessionInfo sessionInfo = sessionUtil.getSessionInfo(request);

        if(sessionInfo!=null){
            /*
                SESSION INFO 를 기반으로 한 USER 별 데이터 불러오기
             */
            UserDTO userDTO = userService.getUserInfo(UserDTO.builder()
                                                .id(sessionInfo.getId())
                                                .email(sessionInfo.getEmail())
                                                .build());

            modelAndView.addObject("user",userDTO);

        }

        modelAndView.setViewName("/index");

        return modelAndView;
    }
}
