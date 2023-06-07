package vuong.nguyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vuong.nguyen.model.Student;

import java.util.List;

@Repository
public interface IStudentRepository extends CrudRepository<Student, Long>, JpaRepository<Student, Long> {
    List<Student> findByNameContaining(String name);
    @Query("select st from Student st where st.name like concat('%',:name,'%') ")
    List<Student> findByFullName(@Param("name") String name);
}
