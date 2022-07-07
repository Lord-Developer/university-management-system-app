package lord.dev.model;

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
@Table(name = "university_tb")
public class University implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "university", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Faculty> faculties;
}


