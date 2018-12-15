package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.StandardArgumentsPair;
import code.util.CustList;
import code.util.IdMap;

public final class ExpressionLanguage {

    private final CustList<OperationNode> operations;
    private final IdMap<OperationNode,ArgumentsPair> arguments;
    private OperationNode currentOper;
    private Argument argument;
    private int index;

    public ExpressionLanguage(CustList<OperationNode> _operations) {
        operations = _operations;
        arguments = buildArguments();
    }

    private IdMap<OperationNode,ArgumentsPair> buildArguments() {
        IdMap<OperationNode,ArgumentsPair> arguments_;
        arguments_ = new IdMap<OperationNode,ArgumentsPair>();
        for (OperationNode o: operations) {
            ArgumentsPair a_ = new StandardArgumentsPair();
            a_.setArgument(o.getArgument());
            if (o instanceof PossibleIntermediateDotted) {
                a_.setPreviousArgument(((PossibleIntermediateDotted)o).getPreviousArgument());
            }
            arguments_.put(o, a_);
        }
        return arguments_;
    }

    public Argument getArgument() {
        return argument;
    }

    public void setArgument(Argument _arg, ContextEl _cont) {
        if (currentOper instanceof CallSimpleOperation) {
            ((CallSimpleOperation)currentOper).endCalculate(_cont, arguments, _arg);
            index = ElUtil.getNextIndex(currentOper, _arg.getStruct());
            return;
        }
        currentOper.setSimpleArgument(_arg, _cont, arguments);
        index = ElUtil.getNextIndex(currentOper, _arg.getStruct());
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
        return ElUtil.tryToCalculate(_context, this, _offset);
    }
    public CustList<OperationNode> getOperations() {
        return operations;
    }

    public OperationNode getCurrentOper() {
        return currentOper;
    }

    public void setCurrentOper(OperationNode _currentOper) {
        currentOper = _currentOper;
        index = _currentOper.getOrder();
    }
    public int getIndex() {
        return index;
    }
}
