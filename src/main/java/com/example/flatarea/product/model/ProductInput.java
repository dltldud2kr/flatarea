package com.example.flatarea.product.model;

import lombok.Data;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@Data
public class ProductInput {

String pName;
LocalDateTime regDt;


}
