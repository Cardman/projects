package code.maths.litteral;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.NumericableString;

final class EvolvedNumString implements NumericableString<Rate> {

    private StringBuilder numericString;
    private Argument arg;

    EvolvedNumString(String _chaineNumerique, StringMap<String> _vars) {
        if (_vars.isEmpty()) {
            numericString = new StringBuilder(_chaineNumerique);
        } else {
            numericString = new StringBuilder(StringList.replaceWordsJoin(_chaineNumerique, _vars));
        }
    }
    @Override
    public void evaluateExp(boolean _checkSyntax) {
        arg = MathUtil.processEl(numericString.toString(), 0, _checkSyntax, new StringMap<String>());
    }

    @Override
    public Rate getResult() {
        return (Rate) arg.getObject();
    }

    @Override
    public String beforeEvaluated() {
        return numericString.toString();
    }

    @Override
    public String toString() {
        return arg.toString();
    }

    @Override
    public boolean isValid() {
        return arg.getArgClass() == MathType.RATE;
    }
}
