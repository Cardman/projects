package code.expressionlanguage.exec.types;

import code.expressionlanguage.types.KindPartType;
import code.maths.litteralcom.StrTypes;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ExecAnalyzingType {

    private StrTypes operators = new StrTypes();
    private StrTypes values = new StrTypes();
    private boolean error;
    private KindPartType kind = KindPartType.NOTHING;
    private int prio;

    public void setupValueExec(String _string) {
        values = new StrTypes();
        values.addEntry(IndexConstants.FIRST_INDEX, _string);
    }

    public void setupUnaryValuesExec(String _string, String _operator) {
        int first_ = StringUtil.getFirstPrintableCharIndex(_string);
        int arr_ = first_;
        first_++;
        int offset_ = StringUtil.getFirstPrintableCharIndex(_string.substring(first_));
        if (offset_ >= 0) {
            first_ += offset_;
        } else {
            error = true;
        }
        feedArr(_string, _operator, first_, arr_);
    }

    private void feedArr(String _string, String _operator, int _first, int _arr) {
        String str_ = _string.substring(_first);
        values = new StrTypes();
        values.addEntry(_first, str_);
        operators = new StrTypes();
        operators.addEntry(_arr, _operator);
    }

    public void setupValuesExec(String _string) {
        values = new StrTypes();
        if (operators.isEmpty()) {
            values.addEntry(IndexConstants.FIRST_INDEX, _string);
            error = true;
            return;
        }
        int beginValuePartEx_ = IndexConstants.FIRST_INDEX;
        int endValuePartEx_ = operators.firstKey();
        String str_ = _string.substring(beginValuePartEx_, endValuePartEx_);
        values.addEntry(beginValuePartEx_, str_);
        int i_ = IndexConstants.SECOND_INDEX;
        int nbKeys_ = operators.size();
        while (i_ < nbKeys_) {
            beginValuePartEx_ = endValuePartEx_ + operators.getValue(i_-1).length();
            endValuePartEx_ = operators.getKey(i_);
            str_ = _string.substring(beginValuePartEx_, endValuePartEx_);
            values.addEntry(beginValuePartEx_, str_);
            i_++;
        }
        after(_string, endValuePartEx_);
    }

    private void after(String _string, int _endValuePartEx) {
        int beginValuePartEx_ = _endValuePartEx + operators.lastValue().length();
        String str_ = _string.substring(beginValuePartEx_);
        if (!str_.trim().isEmpty() && prio == ExecPartTypeUtil.TMP_PRIO) {
            values = new StrTypes();
            values.addEntry(IndexConstants.FIRST_INDEX, _string);
            error = true;
            return;
        }
        if (prio != ExecPartTypeUtil.TMP_PRIO){
            values.addEntry(beginValuePartEx_, str_);
        }
    }

    KindPartType getKindEx() {
        return kind;
    }
    void setKindEx(KindPartType _kind) {
        kind = _kind;
    }

    StrTypes getOperatorsEx() {
        return operators;
    }
    StrTypes getValuesEx() {
        return values;
    }
    boolean isErrorEx() {
        return error;
    }

    void setErrorEx() {
        error = true;
    }
    int getPrioEx() {
        return prio;
    }
    void setPrioEx(int _prio) {
        prio = _prio;
    }

}
