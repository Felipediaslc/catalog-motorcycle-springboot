package br.com.catalog.model;

import br.com.catalog.model.enums.CategoryTypeEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "category")
public class CategoryModel {

    @NotNull
    @Length(max = 20)
    @Column(name = "type")
    private CategoryTypeEnum type;

    @NotNull
    @Length(max = 50)
    @Column(name = "description")
    private String description;

    public CategoryModel() {
    }

    public CategoryModel(CategoryTypeEnum type, String description) {
        this.type = type;
        this.description = description;
    }

    public CategoryTypeEnum getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setType(CategoryTypeEnum type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
