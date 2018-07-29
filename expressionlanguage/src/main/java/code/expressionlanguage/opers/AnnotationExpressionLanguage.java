package code.expressionlanguage.opers;
import code.expressionlanguage.AnnotationUtil;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.InvokingArgumentsPair;
import code.expressionlanguage.methods.util.StandardArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class AnnotationExpressionLanguage {

    private final OperationNode root;
    private final CustList<OperationNode> operations;
    private final boolean alwaysCalculated;
    private final SettableElResult settable;
    private final IdMap<OperationNode,ArgumentsPair> arguments;
    private OperationNode currentOper;
    private Argument argument;

    public AnnotationExpressionLanguage(CustList<OperationNode> _operations) {
        operations = _operations;
        root = operations.last();
        arguments = buildArguments();
        alwaysCalculated = root.isCalculated(arguments);
        settable = buildSettable();
    }

    private SettableElResult buildSettable() {
        return getSettable(operations);
    }

    public static SettableElResult getSettable(CustList<OperationNode> _operations) {
        OperationNode root_ = _operations.last();
        if (root_ instanceof SettableElResult) {
            return (SettableElResult) root_;
        }
        if (_operations.size() > 1){
            OperationNode beforeLast_ = _operations.getPrev(_operations.getLastIndex());
            if (beforeLast_ instanceof SettableElResult) {
                return (SettableElResult) beforeLast_;
            }
        }
        return null;
    }

    private IdMap<OperationNode,ArgumentsPair> buildArguments() {
        IdMap<OperationNode,ArgumentsPair> arguments_;
        arguments_ = new IdMap<OperationNode,ArgumentsPair>();
        for (OperationNode o: operations) {
            if (o instanceof InvokingOperation) {
                ArgumentsPair a_ = new InvokingArgumentsPair();
                a_.setArgument(o.getArgument());
                a_.setPreviousArgument(((PossibleIntermediateDotted)o).getPreviousArgument());
                arguments_.put(o, a_);
                continue;
            }
            ArgumentsPair a_ = new StandardArgumentsPair();
            a_.setArgument(o.getArgument());
            if (o instanceof PossibleIntermediateDotted) {
                a_.setPreviousArgument(((PossibleIntermediateDotted)o).getPreviousArgument());
            }
            arguments_.put(o, a_);
        }
        return arguments_;
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

    public Argument calculateMember(ContextEl _context) {
        return calculateMember(_context, 0);
    }
    public Argument calculateMember(ContextEl _context, int _offset) {
        return AnnotationUtil.tryToCalculate(_context, this, _offset);
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
