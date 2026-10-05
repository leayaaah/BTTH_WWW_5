package fit.lythithuy.model;

import java.sql.Date;

public class Employee {
    private int id;
    private String name;
    private String role;
    private double salary;
    private String email;
    private String phone;
    private Date hireDate;
    private String status;
    private int departmentId;
    private int positionId;

    // Constructors
    public Employee() {
    }

    public Employee(int id, String name, String role, double salary, String email, String phone, Date hireDate, String status, int departmentId, int positionId) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
        this.status = status;
        this.departmentId = departmentId;
        this.positionId = positionId;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Date getHireDate() { return hireDate; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    public int getPositionId() { return positionId; }
    public void setPositionId(int positionId) { this.positionId = positionId; }
}