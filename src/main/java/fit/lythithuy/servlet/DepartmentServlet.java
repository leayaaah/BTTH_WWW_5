package fit.lythithuy.servlet;

import fit.lythithuy.dao.DepartmentDAO;
import fit.lythithuy.model.Department;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {

    @Resource(name = "jdbc/employee_db")
    private DataSource dataSource;

    private DepartmentDAO deptDao;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
            deptDao = new DepartmentDAO(dataSource);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing DepartmentDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        if ("list".equals(action)) {
            String keyword = req.getParameter("keyword");
            List<Department> departments;

            if (keyword != null && !keyword.trim().isEmpty()) {
                departments = deptDao.searchByName(keyword.trim());
            } else {
                departments = deptDao.getAll();
            }

            req.setAttribute("departments", departments);
            req.setAttribute("keyword", keyword);
            req.getRequestDispatcher("view/department-list.jsp").forward(req, resp);
        }
    }
}
