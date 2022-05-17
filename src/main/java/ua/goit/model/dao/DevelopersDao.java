package ua.goit.model.dao;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "developers")
public class DevelopersDao {
    private Integer id;
    private String name;
    private Integer age;
    private Integer salary;
    private Set<SkillsDao> skills;

    public DevelopersDao(Integer id, String name, Integer age, Integer salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public DevelopersDao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
            name = "developers_skills",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    public Set<SkillsDao> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillsDao> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "DevelopersDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
