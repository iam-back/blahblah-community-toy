package data.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;
    private String name;
    private String password;
    private String email;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
