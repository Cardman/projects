package code.maths.litteral;

import code.maths.litteralcom.MatCommonCst;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class MbOperationNode {
    public static final String TRUE_STRING = "V";
    public static final String FALSE_STRING = "F";
    public static final String PUIS = "puis";

    public static final String QUOT = "quot";

    public static final String MOD = "mod";

    public static final String MODTAUX = "modtaux";

    public static final String ABS = "abs";

    public static final String ENT = "ent";

    public static final String TRONC = "troncature";

    public static final String NUM = "num";

    public static final String DEN = "den";

    public static final String MIN = "min";

    public static final String MAX = "max";

    public static final String MOY = "moy";

    public static final String VAR = "var";

    public static final String CARAC_FERME = "caracferme";

    public static final String CARAC_OUVERT = "caracouvert";

    public static final String CARAC_SEMI_OUVERT_G = "caracsemiouvertg";

    public static final String CARAC_SEMI_OUVERT_D = "caracsemiouvertd";

    public static final String CARAC_DROITE_OUVERT = "caracdroiteouvert";

    public static final String CARAC_DROITE_FERME = "caracdroiteferme";

    public static final String CARAC_GAUCHE_OUVERT = "caracgaucheouvert";

    public static final String CARAC_GAUCHE_FERME = "caracgaucheferme";

    public static final String SGN = "sgn";

    public static final String CARD = "cardinal";

    public static final String INTER = "inter";

    public static final String UNION = "union";

    public static final String COMPL = "complementaire";

    public static final String INCL = "inclusnum";

    public static final String NON_INCL = "noninclusnum";

    public static final String EQ_NUM = "egalnum";

    public static final String NON_EQ_NUM = "differentnum";

    public static final String DIV_FCT = "div";
    protected static final char DELIMITER_STRING_BEGIN = '{';
    protected static final char DELIMITER_STRING_SEP = ';';
    protected static final char DELIMITER_STRING_END = '}';
    protected static final char PAR_LEFT = '(';
    protected static final char PAR_RIGHT = ')';

    protected static final String NEG_BOOL = "!";

    protected static final String UNARY_MINUS = "-";

    protected static final String MULT = "*";

    protected static final String PLUS = "+";

    protected static final String MINUS = "-";

    protected static final String LOWER_EQ = "<=";

    protected static final String LOWER = "<";

    protected static final String GREATER_EQ = ">=";

    protected static final String GREATER = ">";

    protected static final String EQ = "=";

    protected static final String DIFF = "!=";

    protected static final String OR = "|";

    private final MethodMbOperation parent;

    private MbOperationNode nextSibling;

    private MbArgument argument;

    private final int indexInEl;

    private int order = IndexConstants.INDEX_NOT_FOUND_ELT;

    private final int indexChild;

    private MathType resultClass;


    MbOperationNode(int _indexInEl, int _indexChild, MethodMbOperation _m) {
        parent = _m;
        indexInEl = _indexInEl;
        indexChild = _indexChild;
    }

    abstract void analyze(StringMap<String> _conf, ErrorStatus _error, MbDelimiters _del);
    abstract void calculate(StringMap<String> _conf, ErrorStatus _error);

    static MbOperationNode createOperationNodeAndChild(int _index,int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        return createOperationNode(_index, _indexChild, _m, _op);
    }
    private static MbOperationNode createOperationNode(int _index,
                                               int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        if (_op.getOperators().isEmpty()) {
            return new ConstantMbOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MatCommonCst.FCT_OPER_PRIO) {
            return procFct(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MatCommonCst.UNARY_PRIO) {
            return procUnary(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MatCommonCst.MULT_PRIO) {
            return new MultMbOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MatCommonCst.ADD_PRIO) {
            return new AddMbOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MatCommonCst.CMP_PRIO) {
            return new CmpMbOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MatCommonCst.EQ_PRIO) {
            return new EqMbOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MatCommonCst.AND_PRIO) {
            return new AndMbOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MatCommonCst.OR_PRIO) {
            return new OrMbOperation(_index, _indexChild, _m, _op);
        }
        return null;
    }

    private static PrimitiveBoolMbOperation procUnary(int _index, int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        if (StringUtil.quickEq(_op.getOperators().firstValue().trim(), NEG_BOOL)) {
            return new UnaryBooleanMbOperation(_index, _indexChild, _m, _op);
        }
        return new UnaryMbOperation(_index, _indexChild, _m, _op);
    }

    private static MethodMbOperation procFct(int _index, int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        if (_op.getFctName().trim().isEmpty()) {
            return new IdMbOperation(_index, _indexChild, _m, _op);
        }
        return new FctMbOperation(_index, _indexChild, _m, _op);
    }

    public final MbOperationNode getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(MbOperationNode _nextSibling) {
        nextSibling = _nextSibling;
    }

    static int getNextIndex(MbOperationNode _operation, boolean _value, int _least) {
        MethodMbOperation par_ = _operation.getParent();
        if (par_ instanceof QuickMbOperation) {
            QuickMbOperation q_ = (QuickMbOperation) par_;
            boolean bs_ = q_.absorbingStruct();
            if (bs_ == _value) {
                return NumberUtil.max(_least, par_.getOrder());
            }
        }
        return NumberUtil.max(_least, _operation.getOrder() + 1);
    }
    public MethodMbOperation getParent() {
        return parent;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int _order) {
        order = _order;
    }

    public int getIndexInEl() {
        return indexInEl;
    }

    public int getIndexChild() {
        return indexChild;
    }

    public MbArgument getArgument() {
        return argument;
    }

    public void setArgument(MbArgument _argument) {
        argument = _argument;
    }

    public MathType getResultClass() {
        return resultClass;
    }

    public void setResultClass(MathType _resultClass) {
        resultClass = _resultClass;
    }

}
