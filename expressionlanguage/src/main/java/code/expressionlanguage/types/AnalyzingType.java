package code.expressionlanguage.types;

import code.expressionlanguage.Options;
import code.expressionlanguage.Templates;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;

public class AnalyzingType {

    private Numbers<Integer> indexes = new Numbers<Integer>();
    private NatTreeMap<Integer, String> operators = new NatTreeMap<Integer, String>();
    private NatTreeMap<Integer, String> values = new NatTreeMap<Integer, String>();
    private boolean error;
    private KindPartType kind = KindPartType.NOTHING;
    private int offset;
    private int prio;

    public void setupValue(String _string, int _offset, Options _options) {
        values = new NatTreeMap<Integer,String>();
        values.put((int)CustList.FIRST_INDEX, _string);
        offset = _offset;
    }
    public void setupArrayValues(String _string, Options _options) {
        if (_options.isDoubleBracketsArray()) {
            int j_ = _string.length()-1;
            while (_string.charAt(j_) != ']') {
                j_--;
            }
            while (_string.charAt(j_) != '[') {
                j_--;
            }
            int last_ = StringList.getLastPrintableCharIndex(_string.substring(0, j_));
            if (last_ < 0) {
                values = new NatTreeMap<Integer,String>();
                values.put((int)CustList.FIRST_INDEX, _string);
                error = true;
                return;
            }
            String str_ = _string.substring(0, j_);
            values = new NatTreeMap<Integer,String>();
            values.put((int)CustList.FIRST_INDEX, str_);
            operators = new NatTreeMap<Integer,String>();
            operators.put(last_, "[]");
            return;
        }
        int first_ = StringList.getFirstPrintableCharIndex(_string);
        int arr_ = first_;
        first_++;
        int offset_ = StringList.getFirstPrintableCharIndex(_string.substring(first_));
        if (offset_ > 0) {
            first_ += offset_;
        }
        String str_ = _string.substring(first_);
        values = new NatTreeMap<Integer,String>();
        values.put(first_, str_);
        operators = new NatTreeMap<Integer,String>();
        operators.put(arr_, Templates.ARR_BEG_STRING);
    }
    public void setupValues(String _string, Options _options) {
        values = new NatTreeMap<Integer,String>();
        if (operators.isEmpty()) {
            values.put((int)CustList.FIRST_INDEX, _string);
            error = true;
            return;
        }
        int beginValuePart_ = CustList.FIRST_INDEX;
        int endValuePart_ = operators.firstKey();
        String str_;
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
    }
    public void setupValueExec(String _string, int _offset) {
        values = new NatTreeMap<Integer,String>();
        values.put((int)CustList.FIRST_INDEX, _string);
        offset = _offset;
    }
    public void setupArrayValuesExec(String _string) {
        int first_ = StringList.getFirstPrintableCharIndex(_string);
        int arr_ = first_;
        first_++;
        int offset_ = StringList.getFirstPrintableCharIndex(_string.substring(first_));
        if (offset_ > 0) {
            first_ += offset_;
        }
        String str_ = _string.substring(first_);
        values = new NatTreeMap<Integer,String>();
        values.put(first_, str_);
        operators = new NatTreeMap<Integer,String>();
        operators.put(arr_, Templates.ARR_BEG_STRING);
    }
    public void setupValuesExec(String _string) {
        values = new NatTreeMap<Integer,String>();
        if (operators.isEmpty()) {
            values.put((int)CustList.FIRST_INDEX, _string);
            error = true;
            return;
        }
        int beginValuePart_ = CustList.FIRST_INDEX;
        int endValuePart_ = operators.firstKey();
        String str_;
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
    }
    public boolean isRemovedEmptyFirstChild() {
        return prio == ParserType.INT_PRIO && values.firstValue().isEmpty();
    }
    public KindPartType getKind() {
        return kind;
    }
    public void setKind(KindPartType _kind) {
        kind = _kind;
    }
    public int getOffset() {
        return offset;
    }
    public void setOffset(int _offset) {
        offset = _offset;
    }
    public Numbers<Integer> getIndexes() {
        return indexes;
    }
    public NatTreeMap<Integer, String> getOperators() {
        return operators;
    }
    public NatTreeMap<Integer, String> getValues() {
        return values;
    }
    public boolean isError() {
        return error;
    }

    public void setError(boolean _error) {
        error = _error;
    }
    public int getPrio() {
        return prio;
    }
    public void setPrio(int _prio) {
        prio = _prio;
    }
    
}
