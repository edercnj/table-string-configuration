package com.br.edercnj.tablestringconfiguration.service;

import com.br.edercnj.tablestringconfiguration.annotation.HexadecimalConfiguration;
import com.br.edercnj.tablestringconfiguration.annotation.TableField;
import com.br.edercnj.tablestringconfiguration.exception.TableConfigurationException;
import com.br.edercnj.tablestringconfiguration.util.HexUtils;
import com.br.edercnj.tablestringconfiguration.util.HexadecimalConfigurationUtil;
import com.br.edercnj.tablestringconfiguration.util.TableConfigurationUtil;
import com.br.edercnj.tablestringconfiguration.util.TableFieldUtil;
import org.apache.commons.lang.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

public class TableConfigurationService {
    public static <T> String generateConfigurationString(T configurationClass) throws TableConfigurationException, InstantiationException, IllegalAccessException {
        Class<?> localClass = configurationClass.getClass();
        if (TableConfigurationUtil.isTableConfiguration(localClass)) {
            String tableId = TableConfigurationUtil.getTableId(TableConfigurationUtil.getTableConfigurationAnnotations(localClass));
            Field[] fields = localClass.getDeclaredFields();
            String configuration = convertTableFieldsToStringConfiguration(configurationClass, fields);
            return generateTableString(tableId, configuration);
        } else {
            throw new NullPointerException("No TableConfiguration annotation found");
        }
    }

    private static String generateTableString(String tableId, String configuration) {
        return tableId + TableConfigurationUtil.getTableLength(configuration) + configuration;
    }

    private static <T> String convertTableFieldsToStringConfiguration(T configurationClass, Field[] fields) throws IllegalAccessException {
        String[] configuration = new String[fields.length];
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (TableFieldUtil.isTableField(annotation)) {
                    processTableField(configurationClass, configuration, field, (TableField) annotation);
                } else if (HexadecimalConfigurationUtil.isHexadecimalConfiguration(annotation)) {
                    processHexadecimalConfiguration(configuration, field.get(configurationClass), (HexadecimalConfiguration) annotation);
                }
            }
        }
        StringBuilder fieldsConfiguration = new StringBuilder();
        Arrays.stream(configuration).forEach(fieldsConfiguration::append);
        return fieldsConfiguration.toString();
    }

    private static <T> void processTableField(T configurationClass, String[] configuration, Field field, TableField annotation) {
        try {
            String value;
            switch (annotation.dataType()) {
                case NUMBER:
                    value = setNumberValue(configurationClass, field, annotation);
                    configuration[TableFieldUtil.getOrder(annotation)] = value;
                    break;
                case BCD:
                    value = setBcdValue(configurationClass, field, annotation);
                    configuration[TableFieldUtil.getOrder(annotation)] = value;
                    break;
                case ANS:
                    value = setAnsValue(configurationClass, field, annotation);
                    configuration[TableFieldUtil.getOrder(annotation)] = value;
                    break;
                default:
                    value = setDefaultValue(configurationClass, field, annotation);
                    configuration[TableFieldUtil.getOrder(annotation)] = value;
                    break;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static <T> String setDefaultValue(T configurationClass, Field field, TableField annotation) throws IllegalAccessException {
        String value;
        value = StringUtils.leftPad(String.valueOf(field.get(configurationClass).toString()), annotation.length(), annotation.padLeft());
        String length = setLength(annotation, value);
        return length + value;
    }

    private static String setLength(TableField annotation, String value) {
        switch (annotation.lengthType()) {
            case LLVAR:
                return String.format("%02d", value.length());
            case LLLVAR:
                return String.format("%03d", value.length());
            default:
                return "";
        }
    }

    private static <T> String setBcdValue(T configurationClass, Field field, TableField annotation) throws IllegalAccessException {
        String value;
        int bcdValue = field.getInt(configurationClass);
        if (bcdValue % 2 != 0) {
            value = StringUtils.leftPad(String.valueOf(field.get(configurationClass).toString()), bcdValue % 2, "0");
        } else {
            value = field.get(configurationClass).toString();
        }
        String length = setLength(annotation, value);
        return length + value;
    }

    private static <T> String setAnsValue(T configurationClass, Field field, TableField annotation) throws IllegalAccessException {
        String value;
        value = StringUtils.leftPad(String.valueOf(field.get(configurationClass).toString()), annotation.length(), annotation.padLeft());
        String length = setLength(annotation, value);
        return length + value;
    }

    private static <T> String setNumberValue(T configurationClass, Field field, TableField annotation) throws IllegalAccessException {
        String value;
        value = StringUtils.leftPad(String.valueOf(field.get(configurationClass).toString()), annotation.length(), "0");
        String length = setLength(annotation, value);
        return length + value;
    }

    private static <T> void processHexadecimalConfiguration(String[] configuration, T hexadecimalClassConfiguration, HexadecimalConfiguration annotation) {
        try {
            StringBuilder bitConfiguration = new StringBuilder();
            for (Field field : hexadecimalClassConfiguration.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Annotation[] annotations = field.getDeclaredAnnotations();
                for (Annotation tableFieldAnnotation : annotations) {
                    if (HexadecimalConfigurationUtil.isBitConfiguration(tableFieldAnnotation) && field.getType().equals(boolean.class)) {
                        boolean enable = field.getBoolean(hexadecimalClassConfiguration);
                        bitConfiguration.append(enable ? "1" : "0");
                    }
                }
            }
            configuration[HexadecimalConfigurationUtil.getOrder(annotation)] = HexUtils.convertStringToHex(bitConfiguration.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
