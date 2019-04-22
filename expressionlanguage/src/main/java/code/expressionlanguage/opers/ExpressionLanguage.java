package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.TwoStepsArgumentsPair;
import code.expressionlanguage.opers.exec.*;
import code.util.CustList;
import code.util.IdMap;

public final class ExpressionLanguage {

    private final CustList<ExecOperationNode> operations;
    private final IdMap<ExecOperationNode,ArgumentsPair> arguments;
    private ExecOperationNode currentOper;
    private Argument argument;
    private int index;

    public ExpressionLanguage(CustList<ExecOperationNode> _operations) {
        operations = _operations;
        arguments = buildArguments();
    }

    private IdMap<ExecOperationNode,ArgumentsPair> buildArguments() {
        IdMap<ExecOperationNode,ArgumentsPair> arguments_;
        arguments_ = new IdMap<ExecOperationNode,ArgumentsPair>();
        for (ExecOperationNode o: operations) {
            boolean std_ = true;
            if (o instanceof CallExecSimpleOperation) {
                ExecSettableElResult set_ = ((CallExecSimpleOperation) o).getSettable();
                if (set_ instanceof ExecCustArrOperation) {
                    std_ = false;
                }
            }
            ArgumentsPair a_;
            if (std_) {
                a_ = new ArgumentsPair();
            } else {
                a_ = new TwoStepsArgumentsPair();
            }
            a_.setArgument(o.getArgument());
            if (o instanceof ExecPossibleIntermediateDotted) {
                a_.setPreviousArgument(((ExecPossibleIntermediateDotted)o).getPreviousArgument());
            }
            arguments_.addEntry(o, a_);
        }
        return arguments_;
    }

    public Argument getArgument() {
        return argument;
    }

    public void setArgument(Argument _arg, ContextEl _cont) {
        if (currentOper instanceof CallExecSimpleOperation) {
            ((CallExecSimpleOperation)currentOper).endCalculate(_cont, arguments, _arg);
            index = ExecOperationNode.getNextIndex(currentOper, _arg.getStruct());
            return;
        }
        currentOper.setSimpleArgument(_arg, _cont, arguments);
        index = ExecOperationNode.getNextIndex(currentOper, _arg.getStruct());
    }

    public boolean isFinished() {
        return argument != null;
    }

    public void finish() {
        argument = arguments.lastValue().getArgument();
    }

    public IdMap<ExecOperationNode, ArgumentsPair> getArguments() {
        return arguments;
    }

    public Argument calculateMember(ContextEl _context) {
        return calculateMember(_context, 0);
    }
    public Argument calculateMember(ContextEl _context, int _offset) {
        return ElUtil.tryToCalculate(_context, this, _offset);
    }

    public void setCurrentOper(ExecOperationNode _currentOper) {
        currentOper = _currentOper;
        index = _currentOper.getOrder();
    }
    public int getIndex() {
        return index;
    }
}
