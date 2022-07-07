package lord.dev.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
            columnNames = {
                    "name",
                    "university_id"}
        ),
        name = "faculty_tb"
)
public class Faculty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private University university;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Group> groups;

}
