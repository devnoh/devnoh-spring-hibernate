package devnoh.demoapp.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "DEPT")
public class Department extends BaseObject {

    @Id
    @Column(name = "DEPTNO", length = 2)
    private Integer deptNo;

    @NotNull
    @Size(max = 14)
    @Column(name = "DNAME", length = 14, nullable = false)
    private String name;

    @Column(name = "LOC", length = 13)
    private String location;

    @OneToMany(mappedBy = "department", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Employee> employees;

    public Department() {
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        return deptNo == that.deptNo;
    }

    @Override
    public int hashCode() {
        int result = deptNo.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptNo=" + deptNo +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
