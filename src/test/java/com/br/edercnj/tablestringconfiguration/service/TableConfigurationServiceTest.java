package com.br.edercnj.tablestringconfiguration.service;

import com.br.edercnj.tablestringconfiguration.exception.TableConfigurationException;
import com.br.edercnj.tablestringconfiguration.mocks.BinConfiguration;
import com.br.edercnj.tablestringconfiguration.mocks.RangeBin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TableConfigurationServiceTest {

    private RangeBin rangeBin;

    @BeforeEach
    void setUp() {
        BinConfiguration binConfiguration = new BinConfiguration();
        binConfiguration.setBit0(false);
        binConfiguration.setBit1(true);
        binConfiguration.setBit2(false);
        binConfiguration.setBit3(true);
        binConfiguration.setBit4(false);
        binConfiguration.setBit5(true);
        binConfiguration.setBit6(false);
        binConfiguration.setBit7(true);

        rangeBin = new RangeBin();
        rangeBin.setBinConfiguration(binConfiguration);
        rangeBin.setCode(1);
        rangeBin.setBrandId(1);
        rangeBin.setStartRange(600012);
        rangeBin.setEndRange(600020);
        rangeBin.setFlows("00010002");
        rangeBin.setPanLength(12);
        rangeBin.setProductId(2);
        rangeBin.setName("Bin test");
    }

    @Test
    void test() throws TableConfigurationException, IllegalAccessException {
        String config = TableConfigurationService.generateConfigurationString(rangeBin);
        Assertions.assertEquals("020430001600012600020120000000000102080001000255",  config);
    }
}