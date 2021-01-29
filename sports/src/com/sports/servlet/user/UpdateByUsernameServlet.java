package com.sports.servlet.user;

import com.sports.domain.User;
import com.sports.service.UserService;
import com.sports.service.impl.UserServiceImpl;
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
 * 根据用户名修改用户信息接口
 */
@WebServlet("/user/updateByUsername")
public class UpdateByUsernameServlet extends HttpServlet {
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
            Object username = reqJson.get("username");
            Object password = reqJson.get("password");
            Object sex = reqJson.get("sex");
            if(username!=null && password!=null && sex!=null){
                //数据都获得到了
                User sessionUser = (User)request.getSession().getAttribute("user");
                if(sessionUser!=null && sessionUser.equals(username.toString())) {
                    //是当前登录的用户。用户只可以改自己的信息
                    UserService service = new UserServiceImpl();
                    service.updateByUsername(username.toString(), password.toString(), sex.toString());
                    User user = service.selectOne(username.toString());
                    //把user对象存到session中，以便做权限验证
                    request.getSession().setAttribute("user", user);
                    //返回成功json
                    resJson.put("status", 0);
                    resJson.put("msg", "ok");
                    resJson.put("data", user);
                }else{
                    //不是当前用户
                    resJson.put("status",1);
                    resJson.put("msg","no power");
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
