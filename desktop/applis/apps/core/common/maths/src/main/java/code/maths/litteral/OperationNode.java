package code.maths.litteral;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

abstract class OperationNode {

    protected static final char DELIMITER_STRING_BEGIN = '{';
    protected static final char DELIMITER_STRING_SEP = ';';
    protected static final char DELIMITER_STRING_END = '}';
    protected static final char PAR_LEFT = '(';
    protected static final char PAR_RIGHT = ')';
    protected static final String TRUE_STRING = "V";
    protected static final String FALSE_STRING = "F";

    protected static final String NEG_BOOL = "!";

    protected static final String UNARY_PLUS = "+";

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

    private final MethodOperation parent;

    private OperationNode nextSibling;

    private Argument argument;

    private final OperationsSequence operations;

    private final int indexInEl;

    private int order = IndexConstants.INDEX_NOT_FOUND_ELT;

    private final int indexChild;

    private MathType resultClass;


    OperationNode(String _el, int _indexInEl, StringMap<String> _importingPage, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        parent = _m;
        indexInEl = _indexInEl;
        operations = _op;
        indexChild = _indexChild;
    }

    abstract void analyze(StringMap<String> _conf, ErrorStatus _error);
    abstract void calculate(StringMap<String> _conf, ErrorStatus _error);

    static OperationNode createOperationNode(String _el, int _index, StringMap<String> _conf,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        if (_op.getOperators().isEmpty()) {
            return new ConstantOperation(_el, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.FCT_OPER_PRIO) {
            if (_op.getFctName().trim().isEmpty()) {
                return new IdOperation(_el, _index, _conf, _indexChild, _m, _op);
            }
            return new FctOperation(_el, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.UNARY_PRIO) {
            int key_ = _op.getOperators().firstKey();
            if (StringUtil.quickEq(_op.getOperators().getVal(key_).trim(), NEG_BOOL)) {
                return new UnaryBooleanOperation(_el, _index, _conf, _indexChild, _m, _op);
            }
            return new UnaryOperation(_el, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.MULT_PRIO) {
            return new MultOperation(_el, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.ADD_PRIO) {
            return new AddOperation(_el, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.CMP_PRIO) {
            return new CmpOperation(_el, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.EQ_PRIO) {
            return new EqOperation(_el, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.AND_PRIO) {
            return new AndOperation(_el, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.OR_PRIO) {
            return new OrOperation(_el, _index, _conf, _indexChild, _m, _op);
        }
        return null;
    }

    public final OperationNode getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(OperationNode _nextSibling) {
        nextSibling = _nextSibling;
    }

    static int getNextIndex(OperationNode _operation, boolean _value) {
        MethodOperation par_ = _operation.getParent();
        if (par_ instanceof QuickOperation) {
            QuickOperation q_ = (QuickOperation) par_;
            boolean bs_ = q_.absorbingStruct();
            if (bs_ == _value) {
                return par_.getOrder();
            }
        }
        return _operation.getOrder() + 1;
    }
    public MethodOperation getParent() {
        return parent;
    }

    public OperationsSequence getOperations() {
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

    public Argument getArgument() {
        return argument;
    }

    public void setArgument(Argument _argument) {
        argument = _argument;
    }

    public MathType getResultClass() {
        return resultClass;
    }

    public void setResultClass(MathType _resultClass) {
        resultClass = _resultClass;
    }

}
