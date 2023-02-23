package data.mapper;

import data.entity.ReplyEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {

    ReplyEntity select(ReplyEntity replyEntity);

}
