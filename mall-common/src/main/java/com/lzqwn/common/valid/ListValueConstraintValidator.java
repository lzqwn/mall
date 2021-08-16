package com.lzqwn.common.valid;

import org.checkerframework.checker.units.qual.A;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;


/**
 * ConstraintValidator<注解名称, 校验值类型>
 * @author Administrator
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {
    // 存储所有可能的值
    private Set<Integer> set=new HashSet<>();

    @Override // 初始化，你可以获取注解上的内容并进行处理
    public void initialize(ListValue constraintAnnotation) {
        // 获取后端写好的限制
        // 这个value就是ListValue里的value，我们写的注解是@ListValue(value={0,1})
        int[] value = constraintAnnotation.value();
        for (int i : value) {
            set.add(i);
        }
    }

    @Override // 覆写验证逻辑
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 看是否在限制的值里
        return  set.contains(value);
    }
}
