package com.br.edercnj.tablestringconfiguration.annotation;

import org.hibernate.validator.constraints.Range;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BitConfiguration {


    @Range(min = 0, max = 7)
    int bit();
}
