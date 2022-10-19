package com.example.flatarea.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BrandInput {

    long id;
    String brandName;
    int sortValue;
    boolean usingYn;


}
