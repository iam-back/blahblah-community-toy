package data.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private int id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private int viewCount;
    private int likeCount;

}
