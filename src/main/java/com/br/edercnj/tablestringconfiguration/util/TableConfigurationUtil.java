package com.br.edercnj.tablestringconfiguration.util;

import com.br.edercnj.tablestringconfiguration.annotation.TableConfiguration;
import com.br.edercnj.tablestringconfiguration.annotation.TableField;
import com.br.edercnj.tablestringconfiguration.exception.TableConfigurationClassNotFound;
import com.br.edercnj.tablestringconfiguration.exception.TableConfigurationNotFound;
import org.apache.commons.lang.StringUtils;

import java.lang.annotation.Annotation;

public class TableConfigurationUtil {

    protected TableConfigurationUtil() throws IllegalAccessException {throw new IllegalAccessException("Utility class");}

    public static TableConfiguration getTableConfigurationAnnotations(Class classWithAnnotations) throws TableConfigurationNotFound {
        Annotation[] annotations = classWithAnnotations.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof TableConfiguration) {
                return (TableConfiguration) annotation;
            }
        }
        throw new TableConfigurationNotFound();
    }

    public static String getTableLength(String configurationString) {
        return StringUtils.leftPad(String.valueOf(configurationString.length()), 3, "0");
    }

    public static String getTableId(TableConfiguration tableConfiguration) {
        return StringUtils.leftPad(String.valueOf(tableConfiguration.id()), 2, "0");
    }

    public static boolean isTableConfiguration(Class classForAnalyze) throws TableConfigurationClassNotFound {
        Annotation[] annotations = classForAnalyze.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof TableConfiguration)
                return true;
        }
        throw new TableConfigurationClassNotFound();
    }


}
