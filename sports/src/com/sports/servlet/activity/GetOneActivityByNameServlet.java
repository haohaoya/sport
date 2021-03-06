package com.sports.servlet.activity;

import com.sports.domain.*;
import com.sports.service.*;
import com.sports.service.impl.*;
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
 * 根据用户名获取的活动列表
 */
@WebServlet("/activity/getOneActivityByName")
public class GetOneActivityByNameServlet extends HttpServlet {
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
                //数据已获得到
                ActivityService service = new ActivityServiceImpl();
                UserAndActivityService userAndActivityService = new UserAndActivityServiceImpl();
                DiscussService discussService = new DiscussServiceImpl();
                UserService userService = new UserServiceImpl();
                List<User> users = userAndActivityService.getUserByActivity(name.toString());
                List<Discuss> discusses = discussService.getDiscuss(name.toString());

                Activity activity = service.getActivityByName(name.toString());
                ActivityPlusPlus activityPlusPlus = new ActivityPlusPlus();
                activityPlusPlus.setCategory(activity.getCategory());
                activityPlusPlus.setCreator(activity.getCreator());
                activityPlusPlus.setId(activity.getId());
                activityPlusPlus.setInformation(activity.getInformation());
                activityPlusPlus.setName(activity.getName());
                activityPlusPlus.setTime(activity.getTime());
                String num = service.getNumByActivity(activity.getName());
                activityPlusPlus.setNum(num);

                activityPlusPlus.setUser(users);
                List<DiscussPlus> discussPluses = new ArrayList<>();
                for(Discuss discuss : discusses){
                    User user = userService.selectOne(discuss.getCreator());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("username",user.getUsername());
                    jsonObject.put("phone",user.getPhone());
                    jsonObject.put("password",user.getPassword());
                    jsonObject.put("id",user.getId());
                    jsonObject.put("sex",user.getSex());
                    DiscussPlus discussPlus = new DiscussPlus();
                    discussPlus.setActivity(discuss.getActivity());
                    discussPlus.setId(discuss.getId());
                    discussPlus.setInformation(discuss.getInformation());
                    discussPlus.setCreator(jsonObject);
                    discussPluses.add(discussPlus);
                }

                activityPlusPlus.setDiscuss(discussPluses);

                //返回成功json
                resJson.put("status",0);
                resJson.put("msg","ok");
                resJson.put("data",activityPlusPlus);
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
