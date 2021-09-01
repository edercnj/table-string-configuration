package com.br.edercnj.tablestringconfiguration.exception;

public class TableConfigurationClassNotFound extends TableConfigurationException {

    public TableConfigurationClassNotFound() {
        super("Class is not a Table Configuration");
    }
}
