package lord.dev.repository;

import lord.dev.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

    boolean existsByName(String name);
}
