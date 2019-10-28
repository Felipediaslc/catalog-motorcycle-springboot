package br.com.catalog.model;

import br.com.catalog.model.enums.CategoryTypeEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Length(max = 20)
    @Column(name = "type")
    private String type;

    @NotNull
    @Length(max = 50)
    @Column(name = "description")
    private String description;

    public CategoryModel() {
    }

    public CategoryModel(String type, String description) {
        this.type = type;
        this.description = description;
    }

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
