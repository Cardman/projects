package code.maths.litteral;
import code.util.CustList;
import code.util.NatTreeMap;

public final class OperationsSequence {

    private String fctName = "";

    private boolean useFct;

    private int priority;

    private NatTreeMap<Integer,String> values;

    private NatTreeMap<Integer,String> operators;

    private boolean boolvalue;

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
        if (priority != MathResolver.UNARY_PRIO && !(fctName.isEmpty() && useFct)) {
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
        if (priority != MathResolver.FCT_OPER_PRIO) {
            values.put(beginValuePart_, str_);
        }
    }

    public void addOffset(int _offset) {
        int len_;
        len_ = values.size();
        for (int i = len_ - 1; i >= CustList.FIRST_INDEX; i--) {
            values.move(values.getKey(i), values.getKey(i)+_offset);
        }
        len_ = operators.size();
        for (int i = len_ - 1; i >= CustList.FIRST_INDEX; i--) {
            operators.move(operators.getKey(i), operators.getKey(i)+_offset);
        }
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

    public boolean isBoolvalue() {
        return boolvalue;
    }

    public void setBoolvalue(boolean _boolvalue) {
        boolvalue = _boolvalue;
    }

    public Delimiters getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(Delimiters _delimiter) {
        delimiter = _delimiter;
    }

}
