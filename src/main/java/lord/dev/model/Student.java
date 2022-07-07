package lord.dev.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student_tb")
public class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(nullable = false)
        private String firstName;

        @Column(nullable = false)
        private String lastName;

        @OneToOne(cascade = CascadeType.ALL)
        private Address address;

        @JsonIgnore
        @JsonBackReference
        @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
        private Group group;


}
