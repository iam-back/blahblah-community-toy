package service.core.impl;

import data.dto.UserDTO;
import data.entity.UserEntity;
import data.mapper.UserMapper;
import data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.core.UserService;
import service.util.HashUtil;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final HashUtil hashUtil;

    @Override
    public UserDTO signIn(String email,String password) {

        UserEntity userEntity = userRepository.select(UserEntity.builder()
                .email(email)
                .build());

        if(userEntity!=null){
            String hashedPwd = hashUtil.doHash(password,userEntity.getSalt());
            if(hashedPwd.equals(userEntity.getPassword())){
                return UserMapper.INSTANCE.toDTO(userEntity);
            }
        }

        return null;
    }

    @Override
    @Transactional
    public boolean signUp(UserDTO userDTO) {

        if(userRepository.isEmailExist(userDTO.getEmail())==0){
            String salt = hashUtil.getSalt();
            String hashedPwd = hashUtil.doHash(userDTO.getPassword(),salt);

            userDTO.setSalt(salt);
            userDTO.setPassword(hashedPwd);
            userDTO.setCreateDate(LocalDateTime.now());
            userDTO.setUpdateDate(LocalDateTime.now());

            UserEntity userEntity = UserMapper.INSTANCE.toEntity(userDTO);

            return (userRepository.insert(userEntity)==1);
        }

        return false;
    }

    @Override
    public UserDTO getUserInfo(UserDTO userDTO) {
        UserEntity userEntity = userRepository.select(UserMapper.INSTANCE.toEntity(userDTO));
        return UserMapper.INSTANCE.toDTO(userEntity);
    }

    @Override
    public boolean modifyUser(UserDTO userDTO) {
        return (userRepository.modify(UserMapper.INSTANCE.toEntity(userDTO))==1);
    }

    @Override
    public boolean deleteUser(UserDTO userDTO) {
        return (userRepository.delete(UserMapper.INSTANCE.toEntity(userDTO))==1);
    }

}
