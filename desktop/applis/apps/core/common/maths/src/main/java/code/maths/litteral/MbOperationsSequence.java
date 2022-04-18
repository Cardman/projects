package code.maths.litteral;
import code.maths.litteralcom.MatCommonCst;
import code.maths.litteralcom.MatConstType;
import code.maths.litteralcom.StrTypes;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class MbOperationsSequence {
    private static final char NEG_BOOL_CHAR = '!';

    private String fctName = "";

    private int priority;

    private StrTypes values;

    private StrTypes operators;

    private MatConstType constType;

    private int indexCst;

    public void setupValue(String _string) {
        values = new StrTypes();
        values.addEntry(IndexConstants.FIRST_INDEX, _string);
    }
    public void setupValues(String _string) {
        values = new StrTypes();
        if (operators.isEmpty()) {
            values.addEntry(IndexConstants.FIRST_INDEX, _string);
            return;
        }
        if (priority == MatCommonCst.EQ_PRIO && StringUtil.quickEq(operators.firstValue(), Character.toString(NEG_BOOL_CHAR))) {
            priority = MatCommonCst.BAD_PRIO;
            return;
        }
        feedValues(_string);
    }

    private void feedValues(String _string) {
        firstOperand(_string);
        if (priority == MatCommonCst.FCT_OPER_PRIO) {
            int afterLastPar_ = operators.lastKey()+1;
            if (!_string.substring(afterLastPar_).trim().isEmpty()) {
                operators.clear();
                operators.addEntry(afterLastPar_, "");
                priority = MatCommonCst.BAD_PRIO;
                return;
            }
            if (operators.size() == 2) {
                StrTypes.addNotEmpty(_string,operators,values);
                return;
            }
            loop(_string);
            return;
        }
        loop(_string);
        lastPart(_string);
    }

    private void loop(String _string) {
        StrTypes.loopArgs(_string,operators,values);
    }

    private void lastPart(String _string) {
        StrTypes.lastPart(_string,operators,values);
    }

    private void firstOperand(String _string) {
        if (priority != MatCommonCst.UNARY_PRIO) {
            //not unary priority, not identity priority
            StrTypes.firstPartNotUnary(_string,operators,values);
        }
    }

    public void ad() {
        if (getOperators().isEmpty()) {
            return;
        }
        if (getPriority() == MatCommonCst.FCT_OPER_PRIO) {
            StrTypes vs_ = getValues();
            vs_.remove(0);
        }
    }
    public String getFctName() {
        return fctName;
    }

    public void setFctName(String _fctName) {
        fctName = _fctName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int _priority) {
        priority = _priority;
    }

    public StrTypes getOperators() {
        return operators;
    }

    public void setOperators(StrTypes _operators) {
        operators = _operators;
    }

    public StrTypes getValues() {
        return values;
    }

    public MatConstType getConstType() {
        return constType;
    }

    public void setConstType(MatConstType _constType) {
        constType = _constType;
    }

    public int getIndexCst() {
        return indexCst;
    }

    public void setIndexCst(int _indexCst) {
        indexCst = _indexCst;
    }
}
