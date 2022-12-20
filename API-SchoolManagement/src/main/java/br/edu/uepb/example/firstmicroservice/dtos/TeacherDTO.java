package br.edu.uepb.example.firstmicroservice.dtos;

import br.edu.uepb.example.firstmicroservice.models.TeacherModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

	private Long id;
	private String name;
	private	String graduation;
	private String registration;
	private String email;

	public TeacherDTO(String name, String graduation, String registration, String email) {
		this.name = name;
		this.graduation = graduation;
		this.registration = registration;
		this.email = email;
	}

	public TeacherDTO(TeacherModel entity) {
		id = entity.getId();
		name = entity.getName();
		graduation = entity.getGraduation();
		registration = entity.getRegistration();
		email = entity.getEmail();
	}
}
