package lord.dev.repository;

import lord.dev.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query(value = "SELECT count(*) > 0 FROM group_tb  g where g.name = :name AND g.faculty_id = :id", nativeQuery = true)
    boolean existsGroupInTheFaculty(String name, long id);
}
