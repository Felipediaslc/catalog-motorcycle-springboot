package br.com.catalog.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryRequestDto {

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 20)
    private String type;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 10, max = 50)
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
