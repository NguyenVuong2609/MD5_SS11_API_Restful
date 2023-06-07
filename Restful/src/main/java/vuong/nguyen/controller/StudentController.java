package vuong.nguyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vuong.nguyen.dto.response.ResponseMessage;
import vuong.nguyen.model.Customer;
import vuong.nguyen.model.Student;
import vuong.nguyen.service.student.IStudentService;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    //* API get List *//
    @GetMapping
    public ResponseEntity<?> showListStudent(){
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }
    //* API post Create *//
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        studentService.save(student);
        return new ResponseEntity<>(new ResponseMessage("create-success"), HttpStatus.CREATED);
    }
    //* API get Detail *//
    @GetMapping("/{id}")
    public ResponseEntity<?> detailStudent(@PathVariable Long id){
        if (!studentService.findById(id).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(studentService.findById(id).get(), HttpStatus.OK);
    }
    //* APT put Update *//
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student){
        Optional<Student> studentOptional = studentService.findById(id);
        if (!studentOptional.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        student.setId(studentOptional.get().getId());
        studentService.save(student);
        return new ResponseEntity<>(new ResponseMessage("update_success"), HttpStatus.OK);
    }
    //* API delete *//
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        if (!studentService.findById(id).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        studentService.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("delete_success"), HttpStatus.OK);
    }
    //* API page Student *//
    @GetMapping("/page")
    public ResponseEntity<?> showPageStudent(@PageableDefault(size = 5,sort = "name", direction = Sort.Direction.ASC) Pageable pageable){
        return new ResponseEntity<>(studentService.findAll(pageable), HttpStatus.OK);
    }
    //* API search Student *//
    @GetMapping("/search/{name}")
    public ResponseEntity<?> showSearchListContaining(@PathVariable String name){
        return new ResponseEntity<>(studentService.findByNameContaining(name), HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<?> showSearchListQuery(@RequestParam("name") String name){
        return new ResponseEntity<>(studentService.findByFullName(name), HttpStatus.OK);
    }
    //* API patch Student *//
    @PatchMapping("{id}")
    public ResponseEntity<?> editStudent(@PathVariable Long id,@RequestBody Student student){
        Optional<Student> studentOptional = studentService.findById(id);
        if (!studentOptional.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Student st = studentOptional.get();
        student.setId(st.getId());
        student.setName(student.getName() == null ? st.getName() : student.getName());
        student.setAge(student.getAge() == null ? st.getAge() : student.getAge());
        student.setSex(student.getSex() == null ? st.getSex() : student.getSex());
        student.setBirthDay(student.getBirthDay() == null ? st.getBirthDay() : student.getBirthDay());
        studentService.save(student);
        return new ResponseEntity<>(new ResponseMessage("patch_success"), HttpStatus.OK);
    }
}
