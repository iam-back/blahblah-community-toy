package data.repository;

import data.dto.PageInfo;
import data.entity.BoardEntity;
import data.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardRepository extends PagingRepository<BoardEntity> {

    BoardEntity select(int boardId);
    List<BoardEntity> searchByUserInfo(@Param("userId") int userId, PageInfo pageInfo);
    int insert(BoardEntity boardEntity);
    int update(BoardEntity boardEntity);
    int delete(@Param("boardId") int boardId, @Param("userId") int userId);


}
