package data.dto;

import data.entity.BoardEntity;
import data.entity.ReplyEntity;
import data.entity.UserEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleInfo {

    private BoardEntity boardEntity;
    private UserEntity userEntity;
    private List<ReplyEntity> replyEntities;

}
