package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CallPrepareState;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecCastOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendInterfaceFctConstructor extends RendInvokingOperation implements RendCalculableOperation {
    private String className;

    private ExecInvokingConstructorContent invokingConstructorContent;

    private ExecTypeFunction pair;
    public RendInterfaceFctConstructor(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, String _className, ExecTypeFunction _pair) {
        super(_content, _intermediateDottedOperation);
        invokingConstructorContent = _invokingConstructorContent;
        className = _className;
        pair = _pair;

    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        RendDynOperationNode main_ = getMainNode(this);
        ArgumentsPair pair_ = getArgumentPair(_nodes, main_);
        if (getIndexChild() == 1) {
            //init and test
            Argument lda_ = new Argument(Argument.getNullableValue(pair_.getArgument()).getStruct());
            if (!ExecTemplates.checkObject(_context.getStandards().getContent().getReflect().getAliasFct(), lda_, _context)) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes, _context);
                return;
            }
            String form_ = className;
            Argument ref_ = new Argument(lda_.getStruct());
            ExecCastOperation.wrapFct(form_,true, _context, ref_);
            if (!ExecTemplates.checkObject(form_, ref_, _context)) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes, _context);
                return;
            }
            pair_.setArgument(ref_);
            Argument argres_ = RendDynOperationNode.processCall(getArgument(_nodes, ref_, _conf, _context), _context);
            setSimpleArgument(argres_,_conf,_nodes, _context);
            return;
        }
        Argument argres_ = RendDynOperationNode.processCall(getArgument(_nodes, Argument.getNullableValue(pair_.getArgument()), _conf, _context), _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }
    Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all, Argument _arguments, Configuration _conf, ContextEl _context) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ invokingConstructorContent.getOffsetOper(), _conf);
        String superClass_ = invokingConstructorContent.getClassFromName();
        String lastType_ = getLastType();
        lastType_ = ExecTemplates.quickFormat(pair.getType(),superClass_, lastType_);
        int natvararg_ = getNaturalVararg();
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, chidren_).getArguments();
        CustList<Argument> firstArgs_ = listArguments(chidren_, natvararg_, lastType_, first_);
        ExecInvokingOperation.checkParameters(_context, superClass_, pair, _arguments,null, firstArgs_,CallPrepareState.CTOR, InstancingStep.USING_SUPER,null, MethodAccessKind.INSTANCE);
        return Argument.createVoid();
    }

    public String getLastType() {
        return invokingConstructorContent.getLastType();
    }

    public int getNaturalVararg() {
        return invokingConstructorContent.getNaturalVararg();
    }

}
