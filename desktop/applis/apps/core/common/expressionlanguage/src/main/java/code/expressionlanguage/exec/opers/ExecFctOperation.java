package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.FctOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;
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

    protected ExecFctOperation(FctOperation _fct, ContextEl _context, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_fct);
        methodName = _fct.getMethodName();
        className = ExecOperationNode.getType(_context,_fct.getClassMethodId());
        staticChoiceMethod = _fct.isStaticChoiceMethod();
        lastType = _fct.getLastType();
        naturalVararg = _fct.getNaturalVararg();
        anc = _fct.getAnc();
        named = _named;
        rootBlock = _rootBlock;
    }

    public ExecFctOperation(ClassArgumentMatching _res,
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
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        CustList<Argument> firstArgs_;
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        ExecNamedFunctionBlock fct_ = getNamed();
        ExecRootBlock type_ = rootBlock;
        classNameFound_ = getClassName();
        Struct argPrev_ = _previous.getStruct();
        Argument prev_ = new Argument(ExecTemplates.getParent(anc, classNameFound_, argPrev_, _conf));
        if (_conf.callsOrException()) {
            return new Argument();
        }
        String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
        CustList<Argument> first_ = listNamedArguments(_nodes, chidren_).getArguments();
        if (staticChoiceMethod) {
            String argClassName_ = prev_.getObjectClassName(_conf);
            String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf);
            lastType_ = ExecTemplates.quickFormat(rootBlock,fullClassNameFound_, lastType_);
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, first_);
        } else {
            Struct previous_ = prev_.getStruct();
            ExecOverrideInfo polymorph_ = polymorph(_conf, previous_, rootBlock, named);
            fct_ = polymorph_.getOverridableBlock();
            type_ = polymorph_.getRootBlock();
            String argClassName_ = stds_.getStructClassName(previous_, _conf);
            String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf);
            lastType_ = ExecTemplates.quickFormat(rootBlock,fullClassNameFound_, lastType_);
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, first_);
            classNameFound_ = polymorph_.getClassName();
        }
        return callPrepare(new DefaultExiting(_conf),_conf, classNameFound_,type_, prev_, firstArgs_, null,fct_, MethodAccessKind.INSTANCE, "");
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
