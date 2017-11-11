package code.expressionlanguage;
import code.util.CustList;
import code.util.NatTreeMap;

public final class OperationsSequence {

    private static final char ARR_RIGHT = ']';

    private String fctName = "";

    private boolean useFct;

    private int priority;

    private NatTreeMap<Integer,String> values;

    private NatTreeMap<Integer,String> operators;

    private Delimiters delimiter;

    public void setupValues(String _string) {
        values = new NatTreeMap<Integer,String>();
        if (operators.isEmpty()) {
            values.put(CustList.FIRST_INDEX, _string);
            return;
        }
        int beginValuePart_ = CustList.FIRST_INDEX;
        int endValuePart_ = operators.firstKey();
        String str_;
        if (priority != ElResolver.UNARY_PRIO && !(fctName.isEmpty() && useFct)) {
            //not unary priority, not identity priority
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.put(beginValuePart_, str_);
        }
        if (useFct && operators.size() == 2) {
            beginValuePart_ = endValuePart_ + operators.firstValue().length();
            endValuePart_ = operators.getKey(CustList.SECOND_INDEX);
            str_ = _string.substring(beginValuePart_, endValuePart_);
            if (!str_.trim().isEmpty()) {
                values.put(beginValuePart_, str_);
            }
            return;
        }
        if (priority == ElResolver.FCT_OPER_PRIO) {
            int i_ = CustList.SECOND_INDEX;
            int nbKeys_ = operators.size();
            while (i_ < nbKeys_) {
                beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
                endValuePart_ = operators.getKey(i_);
                str_ = _string.substring(beginValuePart_, endValuePart_);
                values.put(beginValuePart_, str_);
                i_++;
            }
            return;
        }
        if (priority == ElResolver.ARR_OPER_PRIO) {
            int i_ = CustList.SECOND_INDEX;
            int nbKeys_ = operators.size();
            while (i_ < nbKeys_) {
                beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
                endValuePart_ = operators.getKey(i_);
                str_ = _string.substring(beginValuePart_, endValuePart_);
                if (_string.charAt(endValuePart_) == ARR_RIGHT) {
                    values.put(beginValuePart_, str_);
                }
                i_++;
            }
            return;
        }
        int i_ = CustList.SECOND_INDEX;
        int nbKeys_ = operators.size();
        while (i_ < nbKeys_) {
            beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
            endValuePart_ = operators.getKey(i_);
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.put(beginValuePart_, str_);
            i_++;
        }
        beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
        str_ = _string.substring(beginValuePart_);
        values.put(beginValuePart_, str_);
    }

    public String getFctName() {
        return fctName;
    }

    public void setFctName(String _fctName) {
        fctName = _fctName;
    }

    public boolean isUseFct() {
        return useFct;
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

    public NatTreeMap<Integer, String> getValues() {
        return values;
    }

    public void setValues(NatTreeMap<Integer, String> _values) {
        values = _values;
    }

    public NatTreeMap<Integer, String> getOperators() {
        return operators;
    }

    public void setOperators(NatTreeMap<Integer, String> _operators) {
        operators = _operators;
    }

    public Delimiters getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(Delimiters _delimiter) {
        delimiter = _delimiter;
    }

}
