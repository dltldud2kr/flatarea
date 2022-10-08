package com.example.flatarea.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryInput {

    long id;
    String categoryName;
    int sortValue;
    boolean usingYn;


}
