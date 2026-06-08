package com.lms.service;

import com.lms.dto.user.UserRequestDTO;
import com.lms.dto.user.UserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
}
