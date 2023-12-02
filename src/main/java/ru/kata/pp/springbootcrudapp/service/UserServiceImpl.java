package ru.kata.pp.springbootcrudapp.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kata.pp.springbootcrudapp.dto.UserDto;
import ru.kata.pp.springbootcrudapp.model.User;
import ru.kata.pp.springbootcrudapp.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public Page<User> fetchUsers(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public UserDto findById(Long id) {
        return modelMapper.map(userRepository.getReferenceById(id), UserDto.class);
    }

    public void saveUser(UserDto user) {
        userRepository.save(modelMapper.map(user, User.class));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
