package com.example.flatarea.product.model;

import com.example.flatarea.admin.model.CommonParam;
import lombok.Data;

@Data
public class ProductParam extends CommonParam {

    long id; //product.id
    long brandId;
    long categoryId;

}
