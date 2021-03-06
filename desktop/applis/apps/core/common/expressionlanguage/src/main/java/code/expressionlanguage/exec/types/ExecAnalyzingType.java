package code.expressionlanguage.exec.types;

import code.maths.litteralcom.StrTypes;
import code.expressionlanguage.types.KindPartType;
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
        String str_ = _string.substring(first_);
        values = new StrTypes();
        values.addEntry(first_, str_);
        operators = new StrTypes();
        operators.addEntry(arr_, _operator);
    }

    public void setupValuesExec(String _string) {
        values = new StrTypes();
        if (operators.isEmpty()) {
            values.addEntry(IndexConstants.FIRST_INDEX, _string);
            error = true;
            return;
        }
        int beginValuePart_ = IndexConstants.FIRST_INDEX;
        int endValuePart_ = operators.firstKey();
        String str_ = _string.substring(beginValuePart_, endValuePart_);
        values.addEntry(beginValuePart_, str_);
        int i_ = IndexConstants.SECOND_INDEX;
        int nbKeys_ = operators.size();
        while (i_ < nbKeys_) {
            beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
            endValuePart_ = operators.getKey(i_);
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(beginValuePart_, str_);
            i_++;
        }
        beginValuePart_ = endValuePart_ + operators.lastValue().length();
        str_ = _string.substring(beginValuePart_);
        if (!str_.trim().isEmpty() && prio == ExecPartTypeUtil.TMP_PRIO) {
            values = new StrTypes();
            values.addEntry(IndexConstants.FIRST_INDEX, _string);
            error = true;
            return;
        }
        values.addEntry(beginValuePart_, str_);
    }
    public KindPartType getKind() {
        return kind;
    }
    public void setKind(KindPartType _kind) {
        kind = _kind;
    }

    public StrTypes getOperators() {
        return operators;
    }
    public StrTypes getValues() {
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
