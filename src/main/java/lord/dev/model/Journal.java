package lord.dev.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "journal_tb")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Group group;

    @ToString.Exclude
    @OneToMany(mappedBy = "journal", fetch = FetchType.LAZY)
    private List<Subject> subjects;
}
