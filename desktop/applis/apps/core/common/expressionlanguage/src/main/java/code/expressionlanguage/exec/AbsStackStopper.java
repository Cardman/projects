package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public interface AbsStackStopper {
    StepDbgActionEnum firstStep();
    boolean firstEnter(AbstractPageEl _page);
    boolean stopAt(AbstractPageEl _page, StackCall _stack, int _size);
    boolean stopAt(ContextEl _context, StackCall _stack, int _size);
    boolean stopBreakPoint(ContextEl _context, StackCall _stackCall);
    boolean isCheckingException(StackCall _stackCall);
    boolean hasFoundException(StackCall _stackCall);
    ExpressionLanguageBp checkBpWithoutClear(StackCall _stack, int _index, AbstractPageEl _ip, CustList<ExecOperationNode> _list, ExecBlock _bl);

    boolean isStopAt(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, StackCall _stackCall);
    boolean isChecking(ExpressionLanguage _o,ContextEl _context, StackCall _stackCall);
    boolean isStopAtRefGetField(FieldMetaInfo _meta, Struct _instance, ContextEl _context, StackCall _stackCall);
    boolean isStopAtRefSetField(FieldMetaInfo _meta, Struct _instance, Struct _right, ContextEl _context, StackCall _stackCall);
    boolean isStopAtRefGetVar(ArgumentListCall _meta, ContextEl _context, StackCall _stackCall);
    boolean isStopAtRefSetVar(ArgumentListCall _meta, ContextEl _context, StackCall _stackCall);
}
