package controller;

import data.dto.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.core.BoardService;
import service.core.UserService;
import service.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final UserService userService;
    private final BoardService boardService;
    private final SessionUtil sessionUtil;

    @GetMapping("/list")
    public ModelAndView getList(PageInfo pageInfo, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();




        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView getEdit(){
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }

    @PostMapping("/edit")
    public String postEdit(){

        return "board/list";
    }

    @GetMapping("/article/{boardId}")
    public ModelAndView getArticle(@PathVariable String boardId){
        ModelAndView modelAndView = new ModelAndView();

        

        return modelAndView;
    }
/*

    @GetMapping("/deletion")
    public String getDeletion(HttpServletRequest request){
        SessionInfo sessionInfo = sessionUtil.getSessionInfo(request);

        if(sessionInfo!=null){
            UserDTO userDTO = userService.getUserInfo(UserDTO.builder()
                    .id(sessionInfo.getId())
                    .email(sessionInfo.getEmail())
                    .build());

            if(userDTO!=null){
                // Board 에서 삭제
            }
        }

    }
*/

}
