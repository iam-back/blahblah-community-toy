package data.repository;

import data.entity.ReplyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReplyRepository extends PagingRepository<ReplyEntity> {


}
