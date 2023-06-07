package vuong.nguyen.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vuong.nguyen.model.Student;
import vuong.nguyen.repository.IStudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceIMPL implements IStudentService{
    @Autowired
    private IStudentRepository studentRepository;
    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public List<Student> findByNameContaining(String name) {
        return studentRepository.findByNameContaining(name);
    }

    @Override
    public List<Student> findByFullName(String name) {
        return studentRepository.findByFullName(name);
    }
}
