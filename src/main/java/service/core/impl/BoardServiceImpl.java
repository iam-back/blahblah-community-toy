package service.core.impl;

import data.dto.BoardDTO;
import data.dto.PageInfo;
import data.entity.BoardEntity;
import data.mapper.BoardMapper;
import data.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.core.BoardService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public List<BoardDTO> getBoardList(PageInfo pageInfo) {

        List<BoardEntity> lists = boardRepository.getListByPageInfo(pageInfo);
        return BoardMapper.INSTANCE.toDTOList(lists);
    }

    @Override
    public BoardDTO getBoard(BoardDTO boardDTO) {

        BoardEntity boardEntity = boardRepository.select(boardDTO.getId());
        return BoardMapper.INSTANCE.toDTO(boardEntity);
    }

    @Override
    @Transactional
    public boolean insertBoard(BoardDTO boardDTO) {
        return (boardRepository.insert(BoardMapper.INSTANCE.toEntity(boardDTO)))==1;
    }

    @Override
    @Transactional
    public boolean modifyBoard(BoardDTO boardDTO) {
        return (boardRepository.update(BoardMapper.INSTANCE.toEntity(boardDTO)))==1;
    }

    @Override
    @Transactional
    public boolean deleteBoard(int boardId, int userId) {

        return (boardRepository.delete(boardId, userId))==1;
    }
}
