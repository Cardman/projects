package code.maths.litteraladv;

import code.maths.litteralcom.MatCommonCst;
import code.maths.litteralcom.MatConstType;
import code.maths.litteralcom.StrTypes;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class MaOperationsSequence {

    static final String NEG_BOOL = "!";

    static final String UNARY_MINUS = "-";

    static final String REP = "&&";
    static final String RAND = "?";
    static final String STAT = "<>-|";
    static final String POLYNOM_SYMB = ";";
    static final String POLYGON_SYMBOL = "|||";
    static final String FIRST_DELAUNAY = "%%";
    static final String SECOND_DELAUNAY = "%%%";
    static final String EQ_VAR = "=";
    static final char DERIVE = '\'';
    static final String SGN = "-";
    static final String ABS = "|";
    static final String COMPLEX = "//";
    static final String NUM = "/0";
    static final String DEN = "/1";
    static final String ENT = "0/";
    static final String TRONC = "1/";
    static final String PREM = "/";
    static final String DIVS = "||";
    static final String DECOMP = "&";
    static final String GRAV = "*";
    static final String ID_MAT = "#";
    static final String TRUE = "&";
    static final String FALSE = "|";
    static final String WIDE_BOUND = "<=";
    static final String STRICT_BOUND = "<";
    static final String WIDE_BOUNDS = WIDE_BOUND+','+WIDE_BOUND;
    static final String STRICT_BOUNDS = STRICT_BOUND+','+STRICT_BOUND;
    static final String STRICT_WIDE_BOUNDS = STRICT_BOUND+','+WIDE_BOUND;
    static final String WIDE_STRICT_BOUNDS = WIDE_BOUND+','+STRICT_BOUND;
    static final String RIGHT_OPEN = "==<";
    static final String RIGHT_CLOSE = "==>";
    static final String LEFT_OPEN = ">==";
    static final String LEFT_CLOSE = "<==";
    static final String QUOT = "/";
    static final String MOD = "%";
    static final String POW = "^";
    static final String PARMI = "<=";
    static final String BEZOUT = "/%";
    static final String POINT = ".";
    static final String EDGE = "-";
    static final String FIRST_INTER = "^^";
    static final String SECOND_INTER = "^^^";
    static final String LINE_THREE = "-";
    static final String ARR = "[";
    static final String MATRIX = "{";
    static final String ASSOC = "=>";
    static final String EVT = "<>";
    static final String KER = "<";
    static final String IM = ">";
    static final String KERIM = "<>";
    static final String IMKER = "><";
    private String fct = "";
    private boolean emptyFct;

    private int prio;

    private StrTypes parts;

    private StrTypes opers;

    private MatConstType type;

    private int offset;

    private int cst;

    public void setupValue(String _string, int _offset) {
        parts = new StrTypes();
        parts.addEntry(IndexConstants.FIRST_INDEX, _string);
        offset = _offset;
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
        if (prio == MatCommonCst.FCT_OPER_PRIO) {
            int afterLastPar_ = opers.lastKey()+1;
            if (!_string.substring(afterLastPar_).trim().isEmpty()) {
                opers.clear();
                opers.addEntry(afterLastPar_, "");
                prio = MatCommonCst.BAD_PRIO;
                return;
            }
            if (opers.size() == 2) {
                StrTypes.addNotEmpty(_string,opers,parts);
                return;
            }
            loop(_string);
            return;
        }
        if (prio == MatCommonCst.FACT_PRIO) {
            return;
        }
        loop(_string);
        lastPart(_string);
    }

    private void loop(String _string) {
        StrTypes.loopArgs(_string,opers,parts);
    }

    private void lastPart(String _string) {
        StrTypes.lastPart(_string,opers,parts);
    }

    private void firstOperand(String _string) {
        if (prio != MatCommonCst.UNARY_PRIO) {
            //not unary priority, not identity priority
            StrTypes.firstPartNotUnary(_string,opers,parts);
        }
    }
    public void adj() {
        if (getOpers().isEmpty() || getPrio() != MatCommonCst.FCT_OPER_PRIO) {
            return;
        }
        if (!fct.trim().isEmpty()) {
            StrTypes vs_ = getParts();
            vs_.remove(0);
            return;
        }
        if (StringUtil.quickEq(getOpers().firstValue(), MATRIX)) {
            StrTypes vs_ = getParts();
            vs_.remove(0);
            return;
        }
        if (StringUtil.quickEq(getOpers().firstValue(), ARR)) {
            return;
        }
        symbOrId();
    }

    private void symbOrId() {
        if (getParts().size() == 2 && isAndOr(this.getParts().lastValue().trim())) {
            fct = getParts().lastValue().trim();
            offset = getParts().lastKey();
            removeBounds(this);
            return;
        }
        if (getParts().size() >= 2 && isVarSymbol(this.getParts().lastValue().trim())) {
            fct = getParts().lastValue().trim();
            offset = getParts().lastKey();
            removeBounds(this);
            return;
        }
        if (getParts().size() == 3 && isUnarySymbol(this.getParts().lastValue().trim())) {
            fct = getParts().lastValue().trim();
            offset = getParts().lastKey();
            removeBounds(this);
            return;
        }
        if (getParts().size() == 4 && isBinSymbol(this.getParts().lastValue().trim())) {
            fct = getParts().lastValue().trim();
            offset = getParts().lastKey();
            removeBounds(this);
            return;
        }
        if (getParts().size() == 4 && isPairSymbol(this.getParts().lastValue().trim())) {
            fct = getParts().lastValue().trim();
            offset = getParts().lastKey();
            removeBounds(this);
            return;
        }
        if (getParts().size() == 5 && StringUtil.quickEq(getParts().lastValue().trim(), LINE_THREE)){
            fct = getParts().lastValue().trim();
            offset = getParts().lastKey();
            removeBounds(this);
            return;
        }
        if (getParts().size() == 6 && areBinarySymbols(this)) {
            String sec_ = getParts().lastValue().trim();
            String first_ = getParts().getValue(getParts().size()-2).trim();
            offset = getParts().getKey(getParts().size()-2);
            fct = first_+","+sec_;
            StrTypes vs_ = getParts();
            vs_.remove(vs_.size()-1);
            removeBounds(this);
            return;
        }
        StrTypes vs_ = getParts();
        vs_.remove(0);
    }

    private static void removeBounds(MaOperationsSequence _op) {
        StrTypes vs_ = _op.getParts();
        vs_.remove(vs_.size()-1);
        vs_.remove(0);
    }

    private static boolean areBinarySymbols(MaOperationsSequence _op) {
        int size_ = _op.getParts().size();
        String val_ = _op.getParts().getValue(size_-2).trim();
        String valTwo_ = _op.getParts().lastValue().trim();
        return areBinarySymbols(val_, valTwo_);
    }

    static boolean isBinarySymbols(String _op) {
        return StringUtil.quickEq(WIDE_BOUNDS,_op)
                ||StringUtil.quickEq(STRICT_BOUNDS,_op)
                ||StringUtil.quickEq(STRICT_WIDE_BOUNDS,_op)
                ||StringUtil.quickEq(WIDE_STRICT_BOUNDS,_op);
    }

    private static boolean areBinarySymbols(String val_, String valTwo_) {
        return isCmpSymbol(val_)
                && isCmpSymbol(valTwo_);
    }

    static boolean isVarSymbol(String _fct) {
        return algebreVar(_fct)|| StringUtil.quickEq(_fct, POLYGON_SYMBOL)|| StringUtil.quickEq(_fct, FIRST_DELAUNAY)|| StringUtil.quickEq(_fct, SECOND_DELAUNAY);
    }

    private static boolean algebreVar(String _var) {
        return classicVar(_var)
                || StringUtil.quickEq(_var, STAT)
                || StringUtil.quickEq(_var, EQ_VAR);
    }

    private static boolean classicVar(String _var) {
        return StringUtil.quickEq(_var, POLYNOM_SYMB)
                || StringUtil.quickEq(_var, REP)
                || StringUtil.quickEq(_var, RAND);
    }

    static boolean isPairSymbol(String _fct) {
        return StringUtil.quickEq(_fct,LEFT_OPEN)
                ||StringUtil.quickEq(_fct,LEFT_CLOSE)
                ||StringUtil.quickEq(_fct,RIGHT_OPEN)
                ||StringUtil.quickEq(_fct,RIGHT_CLOSE);
    }

    private static boolean isCmpSymbol(String _val) {
        return StringUtil.quickEq(_val, STRICT_BOUND) || StringUtil.quickEq(_val, WIDE_BOUND);
    }
    static boolean isBinSymbol(String _fct) {
        return algebreBin(_fct)|| geoBin(_fct);
    }

    private static boolean geoBin(String _val) {
        return StringUtil.quickEq(_val, POINT)
                ||StringUtil.quickEq(_val, EDGE)
                ||StringUtil.quickEq(_val, FIRST_INTER)
                ||StringUtil.quickEq(_val, SECOND_INTER);
    }

    private static boolean algebreBin(String _val) {
        return divmod(_val)
                || StringUtil.quickEq(_val, POW) || StringUtil.quickEq(_val, PARMI)
                || StringUtil.quickEq(_val, BEZOUT);
    }

    private static boolean divmod(String _val) {
        return StringUtil.quickEq(_val, QUOT) || StringUtil.quickEq(_val, MOD);
    }
    static boolean isUnarySymbol(String _fct) {
        return algebreUn(_fct) || StringUtil.quickEq(GRAV, _fct);
    }

    private static boolean algebreUn(String _val) {
        return classicUn(_val)
                || containsOnlySimpleQuotes(_val) > 0
                || StringUtil.quickEq(ID_MAT, _val);
    }

    private static boolean classicUn(String _val) {
        return nbOp(_val) || arith(_val) || matrixAddon(_val);
    }

    private static boolean matrixAddon(String _val) {
        return StringUtil.quickEq(_val,IM) || StringUtil.quickEq(_val,KER)
                ||StringUtil.quickEq(_val,IMKER) || StringUtil.quickEq(_val,KERIM);
    }
    private static boolean nbOp(String _val) {
        return nbPart(_val) || nbDecSymbol(_val);
    }

    private static boolean nbPart(String _val) {
        return nbPartSymbolSgn(_val)
                || nbPartSymbol(_val);
    }

    static int containsOnlySimpleQuotes(String _val) {
        if (_val.isEmpty()) {
            return -1;
        }
        int count_ = 0;
        for (char c: _val.toCharArray()) {
            if (c != DERIVE) {
                return -1;
            }
            count_++;
        }
        return count_;
    }

    private static boolean nbPartSymbolSgn(String _val) {
        return StringUtil.quickEq(_val, SGN) || StringUtil.quickEq(_val, ABS);
    }
    private static boolean nbPartSymbol(String _val) {
        return StringUtil.quickEq(_val, NUM) || StringUtil.quickEq(_val, DEN);
    }

    private static boolean nbDecSymbol(String _val) {
        return StringUtil.quickEq(_val, ENT) || StringUtil.quickEq(_val, TRONC);
    }

    private static boolean arith(String _val) {
        return StringUtil.quickEq(_val, PREM) || StringUtil.quickEq(_val, COMPLEX) || StringUtil.quickEq(_val, DIVS) || StringUtil.quickEq(_val, DECOMP);
    }

    static boolean isAndOr(String _fct) {
        return StringUtil.quickEq(_fct,TRUE) || StringUtil.quickEq(_fct,FALSE);
    }
    public int getOffset() {
        return offset;
    }

    public String getFct() {
        return fct;
    }

    public void setFct(String _fct) {
        this.fct = _fct;
        emptyFct = _fct.trim().isEmpty();
    }

    public boolean isEmptyFct() {
        return emptyFct;
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
