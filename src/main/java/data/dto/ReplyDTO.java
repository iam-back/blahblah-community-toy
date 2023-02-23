package data.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {

    private String writer;
    private String content;
    private LocalDateTime createDate;

}
