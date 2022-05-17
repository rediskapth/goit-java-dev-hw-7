package ua.goit.model.convert;

import ua.goit.model.dao.DevelopersDao;
import ua.goit.model.dto.DevelopersDto;

import java.util.stream.Collectors;

public class DevelopersConverter {
    private final SkillsConverter skillsConverter;

    public DevelopersConverter(SkillsConverter skillsConverter) {
        this.skillsConverter = skillsConverter;
    }

    public DevelopersDto convert(DevelopersDao developersDao) {
        DevelopersDto dto = new DevelopersDto();
        dto.setId(developersDao.getId());
        dto.setName(developersDao.getName());
        dto.setAge(developersDao.getAge());
        dto.setSalary(developersDao.getSalary());
        dto.setSkills(developersDao.getSkills().stream()
                .map(skillsConverter::convert)
                .collect(Collectors.toSet()));
        return dto;
    }

    public DevelopersDao convert(DevelopersDto developersDto) {
        DevelopersDao dao = new DevelopersDao();
        dao.setId(developersDto.getId());
        dao.setName(developersDto.getName());
        dao.setAge(developersDto.getAge());
        dao.setSalary(developersDto.getSalary());
        if (developersDto.getSkills() != null) {
            dao.setSkills(developersDto.getSkills().stream()
                    .map(skillsConverter::convert)
                    .collect(Collectors.toSet()));
        }
        return dao;
    }
}
