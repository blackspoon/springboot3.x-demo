package com.clx.springboot30.application.persistent.mongo;

import com.clx.springboot30.application.model.Student;
import jakarta.annotation.Resource;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDao {
    @Resource
    private MongoTemplate mongoTemplate;

    public void insert(Student student) {
        mongoTemplate.insert(student);
    }

    public void update(Student student) {
        Query query = new Query(Criteria.where("id").is(student.getId()));
        Update update = new Update();
        update.set("name", student.getName());
        update.set("gender", student.getGender());
        update.set("age", student.getAge());
        mongoTemplate.updateFirst(query, update, Student.class);
    }

    public void remove(String name) {
        mongoTemplate.remove(new Query(Criteria.where("name").is(name)),Student.class);
    }

    public List<Student> findAll() {
        return mongoTemplate.findAll(Student.class);
    }

    public Student findStudentById(ObjectId id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Student student = mongoTemplate.findOne(query, Student.class);
        return student;
    }

}
