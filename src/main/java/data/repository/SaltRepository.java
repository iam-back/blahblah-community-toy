package data.repository;

import data.entity.SaltEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SaltRepository {

    int insertSalt(SaltEntity saltEntity);
    SaltEntity select(SaltEntity saltEntity);

}
