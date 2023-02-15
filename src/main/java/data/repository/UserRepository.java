package data.repository;

import data.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRepository {

    UserEntity select(UserEntity userEntity);
    int isEmailExist(@Param("email") String email);
    int insert(UserEntity userEntity);
    int modify(UserEntity userEntity);
    int delete(UserEntity userEntity);

}
