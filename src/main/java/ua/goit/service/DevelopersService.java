package ua.goit.service;

import ua.goit.model.convert.DevelopersConverter;
import ua.goit.model.dto.DevelopersDto;
import ua.goit.repository.DevelopersRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DevelopersService {
    private final DevelopersRepository repository;
    private final DevelopersConverter developersConverter;

    public DevelopersService(DevelopersRepository repository, DevelopersConverter developersConverter) {
        this.repository = repository;
        this.developersConverter = developersConverter;
    }

    public void save(DevelopersDto dto) {
        repository.save(developersConverter.convert(dto));
    }

    public DevelopersDto findById(int id) {
        return developersConverter.convert(repository.findById(id).orElseThrow(() -> new IllegalArgumentException
                (String.format("Developer with id %d not found", id))));
    }

    public Set<DevelopersDto> findByIds(Set<Integer> ids) {
        return repository.findByIds(ids).stream()
                .map(developersConverter::convert)
                .collect(Collectors.toSet());
    }

    public List<DevelopersDto> findAll() {
        return repository.findAll().stream()
                .map(developersConverter::convert)
                .collect(Collectors.toList());
    }

    public void update(DevelopersDto dto) {
        repository.update(developersConverter.convert(dto));
    }

    public void remove(DevelopersDto dto) {
        repository.remove(developersConverter.convert(dto));
    }

}
