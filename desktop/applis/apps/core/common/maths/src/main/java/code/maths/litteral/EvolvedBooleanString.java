package code.maths.litteral;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class EvolvedBooleanString {

    private StringBuilder numericString;
    private Argument arg;

    EvolvedBooleanString(String _chaineNumerique, StringMap<String> _vars) {
        if (_vars.isEmpty()) {
            numericString = new StringBuilder(_chaineNumerique);
        } else {
            numericString = new StringBuilder(StringUtil.replaceWordsJoin(_chaineNumerique, _vars));
        }
    }

    public void evaluateExp(boolean _checkSyntax) {
        arg = MathUtil.processEl(numericString.toString(), _checkSyntax, new StringMap<String>());
    }


    public Boolean getResult() {
        return (Boolean) arg.getObject();
    }

    public String beforeEvaluated() {
        return numericString.toString();
    }

    public boolean isValid() {
        return arg.getArgClass() == MathType.BOOLEAN;
    }
}
