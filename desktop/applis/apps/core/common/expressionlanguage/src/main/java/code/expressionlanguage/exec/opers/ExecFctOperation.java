package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.FctOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecFctOperation extends ExecInvokingOperation {

    private String methodName;

    private String className;

    private boolean staticChoiceMethod;

    private String lastType;

    private int naturalVararg;

    private int anc;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;

    protected ExecFctOperation(FctOperation _fct, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_fct);
        methodName = _fct.getMethodName();
        className = ExecOperationNode.getType(_fct.getClassMethodId());
        staticChoiceMethod = _fct.isStaticChoiceMethod();
        lastType = _fct.getLastType();
        naturalVararg = _fct.getNaturalVararg();
        anc = _fct.getAnc();
        named = _named;
        rootBlock = _rootBlock;
    }

    public ExecFctOperation(ExecClassArgumentMatching _res,
                            ClassMethodId _classMethodId,
                            int _child, int _order, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_child,_res,_order,true);
        className = _classMethodId.getClassName();
        methodName = _classMethodId.getConstraints().getName();
        naturalVararg = -1;
        lastType = "";
        named = _named;
        rootBlock = _rootBlock;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_,_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(Argument _previous, IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String classNameFound_ = getClassName();
        Struct argPrev_ = _previous.getStruct();
        Argument prev_ = new Argument(ExecTemplates.getParent(anc, classNameFound_, argPrev_, _conf));
        if (_conf.callsOrException()) {
            return new Argument();
        }
        Struct pr_ = prev_.getStruct();
        CustList<Argument> firstArgs_ = getArgs(_nodes, _conf, pr_);
        ExecOverrideInfo polymorph_ = polymorphOrSuper(staticChoiceMethod,_conf,pr_,classNameFound_,rootBlock,getNamed());
        ExecNamedFunctionBlock fct_ = polymorph_.getOverridableBlock();
        ExecRootBlock type_ = polymorph_.getRootBlock();
        classNameFound_ = polymorph_.getClassName();
        return callPrepare(new DefaultExiting(_conf),_conf, classNameFound_,type_, prev_, firstArgs_, null,fct_, MethodAccessKind.INSTANCE, "");
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct pr_) {
        return fetchFormattedArgs(_nodes,_conf,pr_,getClassName(),rootBlock,lastType,naturalVararg);
    }

    public ExecNamedFunctionBlock getNamed() {
        return named;
    }

    public String getClassName() {
        return className;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

}
