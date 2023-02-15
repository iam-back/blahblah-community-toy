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
public class UserEntity {

    private int id;
    private String name;
    private String password;
    private String salt;
    private String email;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
