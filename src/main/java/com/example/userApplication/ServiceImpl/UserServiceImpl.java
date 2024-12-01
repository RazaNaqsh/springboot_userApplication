package com.example.userApplication.ServiceImpl;

import com.example.userApplication.DTO.UserInputDTO;
import com.example.userApplication.DTO.UserOutputDTO;
import com.example.userApplication.Model.User;
import com.example.userApplication.Repo.UserRepo;
import com.example.userApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserOutputDTO addUser(UserInputDTO userInputDTO) {
        User newUser = new User();
        newUser.setFirstname(userInputDTO.getFirstname());
        newUser.setLastname(userInputDTO.getLastname());
        newUser.setEmail(userInputDTO.getEmail());
        newUser.setPassword(userInputDTO.getPassword());
        User savedUser  = userRepo.save(newUser);

        UserOutputDTO userOutputDTO = new UserOutputDTO();
        userOutputDTO.setId(savedUser.getId());
        userOutputDTO.setFirstname(savedUser.getFirstname());
        userOutputDTO.setLastname(savedUser.getLastname());
        userOutputDTO.setEmail(savedUser.getEmail());
        return userOutputDTO;
    }

    @Override
    public List<UserOutputDTO> getAllUsers() {
        List<UserOutputDTO> userList = new ArrayList<>();
        List<User> allUsers = userRepo.findAll();

        for(User user:allUsers){
            UserOutputDTO userOutputDTO = new UserOutputDTO();
            userOutputDTO.setId(user.getId());
            userOutputDTO.setFirstname(user.getFirstname());
            userOutputDTO.setLastname(user.getLastname());
            userOutputDTO.setEmail(user.getEmail());
            userList.add(userOutputDTO);
        }
        return userList;

//another implementation
//        return users.stream().map(user -> {
//            UserOutputDTO dto = new UserOutputDTO();
//            dto.setId(user.getId());
//            dto.setName(user.getName());
//            dto.setAge(user.getAge());
//            dto.setEmail(user.getEmail());
//            return dto;
//        }).collect(Collectors.toList());
    }

    @Override
    public UserOutputDTO getUserById(Long id) {
        Optional<User> foundUser = userRepo.findById(id);

        if(foundUser.isPresent()){
            UserOutputDTO userOutputDTO = new UserOutputDTO();
            User user = foundUser.get();
            userOutputDTO.setId(user.getId());
            userOutputDTO.setFirstname(user.getFirstname());
            userOutputDTO.setLastname(user.getLastname());
            userOutputDTO.setEmail(user.getEmail());

            return userOutputDTO;
        }
        return null;
    }

    @Override
    public UserOutputDTO updateUser(Long id, UserInputDTO userInputDTO) {
        Optional<User> searchedUser = userRepo.findById(id);
        if(searchedUser.isPresent()){
            User foundUser = searchedUser.get();
            foundUser.setFirstname(userInputDTO.getFirstname());
            foundUser.setLastname(userInputDTO.getLastname());
            foundUser.setEmail(userInputDTO.getEmail());
            foundUser.setPassword(userInputDTO.getPassword());

            userRepo.save(foundUser);

            UserOutputDTO dto = new UserOutputDTO();
            dto.setId(foundUser.getId());
            dto.setFirstname(foundUser.getFirstname());
            dto.setLastname(foundUser.getLastname());
            dto.setEmail(foundUser.getEmail());

            return dto;
        }
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        Optional<User> foundUser = userRepo.findById(id);
        if(foundUser.isPresent()){
            User user = foundUser.get();
            userRepo.deleteById(id);
            return user.getFirstname()+" was deleted....";
        }
        return "User Not Found!";
    }
}
