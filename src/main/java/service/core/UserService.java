package service.core;

import data.dto.UserDTO;

public interface UserService {

    UserDTO signIn(String email, String password);
    boolean signUp(UserDTO userDTO);
    UserDTO getUserInfo(UserDTO userDTO);
    boolean modifyUser(UserDTO userDTO);
    boolean deleteUser(UserDTO userDTO);
}
