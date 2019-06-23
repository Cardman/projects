package code.maths.litteral;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;

public final class EvolvedNumString {

    private StringBuilder numericString;
    private Argument arg;

    EvolvedNumString(String _chaineNumerique, StringMap<String> _vars) {
        if (_vars.isEmpty()) {
            numericString = new StringBuilder(_chaineNumerique);
        } else {
            numericString = new StringBuilder(StringList.replaceWordsJoin(_chaineNumerique, _vars));
        }
    }

    public void evaluateExp(boolean _checkSyntax) {
        arg = MathUtil.processEl(numericString.toString(), _checkSyntax, new StringMap<String>());
    }

    public Rate getResult() {
        return (Rate) arg.getObject();
    }

    public String beforeEvaluated() {
        return numericString.toString();
    }

    public boolean isValid() {
        return arg.getArgClass() == MathType.RATE;
    }
}
