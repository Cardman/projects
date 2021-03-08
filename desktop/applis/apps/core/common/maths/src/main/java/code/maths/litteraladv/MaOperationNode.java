package code.maths.litteraladv;

import code.maths.litteralcom.MatCommonCst;
import code.maths.litteralcom.MatConstType;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class MaOperationNode {
    protected static final char PAR_LEFT = '(';
    protected static final char PAR_RIGHT = ')';
    protected static final String TRUE_STRING = "vrai";
    protected static final String FALSE_STRING = "faux";

    protected static final String NEG_BOOL = "!";

    protected static final String UNARY_MINUS = "-";

    protected static final String MULT = "*";

    protected static final String PLUS = "+";

    protected static final String MINUS = "-";

    protected static final String EQ = "=";

    protected static final String DIFF = "!=";

    protected static final String OR = "|";
    protected static final String PUIS = "puis";

    protected static final String QUOT = "quot";

    protected static final String MOD = "mod";

    protected static final String MODTAUX = "modtaux";

    protected static final String BEZOUT = "bezout";

    protected static final String SGN = "sgn";

    protected static final String ABS = "abs";

    protected static final String ENT = "ent";

    protected static final String TRONC = "troncature";

    protected static final String NUM = "num";

    protected static final String DEN = "den";

    protected static final String LG = "lg";

    protected static final String PREM = "prem";

    protected static final String DIVS = "divs";

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
                                               int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, StringMap<String> _mapping) {
        MaOperationNode created_ = createOperationNode(_index, _indexChild, _m, _op, _mapping);
        if (created_ instanceof MethodMaOperation) {
            ((MethodMaOperation)created_).calculate();
        }
        return created_;
    }
    private static MaOperationNode createOperationNode(int _index,
                                               int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, StringMap<String> _mapping) {
        if (_op.getOpers().isEmpty()) {
            return processLeaf(_index, _indexChild, _m, _op);
        }
        if (_op.getPrio() == MatCommonCst.FCT_OPER_PRIO) {
            return procFct(_index, _indexChild, _m, _op, _mapping);
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

    private static MethodMaOperation procFct(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, StringMap<String> _mapping) {
        if (_op.getFct().trim().isEmpty()) {
            if (StringUtil.quickEq(_op.getOpers().firstValue(),"[")) {
                return new ArrMaOperation(_index, _indexChild, _m, _op);
            }
            return new IdMaOperation(_index, _indexChild, _m, _op);
        }
        return new FctMaOperation(_index, _indexChild, _m, _op, _mapping);
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
        if (par_ instanceof QuickMaOperation) {
            QuickMaOperation q_ = (QuickMaOperation) par_;
            boolean bs_ = q_.isAbs();
            if (MaBoolStruct.of(bs_).sameReference(_value)) {
                return par_.getOrder();
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
