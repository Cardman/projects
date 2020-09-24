package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.InterfaceFctConstructor;
import code.util.CustList;
import code.util.IdMap;

public final class ExecInterfaceFctConstructor extends ExecAbstractInvokingConstructor {
    private String className;
    public ExecInterfaceFctConstructor(InterfaceFctConstructor _abs, AnalyzedPageEl _page) {
        super(_abs, _page);
        className = _abs.getClassName();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        int order_ = getParent().getFirstChild().getOrder();
        Argument mainArgument_ = Argument.getNullableValue(_nodes.getValue(order_).getArgument());
        if (getIndexChild() == 1) {
            //init and test
            Argument lda_ = new Argument(mainArgument_.getStruct());
            if (!ExecTemplates.checkObject(_conf.getStandards().getAliasFct(), lda_, _conf)) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes);
                return;
            }
            String form_ = _conf.getLastPage().formatVarType(className, _conf);
            Argument ref_ = new Argument(lda_.getStruct());
            ExecCastOperation.wrapFct(form_,true, _conf, ref_);
            if (!ExecTemplates.checkObject(form_, ref_, _conf)) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes);
                return;
            }
            _nodes.getValue(order_).setArgument(ref_);
            Argument res_ = getArgument(_nodes,ref_, _conf);
            setSimpleArgument(res_, _conf, _nodes);
            return;
        }
        Argument res_ = getArgument(_nodes, mainArgument_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes,Argument _argument, ContextEl _conf) {
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String superClass_ = _conf.getLastPage().formatVarType(getClassFromName(),_conf);
        CustList<Argument> firstArgs_ = getArgs(_nodes, superClass_);
        checkParametersCtors(_conf, superClass_, getRootBlock(),getCtor(), _argument, firstArgs_, InstancingStep.USING_SUPER);
        return Argument.createVoid();
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String superClass_) {
        String lastType_ = ExecTemplates.quickFormat(getRootBlock(),superClass_, getLastType());
        return fectchArgs(_nodes,lastType_,getNaturalVararg());
    }
}
