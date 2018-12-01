package code.expressionlanguage.errors.custom;

import code.util.StringList;

public class BadOperandsNumber extends FoundErrorInterpret {

    private int operandsNumber;

    @Override
    public String display() {
        return StringList.concat(super.display(), SEP_INFO, Integer.toString(operandsNumber));
    }

    public int getOperandsNumber() {
        return operandsNumber;
    }

    public void setOperandsNumber(int _operandsNumber) {
        operandsNumber = _operandsNumber;
    }
}
