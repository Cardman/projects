package code.maths.litteral;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class OperationsSequence {
    private static final char NEG_BOOL_CHAR = '!';

    private String fctName = "";

    private boolean useFct;

    private int priority;

    private StrTypes values;

    private StrTypes operators;

    private Delimiters delimiter;

    private ConstType constType;

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
        if (priority == MathResolver.EQ_PRIO && StringUtil.quickEq(operators.firstValue(), Character.toString(NEG_BOOL_CHAR))) {
            priority = MathResolver.BAD_PRIO;
            return;
        }
        feedValues(_string);
    }

    private void feedValues(String _string) {
        firstOperand(_string);
        int endValuePart_ = operators.firstKey();
        if (priority == MathResolver.FCT_OPER_PRIO) {
            int afterLastPar_ = operators.lastKey()+1;
            if (!_string.substring(afterLastPar_).trim().isEmpty()) {
                operators.clear();
                operators.addEntry(afterLastPar_, "");
                priority = MathResolver.BAD_PRIO;
                return;
            }
        }
        if (priority == MathResolver.FCT_OPER_PRIO && operators.size() == 2) {
            int beginValuePart_ = endValuePart_ + operators.firstValue().length();
            endValuePart_ = operators.getKey(IndexConstants.SECOND_INDEX);
            String str_ = _string.substring(beginValuePart_, endValuePart_);
            if (!str_.isEmpty()) {
                values.addEntry(beginValuePart_, str_);
            }
            return;
        }
        int i_ = IndexConstants.SECOND_INDEX;
        int nbKeys_ = operators.size();
        while (i_ < nbKeys_) {
            int beginValuePart_ = endValuePart_ + operators.getValue(i_ - 1).length();
            endValuePart_ = operators.getKey(i_);
            String str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(beginValuePart_, str_);
            i_++;
        }
        if (priority != MathResolver.FCT_OPER_PRIO) {
            int beginValuePart_ = endValuePart_ + operators.lastValue().length();
            String str_ = _string.substring(beginValuePart_);
            values.addEntry(beginValuePart_, str_);
        }
    }

    private void firstOperand(String _string) {
        if (priority != MathResolver.UNARY_PRIO && !(fctName.trim().isEmpty() && useFct)) {
            //not unary priority, not identity priority
            int beginValuePart_ = IndexConstants.FIRST_INDEX;
            int endValuePart_ = operators.firstKey();
            String str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(beginValuePart_, str_);
        }
    }

    public String getFctName() {
        return fctName;
    }

    public void setFctName(String _fctName) {
        fctName = _fctName;
    }

    public void setUseFct(boolean _useFct) {
        useFct = _useFct;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int _priority) {
        priority = _priority;
    }

    public StrTypes getValues() {
        return values;
    }

    public StrTypes getOperators() {
        return operators;
    }

    public void setOperators(StrTypes _operators) {
        operators = _operators;
    }

    public Delimiters getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(Delimiters _delimiter) {
        delimiter = _delimiter;
    }

    public ConstType getConstType() {
        return constType;
    }

    public void setConstType(ConstType _constType) {
        constType = _constType;
    }

    public int getIndexCst() {
        return indexCst;
    }

    public void setIndexCst(int _indexCst) {
        indexCst = _indexCst;
    }
}
