package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExpressionLanguage {

    private static final String EMPTY_STRING = "";
    private final String expression;
    private final OperationNode root;
    private final CustList<OperationNode> operations;
    private final boolean alwaysCalculated;
    private OperationNode currentOper;
    private IdMap<OperationNode,ArgumentsPair> arguments = new IdMap<OperationNode,ArgumentsPair>();
    private Argument argument;

//    public ExpressionLanguage(OperationNode _operationNode) {
//        root = _operationNode;
//        operations = getOperations();
//    }

//    public ExpressionLanguage(String _expression, ContextEl _context, boolean _staticAccess, Calculation _setting) {
//        expression = _expression;
////        root = OperationNode.createOperationNode(_expression, _index, _context, _indexChild, null, _op);
//        operations = ElUtil.getAnalyzedOperations(_expression, _context, _staticAccess, _setting);
//        for (OperationNode o: operations) {
//            arguments.put(o, new ArgumentsPair());
//        }
//        root = operations.last();
//    }

    public ExpressionLanguage(CustList<OperationNode> _operations) {
        expression = EMPTY_STRING;
        operations = _operations;
        for (OperationNode o: operations) {
            ArgumentsPair a_ = new ArgumentsPair();
            a_.setArgument(o.getArgument());
            a_.setPreviousArgument(o.getPreviousArgument());
            arguments.put(o, a_);
        }
        root = operations.last();
//        alwaysCalculated = root.isCalculated(new Calculation(StepCalculation.RIGHT), arguments);
        alwaysCalculated = root.isCalculated(arguments);
    }

    public Argument getArgument() {
        return argument;
    }

    public void setArgument(Argument _arg, ContextEl _cont) {
        arguments.getVal(currentOper).setArgument(_arg);
        currentOper.setSimpleArgument(_arg, _cont, arguments);
    }

    public boolean isFinished() {
        return argument != null;
    }

    public void finish() {
        argument = arguments.lastValue().getArgument();
    }

    public IdMap<OperationNode, ArgumentsPair> getArguments() {
        return arguments;
    }

    public String getExpression() {
        return expression;
    }
    
//    public Argument calculateMemberPrep(ContextEl _context) {
//        return ElUtil.tryToCalculatePrep(_context, this);
//    }

    public Argument calculateMember(ContextEl _context) {
        return ElUtil.tryToCalculate(_context, this);
    }
    public void affectMember(ContextEl _context, ExpressionLanguage _right, String _oper) {
        ElUtil.tryToCalculateAffect(this, _context, _right, _oper);
    }
    public void affectLeftMember(ContextEl _context, String _oper) {
        ElUtil.tryToCalculateLeftAffect(this, _context, _oper);
    }
    public void affectRightMember(ContextEl _context, String _oper) {
        ElUtil.tryToCalculateRightAffect(this, _context, _oper);
    }
    public void affectAllMember(ContextEl _context, String _oper) {
        ElUtil.tryToCalculateAllAffect(this, _context, _oper);
    }
    public CustList<OperationNode> getOperations() {
//        List<OperationNode> l_ = new List<OperationNode>();
//        for (SortedNode<OperationNode> s: TreeRetrieving.getSortedDescNodes(root)) {
//            l_.add((OperationNode) s);
//        }
        return operations;
    }

    public ClassArgumentMatching getClassArgumentMatching() {
        return root.getResultClass();
    }

    public boolean isAlwaysCalculated() {
        return alwaysCalculated;
    }

    public Struct getConstValue() {
        return root.getArgument().getStruct();
    }

    public OperationNode getRoot() {
        return root;
    }

    public OperationNode getCurrentOper() {
        return currentOper;
    }

    public void setCurrentOper(OperationNode _currentOper) {
        currentOper = _currentOper;
    }

}
