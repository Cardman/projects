package code.expressionlanguage.methods.util;

import code.util.StringList;

public final class UnexpectedTypeOperationError extends FoundErrorInterpret {

    private StringList operands;
    private String expectedResult;

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,operands.join(";"),SEP_INFO,expectedResult);
    }

    public StringList getOperands() {
        return operands;
    }

    public void setOperands(StringList _operands) {
        operands = _operands;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String _expectedResult) {
        expectedResult = _expectedResult;
    }
}
