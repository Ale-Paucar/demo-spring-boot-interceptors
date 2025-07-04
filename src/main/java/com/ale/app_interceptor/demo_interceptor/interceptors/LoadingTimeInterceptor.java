package com.ale.app_interceptor.demo_interceptor.interceptors;

//import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/*
import java.util.Date;
import java.util.HashMap;
import java.util.Map;*/
import java.util.Random;


@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = ((HandlerMethod) handler);
        logger.info("Loading Time Interceptor: preHandle() entrando..."+method.getMethod().getName());
        long start = System.currentTimeMillis();
        request.setAttribute("start", start);
        //simulamos un delay
        int delay= new Random().nextInt(500);
        Thread.sleep(delay);
/*
        Map<String,String> json = new HashMap<>();
        json.put("error", "no tienes acceso a esta pagina!!");
        json.put("date", new Date().toString());
        json.put("error", "no tienes acceso a esta pagina2!!");

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(json);
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(jsonString);
        return false;*/

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        long end = System.currentTimeMillis();
        long start = (long) request.getAttribute("start");
        long result = end - start;
        logger.info("tiempo transcurrido: "+result+" ms.");
        logger.info("Loading Time Interceptor: postHandle() saliendo..."+((HandlerMethod) handler).getMethod().getName());
    }
}
