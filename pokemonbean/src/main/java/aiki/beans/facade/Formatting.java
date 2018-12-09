package aiki.beans.facade;
import aiki.DataBase;
import code.util.CustList;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.StringMap;

final class Formatting {

    protected static final String LEFT_BRACE = "{";
    protected static final String RIGHT_BRACE = "}";
    protected static final String QUOTED_LEFT_BRACE = "'{";
    protected static final String QUOTED_RIGHT_BRACE = "}'";
    protected static final String E_AMP = "&amp;";
    protected static final String E_GT = "&gt;";
    protected static final String E_LT = "&lt;";
    protected static final String EAMP = "&";
    protected static final String EGT = ">";
    protected static final String ELT = "<";
    private static final char LEFT_PAR = '(';
    private static final char RIGHT_PAR = ')';
    private static final char PIPE_CHAR = '|';

    private Formatting() {
    }

    public static StringList getFormattedReasons(DataBase _data, StringList _reasons, String _language) {
        StringMap<String> locHtml_ = new StringMap<String>();
        locHtml_.put(EAMP, E_AMP);
        locHtml_.put(EGT, E_GT);
        locHtml_.put(ELT, E_LT);
        locHtml_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
        locHtml_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        StringList reasons_;
        reasons_ = new StringList();
        for (String f: _reasons) {
            String formula_ = _data.getFormula(f, _language);
            formula_ = StringList.replaceMultiple(formula_, locHtml_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            formula_ = formula_.replace(EAMP, E_AMP);
//            formula_ = formula_.replace(EGT, E_GT);
//            formula_ = formula_.replace(ELT, E_LT);
            reasons_.add(formula_);
        }
        return reasons_;
    }

    public static NatStringTreeMap<String> getMapVarsFail(DataBase _data, String _fail, String _language) {
        NatStringTreeMap<String> mapVars_ = _data.getDescriptions(_fail, _language);
        NatStringTreeMap<String> mapVarsFail_ = new NatStringTreeMap<String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        for (String k: desc_) {
            mapVarsFail_.put(k, mapVars_.getVal(k));
        }
        return mapVarsFail_;
    }

    public static StringList getReasons(String _booleanString) {
        StringList reasons_;
        reasons_ = new StringList();
        int i_ = CustList.FIRST_INDEX;
        int iPostSep_ = CustList.FIRST_INDEX;
        int nbLeftPar_ = 0;
        int nbRightPar_ = 0;
        String fail_ = _booleanString;
        while (i_ < fail_.length()) {
            if (fail_.charAt(i_) == LEFT_PAR) {
                nbLeftPar_++;
            }
            if (fail_.charAt(i_) == RIGHT_PAR) {
                nbRightPar_++;
            }
            if (fail_.charAt(i_) == PIPE_CHAR) {
                if (nbLeftPar_ == nbRightPar_) {
                    reasons_.add(fail_.substring(iPostSep_, i_));
                    iPostSep_ = i_ + 1;
                }
            }
            i_++;
        }
        if (iPostSep_ < fail_.length()) {
            reasons_.add(fail_.substring(iPostSep_));
        }
        return reasons_;
    }

}