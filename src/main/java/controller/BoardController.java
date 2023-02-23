package controller;

import data.dto.BoardDTO;
import data.dto.PageInfo;
import data.dto.SessionInfo;
import data.dto.enums.Order;
import exception.BoardNotFoundException;
import exception.UnExpectedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.core.BoardService;
import service.core.UserService;
import service.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final SessionUtil sessionUtil;

    @GetMapping("/list")
    public ModelAndView getList(PageInfo pageInfo, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        SessionInfo sessionInfo = sessionUtil.getSessionInfo(request);
        List<BoardDTO> lists;

        if(sessionInfo!=null){
            if(pageInfo==null){
                pageInfo = new PageInfo();
            }
            lists = boardService.getBoardList(pageInfo);
            modelAndView.addObject("boardList",lists);
        }


        return modelAndView;
    }

    @GetMapping("/edit/{boardId}")
    public ModelAndView getEdit(@PathVariable int boardId){
        ModelAndView modelAndView = new ModelAndView();

        if(boardId!=0){
            BoardDTO boardDTO = boardService.getBoard(BoardDTO.builder().id(boardId).build());
            if(boardDTO==null){
            }
            modelAndView.addObject("board",boardDTO);
        }

        modelAndView.setViewName("/board/edit");

        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView postEdit(@ModelAttribute BoardDTO boardDTO, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        SessionInfo sessionInfo = sessionUtil.getSessionInfo(request);

        if(sessionInfo!=null){
            boardDTO.setUserId(sessionInfo.getId());
            if(boardService.insertBoard(boardDTO)){
                modelAndView.setViewName("redirect:/board/list");
            }
        }

        return modelAndView;
    }

    @GetMapping("/article/{boardId}")
    public ModelAndView getArticle(@PathVariable int boardId){
        ModelAndView modelAndView = new ModelAndView();

        BoardDTO boardDTO = boardService.getBoard(BoardDTO.builder().id(boardId).build());

        if(boardDTO!=null){
            modelAndView.addObject("board",boardDTO);
            modelAndView.setViewName("/board/article");

            return modelAndView;
        }

        throw new UnExpectedException();
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String keyword){
        ModelAndView modelAndView = new ModelAndView();
        PageInfo pageInfo = PageInfo.builder()
                .currentPageNo(1)
                .pageListSize(20)
                .keyword(keyword)
                .order(Order.DATE)
                .build();

        List<BoardDTO> lists = boardService.getBoardList(pageInfo);

        if(lists!=null){
            modelAndView.addObject(lists);
            modelAndView.setViewName("/board/list");
        }

        return modelAndView;
    }

    @GetMapping("/deletion/{boardId}")
    public ModelAndView getDeletion(@PathVariable int boardId, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        SessionInfo sessionInfo = sessionUtil.getSessionInfo(request);

        if(sessionInfo!=null){
            if(boardService.deleteBoard(boardId,sessionInfo.getId())){
                modelAndView.setViewName("/board/list");
            }
        }

        return modelAndView;
    }

}
