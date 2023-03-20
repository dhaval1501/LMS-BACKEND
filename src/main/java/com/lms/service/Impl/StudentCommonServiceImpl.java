package com.lms.service.Impl;

import com.lms.model.Student;
import com.lms.repository.StudentRepository;
import com.lms.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class StudentCommonServiceImpl implements CommonService<Student> {

    private StudentRepository studentRepository;
    @Autowired
    public StudentCommonServiceImpl(StudentRepository studentRepository){
        this.studentRepository =studentRepository;
    }
    @Override
    public void add(Student student) {
        Student student1 = studentRepository.save(student);
    }

    @Override
    public List<Student> getAll() {

        List<Student> studentList = studentRepository.findAll();
        return studentList;

    }

    @Override
    public void update(Long id, Student student) {

        Optional<Student> optionalStudent =studentRepository.findById(id);
        Student student1 = new Student();
        if(optionalStudent.isPresent()){
            student1 = optionalStudent.get();
            student1.setFirstName( student.getFirstName());
            student1.setLastName( student.getLastName());
            student1.setAddress( student.getAddress());
            student1.setEmail( student.getEmail());
            student1.setNumber( student.getNumber());
        }
        studentRepository.save(student1);


    }

    @Override
    public void delete(Long id) {

        Optional<Student> student =studentRepository.findById(id);
        studentRepository.delete(student.get());
    }

    @Override
    public Student getById(Long id) {

        Optional<Student> student =studentRepository.findById(id);
        return student.get();

    }

    @Override
    public List<Student> getByName(String name) {

        List<Student> studentList = studentRepository.getByFirstName(name);
        return studentList;

    }

}
