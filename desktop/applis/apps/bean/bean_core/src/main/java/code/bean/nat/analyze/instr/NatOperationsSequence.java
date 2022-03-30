package code.bean.nat.analyze.instr;
import code.maths.litteralcom.StrTypes;
import code.util.core.IndexConstants;

public final class NatOperationsSequence {
    private static final char DOT_VAR = '.';
    private static final char PAR = '(';
    private boolean varIndex;

    private String fctName = "";

    private int prioNat;

    private StrTypes valNat;

    private StrTypes opersNat;

    private NatDelimiters delimiterNat;

    public void setValue(String _string) {
        valNat = new StrTypes();
        valNat.addEntry(IndexConstants.FIRST_INDEX, _string);
    }

    public void setupValues(String _string) {
        valNat = new StrTypes();
        String op_ = opersNat.firstValue();
        boolean pureDot_ = op_.charAt(0) == DOT_VAR;
        int beginValuePart_ = IndexConstants.FIRST_INDEX;
        int endValuePart_ = opersNat.firstKey();
        String str_;
        if (prioNat != NatElResolver.UNARY_PRIO) {
            //not unary priority, not identity priority
            str_ = _string.substring(beginValuePart_, endValuePart_);
            valNat.addEntry(beginValuePart_, str_);
        }
        if (pureDot_) {
            beginValuePart_ = endValuePart_ + opersNat.lastValue().length();
            str_ = _string.substring(beginValuePart_);
            valNat.addEntry(beginValuePart_, str_);
            return;
        }
        if (prioNat == NatElResolver.FCT_OPER_PRIO) {
            if (opersNat.size() == 2) {
                beginValuePart_ = endValuePart_ + opersNat.firstValue().length();
                endValuePart_ = opersNat.getKey(IndexConstants.SECOND_INDEX);
                str_ = _string.substring(beginValuePart_, endValuePart_);
                addValueIfNotEmpty(beginValuePart_, str_);
                return;
            }
            int i_ = IndexConstants.SECOND_INDEX;
            int nbKeys_ = opersNat.size();
            while (i_ < nbKeys_) {
                beginValuePart_ = endValuePart_ + opersNat.getValue(i_-1).length();
                endValuePart_ = opersNat.getKey(i_);
                str_ = _string.substring(beginValuePart_, endValuePart_);
                valNat.addEntry(beginValuePart_, str_);
                i_++;
            }
            return;
        }
        beginValuePart_ = endValuePart_ + opersNat.lastValue().length();
        str_ = _string.substring(beginValuePart_);
        valNat.addEntry(beginValuePart_, str_);
    }

    private void addValueIfNotEmpty(int _beginValuePart,
                                    String _str) {
        if (_str.trim().isEmpty()) {
            return;
        }
        valNat.addEntry(_beginValuePart, _str);
    }

    public boolean isVarIndex() {
        return varIndex;
    }

    public void setVarIndex(boolean _varIndex) {
        this.varIndex = _varIndex;
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
        String str_ = opersNat.firstValue();
        return str_.charAt(0) == PAR;
    }

    public int getPrioNat() {
        return prioNat;
    }

    public void setPrioNat(int _priority) {
        prioNat = _priority;
    }

    public StrTypes getValNat() {
        return valNat;
    }

    public StrTypes getOpersNat() {
        return opersNat;
    }

    public void setOpersNat(StrTypes _operators) {
        opersNat = _operators;
    }

    public NatDelimiters getDelimiterNat() {
        return delimiterNat;
    }

    public void setDelimiterNat(NatDelimiters _delimiter) {
        delimiterNat = _delimiter;
    }

}
