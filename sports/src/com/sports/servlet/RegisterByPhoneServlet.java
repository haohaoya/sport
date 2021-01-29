package com.sports.servlet;

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
 * 使用手机号注册接口
 */
@WebServlet("/registerByPhone")
public class RegisterByPhoneServlet extends HttpServlet {
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
            Object phone = reqJson.get("phone");
            Object password = reqJson.get("password");
            Object sex = reqJson.get("sex");
            if(sex==null){
                sex = "unknow";
            }
            if(username!=null && password!=null && phone!=null){
                //三个数据都获得到了
                UserService service = new UserServiceImpl();
                int ret = service.registerByPhone(username.toString(),phone.toString(),password.toString(),sex.toString());
                if(ret == 0){
                    //注册成功
                    resJson.put("status",0);
                    resJson.put("msg","ok");

                }else if(ret == 1){
                    //用户名重复
                    resJson.put("status",1);
                    resJson.put("msg","username repeat");
                }else{
                    //手机号重复
                    resJson.put("status",1);
                    resJson.put("msg","phone repeat");
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
