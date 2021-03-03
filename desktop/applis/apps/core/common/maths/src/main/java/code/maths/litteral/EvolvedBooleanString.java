package code.maths.litteral;
import code.util.StringMap;

public final class EvolvedBooleanString {

    private final StringBuilder numericString;
    private MbArgument arg;

    EvolvedBooleanString(String _chaineNumerique, StringMap<String> _vars) {
        if (_vars.isEmpty()) {
            numericString = new StringBuilder(_chaineNumerique);
        } else {
            numericString = new StringBuilder(MathExpUtil.replaceWordsJoin(_chaineNumerique, _vars));
        }
    }

    public void evaluateExp(boolean _checkSyntax) {
        arg = MathUtil.processEl(numericString.toString(), _checkSyntax, new StringMap<String>());
    }


    public boolean getResult() {
        return arg.isBoolVal();
    }

    public String beforeEvaluated() {
        return numericString.toString();
    }

    public boolean isValid() {
        return arg.getArgClass() == MathType.BOOLEAN;
    }
}
