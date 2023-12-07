package ru.kata.pp.springbootcrudapp.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.pp.springbootcrudapp.dto.UserDto;
import ru.kata.pp.springbootcrudapp.model.User;
import ru.kata.pp.springbootcrudapp.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<UserDto> fetchUsers(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).
                map(user -> modelMapper.map(user, UserDto.class));
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        return modelMapper.map(
                userRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("user with id = %d not found".formatted(id))),
                UserDto.class
        );
    }

    @Transactional
    public void saveUser(UserDto user) {
        userRepository.save(modelMapper.map(user, User.class));
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.findById(id)
                .ifPresent(userRepository::delete);
    }
}
