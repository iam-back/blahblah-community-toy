package service.core.impl;

import data.dto.UserDTO;
import data.entity.SaltEntity;
import data.entity.UserEntity;
import data.mapper.UserMapper;
import data.repository.SaltRepository;
import data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.core.UserService;
import service.util.HashUtil;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SaltRepository saltRepository;
    private final HashUtil hashUtil;

    @Override
    public UserDTO signIn(UserDTO userDTO) {




        return null;
    }

    @Override
    @Transactional
    public boolean signUp(UserDTO userDTO) {

        if(userRepository.isEmailExist(userDTO.getEmail())==0){
            String salt = hashUtil.getSalt();
            String hashedPwd = hashUtil.doHash(userDTO.getPassword(),salt);

            userDTO.setPassword(hashedPwd);
            userDTO.setCreateDate(LocalDateTime.now());
            userDTO.setUpdateDate(LocalDateTime.now());

            UserEntity userEntity = UserMapper.INSTANCE.toEntity(userDTO);
            SaltEntity saltEntity = SaltEntity.builder()
                                    .salt(salt)
                                    .userEmail(userDTO.getEmail())
                                    .build();

            return (userRepository.insertUser(userEntity)==1)&&(saltRepository.insertSalt(saltEntity)==1);
        }

        return false;
    }

    @Override
    public UserDTO getUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.select(UserMapper.INSTANCE.toEntity(userDTO));
        return UserMapper.INSTANCE.toDTO(userEntity);
    }
}
