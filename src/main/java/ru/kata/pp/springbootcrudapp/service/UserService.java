package ru.kata.pp.springbootcrudapp.service;

import org.springframework.data.domain.Page;
import ru.kata.pp.springbootcrudapp.dto.UserDto;

public interface UserService {

    Page<UserDto> fetchUsers(Integer page, Integer size);

    UserDto findById(Long id);

    void saveUser(UserDto user);

    void deleteById(Long id);
}
