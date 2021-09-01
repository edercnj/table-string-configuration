package com.br.edercnj.tablestringconfiguration.util;

import com.br.edercnj.tablestringconfiguration.annotation.BitConfiguration;
import com.br.edercnj.tablestringconfiguration.annotation.HexadecimalConfiguration;

import java.lang.annotation.Annotation;

public class HexadecimalConfigurationUtil {

    public static int getOrder(HexadecimalConfiguration annotation) {return annotation.order();}

    public static boolean isHexadecimalConfiguration(Annotation annotation) {
        return annotation instanceof HexadecimalConfiguration;
    }

    public static boolean isBitConfiguration(Annotation annotation) {return annotation instanceof BitConfiguration;}

}
