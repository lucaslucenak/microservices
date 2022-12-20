package br.edu.uepb.example.firstmicroservice.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.edu.uepb.example.firstmicroservice.models.StudentModel;
import br.edu.uepb.example.firstmicroservice.models.SubjectModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {

    private Long id;
    private String name;
    private String room;
    private Long teacherId;
//	private Long studentId;
	private List<StudentDTO> students = new ArrayList<>();
    
	public SubjectDTO(SubjectModel entity) {
		id = entity.getId();
		name = entity.getName();
		room = entity.getRoom();
		teacherId = entity.getTeacher().getId();
	}

	public SubjectDTO(SubjectModel entity, Set<StudentModel> students) {
		this(entity);
		students.forEach(x -> {
			this.students.add(new StudentDTO(x));
		});
	}
}
