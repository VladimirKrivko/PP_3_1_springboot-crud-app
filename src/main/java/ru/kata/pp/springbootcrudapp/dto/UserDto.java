package ru.kata.pp.springbootcrudapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    private Long id;

    @NotNull
    @NotEmpty(message = "should not be empty")
    @Size(min = 2, max = 32, message = "should be between 2 and 32 characters")
    private String firstName;

    @Size(min = 0, max = 32, message = "can be empty or no longer than 32 characters")
    private String lastName;
}
