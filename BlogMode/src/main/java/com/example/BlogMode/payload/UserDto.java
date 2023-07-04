package com.example.BlogMode.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private Integer id;
    @NotEmpty
    @Size(min = 5,message = "length should not be less than 5")
    private String name;

    @Email(message = "please provide correct email")
    private String email;

    @NotEmpty
    @Size(min = 4, max = 6 , message = "length should be in between 4 to 6")
    @Pattern(regexp = "^[0-9]{4,8}$", message = "number should be in between 4-8")
    private String password;

    @NotEmpty
    private String about;
}
