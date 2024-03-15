package com.lms.controller;

import com.lms.model.Student;
import com.lms.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin( origins = "http://Localhost:4200")
public class StudentController {

    private CommonService<Student> studentCommonService;

    @Autowired
    public StudentController(CommonService<Student> studentCommonService){

        this.studentCommonService = studentCommonService;

    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student){

       return studentCommonService.add(student);
    }

    @GetMapping("/all")
    @CrossOrigin( origins = "http://Localhost:4200")
    public List<Student> getAllStudent(){
        List<Student> studentList = studentCommonService.getAll();
        return studentList;
    }

    @PutMapping("/update/{id}")
    @CrossOrigin( origins = "http://Localhost:4200")
    public Student updateStudent(@PathVariable Long id,@RequestBody Student student){
        return studentCommonService.update(id,student);

    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin( origins = "http://Localhost:4200")
    public void  deleteStudent(@PathVariable Long id){
        studentCommonService.delete(id);
    }

    @GetMapping("/byId/{id}")
    public Student getStudentById(@PathVariable Long id){

        Student student =  (Student) studentCommonService.getById(id);
        return student;
    }

    @GetMapping("/byName/{name}")
    public List<Student> getStudentByName(@PathVariable String name){

        List<Student> studentList= studentCommonService.getByName(name);
        return studentList;
    }

}
