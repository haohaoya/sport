package com.sports.filter;

import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

/**
 * 权限判断的过滤器
 * 只有登录后的用户才可访问二级目录
 */
@WebFilter(filterName = "PowerFilter",
        urlPatterns = {"/user/*","/category/*","/activity/*","/discuss/*","/userAndUser/*","/userAndActivity/*"})
public class PowerFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //转换成httpServletRequest,获取session做登录判断
        HttpServletRequest request = (HttpServletRequest)req;
        HttpSession session = request.getSession();
        if(session.getAttribute("user")!=null){
            //已登录，放行
            chain.doFilter(req, resp);
        }else{
            //未登录，返回
            JSONObject resJson = new JSONObject();
            resJson.put("status",1);
            resJson.put("msg","please login");
            resp.setContentType("text/json; charset=utf-8");
            Writer out = resp.getWriter();
            out.write(resJson.toString());
            out.flush();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
