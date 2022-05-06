package ua.goit.service;

import ua.goit.model.convert.SkillsConverter;
import ua.goit.model.dao.SkillsDao;
import ua.goit.model.dto.SkillsDto;
import ua.goit.repository.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class SkillsService {
    private final SkillsConverter converter;
    private final Repository<SkillsDao> repository;

    public SkillsService(SkillsConverter converter, Repository<SkillsDao> repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public void save(SkillsDto dto) {
        repository.save(converter.convert(dto));
    }

    public SkillsDto findById(int id) {
        return converter.convert(repository.findById(id).orElseThrow(() -> new IllegalArgumentException
                (String.format("Skill with id %d not found", id))));
    }

    public List<SkillsDto> findAll() {
        return repository.findAll().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public void update(SkillsDto dto) {
        repository.update(converter.convert(dto));
    }

    public void remove(SkillsDto dto) {
        repository.remove(converter.convert(dto));
    }

}
