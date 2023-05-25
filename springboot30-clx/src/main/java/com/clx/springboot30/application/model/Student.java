package com.clx.springboot30.application.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "student")
@Data
public class Student implements Serializable {
    @Id
    private ObjectId id;

    private String name;

    private String gender;

    private String age;
}
