package com.pocketdigi.demo.exception;

import com.google.gson.Gson;
import com.pocketdigi.demo.dto.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by fhp on 2017/2/17.
 */
@Slf4j
public class CommonExceptionResolver implements HandlerExceptionResolver{
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception e) {
        try {
            PrintWriter writer = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            ResultDTO<String> result = ResultDTO.wrapError(-1, "未知错误");
            Gson gson=new Gson();
            writer.write(gson.toJson(result));
            writer.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
            log.error("error reponse write failure!", e);
        }finally {
            e.printStackTrace();
        }
        return null;
    }
}
