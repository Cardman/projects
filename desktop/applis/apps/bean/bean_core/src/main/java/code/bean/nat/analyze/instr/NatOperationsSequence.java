package code.bean.nat.analyze.instr;
import code.expressionlanguage.analyze.instr.Delimiters;
import code.expressionlanguage.common.*;
import code.maths.litteralcom.StrTypes;
import code.util.core.IndexConstants;

public final class NatOperationsSequence {
    private static final char DOT_VAR = '.';
    private static final char PAR = '(';
    private ConstType constType = ConstType.NOTHING;

    private String fctName = "";

    private int priority;

    private StrTypes values;

    private StrTypes operators;

    private Delimiters delimiter;

    private int offset;

    public void setValue(String _string, int _offset) {
        values = new StrTypes();
        values.addEntry(IndexConstants.FIRST_INDEX, _string);
        offset = _offset;
    }

    public void setupValues(String _string) {
        values = new StrTypes();
        String op_ = operators.firstValue();
        boolean pureDot_ = op_.charAt(0) == DOT_VAR;
        int beginValuePart_ = IndexConstants.FIRST_INDEX;
        int endValuePart_ = operators.firstKey();
        String str_;
        if (priority != NatElResolver.UNARY_PRIO) {
            //not unary priority, not identity priority
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(beginValuePart_, str_);
        }
        if (pureDot_) {
            beginValuePart_ = endValuePart_ + operators.lastValue().length();
            str_ = _string.substring(beginValuePart_);
            values.addEntry(beginValuePart_, str_);
            return;
        }
        if (priority == NatElResolver.FCT_OPER_PRIO) {
            if (operators.size() == 2) {
                beginValuePart_ = endValuePart_ + operators.firstValue().length();
                endValuePart_ = operators.getKey(IndexConstants.SECOND_INDEX);
                str_ = _string.substring(beginValuePart_, endValuePart_);
                addValueIfNotEmpty(beginValuePart_, str_);
                return;
            }
            int i_ = IndexConstants.SECOND_INDEX;
            int nbKeys_ = operators.size();
            while (i_ < nbKeys_) {
                beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
                endValuePart_ = operators.getKey(i_);
                str_ = _string.substring(beginValuePart_, endValuePart_);
                values.addEntry(beginValuePart_, str_);
                i_++;
            }
            return;
        }
        beginValuePart_ = endValuePart_ + operators.lastValue().length();
        str_ = _string.substring(beginValuePart_);
        values.addEntry(beginValuePart_, str_);
    }

    private void addValueIfNotEmpty(int _beginValuePart,
                                    String _str) {
        if (_str.trim().isEmpty()) {
            return;
        }
        values.addEntry(_beginValuePart, _str);
    }

    public ConstType getConstType() {
        return constType;
    }

    public void setConstType(ConstType _constType) {
        constType = _constType;
    }

    public String getFctName() {
        return fctName;
    }

    public void setFctName(String _fctName) {
        fctName = _fctName;
    }

    public boolean isCallDbArray() {
        return isCall();
    }

    public boolean isCall() {
        String str_ = operators.firstValue();
        return str_.charAt(0) == PAR;
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

    public int getOffset() {
        return offset;
    }

}
