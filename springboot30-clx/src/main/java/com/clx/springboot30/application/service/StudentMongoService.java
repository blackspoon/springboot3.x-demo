package com.clx.springboot30.application.service;

import com.clx.springboot30.application.model.Student;
import com.clx.springboot30.application.persistent.mongo.StudentDao;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentMongoService {

    @Autowired
    private StudentDao studentDao;

    public void insert(Student student) {
        studentDao.insert(student);
    }

    public void update(Student student) {
        studentDao.update(student);
    }

    public void remove(String name) {
        studentDao.remove(name);
    }

    public Student findStudentById(ObjectId id) {
        return studentDao.findStudentById(id);
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }
}
