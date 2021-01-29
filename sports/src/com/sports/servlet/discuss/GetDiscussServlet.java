package com.sports.servlet.discuss;

import com.sports.domain.Discuss;
import com.sports.domain.DiscussPlus;
import com.sports.domain.User;
import com.sports.service.DiscussService;
import com.sports.service.UserService;
import com.sports.service.impl.DiscussServiceImpl;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 获取评论接口
 */
@WebServlet("/discuss/getDiscuss")
public class GetDiscussServlet extends HttpServlet {
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
                //数据都获得到了

                DiscussService service = new DiscussServiceImpl();
                UserService userService = new UserServiceImpl();
                List<Discuss> discusses = service.getDiscuss(name.toString());
                List<DiscussPlus> discussPluses = new ArrayList<>();
                for(Discuss discuss : discusses){
                    DiscussPlus discussPlus = new DiscussPlus();
                    discussPlus.setActivity(discuss.getActivity());
                    discussPlus.setId(discuss.getId());
                    discussPlus.setInformation(discuss.getInformation());
                    User user = userService.selectOne(discuss.getCreator());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("username",user.getUsername());
                    jsonObject.put("phone",user.getPhone());
                    jsonObject.put("password",user.getPassword());
                    jsonObject.put("id",user.getId());
                    jsonObject.put("sex",user.getSex());
                    discussPlus.setCreator(jsonObject);
                    discussPluses.add(discussPlus);
                }
                resJson.put("status",0);
                resJson.put("msg","ok");
                resJson.put("data",discussPluses);

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
