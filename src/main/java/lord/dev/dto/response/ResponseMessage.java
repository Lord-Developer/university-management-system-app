package lord.dev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseMessage {


    UNIVERSITY_SAVE("University saved successfully!"),
    UNIVERSITY_UPDATE("University updated successfully"),
    UNIVERSITY_DELETE("University deleted successfully!"),
    UNIVERSITY_LIST("University List"),
    UNIVERSITY_ALREADY_EXISTS("api.success.university.already.exist"),
    UNIVERSITY_NOT_FOUND("api.error.university.notFound"),
    FACULTY_SAVE("Faculty saved successfully!"),
    FACULTY_UPDATE("Faculty updated successfully!"),
    FACULTY_DELETE("Faculty deleted successfully!"),
    FACULTY_LIST("Faculty List"),
    FACULTY_ALREADY_EXISTS("api.success.faculty.already.exist"),
    FACULTY_NOT_FOUND("api.error.faculty.notFound"),
    GROUP_CREATE("Group saved successfully!"),
    GROUP_UPDATE("Group updated successfully!"),
    GROUP_DELETE("Group deleted successfully!"),
    GROUP_LIST("Group List"),
    GROUP_NOT_FOUND("api.error.group.notFound"),
    GROUP_ALREADY_EXISTS("api.success.group.already.exist"),
    SUBJECT_SAVE("Subject saved successfully!"),
    SUBJECT_UPDATE("Subject updated successfully!"),
    SUBJECT_DELETE("Subject deleted successfully!"),
    SUBJECT_LIST("Subject List"),
    SUBJECT_NOT_FOUND("api.error.subject.notFound"),
    SUBJECT_ALREADY_EXISTS("api.success.subject.already.exist"),
    JOURNAL_SAVE("Journal saved successfully!"),
    JOURNAL_UPDATE("Journal updated successfully!"),
    JOURNAL_DELETE("Journal deleted successfully!"),
    JOURNAL_LIST("Journal List"),
    JOURNAL_NOT_FOUND("api.error.journal.notFound"),
    JOURNAL_ALREADY_EXISTS("api.success.journal.already.exist"),
    STUDENT_SAVE("Student saved successfully!"),
    STUDENT_UPDATE("Student updated successfully!"),
    STUDENT_DELETE("Student deleted successfully!"),
    STUDENT_LIST("Student List"),
    STUDENT_NOT_FOUND("api.error.student.notFound"),
    STUDENT_ALREADY_EXISTS("api.success.student.already.exist"),
    MARK_SAVE("Mark saved successfully!"),
    MARK_UPDATE("Mark updated successfully!"),
    MARK_DELETE("Mark deleted successfully!"),
    MARK_NOT_FOUND("api.error.mark.notFound"),
    MARK_ALREADY_EXISTS("api.success.mark.already.exist"),
    MARK_LIST("Mark List"),
    STUDENT_SUBJECT_LIST("Student Subjects"),
    JOURNAL_NOT_BELONG_THAT_GROUP("Jounal does not belong to this Group!"),
    SUBJECT_NOT_BELONG_THAT_JOURNAL("Subject does not belong to Journal!"),
    SQL_EXCEPTION("Sql Exception is thrown!");


    private final String message;

    @Override
    public String toString() {
        return message;
    }
}
