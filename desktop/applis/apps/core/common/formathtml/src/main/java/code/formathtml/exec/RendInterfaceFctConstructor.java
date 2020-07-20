package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.InterfaceFctConstructor;
import code.expressionlanguage.exec.opers.ExecCastOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.ConstructorId;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendInterfaceFctConstructor extends RendInvokingOperation implements RendCalculableOperation,RendCallable {
    private String className;

    private ConstructorId constId;

    private String lastType;

    private int naturalVararg;
    private int offsetOper;
    private String classFromName;
    public RendInterfaceFctConstructor(InterfaceFctConstructor _abs) {
        super(_abs);
        className = _abs.getClassName();
        constId = _abs.getConstId();
        lastType = _abs.getLastType();
        naturalVararg = _abs.getNaturalVararg();
        offsetOper = _abs.getOffsetOper();
        classFromName = _abs.getClassFromName();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        if (getParent().getFirstChild().getNextSibling() == this) {
            //init and test
            int order_ = getParent().getFirstChild().getOrder();
            Argument lda_ = new Argument(_nodes.getValue(order_).getArgument().getStruct());
            if (!ExecTemplates.checkObject(_conf.getStandards().getAliasFct(), lda_, _conf.getContext())) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes);
                return;
            }
            String form_ = _conf.getPageEl().formatVarType(className, _conf.getContext());
            Argument ref_ = new Argument(lda_.getStruct());
            ExecCastOperation.wrapFct(form_,true, _conf.getContext(), ref_);
            if (!ExecTemplates.checkObject(form_, ref_, _conf.getContext())) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes);
                return;
            }
            _nodes.getValue(getParent().getFirstChild().getOrder()).setArgument(ref_);
            CustList<Argument> arguments_ = getArguments(_nodes, this);
            arguments_.add(0,ref_);
            Argument argres_ = processCall(this, this, Argument.createVoid(), arguments_, _conf, null);
            setSimpleArgument(argres_,_conf,_nodes);
            return;
        }
        int order_ = getParent().getFirstChild().getOrder();
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        arguments_.add(0,_nodes.getValue(order_).getArgument());
        Argument argres_ = processCall(this, this, Argument.createVoid(), arguments_, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }
    Argument getArgument(CustList<Argument> _arguments, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offsetOper, _conf);
        Argument arg_ = _arguments.first();
        CustList<Argument> firstArgs_;
        String superClass_ = _conf.getPageEl().formatVarType(classFromName,_conf.getContext());
        String lastType_ = getLastType();
        lastType_ = Templates.quickFormat(superClass_, lastType_, _conf.getContext());
        int natvararg_ = getNaturalVararg();
        ConstructorId ctorId_ = getConstId();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, _arguments.mid(1));
        ExecInvokingOperation.checkParametersCtors(_conf.getContext(), superClass_, ctorId_, arg_, firstArgs_, InstancingStep.USING_SUPER,null);
        return Argument.createVoid();
    }

    public final ConstructorId getConstId() {
        return constId;
    }

    public String getLastType() {
        return lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    @Override
    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        return getArgument(_arguments,_conf);
    }
}
