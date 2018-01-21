package code.expressionlanguage;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

public final class OperationsSequence {
    private static final char NEG_BOOL_CHAR = '!';
    private static final char PAR_RIGHT = ')';
    private static final char ARR_RIGHT = ']';

    private ConstType constType = ConstType.NOTHING;

    private NumberInfos nbInfos;

    private String fctName = "";

    private boolean useFct;

    private int priority;

    private NatTreeMap<Integer,String> values;

    private NatTreeMap<Integer,String> operators;

    private Delimiters delimiter;

    private int offset;

    public void setValue(String _string, int _offset) {
        values = new NatTreeMap<Integer,String>();
        values.put((int)CustList.FIRST_INDEX, _string);
        offset = _offset;
    }

    public void setupValues(String _string) {
        values = new NatTreeMap<Integer,String>();
        if (operators.isEmpty()) {
            values.put((int)CustList.FIRST_INDEX, _string);
            return;
        }
        if (priority == ElResolver.EQ_PRIO && StringList.quickEq(operators.firstValue(), String.valueOf(NEG_BOOL_CHAR))) {
            priority = ElResolver.BAD_PRIO;
        }
        if (priority == ElResolver.FCT_OPER_PRIO && !_string.substring(_string.lastIndexOf(PAR_RIGHT)+1).trim().isEmpty()) {
            priority = ElResolver.BAD_PRIO;
        }
        if (priority == ElResolver.ARR_OPER_PRIO && !_string.substring(_string.lastIndexOf(ARR_RIGHT)+1).trim().isEmpty()) {
            priority = ElResolver.BAD_PRIO;
        }
        int beginValuePart_ = CustList.FIRST_INDEX;
        int endValuePart_ = operators.firstKey();
        String str_;
        if (priority != ElResolver.UNARY_PRIO && !(fctName.trim().isEmpty() && useFct)) {
            //not unary priority, not identity priority
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.put(beginValuePart_, str_);
        } else if (priority != ElResolver.UNARY_PRIO){
            //fctName.trim().isEmpty() && useFct
            str_ = _string.substring(beginValuePart_, endValuePart_);
            if (!str_.trim().isEmpty()) {
                //let analyze this
                values.put(beginValuePart_, str_);
            }
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
            beginValuePart_ = endValuePart_ + operators.getValue(0).length();
            endValuePart_ = operators.getKey(1);
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.put(beginValuePart_, str_);
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
        beginValuePart_ = endValuePart_ + operators.lastValue().length();
        str_ = _string.substring(beginValuePart_);
        values.put(beginValuePart_, str_);
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

    public int getOffset() {
        return offset;
    }

    public NumberInfos getNbInfos() {
        return nbInfos;
    }

    public void setNbInfos(NumberInfos _nbInfos) {
        nbInfos = _nbInfos;
    }
}
