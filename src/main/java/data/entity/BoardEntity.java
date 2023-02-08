package data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {

    private int id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private int viewCount;
    private int likeCount;

}
