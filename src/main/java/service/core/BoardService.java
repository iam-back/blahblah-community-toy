package service.core;

import data.dto.BoardDTO;
import data.dto.PageInfo;

import java.util.List;

public interface BoardService {

    List<BoardDTO> getBoardList(PageInfo pageInfo);
    BoardDTO getBoard(BoardDTO boardDTO);
    boolean insertBoard(BoardDTO boardDTO);
    boolean modifyBoard(BoardDTO boardDTO);
    boolean deleteBoard(int boardId, int userId);
}
