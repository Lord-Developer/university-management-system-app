package lord.dev.repository;

import lord.dev.model.Group;
import lord.dev.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {

    Optional<Journal> findByGroup(Group group);

    @Query(value = "SELECT count(*) > 0 FROM journal_tb j WHERE j.name = :name AND j.group_id = :id",
        nativeQuery = true)
    boolean existsByJournalAndGroup(String name, long id);
}
