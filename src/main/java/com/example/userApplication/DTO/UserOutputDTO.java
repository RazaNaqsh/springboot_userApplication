package com.example.userApplication.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}
