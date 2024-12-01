package com.example.userApplication.Controllers;


import com.example.userApplication.DTO.UserInputDTO;
import com.example.userApplication.DTO.UserOutputDTO;
import com.example.userApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserOutputDTO> addUser(@RequestBody UserInputDTO userInputDTO){
        UserOutputDTO savedUser = userService.addUser(userInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserOutputDTO>> allUsers(){
        List<UserOutputDTO> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserOutputDTO> getUser(@PathVariable Long id){
        UserOutputDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserOutputDTO> updateUser(@PathVariable Long id, @RequestBody UserInputDTO userInputDTO){
        UserOutputDTO updatedUser = userService.updateUser(id,userInputDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        String deletedUser = userService.deleteUser(id);
        return ResponseEntity.ok(deletedUser);
    }
}
