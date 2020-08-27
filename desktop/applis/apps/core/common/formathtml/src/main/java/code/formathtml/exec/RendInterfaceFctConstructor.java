package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
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

    private String lastType;

    private int naturalVararg;
    private int offsetOper;
    private String classFromName;
    private ExecRootBlock rootBlock;
    private ExecNamedFunctionBlock ctor;
    public RendInterfaceFctConstructor(InterfaceFctConstructor _abs, ContextEl _context) {
        super(_abs);
        className = _abs.getClassName();
        lastType = _abs.getLastType();
        naturalVararg = _abs.getNaturalVararg();
        offsetOper = _abs.getOffsetOper();
        classFromName = _abs.getClassFromName();
        rootBlock = ExecOperationNode.fetchType(_context,_abs.getRootNumber());
        ctor = ExecOperationNode.fetchFunction(_context,_abs.getRootNumber(),_abs.getMemberNumber());

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
        lastType_ = ExecTemplates.quickFormat(rootBlock,superClass_, lastType_);
        int natvararg_ = getNaturalVararg();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, _arguments.mid(1));
        ExecInvokingOperation.checkParametersCtors(_conf.getContext(), superClass_, rootBlock,ctor, arg_, firstArgs_, InstancingStep.USING_SUPER,null);
        return Argument.createVoid();
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
