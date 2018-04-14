package code.expressionlanguage.methods.util;

import code.util.StringList;

public class BadFormatNumber extends FoundErrorInterpret {

    private static final String BAD_FORMAT_NUMBER= "bad format number";

    private String number;

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,BAD_FORMAT_NUMBER,SEP_KEY_VAL,number);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String _number) {
        number = _number;
    }
}
