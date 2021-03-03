package code.maths.litteral;
import code.maths.Rate;
import code.util.StringMap;

public final class EvolvedNumString {

    private final StringBuilder numericString;
    private MbArgument arg;

    EvolvedNumString(String _chaineNumerique, StringMap<String> _vars) {
        if (_vars.isEmpty()) {
            numericString = new StringBuilder(_chaineNumerique);
        } else {
            numericString = new StringBuilder(MathExpUtil.replaceWordsJoin(_chaineNumerique, _vars));
        }
    }

    public void evaluateExp(boolean _checkSyntax) {
        arg = MathUtil.processEl(numericString.toString(), _checkSyntax, new StringMap<String>());
    }

    public Rate getResult() {
        return arg.getRateVal();
    }

    public String beforeEvaluated() {
        return numericString.toString();
    }

    public boolean isValid() {
        return arg.getArgClass() == MathType.RATE;
    }
}
