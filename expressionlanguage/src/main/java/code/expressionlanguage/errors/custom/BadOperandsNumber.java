package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public class BadOperandsNumber extends FoundErrorInterpret {

    private int operandsNumber;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes), SEP_INFO, Integer.toString(operandsNumber));
    }

    public int getOperandsNumber() {
        return operandsNumber;
    }

    public void setOperandsNumber(int _operandsNumber) {
        operandsNumber = _operandsNumber;
    }
}
