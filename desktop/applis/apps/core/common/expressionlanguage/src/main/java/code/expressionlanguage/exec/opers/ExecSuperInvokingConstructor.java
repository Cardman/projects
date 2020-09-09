package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.analyze.opers.SuperInvokingConstructor;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.util.CustList;
import code.util.IdMap;

public final class ExecSuperInvokingConstructor extends ExecAbstractInvokingConstructor {

    public ExecSuperInvokingConstructor(SuperInvokingConstructor _super, ContextEl _context) {
        super(_super,_context);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument res_ = getArgument(_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);

        PageEl page_ = _conf.getLastPage();
        Argument arg_ = page_.getGlobalArgument();
        CustList<Argument> firstArgs_;
        String calledCtorTemp_;
        String superClass_ = page_.formatVarType(getClassFromName(), _conf);
        String lastType_ = getLastType();
        lastType_ = ExecTemplates.quickFormat(getRootBlock(), superClass_, lastType_);
        int natvararg_ = getNaturalVararg();
        CustList<Argument> first_ = listNamedArguments(_nodes, chidren_).getArguments();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, first_);
        calledCtorTemp_ = superClass_;
        checkParametersCtors(_conf, calledCtorTemp_, getRootBlock(),getCtor(),  arg_, firstArgs_, InstancingStep.USING_SUPER,null);
        return Argument.createVoid();
    }

}
