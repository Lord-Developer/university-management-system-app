package lord.dev.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "group_tb")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Faculty faculty;


    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<Student> students;

}
