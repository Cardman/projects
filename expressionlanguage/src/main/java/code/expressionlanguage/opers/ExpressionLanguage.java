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
    private SettableElResult settable;
    private OperationNode currentOper;
    private IdMap<OperationNode,ArgumentsPair> arguments = new IdMap<OperationNode,ArgumentsPair>();
    private Argument argument;

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
        if (root instanceof SettableElResult) {
            settable = (SettableElResult) root;
        } else if (operations.size() > 1){
            OperationNode beforeLast_ = operations.getPrev(operations.getLastIndex());
            if (beforeLast_ instanceof SettableElResult) {
                settable = (SettableElResult) beforeLast_;
            }
        }
        alwaysCalculated = root.isCalculated(arguments);
    }

    public SettableElResult getSettable() {
        return settable;
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

    public Argument calculateMember(ContextEl _context) {
        return ElUtil.tryToCalculate(_context, this);
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
