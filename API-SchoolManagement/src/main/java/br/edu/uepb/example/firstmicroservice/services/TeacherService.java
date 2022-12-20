package br.edu.uepb.example.firstmicroservice.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.uepb.example.firstmicroservice.dtos.TeacherDTO;
import br.edu.uepb.example.firstmicroservice.exceptions.ResourceNotFoundException;
import br.edu.uepb.example.firstmicroservice.models.TeacherModel;
import br.edu.uepb.example.firstmicroservice.repositories.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private final TeacherRepository repository;

	public TeacherService(TeacherRepository repository) {
		this.repository = repository;
	}
	
	public List<TeacherDTO> findAll(){
		return repository.findAll().stream().map(x -> new TeacherDTO(x)).toList();
	}
	
	public TeacherDTO findById(Long id) {
		return new TeacherDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher")));
	}
	
	public TeacherDTO insert(TeacherDTO dto) {
		TeacherModel entity = new TeacherModel();
		BeanUtils.copyProperties(dto, entity);
		repository.save(entity);
		return new TeacherDTO(entity);
	}
	
	public TeacherDTO update(Long id, TeacherDTO dto) {
		try {
			TeacherModel entity = repository.findById(id).get();
			entity.setName(dto.getName());
			entity.setEmail(dto.getEmail());
			entity.setGraduation(dto.getGraduation());
			entity.setRegistration(dto.getRegistration());
			repository.save(entity);
			return new TeacherDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Teacher id: " + id);
		}
	} 
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Teacher id: " + id);
		}
	}
}
