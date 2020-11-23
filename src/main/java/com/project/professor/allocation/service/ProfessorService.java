package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private ProfessorRepository professorRepository;
	private DepartmentService departmentService;

	public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService) {
		super();
		this.professorRepository = professorRepository;
		this.departmentService = departmentService;
	}

	public List<Professor> findAll(String name) {
		if (name == null) {
			return professorRepository.findAll();
		} else {
			return professorRepository.findByNameContainingIgnoreCase(name);
		}
	}

	public Professor findById(Long id) {
		return professorRepository.findById(id).orElse(null);
	}

	public Professor save(Professor professor) {
		professor.setId(null);
		return internalSave(professor);
	}

	public Professor update(Professor professor) {
		Long id = professor.getId();
		if (id == null) {
			return null;
		}

		Professor findedProfessor = professorRepository.findById(id).orElse(null);
		if (findedProfessor == null) {
			return null;
		}

		return internalSave(professor);
	}

	public void deleteById(Long id) {
		try {
			professorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
	}

	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}

	private Professor internalSave(Professor professor) {
		try {
			professor = professorRepository.save(professor);
			if (professor != null) {
				Department department = professor.getDepartment();
				department = departmentService.findById(department.getId());
				professor.setDepartment(department);
			}
			return professor;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
