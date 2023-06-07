package vuong.nguyen.service.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vuong.nguyen.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    Iterable<Student> findAll();
    Optional<Student> findById(Long id);
    void save(Student student);
    void deleteById(Long id);
    Page<Student> findAll(Pageable pageable);
    List<Student> findByNameContaining(String name);
    List<Student> findByFullName(@Param("name") String name);
}
