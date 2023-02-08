package service.core;

import data.dto.UserDTO;

public interface UserService {

    UserDTO signIn(UserDTO userDTO);
    boolean signUp(UserDTO userDTO);
    UserDTO getUser(UserDTO userDTO);
}
