package fit.lythithuy.dao;

import fit.lythithuy.model.Employee;
import fit.lythithuy.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private DBUtil dbutil;

    public EmployeeDAO(DataSource dataSource) {
        dbutil = new DBUtil(dataSource);
    }

    // Map ResultSet to Employee object helper method
    private Employee mapRowToEmployee(ResultSet rs) throws Exception {
        Employee emp = new Employee();
        emp.setId(rs.getInt("ID"));
        emp.setName(rs.getString("name"));
        emp.setRole(rs.getString("role"));
        emp.setSalary(rs.getDouble("salary"));
        emp.setEmail(rs.getString("email"));
        emp.setPhone(rs.getString("phone"));
        emp.setHireDate(rs.getDate("hire_date"));
        emp.setStatus(rs.getString("status"));
        emp.setDepartmentId(rs.getInt("department_id"));
        emp.setPositionId(rs.getInt("position_id"));
        return emp;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> emplist = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection con = dbutil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                emplist.add(mapRowToEmployee(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching all employees", e);
        }
        return emplist;
    }

    public List<Employee> getAllByDepartment(int deptId) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE department_id = ?";

        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, deptId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToEmployee(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Employee getById(int id) {
        String sql = "SELECT * FROM employees WHERE ID = ?";

        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToEmployee(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Employee emp) {
        String sql = "INSERT INTO employees(name, role, salary, email, phone, hire_date, status, department_id, position_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getRole());
            ps.setDouble(3, emp.getSalary());
            ps.setString(4, emp.getEmail());
            ps.setString(5, emp.getPhone());
            ps.setDate(6, emp.getHireDate());
            ps.setString(7, emp.getStatus() != null ? emp.getStatus() : "Active"); // Giá trị mặc định
            ps.setInt(8, emp.getDepartmentId());
            ps.setInt(9, emp.getPositionId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Employee emp) {
        String sql = "UPDATE employees SET name=?, role=?, salary=?, email=?, phone=?, " +
                "hire_date=?, status=?, department_id=?, position_id=? WHERE ID=?";

        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getRole());
            ps.setDouble(3, emp.getSalary());
            ps.setString(4, emp.getEmail());
            ps.setString(5, emp.getPhone());
            ps.setDate(6, emp.getHireDate());
            ps.setString(7, emp.getStatus());
            ps.setInt(8, emp.getDepartmentId());
            ps.setInt(9, emp.getPositionId());
            ps.setInt(10, emp.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM employees WHERE ID=?";

        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}