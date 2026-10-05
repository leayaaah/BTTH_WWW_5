package fit.lythithuy.servlet;


// Cần import thêm PositionDAO (bạn tạo tương tự như DepartmentDAO)

import fit.lythithuy.dao.DepartmentDAO;
import fit.lythithuy.dao.EmployeeDAO;
import fit.lythithuy.dao.PositionDAO;
import fit.lythithuy.model.Employee;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {

    @Resource(name = "jdbc/employee_db")
    private DataSource dataSource;

    private EmployeeDAO empDao;
    private DepartmentDAO deptDao;
    private PositionDAO posDao;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
            empDao = new EmployeeDAO(dataSource);
            deptDao = new DepartmentDAO(dataSource);
            posDao = new PositionDAO(dataSource); // Khởi tạo PositionDAO
        } catch (Exception e) {
            throw new RuntimeException("Error initializing DAOs", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                List<Employee> allEmployees = empDao.getAllEmployees();
                req.setAttribute("employees", allEmployees);
                req.getRequestDispatcher("view/employee-list.jsp").forward(req, resp);
                break;

            case "new":
                req.setAttribute("departments", deptDao.getAll());
                req.setAttribute("positions", posDao.getAll()); // Đẩy list position xuống form
                req.getRequestDispatcher("view/employee-form.jsp").forward(req, resp);
                break;

            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                Employee emp = empDao.getById(id); // Gọi hàm getById vừa bổ sung
                req.setAttribute("employee", emp);
                req.setAttribute("departments", deptDao.getAll());
                req.setAttribute("positions", posDao.getAll());
                req.getRequestDispatcher("view/employee-form.jsp").forward(req, resp);
                break;

            case "delete":
                empDao.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("employees");
                break;

            case "viewbyid":
                String deptIdStr = req.getParameter("deptId");
                List<Employee> list;

                if (deptIdStr != null && !deptIdStr.isEmpty()) {
                    list = empDao.getAllByDepartment(Integer.parseInt(deptIdStr));
                } else {
                    list = empDao.getAllByDepartment(1);
                }
                req.setAttribute("employees", list);
                req.setAttribute("departments", deptDao.getAll());
                req.getRequestDispatcher("view/employee-list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // Đảm bảo tiếng Việt không bị lỗi font

        // 1. Lấy ID
        String idParam = req.getParameter("id");
        int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;

        // 2. Lấy dữ liệu Text & Number
        String name = req.getParameter("name");
        String role = req.getParameter("role");

        String salaryParam = req.getParameter("salary");
        double salary = (salaryParam != null && !salaryParam.isEmpty()) ? Double.parseDouble(salaryParam) : 0.0;

        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        // 3. Xử lý Date (Dữ liệu từ thẻ <input type="date"> có định dạng yyyy-mm-dd)
        String hireDateParam = req.getParameter("hireDate");
        Date hireDate = null;
        if (hireDateParam != null && !hireDateParam.trim().isEmpty()) {
            hireDate = Date.valueOf(hireDateParam);
        }

        String status = req.getParameter("status");

        // 4. Lấy Foreign Keys
        String deptIdParam = req.getParameter("departmentId");
        int deptId = (deptIdParam != null && !deptIdParam.isEmpty()) ? Integer.parseInt(deptIdParam) : 0;

        String posIdParam = req.getParameter("positionId");
        int posId = (posIdParam != null && !posIdParam.isEmpty()) ? Integer.parseInt(posIdParam) : 0;

        // 5. Gói vào Model và Save/Update
        Employee emp = new Employee(id, name, role, salary, email, phone, hireDate, status, deptId, posId);

        if (id > 0) {
            empDao.update(emp);
        } else {
            empDao.save(emp);
        }

        // Redirect về trang danh sách nhân viên
        resp.sendRedirect("employees");
    }
}