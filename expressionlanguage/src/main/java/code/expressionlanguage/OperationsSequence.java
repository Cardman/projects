package code.expressionlanguage;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatTreeMap;

public final class OperationsSequence {

    private String fctName = "";

    private boolean useFct;

    private int priority;

    private NatTreeMap<Integer,String> values;

    private NatTreeMap<Integer,String> operators;

    private Delimiters delimiter;

    private boolean firstOpt;

    public void setupValues(String _string, boolean _unary, char _varag) {
        values = new NatTreeMap<Integer,String>();
        if (operators.isEmpty()) {
            if (firstOpt) {
                int i_ = _string.length() - 1;
                while (i_ >= 0) {
                    if (_string.charAt(i_) == _varag) {
                        break;
                    }
                    i_--;
                }
                values.put(CustList.FIRST_INDEX, _string.substring(0, i_));
                return;
            }
            values.put(CustList.FIRST_INDEX, _string);
            return;
        }
        if (_unary) {
            int firstKey_ = operators.firstKey();
            String value_ = operators.getVal(firstKey_);
            operators.clear();
            operators.put(firstKey_, value_);
        }
        int beginValuePart_ = CustList.FIRST_INDEX;
        int endValuePart_ = operators.firstKey();
        String str_;
        if (beginValuePart_ < endValuePart_) {
            str_ = _string.substring(beginValuePart_, endValuePart_);
            if (!str_.trim().isEmpty()) {
                values.put(beginValuePart_, str_);
            }
        }
        int i_ = CustList.SECOND_INDEX;
        int nbKeys_ = operators.size();
        while (i_ < nbKeys_) {
            beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
            endValuePart_ = operators.getKey(i_);
            str_ = _string.substring(beginValuePart_, endValuePart_);
            if (!str_.trim().isEmpty()) {
                values.put(beginValuePart_, str_);
            }
            i_++;
        }
        beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
        str_ = _string.substring(beginValuePart_);
        if (!str_.trim().isEmpty()) {
            values.put(beginValuePart_, str_);
        }
        if (firstOpt) {
            EntryCust<Integer, String> e_ = values.lastEntry();
            String lastValue_ = e_.getValue();
            int ilast_ = lastValue_.length() - 1;
            while (ilast_ >= 0) {
                if (lastValue_.charAt(ilast_) == _varag) {
                    break;
                }
                ilast_--;
            }
            String extracted_ = lastValue_.substring(0, ilast_);
            if (!extracted_.trim().isEmpty()) {
                e_.setValue(extracted_);
            } else {
                values.removeKey(e_.getKey());
            }
            return;
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

    public Delimiters getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(Delimiters _delimiter) {
        delimiter = _delimiter;
    }

    public boolean isFirstOpt() {
        return firstOpt;
    }

    public void setFirstOpt(boolean _firstOpt) {
        firstOpt = _firstOpt;
    }

}
