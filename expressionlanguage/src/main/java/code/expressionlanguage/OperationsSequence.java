package code.expressionlanguage;
import code.util.CustList;
import code.util.NatTreeMap;

public final class OperationsSequence {
    private static final char DOT_VAR = '.';
    private static final char ARR = '[';
    private static final char PAR = '(';
    private static final char ARR_ANNOT = '{';
    private ConstType constType = ConstType.NOTHING;

    private NumberInfos nbInfos;

    private String fctName = "";

    private boolean useFct;

    private int priority;

    private NatTreeMap<Integer,String> values;

    private NatTreeMap<Integer,String> operators;

    private Delimiters delimiter;

    private int offset;

    private boolean instanceTest;

    private boolean declaring;

    public void setValue(String _string, int _offset) {
        values = new NatTreeMap<Integer,String>();
        values.put((int)CustList.FIRST_INDEX, _string);
        offset = _offset;
    }

    public void setupValues(String _string, boolean _is, boolean _annot) {
        values = new NatTreeMap<Integer,String>();
        if (operators.isEmpty() && !_annot) {
            priority = ElResolver.BAD_PRIO;
            return;
        }
        if (operators.isEmpty() && _annot) {
        	priority = ElResolver.FCT_OPER_PRIO;
        	values.put((int)CustList.FIRST_INDEX, _string);
        	operators.put(_string.length(), "(");
        	operators.put(_string.length()+1, ")");
        	return;
        }
        String op_ = operators.firstValue();
        boolean pureDot_ = false;
        if (!op_.isEmpty()) {
            if (_annot && op_.charAt(0) == ARR_ANNOT) {
            	int i_ = CustList.SECOND_INDEX;
                int nbKeys_ = operators.size();
                int beginValuePart_ = CustList.FIRST_INDEX;
                int endValuePart_ = operators.firstKey();
                while (i_ < nbKeys_) {
                    beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
                    endValuePart_ = operators.getKey(i_);
                    String str_ = _string.substring(beginValuePart_, endValuePart_);
                    values.put(beginValuePart_, str_);
                    i_++;
                }
                return;
            }
            if (op_.charAt(0) != DOT_VAR) {
                if (priority == ElResolver.FCT_OPER_PRIO && !declaring) {
                    int afterLastPar_ = operators.lastKey()+1;
                    if (!_string.substring(afterLastPar_).trim().isEmpty()) {
                        operators.clear();
                        operators.put(afterLastPar_, "");
                        priority = ElResolver.BAD_PRIO;
                        return;
                    }
                }
            } else {
                pureDot_ = true;
            }
        } else {
            pureDot_ = true;
        }
        int beginValuePart_ = CustList.FIRST_INDEX;
        int endValuePart_ = operators.firstKey();
        String str_;
        if (declaring) {
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.put(beginValuePart_, str_);
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
            return;
        }
        if (priority == ElResolver.POST_INCR_PRIO) {
            values.put((int)CustList.FIRST_INDEX, _string.substring(beginValuePart_, endValuePart_));
            return;
        } else if (_is && priority == ElResolver.CMP_PRIO) {
            //instanceof operator
            instanceTest = true;
            values.put((int)CustList.FIRST_INDEX, _string.substring(beginValuePart_, endValuePart_));
            return;
        } else if (priority != ElResolver.UNARY_PRIO && !(fctName.trim().isEmpty() && useFct)) {
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
        if (pureDot_) {
            beginValuePart_ = endValuePart_ + operators.lastValue().length();
            str_ = _string.substring(beginValuePart_);
            values.put(beginValuePart_, str_);
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

    public boolean isError() {
        return priority == ElResolver.BAD_PRIO;
    }

    public boolean isInstanceTest() {
        return instanceTest;
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

    public boolean isCall() {
        if (priority != ElResolver.FCT_OPER_PRIO) {
            return false;
        }
        String str_ = operators.firstValue();
        if (str_.isEmpty()) {
            return false;
        }
        return str_.charAt(0) == PAR;
    }

    public boolean isArray() {
        if (priority != ElResolver.FCT_OPER_PRIO) {
            return false;
        }
        String str_ = operators.firstValue();
        if (str_.isEmpty()) {
            return false;
        }
        return str_.charAt(0) == ARR;
    }

    public boolean isDot() {
        return priority == ElResolver.FCT_OPER_PRIO;
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

    public boolean isDeclaring() {
        return declaring;
    }

    public void setDeclaring(boolean _declaring) {
        declaring = _declaring;
    }
}
