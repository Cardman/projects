package code.maths.litteraladv;

import code.maths.Complex;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteralcom.MatCommonCst;
import code.maths.litteralcom.MatConstType;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class MaOperationNode {
    protected static final char PAR_LEFT = '(';
    protected static final char PAR_RIGHT = ')';
//    protected static final String TRUE_STRING = "vrai";
//    protected static final String FALSE_STRING = "faux";

    protected static final String NEG_BOOL = "!";

    protected static final String UNARY_MINUS = "-";

    protected static final String MULT = "*";

    protected static final String PLUS = "+";

    protected static final String MINUS = "-";

    protected static final String EQ = "=";

    protected static final String DIFF = "!=";

    protected static final String OR = "|";
    protected static final String REP = "&&";
    protected static final String RAND = "?";
    protected static final String STAT = "<>-|";
    protected static final String POLYNOM_SYMB = ";";
    protected static final String POLYGON_SYMBOL = "|||";
    protected static final String FIRST_DELAUNAY = "%%";
    protected static final String SECOND_DELAUNAY = "%%%";
    protected static final String EQ_VAR = "=";
    protected static final char DERIVE = '\'';
    protected static final String SGN = "-";
    protected static final String ABS = "|";
    protected static final String COMPLEX = "//";
    protected static final String NUM = "/0";
    protected static final String DEN = "/1";
    protected static final String ENT = "0/";
    protected static final String TRONC = "1/";
    protected static final String PREM = "/";
    protected static final String DIVS = "||";
    protected static final String DECOMP = "&";
    protected static final String GRAV = "*";
    protected static final String ID_MAT = "#";
    protected static final String TRUE = "&";
    protected static final String FALSE = "|";
    protected static final String WIDE_BOUND = "<=";
    protected static final String STRICT_BOUND = "<";
    protected static final String RIGHT_OPEN = "==<";
    protected static final String RIGHT_CLOSE = "==>";
    protected static final String LEFT_OPEN = ">==";
    protected static final String LEFT_CLOSE = "<==";
    protected static final String QUOT = "/";
    protected static final String MOD = "%";
    protected static final String POW = "^";
    protected static final String PARMI = "<=";
    protected static final String BEZOUT = "/%";
    protected static final String POINT = ".";
    protected static final String EDGE = "-";
    protected static final String FIRST_INTER = "^^";
    protected static final String SECOND_INTER = "^^^";
    protected static final String LINE_THREE = "-";
    protected static final String ARR = "[";
    protected static final String MATRIX = "{";
    protected static final String ASSOC = "=>";
    protected static final String EVT = "<>";
//    protected static final String PUIS = "puis";
//
//    protected static final String QUOT = "quot";
//
//    protected static final String MOD = "mod";
//
//    protected static final String MODTAUX = "modtaux";
//
//    protected static final String BEZOUT = "bezout";
//
//    protected static final String SGN = "sgn";
//
//    protected static final String ABS = "abs";
//
//    protected static final String ENT = "ent";
//
//    protected static final String TRONC = "troncature";
//
//    protected static final String NUM = "num";
//
//    protected static final String DEN = "den";
//
//    protected static final String PREM = "prem";
//
//    protected static final String DIVS = "divs";
//
//    protected static final String DECOMP = "decomp";
//
//    protected static final String PARMI = "parmi";
//
//    protected static final String REP = "rep";
//
//    protected static final String ALEA = "alea";
//
//    protected static final String STAT = "stat";

//    protected static final String MIN = "min";
//
//    protected static final String MAX = "max";
//
//    protected static final String MOY = "moy";
//
//    protected static final String VAR = "var";
//
//    protected static final String CARAC_FERME = "caracferme";
//
//    protected static final String CARAC_OUVERT = "caracouvert";
//
//    protected static final String CARAC_SEMI_OUVERT_G = "caracsemiouvertg";
//
//    protected static final String CARAC_SEMI_OUVERT_D = "caracsemiouvertd";
//
//    protected static final String CARAC_DROITE_OUVERT = "caracdroiteouvert";
//
//    protected static final String CARAC_DROITE_FERME = "caracdroiteferme";
//
//    protected static final String CARAC_GAUCHE_OUVERT = "caracgaucheouvert";
//
//    protected static final String CARAC_GAUCHE_FERME = "caracgaucheferme";
//
//    protected static final String DIV_FCT = "div";

    private final MethodMaOperation par;

    private MaOperationNode next;

    private MaStruct struct;

    private final MaOperationsSequence operats;

    private final int indexExp;

    private int order = IndexConstants.INDEX_NOT_FOUND_ELT;

    private final int childIndex;

    MaOperationNode(int _indexInEl, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        par = _m;
        indexExp = _indexInEl;
        operats = _op;
        childIndex = _indexChild;
    }

    static MaOperationNode createOperationNodeAndChild(int _index,
                                               int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, MaParameters _mapping) {
        MaOperationNode created_ = createOperationNode(_index, _indexChild, _m, _op, _mapping);
        if (created_ instanceof MethodMaOperation) {
            ((MethodMaOperation)created_).calculate();
        }
        return created_;
    }
    private static MaOperationNode createOperationNode(int _index,
                                               int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, MaParameters _mapping) {
        if (_op.getOpers().isEmpty()) {
            return processLeaf(_index, _indexChild, _m, _op);
        }
        if (_op.getPrio() == MatCommonCst.FCT_OPER_PRIO) {
            return procFct(_index, _indexChild, _m, _op, _mapping);
        }
        if (_op.getPrio() == MatCommonCst.DECL_PRIO) {
            return new VectMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPrio() == MatCommonCst.ASS_PRIO) {
            if (_op.getParts().size() == 2 && StringUtil.quickEq(_op.getOpers().firstValue(), ASSOC)) {
                return new PolInterMaOperation(_index, _indexChild, _m, _op);
            }
            if (_op.getParts().size() == 2) {
                if (_m instanceof SymbVarFctMaOperation && StringUtil.quickEq(((SymbVarFctMaOperation)_m).getOper(),POLYNOM_SYMB)) {
                    return new PolMemMaOperation(_index, _indexChild, _m, _op);
                }
                return new EvMaOperation(_index, _indexChild, _m, _op);
            }
        }
        return procSymbol(_index, _indexChild, _m, _op);
    }

    private static MethodMaOperation procSymbol(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        if (_op.getPrio() == MatCommonCst.FACT_PRIO) {
            return new FactMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPrio() == MatCommonCst.UNARY_PRIO) {
            return procUnary(_index, _indexChild, _m, _op);
        }
        if (_op.getPrio() == MatCommonCst.MULT_PRIO) {
            return new NumericMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPrio() == MatCommonCst.ADD_PRIO) {
            return new NumericMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPrio() == MatCommonCst.CMP_PRIO) {
            return new CmpMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPrio() == MatCommonCst.EQ_PRIO) {
            return new EqMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPrio() == MatCommonCst.AND_PRIO) {
            return new QuickMaOperation(_index, _indexChild, _m, _op, false);
        }
        if (_op.getPrio() == MatCommonCst.OR_PRIO) {
            return new QuickMaOperation(_index, _indexChild, _m, _op,true);
        }
        return null;
    }

    private static LeafMaOperation processLeaf(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        if (_op.getType() == MatConstType.LOC_VAR) {
            return new VariableMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getType() == MatConstType.NUMBER) {
            return new ConstantMaOperation(_index, _indexChild, _m, _op);
        }
        return null;
    }

    private static MethodMaOperation procUnary(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        if (StringUtil.quickEq(_op.getOpers().firstValue().trim(), NEG_BOOL)) {
            return new UnaryBooleanMaOperation(_index, _indexChild, _m, _op);
        }
        return new UnaryMaOperation(_index, _indexChild, _m, _op);
    }

    private static MethodMaOperation procFct(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, MaParameters _mapping) {
        if (_op.getFct().trim().isEmpty()) {
            if (StringUtil.quickEq(_op.getOpers().firstValue(), MATRIX)) {
                return new MatrixMaOperation(_index, _indexChild, _m, _op);
            }
            return procSymb(_index, _indexChild, _m, _op,_mapping);
        }
        return new FctMaOperation(_index, _indexChild, _m, _op);
    }

    private static MethodMaOperation procSymb(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, MaParameters _mapping) {
        if (StringUtil.quickEq(_op.getOpers().firstValue(), ARR)) {
            return new ArrMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getParts().size() == 2 && isAndOr(_op)) {
            return new SymbGeneMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getParts().size() >= 2 && isVarSymbol(_op)) {
            return new SymbVarFctMaOperation(_index, _indexChild, _m, _op,_mapping);
        }
        if (_op.getParts().size() == 3 && isUnarySymbol(_op)) {
            return new SymbUnFctMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getParts().size() == 4 && isBinSymbol(_op)) {
            return new SymbBinFctMaOperation(_index, _indexChild, _m, _op);
        }
        return defSymb(_index, _indexChild, _m, _op);
    }

    private static MethodMaOperation defSymb(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        if (_op.getParts().size() == 4 && isPairSymbol(_op)) {
            return new SymbCaracFctMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getParts().size() == 5 && StringUtil.quickEq(_op.getParts().lastValue().trim(), LINE_THREE)){
            return new SymbTerFctMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getParts().size() == 6 && areBinarySymbols(_op)) {
            return new SymbDoubleCaracFctMaOperation(_index, _indexChild, _m, _op);
        }
        return new IdMaOperation(_index, _indexChild, _m, _op);
    }

    protected static MaRateStruct asInt(MaStruct _str) {
        MaRateStruct rate_ = asRate(_str);
        if (rate_ == null) {
            return null;
        }
        if (!rate_.getRate().isInteger()) {
            return null;
        }
        return rate_;
    }
    protected static MaRateStruct asRate(MaStruct _str) {
        if (!(_str instanceof MaRateStruct)) {
            return null;
        }
        return (MaRateStruct)_str;
    }

    protected static MaFractPolStruct asPol(MaStruct _str) {
        MaFractPolStruct rate_ = asFract(_str);
        if (rate_ == null) {
            return null;
        }
        if (!rate_.getFractPol().isInteger()) {
            return null;
        }
        return rate_;
    }
    protected static MaFractPolStruct asFract(MaStruct _str) {
        return MaFractPolStruct.wrapOrNull(_str);
    }

    protected static MaDecompositionNbStruct asDecompositionNb(MaStruct _str) {
        if (!(_str instanceof MaDecompositionNbStruct)) {
            return null;
        }
        return (MaDecompositionNbStruct)_str;
    }

    protected static MaMatrixStruct asMatrix(MaStruct _str) {
        if (!(_str instanceof MaMatrixStruct)) {
            return null;
        }
        return (MaMatrixStruct)_str;
    }
    protected static MaComplexStruct asComplex(MaStruct _str) {
        if (_str instanceof MaRateStruct) {
            return new MaComplexStruct(new Complex(((MaRateStruct)_str).getRate()));
        }
        if (!(_str instanceof MaComplexStruct)) {
            return null;
        }
        return (MaComplexStruct)_str;
    }
    protected static MaEdgeStruct asEdge(MaStruct _str) {
        if (!(_str instanceof MaEdgeStruct)) {
            return null;
        }
        return (MaEdgeStruct)_str;
    }
    protected static MaCustLineStruct asLine(MaStruct _str) {
        if (!(_str instanceof MaCustLineStruct)) {
            return null;
        }
        return (MaCustLineStruct)_str;
    }
    protected static MaPolygonStruct asPolygon(MaStruct _str) {
        if (!(_str instanceof MaPolygonStruct)) {
            return null;
        }
        return (MaPolygonStruct)_str;
    }
    protected static MaDelaunayStruct asDelaunay(MaStruct _str) {
        if (!(_str instanceof MaDelaunayStruct)) {
            return null;
        }
        return (MaDelaunayStruct)_str;
    }
    protected static CustList<MaStruct> tryGetAll(MethodMaOperation _this) {
        CustList<MaStruct> rates_ = new CustList<MaStruct>();
        int len_ = _this.getChildren().size();
        for (int i = 0; i < len_; i++) {
            rates_.add(MaNumParsers.tryGet(_this, i));
        }
        return rates_;
    }

    private static boolean areBinarySymbols(MaOperationsSequence _op) {
        int size_ = _op.getParts().size();
        String val_ = _op.getParts().getValue(size_-2).trim();
        String valTwo_ = _op.getParts().lastValue().trim();
        return isCmpSymbol(val_)
                && isCmpSymbol(valTwo_);
    }

    private static boolean isVarSymbol(MaOperationsSequence _op) {
        String valTwo_ = _op.getParts().lastValue().trim();
        return algebreVar(valTwo_)|| StringUtil.quickEq(valTwo_, POLYGON_SYMBOL)|| StringUtil.quickEq(valTwo_, FIRST_DELAUNAY)|| StringUtil.quickEq(valTwo_, SECOND_DELAUNAY);
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

    private static boolean isPairSymbol(MaOperationsSequence _op) {
        String valTwo_ = _op.getParts().lastValue().trim();
        return StringUtil.quickEq(valTwo_,LEFT_OPEN)
                ||StringUtil.quickEq(valTwo_,LEFT_CLOSE)
                ||StringUtil.quickEq(valTwo_,RIGHT_OPEN)
                ||StringUtil.quickEq(valTwo_,RIGHT_CLOSE);
    }

    private static boolean isCmpSymbol(String _val) {
        return StringUtil.quickEq(_val, STRICT_BOUND) || StringUtil.quickEq(_val, WIDE_BOUND);
    }
    private static boolean isBinSymbol(MaOperationsSequence _op) {
        String val_ = _op.getParts().lastValue().trim();
        return algebreBin(val_)|| geoBin(val_);
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
    private static boolean isUnarySymbol(MaOperationsSequence _op) {
        String val_ = _op.getParts().lastValue().trim();
        return algebreUn(val_) || StringUtil.quickEq(GRAV, val_);
    }

    private static boolean algebreUn(String _val) {
        return nbOp(_val) || arith(_val)
                || containsOnlySimpleQuotes(_val) > 0
                || StringUtil.quickEq(ID_MAT, _val);
    }

    private static boolean nbOp(String _val) {
        return nbPart(_val) || nbDecSymbol(_val);
    }

    private static boolean nbPart(String _val) {
        return nbPartSymbolSgn(_val)
                || nbPartSymbol(_val);
    }

    protected static int containsOnlySimpleQuotes(String _val) {
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

    private static boolean isAndOr(MaOperationsSequence _op) {
        String val_ = _op.getParts().lastValue().trim();
        return StringUtil.quickEq(val_,TRUE) || StringUtil.quickEq(val_,FALSE);
    }

    protected void processRatesPol(MaError _error, MaFractPolStruct _first, MaStruct _second, int _index) {
        if (_first.getFractPol().isInteger() && _second instanceof MaRateStruct) {
            MaRateStruct second_ = (MaRateStruct) _second;
            Rate rateInd_ = second_.getRate();
            if (!rateInd_.isInteger()) {
                _error.setOffset(getIndexExp() + _index);
                return;
            }
            CustList<Rate> numbers_ = _first.getFractPol().getNumerator().getNumbers();
            LgInt intInd_ = rateInd_.intPart();
            if (!intInd_.isZeroOrGt()) {
                intInd_.addNb(new LgInt(numbers_.size()));
            }
            if (!intInd_.isZeroOrGt()) {
                _error.setOffset(getIndexExp() + _index);
                return;
            }
            if (LgInt.strLower(intInd_,new LgInt(numbers_.size()))) {
                Rate val_ = numbers_.get((int) intInd_.ll());
                LgInt freq_ = LgInt.minus(new LgInt(numbers_.size()-1L), intInd_);
                setStruct(new MaPolMemberStruct(val_, freq_));
                return;
            }
        }
        _error.setOffset(getIndexExp() + _index);
    }

    abstract void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del);

    public final MaOperationNode getNext() {
        return next;
    }
    final void setNext(MaOperationNode _nextSibling) {
        next = _nextSibling;
    }

    static int getNextIndex(MaOperationNode _operation, MaStruct _value, int _least) {
        MethodMaOperation par_ = _operation.getPar();
        int index_ = _operation.getChildIndex();
        if (par_ instanceof QuickMaOperation) {
            QuickMaOperation q_ = (QuickMaOperation) par_;
            boolean bs_ = q_.isAbs();
            if (MaBoolStruct.of(bs_).sameReference(_value)) {
                return Math.max(_least, par_.getOrder());
            }
        }
        if (par_ instanceof FctMaOperation) {
            if (index_ == 1) {
                return Math.max(_least, par_.getOrder());
            }
            MaOperationNode next_ = _operation.getNext();
            if (index_ == 0 && next_ != null && MaBoolStruct.of(false).sameReference(_value)) {
                return Math.max(_least, next_.getOrder() + 1);
            }
        }
        return Math.max(_least, _operation.getOrder() + 1);
    }
    public MethodMaOperation getPar() {
        return par;
    }

    public MaOperationsSequence getOperats() {
        return operats;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int _order) {
        order = _order;
    }

    public int getIndexExp() {
        return indexExp;
    }

    public int getChildIndex() {
        return childIndex;
    }

    public MaStruct getStruct() {
        return struct;
    }

    public void setStruct(MaStruct _struct) {
        this.struct = _struct;
    }
}
