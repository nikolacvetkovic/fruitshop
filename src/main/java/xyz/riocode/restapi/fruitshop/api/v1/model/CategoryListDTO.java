package xyz.riocode.restapi.fruitshop.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryListDTO {

    private List<CategoryDTO> categories;
}
