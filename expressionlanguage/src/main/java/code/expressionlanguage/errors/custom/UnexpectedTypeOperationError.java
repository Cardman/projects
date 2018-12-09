package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.StringList;

public final class UnexpectedTypeOperationError extends FoundErrorInterpret {

    private StringList operands;
    private String expectedResult;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),SEP_INFO,operands.join(";"),SEP_INFO,expectedResult);
    }

    public StringList getOperands() {
        return operands;
    }

    public void setOperands(StringList _operands) {
        operands = _operands;
    }

    public void setOperands(ClassArgumentMatching... _operands) {
        operands = new StringList();
        for (ClassArgumentMatching c: _operands) {
            operands.add(c.getName());
        }
    }
    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String _expectedResult) {
        expectedResult = _expectedResult;
    }
}
