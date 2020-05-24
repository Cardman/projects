package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.calls.util.InstancingStep;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.InterfaceFctConstructor;
import code.expressionlanguage.opers.exec.ExecCastOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ConstructorId;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendInterfaceFctConstructor extends RendInvokingOperation implements RendCalculableOperation {
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
            Argument lda_ = _nodes.getValue(order_).getArgument();
            if (!Templates.checkObject(_conf.getStandards().getAliasFct(), lda_, _conf.getContext())) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes);
                return;
            }
            String form_ = _conf.getPageEl().formatVarType(className, _conf.getContext());
            Argument ref_ = new Argument(lda_.getStruct());
            ExecCastOperation.wrapFct(form_,true,new CustList<Argument>(ref_),_conf.getContext());
            if (!Templates.checkObject(form_, ref_, _conf.getContext())) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes);
                return;
            }
            _nodes.getValue(getParent().getFirstChild().getOrder()).setArgument(ref_);
            CustList<Argument> arguments_ = getArguments(_nodes, this);
            arguments_.add(0,ref_);
            Argument res_ = getArgument(arguments_, _conf);
            processCall(_nodes,_conf,res_);
            return;
        }
        int order_ = getParent().getFirstChild().getOrder();
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        arguments_.add(0,_nodes.getValue(order_).getArgument());
        Argument res_ = getArgument(arguments_, _conf);
        processCall(_nodes,_conf,res_);
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
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, _arguments.mid(1), _conf);
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
}
