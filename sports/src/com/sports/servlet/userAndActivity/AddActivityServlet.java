package com.sports.servlet.userAndActivity;

import com.sports.domain.User;
import com.sports.service.ActivityService;
import com.sports.service.CategoryService;
import com.sports.service.UserAndActivityService;
import com.sports.service.impl.ActivityServiceImpl;
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

/**
 * 添加活动接口
 */
@WebServlet("/userAndActivity/addActivity")
public class AddActivityServlet extends HttpServlet {
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
            Object category = reqJson.get("category");
            Object information = reqJson.get("information");
            Object name = reqJson.get("name");
            //获取当前登录用户的用户名
            User sessionUser = (User)request.getSession().getAttribute("user");
            String username = sessionUser.getUsername();
            if(name!=null && category!=null && information!=null){
                //数据都获得到了
                UserAndActivityService service = new UserAndActivityServiceImpl();
                int ret = service.addActivity(username,category.toString(),information.toString(),name.toString());
                if(ret == 0){
                    //添加成功
                    resJson.put("status",0);
                    resJson.put("msg","ok");

                }else{
                    //活动名重复
                    resJson.put("status",1);
                    resJson.put("msg","name repeat");
                }
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
