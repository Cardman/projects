package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.InterfaceFctConstructor;
import code.expressionlanguage.exec.opers.ExecCastOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
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
    public RendInterfaceFctConstructor(InterfaceFctConstructor _abs, AnalyzedPageEl _page) {
        super(_abs);
        className = _abs.getClassName();
        lastType = _abs.getLastType();
        naturalVararg = _abs.getNaturalVararg();
        offsetOper = _abs.getOffsetOper();
        classFromName = _abs.getClassFromName();
        rootBlock = ExecOperationNode.fetchType(_abs.getRootNumber(), _page);
        ctor = ExecOperationNode.fetchFunctionOp(_abs.getRootNumber(),_abs.getMemberNumber(), _page);

    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        if (getParent().getFirstChild().getNextSibling() == this) {
            //init and test
            int order_ = getParent().getFirstChild().getOrder();
            Argument lda_ = new Argument(Argument.getNullableValue(_nodes.getValue(order_).getArgument()).getStruct());
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
            Argument argres_ = processCall(this, this, ref_,_nodes, _conf, null);
            setSimpleArgument(argres_,_conf,_nodes);
            return;
        }
        int order_ = getParent().getFirstChild().getOrder();
        Argument argres_ = processCall(this, this, Argument.getNullableValue(_nodes.getValue(order_).getArgument()),_nodes, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }
    Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all,Argument _arguments, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offsetOper, _conf);
        CustList<Argument> firstArgs_;
        String superClass_ = _conf.getPageEl().formatVarType(classFromName,_conf.getContext());
        String lastType_ = getLastType();
        lastType_ = ExecTemplates.quickFormat(rootBlock,superClass_, lastType_);
        int natvararg_ = getNaturalVararg();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, chidren_).getArguments();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, first_);
        ExecInvokingOperation.checkParametersCtors(_conf.getContext(), superClass_, rootBlock,ctor, _arguments, firstArgs_, InstancingStep.USING_SUPER);
        return Argument.createVoid();
    }

    public String getLastType() {
        return lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        return getArgument(_all,_previous,_conf);
    }
}
