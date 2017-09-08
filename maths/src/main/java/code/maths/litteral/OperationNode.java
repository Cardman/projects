package code.maths.litteral;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

abstract class OperationNode {

    protected static final char ESCAPE_META_CHAR = '\\';
    protected static final char DELIMITER_CHAR = 39;
    protected static final char DELIMITER_STRING_BEGIN = '{';
    protected static final char DELIMITER_STRING_SEP = ';';
    protected static final char DELIMITER_STRING_END = '}';
    protected static final char UNICODE = 'u';
    protected static final char IND_FORM = 'f';
    protected static final char IND_LINE = 'n';
    protected static final char IND_LINE_FEED = 'r';
    protected static final char IND_TAB = 't';
    protected static final char IND_BOUND = 'b';
    protected static final char ARR_LEFT = '[';
    protected static final char ARR_RIGHT = ']';
    protected static final char PAR_LEFT = '(';
    protected static final char PAR_RIGHT = ')';
    protected static final String PAR_RIGHT_STR = ")";
    protected static final char SEP_ARG = ',';
    protected static final char FIRST_VAR_ARG = '?';
    protected static final char GET_VAR = ';';
    protected static final char DOT_VAR = '.';
    protected static final char EXTERN_CLASS = '^';
    protected static final char INTERN_CLASS = '$';
    protected static final String INSTANCE = "new";
    protected static final String STATIC_ACCESS = "static";
    protected static final String NULL_REF_STRING = "null";
    protected static final String TRUE_STRING = "V";
    protected static final String FALSE_STRING = "F";
    protected static final String INSTANCEOF = "instanceof";
    protected static final String CAST = "class";
    protected static final char MIN_ENCODE_DIGIT = '0';
    protected static final char MAX_ENCODE_DIGIT = '9';
    protected static final char MIN_ENCODE_LOW_LETTER = 'a';
    protected static final char MAX_ENCODE_LOW_LETTER = 'f';
    protected static final char MIN_ENCODE_UPP_LETTER = 'A';
    protected static final char MAX_ENCODE_UPP_LETTER = 'F';
    protected static final String GET_INDEX = ";;";
    protected static final String GET_LOC_VAR = ";.";
    protected static final String GET_ATTRIBUTE = ";";
    protected static final char MATH_INTERPRET = '`';
    protected static final String GET_PARAM = ";.;";

    protected static final String FCT = "(";

    protected static final String ARR = "[";

    protected static final String DOT = ".";

    protected static final String NEG_BOOL = "!";

    protected static final String UNARY_PLUS = "+";

    protected static final String UNARY_MINUS = "-";

    protected static final String MULT = "*";

    protected static final String DIV = ":";

    protected static final String PLUS = "+";

    protected static final String MINUS = "-";

    protected static final String LOWER_EQ = "<=";

    protected static final String LOWER = "<";

    protected static final String GREATER_EQ = ">=";

    protected static final String GREATER = ">";

    protected static final String EQ = "=";

    protected static final String DIFF = "!=";

    protected static final String AND = "&";

    protected static final String OR = "|";
    protected static final String EMPTY_SET = "vide";
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

//    private static final String RETURN_LINE = "\n";
//    private static final String SPACE = " ";
//    private static final String RETURN_TAB = RETURN_LINE+"\t";

    private MethodOperation parent;

    private boolean initializedNextSibling;

    private OperationNode nextSibling;

    private Argument previousArgument;

    private Argument argument;

    private OperationsSequence operations;

    private int indexInEl;

    private int order = CustList.INDEX_NOT_FOUND_ELT;

//    private ImportingPage importingPage;

    private StringMap<String> conf;

    private final int indexChild;

//    private boolean vararg;
//    private boolean firstOptArg;
    private MathType previousResultClass;
    private MathType resultClass;
//    private boolean calulated;
    private boolean needPrevious;

//    private boolean booleanPrio;

//    private boolean childrenWithBoolPrio;

    OperationNode(String _el, int _indexInEl, StringMap<String> _importingPage, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        parent = _m;
        indexInEl = _indexInEl;
        operations = _op;
        conf = _importingPage;
//        vararg = _el.trim().startsWith(String.valueOf(FIRST_VAR_ARG));
//        firstOptArg = _el.trim().endsWith(String.valueOf(FIRST_VAR_ARG));
//        firstOptArg = _op.isFirstOpt();
        indexChild = _indexChild;
//        if (indexChild == 7)
//        System.out.println(_el);
    }

//    OperationNode(String _el, int _indexInEl, Map<String,String> _importingPage, MethodOperation _m, OperationsSequence _op) {
//        parent = _m;
//        indexInEl = _indexInEl;
//        operations = _op;
//        conf = _importingPage;
//        vararg = _el.trim().startsWith(String.valueOf(FIRST_VAR_ARG));
////        firstOptArg = _el.trim().endsWith(String.valueOf(FIRST_VAR_ARG));
//        firstOptArg = _op.isFirstOpt();
//    }
    abstract void analyze(CustList<OperationNode> _nodes, StringMap<String> _conf);
    abstract void calculate(CustList<OperationNode> _nodes, StringMap<String> _conf);

    static OperationNode createOperationNode(String _el, int _index, StringMap<String> _conf,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        String value_ = _el;
        if (_op.getOperators().isEmpty()) {
            return new ConstantOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.FCT_OPER_PRIO) {
            if (_op.getFctName().trim().isEmpty()) {
                return new IdOperation(value_, _index, _conf, _indexChild, _m, _op);
            }
//            if (_op.getFctName().trim().startsWith(INSTANCE+DOT_VAR)) {
//                return new InstanceOperation(value_, _index, _conf, _indexChild, _m, _op);
//            }
            return new FctOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
//        if (_op.getPriority() == MathResolver.ARR_OPER_PRIO) {
//            return new ArrOperation(value_, _index, _conf, _indexChild, _m, _op);
//        }
//        if (_op.getPriority() == MathResolver.DOT_PRIO) {
//            return new DotOperation(value_, _index, _conf, _indexChild, _m, _op);
//        }
        if (_op.getPriority() == MathResolver.UNARY_PRIO) {
            int key_ = _op.getOperators().firstKey();
            if (StringList.quickEq(_op.getOperators().getVal(key_).trim(), NEG_BOOL)) {
                return new UnaryBooleanOperation(value_, _index, _conf, _indexChild, _m, _op);
            }
            return new UnaryOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.MULT_PRIO) {
            return new MultOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.ADD_PRIO) {
            return new AddOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.CMP_PRIO) {
            return new CmpOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.EQ_PRIO) {
            return new EqOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.AND_PRIO) {
            return new AndOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == MathResolver.OR_PRIO) {
            return new OrOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        return null;
    }

//    boolean isDirectlyCalculable() {
//        return isRealLeaf() && isFirstChild();
//    }
//    abstract boolean isFirstLeaf();
    abstract boolean isFirstChild();
//    abstract boolean isRealLeaf();

    boolean isAnalyzed() {
        return resultClass != null;
    }

    boolean isCalculated() {
//        if (calulated) {
//            return true;
//        }
        OperationNode op_ = this;
        while (op_ != null) {
            if (op_.getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    public abstract OperationNode getFirstChild();

    public OperationNode getNextSibling() {
        if (initializedNextSibling) {
            return nextSibling;
        }
        initializedNextSibling = true;
        MethodOperation p_ = getParent();
        if (p_ == null) {
            return null;
        }
        NatTreeMap<Integer,String> children_ = p_.getChildren();
        if (indexChild + 1 >= children_.size()) {
            return null;
        }
        String value_ = children_.getValue(indexChild + 1);
//        OperationsSequence r_ = MathResolver.getOperationsSequence(indexInEl, value_, conf);
        Delimiters d_ = getOperations().getDelimiter();
        int curKey_ = children_.getKey(indexChild + 1);
        d_.setChildOffest(curKey_);
        OperationsSequence r_ = MathResolver.getOperationsSequence(p_.getIndexInEl(), value_, conf, d_);
//        if (r_.getOperators().isEmpty()) {
//            nextSibling = new ConstantOperation(value_, children_.getKey(indexChild + 1), importingPage, p_, r_, indexChild + 1);
//            return nextSibling;
//        }
//        if (r_.getPriority() == ExpressionLanguageResolver.getMaxPriority()) {
//            nextSibling = new FctOperation(value_, children_.getKey(indexChild + 1), importingPage, indexChild + 1, p_, r_);
//            return nextSibling;
//        }
//        if (r_.getPriority() == ExpressionLanguageResolver.ARR_OPER_PRIO) {
//            nextSibling = new ArrOperation(value_, children_.getKey(indexChild + 1), importingPage, indexChild + 1, p_, r_);
//            return nextSibling;
//        }
        nextSibling = createOperationNode(value_, p_.getIndexInEl()+curKey_, conf, indexChild + 1, p_, r_);
        return nextSibling;
    }

    static MathType[] getClasses(Argument... _args) {
        int len_ = _args.length;
        MathType[] classes_ = new MathType[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getArgClass();
        }
        return classes_;
    }

    static Object[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Object[] classes_ = new Object[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
//            _expected.get(i);
            classes_[i] = _args[i].getObject();
        }
        return classes_;
    }

    void setNextSiblingsArg(Argument _arg) {
        Object o_ = _arg.getObject();
        if (_arg.getArgClass() != MathType.BOOLEAN) {
            return;
        }
        Boolean b_ = (Boolean) o_;
        OperationNode par_ = getParent();
        if (b_ && par_ instanceof OrOperation) {
            CustList<OperationNode> opers_ = new CustList<OperationNode>();
            for (OperationNode s: MathUtil.getDirectChildren(par_)) {
                opers_.add(s);
            }
            int len_ = opers_.size();
            for (int i = getIndexChild() + 1; i < len_; i++) {
                opers_.get(i).setArgument(_arg);
            }
        } else if (!b_ && par_ instanceof AndOperation) {
            CustList<OperationNode> opers_ = new CustList<OperationNode>();
            for (OperationNode s: MathUtil.getDirectChildren(par_)) {
                opers_.add(s);
            }
            int len_ = opers_.size();
            for (int i = getIndexChild() + 1; i < len_; i++) {
                opers_.get(i).setArgument(_arg);
            }
        }
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

    public StringMap<String> getConf() {
        return conf;
    }

//    public ImportingPage getImportingPage() {
//        return importingPage;
//    }

//    public boolean isVararg() {
//        return vararg;
//    }
//
//    public boolean isFirstOptArg() {
//        return firstOptArg;
//    }

    public int getIndexInEl() {
        return indexInEl;
    }

    public int getIndexChild() {
        return indexChild;
    }

    public Argument getPreviousArgument() {
        return previousArgument;
    }

    public void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }

    public Argument getArgument() {
        return argument;
    }

    public void setArgument(Argument _argument) {
        argument = _argument;
        OperationNode n_ = getNextSibling();
        if (n_ != null) {
            n_.setPreviousArgument(_argument);
        }
    }

    public MathType getPreviousResultClass() {
        return previousResultClass;
    }

    public void setPreviousResultClass(MathType _previousResultClass) {
        previousResultClass = _previousResultClass;
    }

    public MathType getResultClass() {
        return resultClass;
    }

    public void setResultClass(MathType _resultClass) {
        resultClass = _resultClass;
        OperationNode n_ = getNextSibling();
        if (n_ != null) {
            n_.setPreviousResultClass(resultClass);
        }
    }

    public boolean isNeedPrevious() {
        return needPrevious;
    }

    public void setNeedPrevious(boolean _needPrevious) {
        needPrevious = _needPrevious;
    }

//    public boolean isCalulated() {
//        return calulated;
//    }
//
//    public void setCalulated(boolean _calulated) {
//        calulated = _calulated;
//    }
}
