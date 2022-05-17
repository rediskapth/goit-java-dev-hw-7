package ua.goit.service;

import ua.goit.model.convert.SkillsConverter;
import ua.goit.model.dto.SkillsDto;
import ua.goit.repository.SkillsRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SkillsService {
    private final SkillsConverter converter;
    private final SkillsRepository repository;

    public SkillsService(SkillsConverter converter, SkillsRepository repository) {
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

    public Set<SkillsDto> findByIds(Set<Integer> ids) {
        return repository.findByIds(ids).stream()
                .map(converter::convert)
                .collect(Collectors.toSet());
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
