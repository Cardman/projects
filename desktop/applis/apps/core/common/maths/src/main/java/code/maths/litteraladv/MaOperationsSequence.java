package code.maths.litteraladv;

import code.maths.litteralcom.MatCommonCst;
import code.maths.litteralcom.MatConstType;
import code.maths.litteralcom.StrTypes;
import code.util.core.IndexConstants;

public final class MaOperationsSequence {

    private String fct = "";

    private int prio;

    private StrTypes parts;

    private StrTypes opers;

    private MatConstType type;

    private int cst;

    public void setupValue(String _string) {
        parts = new StrTypes();
        parts.addEntry(IndexConstants.FIRST_INDEX, _string);
    }
    public void setupValues(String _string) {
        parts = new StrTypes();
        if (opers.isEmpty()) {
            parts.addEntry(IndexConstants.FIRST_INDEX, _string);
            type = MatConstType.ERROR;
            return;
        }
        feedValues(_string);
    }

    private void feedValues(String _string) {
        firstOperand(_string);
        int endPart_ = opers.firstKey();
        if (prio == MatCommonCst.FCT_OPER_PRIO) {
            int afterLastPar_ = opers.lastKey()+1;
            if (!_string.substring(afterLastPar_).trim().isEmpty()) {
                opers.clear();
                opers.addEntry(afterLastPar_, "");
                prio = MatCommonCst.BAD_PRIO;
                return;
            }
        }
        if (prio == MatCommonCst.FACT_PRIO) {
            return;
        }
        if (prio == MatCommonCst.FCT_OPER_PRIO && opers.size() == 2) {
            int beginValuePart_ = endPart_ + opers.firstValue().length();
            endPart_ = opers.getKey(IndexConstants.SECOND_INDEX);
            String str_ = _string.substring(beginValuePart_, endPart_);
            if (!str_.isEmpty()) {
                parts.addEntry(beginValuePart_, str_);
            }
            return;
        }
        int i_ = IndexConstants.SECOND_INDEX;
        int nbKeys_ = opers.size();
        while (i_ < nbKeys_) {
            int beginValuePart_ = endPart_ + opers.getValue(i_ - 1).length();
            endPart_ = opers.getKey(i_);
            String str_ = _string.substring(beginValuePart_, endPart_);
            parts.addEntry(beginValuePart_, str_);
            i_++;
        }
        if (prio != MatCommonCst.FCT_OPER_PRIO) {
            int beginValuePart_ = endPart_ + opers.lastValue().length();
            String str_ = _string.substring(beginValuePart_);
            parts.addEntry(beginValuePart_, str_);
        }
    }

    private void firstOperand(String _string) {
        if (prio != MatCommonCst.UNARY_PRIO) {
            //not unary priority, not identity priority
            int beginValuePart_ = IndexConstants.FIRST_INDEX;
            int endValuePart_ = opers.firstKey();
            String str_ = _string.substring(beginValuePart_, endValuePart_);
            parts.addEntry(beginValuePart_, str_);
        }
    }

    public String getFct() {
        return fct;
    }

    public void setFct(String _fct) {
        this.fct = _fct;
    }

    public int getPrio() {
        return prio;
    }

    public void setPrio(int _prio) {
        this.prio = _prio;
    }

    public StrTypes getParts() {
        return parts;
    }

    public StrTypes getOpers() {
        return opers;
    }

    public void setOpers(StrTypes _opers) {
        this.opers = _opers;
    }

    public MatConstType getType() {
        return type;
    }

    public void setType(MatConstType _type) {
        this.type = _type;
    }

    public int getCst() {
        return cst;
    }

    public void setCst(int _cst) {
        this.cst = _cst;
    }
}
