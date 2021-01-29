package com.sports.servlet.activity;

import com.sports.domain.Activity;
import com.sports.domain.ActivityPlus;
import com.sports.domain.Category;
import com.sports.service.ActivityService;
import com.sports.service.CategoryService;
import com.sports.service.impl.ActivityServiceImpl;
import com.sports.service.impl.CategoryServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取所有活动的接口
 */
@WebServlet("/activity/getAllActivity")
public class GetAllActivityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONObject resJson = new JSONObject();
        ActivityService service = new ActivityServiceImpl();
        List<Activity> activities = service.getAllActivity();

        List<ActivityPlus> activityPluses = new ArrayList<>();
        for(Activity activity : activities){
            ActivityPlus activityPlus = new ActivityPlus();
            activityPlus.setCategory(activity.getCategory());
            activityPlus.setCreator(activity.getCreator());
            activityPlus.setId(activity.getId());
            activityPlus.setInformation(activity.getInformation());
            activityPlus.setName(activity.getName());
            activityPlus.setTime(activity.getTime());
            String num = service.getNumByActivity(activity.getName());

            activityPlus.setNum(num);
            activityPluses.add(activityPlus);
        }


        //返回成功json
        resJson.put("status",0);
        resJson.put("msg","ok");
        resJson.put("data",activityPluses);

        response.setContentType("text/json; charset=utf-8");
        Writer out = response.getWriter();
        out.write(resJson.toString());
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
