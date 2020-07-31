package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.InterfaceFctConstructor;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.CustList;
import code.util.IdMap;

public final class ExecInterfaceFctConstructor extends ExecAbstractInvokingConstructor {
    private String className;
    public ExecInterfaceFctConstructor(InterfaceFctConstructor _abs) {
        super(_abs);
        className = _abs.getClassName();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        if (getParent().getFirstChild().getNextSibling() == this) {
            //init and test
            int order_ = getParent().getFirstChild().getOrder();
            Argument lda_ = new Argument(_nodes.getValue(order_).getArgument().getStruct());
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
            _nodes.getValue(getParent().getFirstChild().getOrder()).setArgument(ref_);
            CustList<Argument> arguments_ = getArguments(_nodes, this);
            arguments_.add(0,ref_);
            Argument res_ = getArgument(arguments_, _conf);
            setSimpleArgument(res_, _conf, _nodes);
            return;
        }
        int order_ = getParent().getFirstChild().getOrder();
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        arguments_.add(0,_nodes.getValue(order_).getArgument());
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    @Override
    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument arg_ = _arguments.first();
        CustList<Argument> firstArgs_;
        String superClass_ = _conf.getLastPage().formatVarType(getClassFromName(),_conf);
        String lastType_ = getLastType();
        lastType_ = ExecTemplates.quickFormat(superClass_, lastType_, _conf);
        int natvararg_ = getNaturalVararg();
        ConstructorId ctorId_ = getConstId();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, _arguments.mid(1));
        checkParametersCtors(_conf, superClass_, ctorId_, arg_, firstArgs_, InstancingStep.USING_SUPER,null);
        return Argument.createVoid();
    }
}
