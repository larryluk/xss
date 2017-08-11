package com.larryluk.xss.exception;

import com.larryluk.xss.bean.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by larryluk on 2017/8/11.
 */
@ControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Object> handler(Exception e){
        e.printStackTrace();
        return null;
    }
}
