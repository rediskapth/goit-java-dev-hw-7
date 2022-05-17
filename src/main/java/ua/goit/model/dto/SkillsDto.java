package ua.goit.model.dto;

import java.util.Set;

public class SkillsDto {
    private Integer id;
    private String language;
    private String skill;
    private Set<DevelopersDto> developers;

    public SkillsDto(Integer id, String language, String skill) {
        this.id = id;
        this.language = language;
        this.skill = skill;
    }

    public SkillsDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Set<DevelopersDto> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DevelopersDto> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "SkillsDto{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
