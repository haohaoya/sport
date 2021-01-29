package com.sports.servlet.category;

import com.sports.domain.Category;
import com.sports.domain.CategoryPlus;
import com.sports.domain.User;
import com.sports.service.CategoryService;
import com.sports.service.UserService;
import com.sports.service.impl.CategoryServiceImpl;
import com.sports.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * 获取所有分类接口
 */
@WebServlet("/category/getAllCategory")
public class GetAllCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONObject resJson = new JSONObject();
        CategoryService service = new CategoryServiceImpl();
        List<CategoryPlus> categories = service.getAllCategory();
        //返回成功json
        resJson.put("status",0);
        resJson.put("msg","ok");
        resJson.put("data",categories);

        response.setContentType("text/json; charset=utf-8");
        Writer out = response.getWriter();
        out.write(resJson.toString());
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
