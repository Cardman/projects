package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.FctOperation;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendFctOperation extends RendInvokingOperation implements RendCalculableOperation,RendCallable {

    private String methodName;

    private ClassMethodId classMethodId;

    private boolean staticMethod;

    private boolean staticChoiceMethod;

    private String lastType;

    private int naturalVararg;

    private int anc;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public RendFctOperation(FctOperation _fct, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_fct);
        methodName = _fct.getMethodName();
        classMethodId = _fct.getClassMethodId();
        staticMethod = _fct.isStaticMethod();
        staticChoiceMethod = _fct.isStaticChoiceMethod();
        lastType = _fct.getLastType();
        naturalVararg = _fct.getNaturalVararg();
        anc = _fct.getAnc();
        named = _named;
        rootBlock = _rootBlock;
    }

    public RendFctOperation(ClassArgumentMatching _res,
                            ClassMethodId _classMethodId,
                            int _child, int _order, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_child,_res,_order,true,null);
        classMethodId = _classMethodId;
        methodName = classMethodId.getConstraints().getName();
        naturalVararg = -1;
        lastType = "";
        named = _named;
        rootBlock = _rootBlock;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = processCall(this, this, previous_, arguments_, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        CustList<Argument> firstArgs_;
        MethodId methodId_ = getClassMethodId().getConstraints();
        String lastType_ = getLastType();
        int naturalVararg_ = getNaturalVararg();
        String classNameFound_;
        Argument prev_ = new Argument();
        ExecNamedFunctionBlock fct_ = named;
        if (!isStaticMethod()) {
            classNameFound_ = getClassMethodId().getClassName();
            prev_.setStruct(ExecTemplates.getParent(getAnc(), classNameFound_, _previous.getStruct(), _conf.getContext()));
            if (_conf.getContext().hasException()) {
                Argument a_ = new Argument();
                return a_;
            }
            String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
            if (isStaticChoiceMethod()) {
                String argClassName_ = prev_.getObjectClassName(_conf.getContext());
                String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf.getContext());
                lastType_ = ExecTemplates.quickFormat(fullClassNameFound_, lastType_, _conf.getContext());
                firstArgs_ = RendInvokingOperation.listArguments(chidren_, naturalVararg_, lastType_, _arguments);
                methodId_ = getClassMethodId().getConstraints();
            } else {
                Struct previous_ = prev_.getStruct();
                ContextEl context_ = _conf.getContext();
                ExecOverrideInfo polymorph_ = ExecInvokingOperation.polymorph(context_, previous_, rootBlock, named);
                fct_ = polymorph_.getOverridableBlock();
                String argClassName_ = stds_.getStructClassName(previous_, context_);
                String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf.getContext());
                lastType_ = ExecTemplates.quickFormat(fullClassNameFound_, lastType_, _conf.getContext());
                firstArgs_ = RendInvokingOperation.listArguments(chidren_, naturalVararg_, lastType_, _arguments);
                methodId_ = classMethodId.getConstraints();
                classNameFound_ = polymorph_.getClassName();
            }
        } else {
            firstArgs_ = RendInvokingOperation.listArguments(chidren_, naturalVararg_, lastType_, _arguments);
            classNameFound_ = getClassMethodId().getClassName();
            if (_conf.hasToExit(classNameFound_)) {
                return Argument.createVoid();
            }
        }
        return ExecInvokingOperation.callPrepare(new AdvancedExiting(_conf),_conf.getContext(), classNameFound_, methodId_, prev_, firstArgs_, null,fct_);
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public int getAnc() {
        return anc;
    }

    public String getLastType() {
        return lastType;
    }

    public String getMethodName() {
        return methodName;
    }

    public boolean isStaticChoiceMethod() {
        return staticChoiceMethod;
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }
}
