package com.sports.servlet.userAndUser;

import com.sports.domain.User;
import com.sports.domain.UserAndUser;
import com.sports.service.DiscussService;
import com.sports.service.UserAndUserService;
import com.sports.service.impl.DiscussServiceImpl;
import com.sports.service.impl.UserAndUserServiceImpl;
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
 * 添加好友接口
 */
@WebServlet("/userAndUser/addFriend")
public class AddFriendServlet extends HttpServlet {
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
            //获取当前登录用户的用户名
            User sessionUser = (User)request.getSession().getAttribute("user");
            String name = sessionUser.getUsername();
            if(username!=null){
                //数据都获得到了
                UserAndUserService service = new UserAndUserServiceImpl();
                int ret = service.addFriend(name,username.toString());
                if(ret == 0){
                    //添加成功
                    resJson.put("status",0);
                    resJson.put("msg","ok");

                }else{
                    //用户名重复
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
