package com.br.edercnj.tablestringconfiguration.mocks;

import com.br.edercnj.tablestringconfiguration.annotation.BitConfiguration;
import com.br.edercnj.tablestringconfiguration.annotation.HexadecimalConfiguration;
import com.br.edercnj.tablestringconfiguration.annotation.TableField;
import com.br.edercnj.tablestringconfiguration.model.LengthType;
import com.br.edercnj.tablestringconfiguration.model.TableFieldType;
import lombok.Data;

@HexadecimalConfiguration
@Data
public class BinConfiguration {
    @BitConfiguration(bit = 7)
    boolean bit7;
    @BitConfiguration(bit = 6)
    boolean bit6;
    @BitConfiguration(bit = 5)
    boolean bit5;
    @BitConfiguration(bit = 4)
    boolean bit4;
    @BitConfiguration(bit = 3)
    boolean bit3;
    @BitConfiguration(bit = 2)
    boolean bit2;
    @BitConfiguration(bit = 1)
    boolean bit1;
    @BitConfiguration(bit = 0)
    boolean bit0;
}
