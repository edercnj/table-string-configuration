package com.br.edercnj.tablestringconfiguration.util;

import com.br.edercnj.tablestringconfiguration.annotation.TableField;

import java.lang.annotation.Annotation;

public class TableFieldUtil {

    protected TableFieldUtil() throws IllegalAccessException {throw new IllegalAccessException("Utility class");}

    public static int getOrder(TableField annotation) {return annotation.order();}

    public static boolean isTableField(Annotation annotation) {return annotation instanceof TableField;}
}


