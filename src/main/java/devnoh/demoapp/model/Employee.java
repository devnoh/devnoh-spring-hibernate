package devnoh.demoapp.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "EMP")
public class Employee extends BaseObject {

    @Id
    //@TableGenerator(name = "EMPNO_GEN", table = "SEQUENCE", pkColumnName = "NAME", valueColumnName = "VALUE", pkColumnValue = "EMPNO", initialValue = 9000, allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.TABLE, generator = "EMPNO_GEN")
    @Column(name = "EMPNO", length = 4)
    private Integer empNo;

    @Column(name = "ENAME", length = 10, nullable = false)
    private String name;

    @Column(name = "JOB", length = 9)
    private String job;

    @Column(name = "MGR", length = 4)
    private Integer mgrNo;

    @Column(name = "HIREDATE")
    private Date hireDate;

    @Column(name = "SAL", columnDefinition = "NUMBER(7,2)", precision = 7, scale = 2)
    private Double salary;

    @Column(name = "COMM", columnDefinition = "NUMBER(7,2)", precision = 7, scale = 2)
    private Double commission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DEPTNO")
    private Department department;

    public Employee() {
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgrNo() {
        return mgrNo;
    }

    public void setMgrNo(Integer mgrNo) {
        this.mgrNo = mgrNo;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (empNo != employee.empNo) return false;
        return !(name != null ? !name.equals(employee.name) : employee.name != null);

    }

    @Override
    public int hashCode() {
        int result = empNo.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", mgrNo='" + mgrNo + '\'' +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", commission=" + commission +
                ", department=" + department +
                '}';
    }
}
