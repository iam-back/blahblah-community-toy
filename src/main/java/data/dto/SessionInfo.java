package data.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionInfo {

    private int id;
    private String email;

}
