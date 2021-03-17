package code.maths.litteral;
import code.maths.litteralcom.MatCommonCst;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

abstract class MbOperationNode {

    protected static final char DELIMITER_STRING_BEGIN = '{';
    protected static final char DELIMITER_STRING_SEP = ';';
    protected static final char DELIMITER_STRING_END = '}';
    protected static final char PAR_LEFT = '(';
    protected static final char PAR_RIGHT = ')';
    protected static final String TRUE_STRING = "V";
    protected static final String FALSE_STRING = "F";

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
    protected static final String PUIS = "puis";

    protected static final String QUOT = "quot";

    protected static final String MOD = "mod";

    protected static final String MODTAUX = "modtaux";

    protected static final String ABS = "abs";

    protected static final String ENT = "ent";

    protected static final String TRONC = "troncature";

    protected static final String NUM = "num";

    protected static final String DEN = "den";

    protected static final String MIN = "min";

    protected static final String MAX = "max";

    protected static final String MOY = "moy";

    protected static final String VAR = "var";

    protected static final String CARAC_FERME = "caracferme";

    protected static final String CARAC_OUVERT = "caracouvert";

    protected static final String CARAC_SEMI_OUVERT_G = "caracsemiouvertg";

    protected static final String CARAC_SEMI_OUVERT_D = "caracsemiouvertd";

    protected static final String CARAC_DROITE_OUVERT = "caracdroiteouvert";

    protected static final String CARAC_DROITE_FERME = "caracdroiteferme";

    protected static final String CARAC_GAUCHE_OUVERT = "caracgaucheouvert";

    protected static final String CARAC_GAUCHE_FERME = "caracgaucheferme";

    protected static final String SGN = "sgn";

    protected static final String CARD = "cardinal";

    protected static final String INTER = "inter";

    protected static final String UNION = "union";

    protected static final String COMPL = "complementaire";

    protected static final String INCL = "inclusnum";

    protected static final String NON_INCL = "noninclusnum";

    protected static final String EQ_NUM = "egalnum";

    protected static final String NON_EQ_NUM = "differentnum";

    protected static final String DIV_FCT = "div";

    private final MethodMbOperation parent;

    private MbOperationNode nextSibling;

    private MbArgument argument;

    private final MbOperationsSequence operations;

    private final int indexInEl;

    private int order = IndexConstants.INDEX_NOT_FOUND_ELT;

    private final int indexChild;

    private MathType resultClass;


    MbOperationNode(int _indexInEl, int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        parent = _m;
        indexInEl = _indexInEl;
        operations = _op;
        indexChild = _indexChild;
    }

    abstract void analyze(StringMap<String> _conf, ErrorStatus _error, MbDelimiters _del);
    abstract void calculate(StringMap<String> _conf, ErrorStatus _error);

    static MbOperationNode createOperationNodeAndChild(int _index,int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        MbOperationNode created_ = createOperationNode(_index, _indexChild, _m, _op);
        if (created_ instanceof MethodMbOperation) {
            ((MethodMbOperation)created_).calculateChildren();
        }
        return created_;
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
                return Math.max(_least, par_.getOrder());
            }
        }
        return Math.max(_least, _operation.getOrder() + 1);
    }
    public MethodMbOperation getParent() {
        return parent;
    }

    public MbOperationsSequence getOperations() {
        return operations;
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
