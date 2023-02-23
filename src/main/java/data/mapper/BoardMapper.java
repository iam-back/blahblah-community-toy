package data.mapper;

import data.dto.BoardDTO;
import data.entity.BoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BoardMapper {

    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    BoardDTO toDTO(BoardEntity boardEntity);
    BoardEntity toEntity(BoardDTO boardDTO);
    List<BoardEntity> toEntityList(List<BoardDTO> boardDTOs);
    List<BoardDTO> toDTOList(List<BoardEntity> boardEntities);

}
