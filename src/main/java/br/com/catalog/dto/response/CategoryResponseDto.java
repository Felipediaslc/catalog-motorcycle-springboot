package br.com.catalog.dto.response;

import br.com.catalog.model.CategoryModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude
@JsonPropertyOrder(alphabetic = true)
public class CategoryResponseDto {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "description")
    private String description;

    public CategoryResponseDto(CategoryModel categoryModel) {
        this.id = categoryModel.getId().toString();
        this.type = categoryModel.getType();
        this.description = categoryModel.getDescription();
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

}
