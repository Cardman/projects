package code.bean.nat.exec.opers;

import code.bean.nat.BeanNatCommonLgNames;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.bean.nat.fwd.opers.NatExecStdFctContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendCalculableOperation;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class NatStdFctOperation extends NatSettableCallFctOperation implements RendCalculableOperation {

    private final NatExecStdFctContent stdFctContent;
    private final ClassMethodId classMethodId;

    public NatStdFctOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, NatExecStdFctContent _stdFctContent) {
        super(_content, _intermediateDottedOperation);
        stdFctContent = _stdFctContent;
        classMethodId = new ClassMethodId(_stdFctContent.getFoundClass(), _stdFctContent.getStandardMethod().getId());
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        int off_ = StringUtil.getFirstPrintableCharIndex(getMethodName());
        setRelOffsetPossibleLastPage(off_, _rendStack);
        CustList<Argument> firstArgs_ = RendDynOperationNode.getArguments(_nodes,this);
        Struct[] args_ = ExecHelper.getObjects(Argument.toArgArray(firstArgs_));
        ArgumentWrapper argres_ = new ArgumentWrapper(new Argument(((BeanNatCommonLgNames)_advStandards).getOtherResultLoc( _context, previous_.getStruct(),classMethodId, args_)), null);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

    public String getMethodName() {
        return stdFctContent.getMethodName();
    }

}
