package data.mapper;

import data.dto.UserDTO;
import data.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "createDate", target = "createDate"),
            @Mapping(source = "updateDate", target = "updateDate"),
            @Mapping(source = "description", target = "description"),
    })
    UserEntity toEntity(UserDTO userDTO);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "createDate", target = "createDate"),
            @Mapping(source = "updateDate", target = "updateDate"),
            @Mapping(source = "description", target = "description"),
    })
    UserDTO toDTO(UserEntity userEntity);
}
