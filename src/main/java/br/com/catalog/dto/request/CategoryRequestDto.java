package br.com.catalog.dto.request;

import javax.validation.constraints.*;

public class CategoryRequestDto {

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 20)
    @Pattern(regexp = "^[^\\s]+(\\s+[^\\s]+)*$")
    private String type;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 10, max = 50)
    @Pattern(regexp = "^[^\\s]+(\\s+[^\\s]+)*$")
    private String description;

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
