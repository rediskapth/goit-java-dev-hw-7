package ua.goit.service;

import ua.goit.model.convert.ProjectsConverter;
import ua.goit.model.dao.ProjectsDao;
import ua.goit.model.dto.ProjectsDto;
import ua.goit.repository.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectsService {
    private final ProjectsConverter converter;
    private final Repository<ProjectsDao> repository;

    public ProjectsService(ProjectsConverter converter, Repository<ProjectsDao> repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public void save(ProjectsDto dto) {
        repository.save(converter.convert(dto));
    }

    public ProjectsDto findById(int id) {
        return converter.convert(repository.findById(id).orElseThrow(() -> new IllegalArgumentException
                (String.format("Project with id %d not found", id))));
    }

    public List<ProjectsDto> findAll() {
        return repository.findAll().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public void update(ProjectsDto dto) {
        repository.update(converter.convert(dto));
    }

    public void remove(ProjectsDto dto) {
        repository.remove(converter.convert(dto));
    }

}
