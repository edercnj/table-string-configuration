package com.br.edercnj.tablestringconfiguration.exception;

public class TableConfigurationNotFound extends TableConfigurationException {

    public TableConfigurationNotFound() {
        super("TableConfiguration annotation not found");
    }
}
