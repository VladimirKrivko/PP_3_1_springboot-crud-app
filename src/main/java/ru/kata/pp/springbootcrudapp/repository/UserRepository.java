package ru.kata.pp.springbootcrudapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.pp.springbootcrudapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
