package lord.dev.repository;

import lord.dev.model.Mark;
import lord.dev.model.Student;
import lord.dev.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {


    @Query(
            value = "SELECT sum(u.mark) FROM mark_tb u where u.student_id = :id",
            nativeQuery = true)
    double sumMark(long id);

    @Query(value = "SELECT count(*) > 0 FROM mark_tb m WHERE m.student_id = :studentId AND m.subject_id = :subjectId AND m.journal_id = :journalId",
    nativeQuery = true)
    boolean existsByStudentAndSubjectAndJournal(long studentId, long subjectId, long journalId);
}
