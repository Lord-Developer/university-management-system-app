package lord.dev.mapper;

import lord.dev.dto.request.StudentRequest;
import lord.dev.dto.response.ResponseMessage;
import lord.dev.exception.NoSuchElementExistsException;
import lord.dev.model.Address;
import lord.dev.model.Group;
import lord.dev.model.Student;
import lord.dev.repository.GroupRepository;
import lord.dev.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public StudentMapper(GroupRepository groupRepository, StudentRepository studentRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
    }

    public Student convertStudentRequestToStudentEntity(StudentRequest studentRequest){
        Address address = new Address();
        address.setCity(studentRequest.getCity());
        address.setDistrict(studentRequest.getDistrict());
        address.setStreet(studentRequest.getStreet());

        Group group = groupRepository.findById(studentRequest.getGroupId())
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.GROUP_NOT_FOUND.getMessage()));

        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setAddress(address);
        student.setGroup(group);
        return student;
    }

    public Student convertStudentRequestToStudentEntity(long id, StudentRequest studentRequest){
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.STUDENT_NOT_FOUND.getMessage()));
        Address address = student.getAddress();
        address.setCity(studentRequest.getCity());
        address.setDistrict(studentRequest.getDistrict());
        address.setStreet(studentRequest.getStreet());

        Group group = groupRepository.findById(studentRequest.getGroupId())
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.GROUP_NOT_FOUND.getMessage()));

        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setAddress(address);
        student.setGroup(group);
        return student;
    }
}
