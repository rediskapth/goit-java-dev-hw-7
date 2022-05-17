package ua.goit.model.dto;

import java.util.Set;

public class DevelopersDto {
    private Integer id;
    private String name;
    private Integer age;
    private Integer salary;
    private Set<SkillsDto> skills;


    public DevelopersDto(Integer id, String name, Integer age, Integer salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public DevelopersDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Set<SkillsDto> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillsDto> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "DevelopersDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
