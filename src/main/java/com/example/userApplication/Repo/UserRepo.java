package com.example.userApplication.Repo;

import com.example.userApplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
