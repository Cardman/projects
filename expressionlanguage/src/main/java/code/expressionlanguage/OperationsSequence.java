package code.expressionlanguage;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;

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

    private String extractType = "";

    private int countArrays;
    private Numbers<Integer> errorParts = new Numbers<Integer>();
    private boolean instance;
    public void setValue(String _string, int _offset) {
        values = new NatTreeMap<Integer,String>();
        values.put((int)CustList.FIRST_INDEX, _string);
        offset = _offset;
    }

    public void setupValues(String _string, boolean _is, boolean _annot, boolean _instance, Numbers<Integer> _nb, Numbers<Integer> _esc) {
        values = new NatTreeMap<Integer,String>();
        instance = _instance;
        if (operators.isEmpty() && !_annot) {
            priority = ElResolver.BAD_PRIO;
            return;
        }
        if (operators.isEmpty() && _annot) {
            priority = ElResolver.FCT_OPER_PRIO;
            values.put((int)CustList.FIRST_INDEX, _string);
            fctName = _string;
            operators.put(_string.length(), "");
            return;
        }
        String op_ = operators.firstValue();
        boolean pureDot_ = false;
        boolean initArrayDim_ = false;
        if (!op_.isEmpty()) {
            if (_annot && op_.charAt(0) == ARR_ANNOT) {
                int beginValuePart_ = CustList.FIRST_INDEX;
                int endValuePart_ = operators.firstKey();
                String str_ = _string.substring(beginValuePart_, endValuePart_);
                str_ = transformToSpaces(str_, beginValuePart_, _esc);
                values.put(beginValuePart_, str_);
                int i_ = CustList.SECOND_INDEX;
                int nbKeys_ = operators.size();
                if (nbKeys_ == 2) {
                    beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
                    endValuePart_ = operators.getKey(i_);
                    str_ = _string.substring(beginValuePart_, endValuePart_);
                    if (!str_.trim().isEmpty()) {
                        str_ = transformToSpaces(str_, beginValuePart_, _esc);
                        values.put(beginValuePart_, str_);
                    }
                    return;
                }
                while (i_ < nbKeys_) {
                    beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
                    endValuePart_ = operators.getKey(i_);
                    str_ = _string.substring(beginValuePart_, endValuePart_);
                    str_ = transformToSpaces(str_, beginValuePart_, _esc);
                    values.put(beginValuePart_, str_);
                    i_++;
                }
                return;
            }
            if (op_.charAt(0) != DOT_VAR) {
                if (priority == ElResolver.FCT_OPER_PRIO && !declaring) {
                    int afterLastPar_ = operators.lastKey()+1;
                    StringBuilder filter_ = new StringBuilder(_string);
                    for (int i : _nb.getReverse()) {
                        if (i < afterLastPar_) {
                            break;
                        }
                        filter_.deleteCharAt(i);
                        countArrays++;
                    }
                    countArrays/=2;
                    if (op_.charAt(0) == ARR && _instance) {
                        initArrayDim_ = true;
                    }
                    if (!filter_.substring(afterLastPar_).trim().isEmpty()) {
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
            str_ = transformToSpaces(str_, beginValuePart_, _esc);
            values.put(beginValuePart_, str_);
            int i_ = CustList.SECOND_INDEX;
            int nbKeys_ = operators.size();
            while (i_ < nbKeys_) {
                beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
                endValuePart_ = operators.getKey(i_);
                str_ = _string.substring(beginValuePart_, endValuePart_);
                str_ = transformToSpaces(str_, beginValuePart_, _esc);
                values.put(beginValuePart_, str_);
                i_++;
            }
            beginValuePart_ = endValuePart_ + operators.lastValue().length();
            str_ = _string.substring(beginValuePart_);
            str_ = transformToSpaces(str_, beginValuePart_, _esc);
            values.put(beginValuePart_, str_);
            return;
        }
        if (priority == ElResolver.POST_INCR_PRIO) {
            str_ = _string.substring(beginValuePart_, endValuePart_);
            str_ = transformToSpaces(str_, beginValuePart_, _esc);
            values.put((int)CustList.FIRST_INDEX, str_);
            return;
        }
        if (_is && priority == ElResolver.CMP_PRIO) {
            //instanceof operator
            instanceTest = true;
            str_ = _string.substring(beginValuePart_, endValuePart_);
            str_ = transformToSpaces(str_, beginValuePart_, _esc);
            values.put((int)CustList.FIRST_INDEX, str_);
            return;
        }
        if (priority != ElResolver.UNARY_PRIO && !(fctName.trim().isEmpty() && useFct)) {
            //not unary priority, not identity priority
            str_ = _string.substring(beginValuePart_, endValuePart_);
            str_ = transformToSpaces(str_, beginValuePart_, _esc);
            values.put(beginValuePart_, str_);
        } else if (priority != ElResolver.UNARY_PRIO){
            //fctName.trim().isEmpty() && useFct
            str_ = _string.substring(beginValuePart_, endValuePart_);
            if (!str_.trim().isEmpty()) {
                //let analyze this
                str_ = transformToSpaces(str_, beginValuePart_, _esc);
                values.put(beginValuePart_, str_);
            }
        }
        if (useFct && operators.size() == 2) {
            beginValuePart_ = endValuePart_ + operators.firstValue().length();
            endValuePart_ = operators.getKey(CustList.SECOND_INDEX);
            str_ = _string.substring(beginValuePart_, endValuePart_);
            if (!str_.trim().isEmpty()) {
                str_ = transformToSpaces(str_, beginValuePart_, _esc);
                values.put(beginValuePart_, str_);
            }
            return;
        }
        if (pureDot_) {
            beginValuePart_ = endValuePart_ + operators.lastValue().length();
            str_ = _string.substring(beginValuePart_);
            str_ = transformToSpaces(str_, beginValuePart_, _esc);
            values.put(beginValuePart_, str_);
            return;
        }
        if (initArrayDim_) {
            int i_ = CustList.SECOND_INDEX;
            int nbKeys_ = operators.size();
            while (i_ < nbKeys_) {
                beginValuePart_ = operators.getKey(i_ - 1) + operators.getValue(i_ - 1).length();
                endValuePart_ = operators.getKey(i_);
                if (i_ + 1 < nbKeys_) {
                    int b_ = operators.getKey(i_) + operators.getValue(i_).length();
                    int e_ = operators.getKey(i_ + 1);
                    String err_ = _string.substring(b_, e_);
                    if (!err_.trim().isEmpty()) {
                        errorParts.add(b_);
                    }
                }
                str_ = _string.substring(beginValuePart_, endValuePart_);
                str_ = transformToSpaces(str_, beginValuePart_, _esc);
                values.put(beginValuePart_, str_);
                i_++;
                i_++;
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
                str_ = transformToSpaces(str_, beginValuePart_, _esc);
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
            str_ = transformToSpaces(str_, beginValuePart_, _esc);
            values.put(beginValuePart_, str_);
            i_++;
        }
        beginValuePart_ = endValuePart_ + operators.lastValue().length();
        str_ = _string.substring(beginValuePart_);
        str_ = transformToSpaces(str_, beginValuePart_, _esc);
        values.put(beginValuePart_, str_);
    }
    static String transformToSpaces(String _sub, int _offsetLocal, Numbers<Integer> _esc) {
        StringBuilder str_ = new StringBuilder(_sub);
        int len_ = _sub.length();
        for (int i: _esc) {
            if (i<_offsetLocal) {
                continue;
            }
            if (i-_offsetLocal >= len_) {
                continue;
            }
            str_.setCharAt(i-_offsetLocal, ' ');
        }
        return str_.toString();
    }

    public Numbers<Integer> getErrorParts() {
        return errorParts;
    }
    public int getCountArrays() {
        return countArrays;
    }
    public boolean isError() {
        return priority == ElResolver.BAD_PRIO || !errorParts.isEmpty();
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

    public boolean isCallDbArray() {
        if (priority != ElResolver.FCT_OPER_PRIO) {
            return false;
        }
        String str_ = operators.firstValue();
        if (str_.isEmpty()) {
            return false;
        }
        return str_.charAt(0) == PAR || instance;
    }

    public boolean isInstance() {
        return instance;
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

    public String getExtractType() {
        return extractType;
    }

    public void setExtractType(String _extractType) {
        extractType = _extractType;
    }

}
