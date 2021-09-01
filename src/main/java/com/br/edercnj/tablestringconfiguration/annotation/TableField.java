package com.br.edercnj.tablestringconfiguration.annotation;

import com.br.edercnj.tablestringconfiguration.model.LengthType;
import com.br.edercnj.tablestringconfiguration.model.TableFieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableField {
    TableFieldType dataType();

    LengthType lengthType() default LengthType.FIXED;

    int length() default 0;

    int maxLength() default 999;

    String padLeft() default " ";

    String padRight() default " ";

    int order();
}
