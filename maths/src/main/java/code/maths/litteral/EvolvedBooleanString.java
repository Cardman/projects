package code.maths.litteral;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.NumericableString;

final class EvolvedBooleanString implements NumericableString<Boolean> {

    private StringBuilder numericString;
    private Argument arg;

    EvolvedBooleanString(String _chaineNumerique, StringMap<String> _vars) {
        if (_vars.isEmpty()) {
            numericString = new StringBuilder(_chaineNumerique);
        } else {
            numericString = new StringBuilder(StringList.replaceWordsJoin(_chaineNumerique, _vars));
        }
    }
    @Override
    public void evaluateExp(boolean _checkSyntax) {
        arg = MathUtil.processEl(numericString.toString(), _checkSyntax, new StringMap<String>());
    }

    @Override
    public Boolean getResult() {
        return (Boolean) arg.getObject();
    }

    @Override
    public String beforeEvaluated() {
        return numericString.toString();
    }

    @Override
    public boolean isValid() {
        return arg.getArgClass() == MathType.BOOLEAN;
    }
}
