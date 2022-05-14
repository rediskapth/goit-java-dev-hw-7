package ua.goit.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "developers")
public class Developer {
    private Integer id;
    private String name;
    private Integer age;
    private Integer salary;
    private Set<Project> projects;
    private Set<Skill> skills;

    public Developer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "salary")
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "developers_projects",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "developers_skills",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
}
