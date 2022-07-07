package lord.dev.repository;

import lord.dev.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query(value = "SELECT COUNT(*) > 0 FROM subject_tb s WHERE s.name = :name AND s.journal_id = :id", nativeQuery = true)
    boolean existsByNameAndJournal(String name, long id);
}
