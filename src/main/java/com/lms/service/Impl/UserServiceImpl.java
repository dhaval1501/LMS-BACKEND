package com.lms.service.Impl;

import com.lms.dto.user.UserRequestDTO;
import com.lms.dto.user.UserResponseDTO;
import com.lms.enums.Role;
import com.lms.model.UserInfo;
import com.lms.repository.UserInfoRepository;
import com.lms.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        UserInfo user= userInfoRepository.findUserInfoByEmail(userRequestDTO.getEmail()).orElseThrow();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        if(userRequestDTO.getRole().equals(Role.ADMIN.toString())){
            user.setRole(Role.ADMIN);
        }else {
            user.setRole(Role.USER);
        }
        user.setPassword("");
        return null;
    }
}
