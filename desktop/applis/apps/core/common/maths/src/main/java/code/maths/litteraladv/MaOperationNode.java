package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteralcom.IndexStrPart;
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
            if (_op.getParts().size() == 2 && StringUtil.quickEq(_op.getOpers().firstValue(),"=>")) {
                return new PolInterMaOperation(_index, _indexChild, _m, _op);
            }
            if (_m instanceof SymbVarFctMaOperation && StringUtil.quickEq(((SymbVarFctMaOperation)_m).getOper(),";")) {
                return new PolMemMaOperation(_index, _indexChild, _m, _op);
            }
            return new EvMaOperation(_index, _indexChild, _m, _op);
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
        String str_ = _op.getParts().getValue(IndexConstants.FIRST_INDEX).trim();
        if (str_.isEmpty()) {
            return null;
        }
        if (_op.getType() == MatConstType.ERROR) {
            return null;
        }
        if (_op.getType() == MatConstType.LOC_VAR) {
            return new VariableMaOperation(_index, _indexChild, _m, _op);
        }
        return new ConstantMaOperation(_index, _indexChild, _m, _op);
    }

    private static MethodMaOperation procUnary(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        if (StringUtil.quickEq(_op.getOpers().firstValue().trim(), NEG_BOOL)) {
            return new UnaryBooleanMaOperation(_index, _indexChild, _m, _op);
        }
        return new UnaryMaOperation(_index, _indexChild, _m, _op);
    }

    private static MethodMaOperation procFct(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, MaParameters _mapping) {
        if (_op.getFct().trim().isEmpty()) {
            if (StringUtil.quickEq(_op.getOpers().firstValue(),"{")) {
                return new MatrixMaOperation(_index, _indexChild, _m, _op);
            }
            return procSymb(_index, _indexChild, _m, _op,_mapping);
        }
        return new FctMaOperation(_index, _indexChild, _m, _op);
    }

    private static MethodMaOperation procSymb(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, MaParameters _mapping) {
        if (StringUtil.quickEq(_op.getOpers().firstValue(),"[")) {
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
        if (_op.getParts().size() == 5 && StringUtil.quickEq(_op.getParts().lastValue().trim(),"-")){
            return new SymbTerFctMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getParts().size() == 6 && areBinarySymbols(_op)) {
            return new SymbDoubleCaracFctMaOperation(_index, _indexChild, _m, _op);
        }
        return new IdMaOperation(_index, _indexChild, _m, _op);
    }

    protected static boolean areTwoIntegers(CustList<MaRateStruct> _rates) {
        return areAllIntegersNb(_rates, 2);
    }
    protected static boolean areAllIntegersNb(CustList<MaRateStruct> _rates, int _count) {
        return _rates.size() == _count && areAllIntegers(_rates);
    }
    protected static boolean areTwoPols(CustList<MaFractPolStruct> _rates) {
        return areAllPolsNb(_rates, 2);
    }
    protected static boolean areAllPolsNb(CustList<MaFractPolStruct> _rates, int _count) {
        return _rates.size() == _count && areAllPols(_rates);
    }
    protected static CustList<MaDecompositionNbStruct> tryGetDecompNb(MethodMaOperation _parent) {
        CustList<MaDecompositionNbStruct> rates_ = new CustList<MaDecompositionNbStruct>();
        int len_ = _parent.getChildren().size();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(_parent, i);
            if (str_ instanceof MaDecompositionNbStruct) {
                rates_.add((MaDecompositionNbStruct)str_);
            }
        }
        return rates_;
    }

    protected static CustList<MaStruct> tryGetAll(MethodMaOperation _this) {
        CustList<MaStruct> rates_ = new CustList<MaStruct>();
        int len_ = _this.getChildren().size();
        for (int i = 0; i < len_; i++) {
            rates_.add(MaNumParsers.tryGet(_this, i));
        }
        return rates_;
    }
    protected static CustList<MaRateStruct> tryGetAllAsRate(MethodMaOperation _current) {
        int len_ = _current.getChildren().size();
        CustList<MaRateStruct> rates_ = new CustList<MaRateStruct>();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(_current, i);
            if (!(str_ instanceof MaRateStruct)) {
                return null;
            }
            rates_.add((MaRateStruct)str_);
        }
        return rates_;
    }
    protected static CustList<MaMatrixStruct> tryGetAllAsMatrix(MethodMaOperation _current) {
        int len_ = _current.getChildren().size();
        CustList<MaMatrixStruct> rates_ = new CustList<MaMatrixStruct>();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(_current, i);
            if (!(str_ instanceof MaMatrixStruct)) {
                continue;
            }
            rates_.add((MaMatrixStruct)str_);
        }
        return rates_;
    }
    protected static CustList<MaDelaunayStruct> tryGetAllAsDelaunay(MethodMaOperation _current) {
        int len_ = _current.getChildren().size();
        CustList<MaDelaunayStruct> rates_ = new CustList<MaDelaunayStruct>();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(_current, i);
            if (!(str_ instanceof MaDelaunayStruct)) {
                continue;
            }
            rates_.add((MaDelaunayStruct)str_);
        }
        return rates_;
    }
    protected static CustList<MaComplexStruct> tryGetAllAsComplex(MethodMaOperation _current) {
        int len_ = _current.getChildren().size();
        CustList<MaComplexStruct> rates_ = new CustList<MaComplexStruct>();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(_current, i);
            if (!(str_ instanceof MaComplexStruct)) {
                continue;
            }
            rates_.add((MaComplexStruct)str_);
        }
        return rates_;
    }
    protected static CustList<MaPolygonStruct> tryGetAllAsPolygon(MethodMaOperation _current) {
        int len_ = _current.getChildren().size();
        CustList<MaPolygonStruct> rates_ = new CustList<MaPolygonStruct>();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(_current, i);
            if (!(str_ instanceof MaPolygonStruct)) {
                continue;
            }
            rates_.add((MaPolygonStruct)str_);
        }
        return rates_;
    }
    protected static CustList<MaCustLineStruct> tryGetAllAsLine(MethodMaOperation _current) {
        int len_ = _current.getChildren().size();
        CustList<MaCustLineStruct> rates_ = new CustList<MaCustLineStruct>();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(_current, i);
            if (!(str_ instanceof MaCustLineStruct)) {
                continue;
            }
            rates_.add((MaCustLineStruct)str_);
        }
        return rates_;
    }
    protected static CustList<MaEdgeStruct> tryGetAllAsEdge(MethodMaOperation _current) {
        int len_ = _current.getChildren().size();
        CustList<MaEdgeStruct> rates_ = new CustList<MaEdgeStruct>();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(_current, i);
            if (!(str_ instanceof MaEdgeStruct)) {
                continue;
            }
            rates_.add((MaEdgeStruct)str_);
        }
        return rates_;
    }
    protected static CustList<MaFractPolStruct> tryGetAllAsFractPol(MethodMaOperation _current) {
        int len_ = _current.getChildren().size();
        CustList<MaFractPolStruct> rates_ = new CustList<MaFractPolStruct>();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(_current, i);
            MaFractPolStruct fract_ = MaFractPolStruct.wrapOrNull(str_);
            if (fract_ == null) {
                return null;
            }
            rates_.add(fract_);
        }
        return rates_;
    }
    protected static CustList<MaRatePointStruct> tryGetAllAsPt(MethodMaOperation _current) {
        int len_ = _current.getChildren().size();
        CustList<MaRatePointStruct> rates_ = new CustList<MaRatePointStruct>();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(_current, i);
            if (!(str_ instanceof MaRatePointStruct)) {
                return null;
            }
            rates_.add((MaRatePointStruct) str_);
        }
        return rates_;
    }
    protected static boolean areAllIntegers(CustList<MaRateStruct> _list) {
        for (MaRateStruct r: _list) {
            if (!r.getRate().isInteger()) {
                return false;
            }
        }
        return true;
    }

    protected static boolean areAllPols(CustList<MaFractPolStruct> _list) {
        for (MaFractPolStruct r: _list) {
            if (!r.getFractPol().isInteger()) {
                return false;
            }
        }
        return true;
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
        return algebreVar(valTwo_)|| StringUtil.quickEq(valTwo_, "|||")|| StringUtil.quickEq(valTwo_, "%%")|| StringUtil.quickEq(valTwo_, "%%%");
    }

    private static boolean algebreVar(String _var) {
        return classicVar(_var)
                || StringUtil.quickEq(_var, "<>-|")
                || StringUtil.quickEq(_var, "=");
    }

    private static boolean classicVar(String _var) {
        return StringUtil.quickEq(_var, ";")
                || StringUtil.quickEq(_var, "&&")
                || StringUtil.quickEq(_var, "?");
    }

    private static boolean isPairSymbol(MaOperationsSequence _op) {
        String valTwo_ = _op.getParts().lastValue().trim();
        return StringUtil.quickEq(valTwo_,"<==")
                ||StringUtil.quickEq(valTwo_,"==>")
                ||StringUtil.quickEq(valTwo_,"==<")
                ||StringUtil.quickEq(valTwo_,">==");
    }

    private static boolean isCmpSymbol(String _val) {
        return StringUtil.quickEq(_val, "<") || StringUtil.quickEq(_val, "<=");
    }
    private static boolean isBinSymbol(MaOperationsSequence _op) {
        String val_ = _op.getParts().lastValue().trim();
        return algebreBin(val_)|| geoBin(val_);
    }

    private static boolean geoBin(String _val) {
        return StringUtil.quickEq(_val, ".")
                ||StringUtil.quickEq(_val, "-")
                ||StringUtil.quickEq(_val, "^^")
                ||StringUtil.quickEq(_val, "^^^");
    }

    private static boolean algebreBin(String _val) {
        return divmod(_val)
                || StringUtil.quickEq(_val, "^") || StringUtil.quickEq(_val, "<=")
                || StringUtil.quickEq(_val, "/%");
    }

    private static boolean divmod(String _val) {
        return StringUtil.quickEq(_val, "/") || StringUtil.quickEq(_val, "%");
    }
    private static boolean isUnarySymbol(MaOperationsSequence _op) {
        String val_ = _op.getParts().lastValue().trim();
        return algebreUn(val_) || StringUtil.quickEq("*", val_);
    }

    private static boolean algebreUn(String _val) {
        return nbOp(_val) || arith(_val)
                || containsOnlySimpleQuotes(_val) > 0
                || StringUtil.quickEq("#", _val);
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
            if (c != '\'') {
                return -1;
            }
            count_++;
        }
        return count_;
    }

    private static boolean nbPartSymbolSgn(String _val) {
        return StringUtil.quickEq(_val, "-") || StringUtil.quickEq(_val, "|");
    }
    private static boolean nbPartSymbol(String _val) {
        return StringUtil.quickEq(_val, "/0") || StringUtil.quickEq(_val, "/1");
    }

    private static boolean nbDecSymbol(String _val) {
        return StringUtil.quickEq(_val, "0/") || StringUtil.quickEq(_val, "1/");
    }

    private static boolean arith(String _val) {
        return StringUtil.quickEq(_val, "/") || StringUtil.quickEq(_val, "//") || StringUtil.quickEq(_val, "||") || StringUtil.quickEq(_val, "&");
    }

    private static boolean isAndOr(MaOperationsSequence _op) {
        String val_ = _op.getParts().lastValue().trim();
        return StringUtil.quickEq(val_,"&") || StringUtil.quickEq(val_,"|");
    }

    static CustList<MaRateStruct> tryGetRates(MethodMaOperation _current) {
        CustList<MaRateStruct> rates_ = new CustList<MaRateStruct>();
        int len_ = _current.getChildren().size();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(_current, i);
            if (str_ instanceof MaRateStruct) {
                rates_.add((MaRateStruct)str_);
            }
        }
        return rates_;
    }
    static CustList<MaFractPolStruct> tryGetFracts(MethodMaOperation _current) {
        CustList<MaFractPolStruct> rates_ = new CustList<MaFractPolStruct>();
        int len_ = _current.getChildren().size();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(_current, i);
            MaFractPolStruct f_ = MaFractPolStruct.wrapOrNull(str_);
            if (f_ != null) {
                rates_.add(f_);
            }
        }
        return rates_;
    }
    protected void processRatesPol(MaError _error, MaFractPolStruct _first, MaStruct _second, IndexStrPart _firstOper) {
        if (_first.getFractPol().isInteger() && _second instanceof MaRateStruct) {
            MaRateStruct second_ = (MaRateStruct) _second;
            Rate rateInd_ = second_.getRate();
            if (!rateInd_.isInteger()) {
                _error.setOffset(getIndexExp() + _firstOper.getIndex());
                return;
            }
            CustList<Rate> numbers_ = _first.getFractPol().getNumerator().getNumbers();
            LgInt intInd_ = rateInd_.intPart();
            if (!intInd_.isZeroOrGt()) {
                intInd_.addNb(new LgInt(numbers_.size()));
            }
            if (!intInd_.isZeroOrGt()) {
                _error.setOffset(getIndexExp() + _firstOper.getIndex());
                return;
            }
            if (LgInt.strLower(intInd_,new LgInt(numbers_.size()))) {
                Rate val_ = numbers_.get((int) intInd_.ll());
                LgInt freq_ = LgInt.minus(new LgInt(numbers_.size()-1L), intInd_);
                setStruct(new MaPolMemberStruct(val_, freq_));
                return;
            }
        }
        _error.setOffset(getIndexExp() + _firstOper.getIndex());
    }

    abstract void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del);

    public final MaOperationNode getNext() {
        return next;
    }
    final void setNext(MaOperationNode _nextSibling) {
        next = _nextSibling;
    }

    static int getNextIndex(MaOperationNode _operation, MaStruct _value) {
        MethodMaOperation par_ = _operation.getPar();
        int index_ = _operation.getChildIndex();
        if (par_ instanceof QuickMaOperation) {
            QuickMaOperation q_ = (QuickMaOperation) par_;
            boolean bs_ = q_.isAbs();
            if (MaBoolStruct.of(bs_).sameReference(_value)) {
                return par_.getOrder();
            }
        }
        if (par_ instanceof FctMaOperation) {
            if (index_ == 1) {
                return par_.getOrder();
            }
            MaOperationNode next_ = _operation.getNext();
            if (index_ == 0 && next_ != null && MaBoolStruct.of(false).sameReference(_value)) {
                return next_.getOrder() + 1;
            }
        }
        return _operation.getOrder() + 1;
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
