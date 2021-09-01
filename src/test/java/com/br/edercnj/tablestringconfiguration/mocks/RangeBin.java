package com.br.edercnj.tablestringconfiguration.mocks;

import com.br.edercnj.tablestringconfiguration.annotation.HexadecimalConfiguration;
import com.br.edercnj.tablestringconfiguration.annotation.TableConfiguration;
import com.br.edercnj.tablestringconfiguration.annotation.TableField;
import com.br.edercnj.tablestringconfiguration.model.LengthType;
import com.br.edercnj.tablestringconfiguration.model.TableFieldType;
import lombok.Data;

@TableConfiguration(id = 2)
@Data
public class RangeBin {
    @TableField(dataType = TableFieldType.NUMBER, lengthType = LengthType.FIXED, order = 0, length = 4)
    int code;
    @TableField(dataType = TableFieldType.NUMBER, lengthType = LengthType.FIXED, order = 1, length = 6)
    int startRange;
    @TableField(dataType = TableFieldType.NUMBER, lengthType = LengthType.FIXED, order = 2, length = 6)
    int endRange;
    @TableField(dataType = TableFieldType.NUMBER, lengthType = LengthType.FIXED, order = 3, length = 2)
    int panLength;
    @TableField(dataType = TableFieldType.NUMBER, lengthType = LengthType.FIXED, order = 4, length = 11)
    int brandId;
    @TableField(dataType = TableFieldType.NUMBER, lengthType = LengthType.FIXED, order = 5, length = 2)
    int productId;
    @TableField(dataType = TableFieldType.NUMBER, lengthType = LengthType.LLVAR, order = 6, maxLength = 40)
    String flows;
    @HexadecimalConfiguration(order = 7)
    BinConfiguration binConfiguration;

}
