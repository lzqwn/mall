package com.lzqwn.mall.product.exception;

import com.lzqwn.common.exception.BizCodeEnum;
import com.lzqwn.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;


@Slf4j
@RestControllerAdvice(basePackages = "com.lzqwn.mall.product.controller")
public class GlobalExceptionHandler {

    /**
     * 全局参数校验异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        HashMap<String, String> map = new HashMap<>();
        // 处理错误
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String message = fieldError.getDefaultMessage();
            String field = fieldError.getField();
            map.put(field, message);
        });
        log.error("数据校验出现问题{},异常类型{}", e.getMessage(), e.getClass());
        return R.error(BizCodeEnum.VAILD_EXCEPTION.getCode(),
                BizCodeEnum.VAILD_EXCEPTION.getMessage()).put("data", map);
    }

    @ExceptionHandler(value = Throwable.class)//异常的范围更大
    public R handleException(Throwable throwable) {
        log.error("未知异常{},异常类型{}",
                throwable.getMessage(),
                throwable.getClass());
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(),
                BizCodeEnum.UNKNOW_EXCEPTION.getMessage());
    }

}
