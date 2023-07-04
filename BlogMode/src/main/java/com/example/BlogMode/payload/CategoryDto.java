package com.example.BlogMode.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;
    @NotBlank
    @Size(min = 3, message = "size should be equal or greater than 3")
    private String categoryTitle;
    @NotBlank
    @Size(min = 5,max=50, message = "description should be in between 5 to 50")
    private String categoryDescription;
}
