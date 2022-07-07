package lord.dev.repository;

import lord.dev.model.Faculty;
import lord.dev.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    boolean existsByNameAndUniversity(String name, University university);
}
