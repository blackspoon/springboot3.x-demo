package com.clx.springboot30.application.controller;

import com.clx.springboot30.application.model.Student;
import com.clx.springboot30.application.service.StudentMongoService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MongoController {

    private StudentMongoService mongoService;

    public MongoController(StudentMongoService mongoService) {
        this.mongoService = mongoService;
    }

    /**
     * http://localhost:3100/mongo/insert?age=60&gender=male&name=lisi
     * @return
     */
    @RequestMapping("/mongo/insert")
    public List<Student> insert(Student student) {
        mongoService.insert(student);
        return mongoService.findAll();
    }

    /**
     * id需要取mongo中查询，然后再传入
     * http://localhost:3100/mongo/findStudentById?id=60bda22a90c4f936a92ddec1
     * @return
     */
    @RequestMapping("/mongo/findStudentById")
    public Student findStudentById(String id) {
        return mongoService.findStudentById(new ObjectId(id));
    }

    /**
     * http://localhost:3100/mongo/findAll
     * @return
     */
    @RequestMapping("/mongo/findAll")
    public List<Student> findAll() {
        return mongoService.findAll();
    }

    /**
     * http://localhost:3100/mongo/insert?age=20&gender=male&name=zhangsan
     *
     * http://localhost:3100/mongo/remove?name=zhangsan
     *
     * @param name
     */
    @RequestMapping("/mongo/remove")
    public List<Student> remove(String name) {
        mongoService.remove(name);
        return mongoService.findAll();
    }

    /**
     * http://localhost:3100/mongo/update?id=60bda22a90c4f936a92ddec1&age=30&gender=female&name=xiaohong
     * @param student
     * @return
     */
    @RequestMapping("/mongo/update")
    public List<Student> update(Student student) {
        mongoService.update(student);
        return mongoService.findAll();
    }
}
