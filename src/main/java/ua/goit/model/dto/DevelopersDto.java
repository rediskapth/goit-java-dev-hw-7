package ua.goit.model.dto;

public class DevelopersDto {
    private Integer developerId;
    private String name;
    private Integer age;
    private Integer salary;


    public DevelopersDto(Integer developerId, String name, Integer age, Integer salary) {
        this.developerId = developerId;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public DevelopersDto() {
    }

    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
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

    @Override
    public String toString() {
        return "DevelopersDto{" +
                "developerId=" + developerId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
