package com.example.userApplication.Service;

import com.example.userApplication.DTO.UserInputDTO;
import com.example.userApplication.DTO.UserOutputDTO;

import java.util.List;

public interface UserService {
    public UserOutputDTO addUser(UserInputDTO userInputDTO);
    public List<UserOutputDTO> getAllUsers();
    public UserOutputDTO getUserById(Long id);
    public UserOutputDTO updateUser(Long id,UserInputDTO userInputDTO);
    public String deleteUser(Long id);
}
