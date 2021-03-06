package com.sports.servlet.category;

import com.sports.domain.Category;
import com.sports.domain.User;
import com.sports.service.CategoryService;
import com.sports.service.UserAndActivityService;
import com.sports.service.impl.CategoryServiceImpl;
import com.sports.service.impl.UserAndActivityServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.List;

/**
 * 根据分类名获取用户列表接口
 */
@WebServlet("/category/getUserByCategory")
public class GetUserByCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        JSONObject reqJson=null;
        JSONObject resJson = new JSONObject();
        if(!"".equals(sb.toString())) {
            //将json字符串转换为json对象
            reqJson = JSONObject.fromObject(sb.toString());
            Object name = reqJson.get("name");
            if(name!=null){
                CategoryService categoryService = new CategoryServiceImpl();
                List<User> users = categoryService.getUserByCategory(name.toString());
                //返回成功json
                resJson.put("status", 0);
                resJson.put("msg", "ok");
                resJson.put("data",users);
            }else{
                //缺少参数，返回错误信息
                resJson.put("status",1);
                resJson.put("msg","missing parameter");
            }
        }else{
            //没有数据，返回错误信息
            resJson.put("status",1);
            resJson.put("msg","missing parameter");
        }
        response.setContentType("text/json; charset=utf-8");
        Writer out = response.getWriter();
        out.write(resJson.toString());
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
