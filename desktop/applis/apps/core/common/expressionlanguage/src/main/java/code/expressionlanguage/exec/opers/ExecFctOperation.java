package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.FctOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecFctOperation extends ExecInvokingOperation {

    private String methodName;

    private ClassMethodId classMethodId;

    private boolean staticMethod;

    private boolean staticChoiceMethod;

    private String lastType;

    private int naturalVararg;

    private int anc;

    protected ExecFctOperation(FctOperation _fct) {
        super(_fct);
        methodName = _fct.getMethodName();
        classMethodId = _fct.getClassMethodId();
        staticMethod = _fct.isStaticMethod();
        staticChoiceMethod = _fct.isStaticChoiceMethod();
        lastType = _fct.getLastType();
        naturalVararg = _fct.getNaturalVararg();
        anc = _fct.getAnc();
    }

    public ExecFctOperation(ClassArgumentMatching _res,
                            ClassMethodId _classMethodId,
                            int _child, int _order) {
        super(_child,_res,_order,true,null);
        classMethodId = _classMethodId;
        methodName = classMethodId.getConstraints().getName();
        naturalVararg = -1;
        lastType = "";
    }
    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_, arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(Argument _previous, CustList<Argument> _arguments, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        CustList<Argument> firstArgs_;
        MethodId methodId_ = classMethodId.getConstraints();
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        Argument prev_ = new Argument();
        if (!staticMethod) {
            classNameFound_ = classMethodId.getClassName();
            Struct argPrev_ = _previous.getStruct();
            prev_.setStruct(ExecTemplates.getParent(anc, classNameFound_, argPrev_, _conf));
            if (_conf.callsOrException()) {
                return new Argument();
            }
            String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
            if (staticChoiceMethod) {
                String argClassName_ = prev_.getObjectClassName(_conf);
                String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf);
                lastType_ = ExecTemplates.quickFormat(fullClassNameFound_, lastType_, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments);
                methodId_ = classMethodId.getConstraints();
            } else {
                Struct previous_ = prev_.getStruct();
                ClassMethodId methodToCall_ = polymorph(_conf, previous_, classMethodId);
                String argClassName_ = stds_.getStructClassName(previous_, _conf);
                String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf);
                lastType_ = ExecTemplates.quickFormat(fullClassNameFound_, lastType_, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments);
                methodId_ = methodToCall_.getConstraints();
                classNameFound_ = methodToCall_.getClassName();
            }
        } else {
            classNameFound_ = classMethodId.getClassName();
            classNameFound_ = classMethodId.formatType(classNameFound_,_conf);
            lastType_ = classMethodId.formatType(classNameFound_,lastType_,_conf);
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments);
            if (ExecutingUtil.hasToExit(_conf,classNameFound_)) {
                return Argument.createVoid();
            }
        }
        return callPrepare(new DefaultExiting(_conf),_conf, classNameFound_, methodId_, prev_, firstArgs_, null);
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

}
