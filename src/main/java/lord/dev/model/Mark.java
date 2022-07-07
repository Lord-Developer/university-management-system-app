package lord.dev.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mark_tb")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double mark;

    @JsonBackReference
    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Journal journal;
}
