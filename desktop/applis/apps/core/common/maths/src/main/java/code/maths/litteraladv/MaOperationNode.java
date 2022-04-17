package code.maths.litteraladv;

import code.maths.Complex;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteralcom.MatCommonCst;
import code.maths.litteralcom.MatConstType;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class MaOperationNode {

    protected static final String POLYNOM_SYMB = MaOperationsSequence.POLYNOM_SYMB;
    protected static final String POLYGON_SYMBOL = MaOperationsSequence.POLYGON_SYMBOL;
    protected static final String POINT = MaOperationsSequence.POINT;
    protected static final String EDGE = MaOperationsSequence.EDGE;
    protected static final String LINE_THREE = MaOperationsSequence.LINE_THREE;
    protected static final String ASSOC = MaOperationsSequence.ASSOC;
    protected static final String EVT = MaOperationsSequence.EVT;

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
        return createOperationNode(_index, _indexChild, _m, _op, _mapping);
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
        if (_op.getPrio() == MatCommonCst.ASS_PRIO && _op.getParts().size() == 2) {
            if (StringUtil.quickEq(_op.getOpers().firstValue(), ASSOC)) {
                return new PolInterMaOperation(_index, _indexChild, _m, _op);
            }
            if (_m instanceof SymbVarFctMaOperation && StringUtil.quickEq(((SymbVarFctMaOperation) _m).getOper(), POLYNOM_SYMB)) {
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
        if (_op.getType() == MatConstType.LOC_VAR) {
            return new VariableMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getType() == MatConstType.NUMBER) {
            return new ConstantMaOperation(_index, _indexChild, _m, _op);
        }
        return null;
    }

    private static MethodMaOperation procUnary(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        if (StringUtil.quickEq(_op.getOpers().firstValue().trim(), MaOperationsSequence.NEG_BOOL)) {
            return new UnaryBooleanMaOperation(_index, _indexChild, _m, _op);
        }
        return new UnaryMaOperation(_index, _indexChild, _m, _op);
    }

    private static MethodMaOperation procFct(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, MaParameters _mapping) {
        if (_op.isEmptyFct()) {
            if (StringUtil.quickEq(_op.getOpers().firstValue(), MaOperationsSequence.MATRIX)) {
                return new MatrixMaOperation(_index, _indexChild, _m, _op);
            }
            return procSymb(_index, _indexChild, _m, _op,_mapping);
        }
        return new FctMaOperation(_index, _indexChild, _m, _op);
    }

    private static MethodMaOperation procSymb(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, MaParameters _mapping) {
        if (StringUtil.quickEq(_op.getOpers().firstValue(), MaOperationsSequence.ARR)) {
            return new ArrMaOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getParts().size() == 0 && isAndOr(_op)) {
            return new SymbGeneMaOperation(_index, _indexChild, _m, _op, _op.getFct());
        }
        if (isVarSymbol(_op)) {
            return new SymbVarFctMaOperation(_index, _indexChild, _m, _op,_mapping, _op.getOffset(), _op.getFct());
        }
        if (_op.getParts().size() == 1 && isUnarySymbol(_op)) {
            return new SymbUnFctMaOperation(_index, _indexChild, _m, _op, _op.getOffset(), _op.getFct());
        }
        if (_op.getParts().size() == 2 && isBinSymbol(_op)) {
            return new SymbBinFctMaOperation(_index, _indexChild, _m, _op, _op.getOffset(), _op.getFct());
        }
        return defSymb(_index, _indexChild, _m, _op);
    }

    private static MethodMaOperation defSymb(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        if (_op.getParts().size() == 2 && isPairSymbol(_op)) {
            return new SymbCaracFctMaOperation(_index, _indexChild, _m, _op, _op.getOffset(), _op.getFct());
        }
        if (_op.getParts().size() == 3 && StringUtil.quickEq(_op.getFct().trim(), LINE_THREE)){
            return new SymbTerFctMaOperation(_index, _indexChild, _m, _op, _op.getOffset(), _op.getFct());
        }
        if (_op.getParts().size() == 3 && areBinarySymbols(_op)) {
            StringList list_ = StringUtil.splitChar(_op.getFct(), '_');
            String val_ = list_.first().trim();
            String valTwo_ = list_.last().trim();
            int off_ = _op.getOffset();
            return new SymbDoubleCaracFctMaOperation(_index, _indexChild, _m, _op, valTwo_, off_, val_);
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
        StringList list_ = StringUtil.splitChar(_op.getFct(), '_');
        String val_ = list_.first();
        String valTwo_ = list_.last();
        return MaOperationsSequence.areBinarySymbols(val_,valTwo_);
    }

    private static boolean isVarSymbol(MaOperationsSequence _op) {
        String valTwo_ = _op.getFct();
        return MaOperationsSequence.isVarSymbol(valTwo_);
    }

    private static boolean isPairSymbol(MaOperationsSequence _op) {
        String valTwo_ = _op.getFct();
        return MaOperationsSequence.isPairSymbol(valTwo_);
    }

    private static boolean isBinSymbol(MaOperationsSequence _op) {
        String val_ = _op.getFct();
        return MaOperationsSequence.isBinSymbol(val_);
    }

    private static boolean isUnarySymbol(MaOperationsSequence _op) {
        String val_ = _op.getFct();
        return MaOperationsSequence.isUnarySymbol(val_);
    }

    protected static int containsOnlySimpleQuotes(String _val) {
        return MaOperationsSequence.containsOnlySimpleQuotes(_val);
    }

    private static boolean isAndOr(MaOperationsSequence _op) {
        String val_ = _op.getFct();
        return MaOperationsSequence.isAndOr(val_);
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
