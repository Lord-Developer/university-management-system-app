package lord.dev.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "subject_tb")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( nullable = false)
    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Journal journal;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<Mark> marks;

}