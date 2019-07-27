package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.FctOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendFctOperation extends RendInvokingOperation implements RendCalculableOperation {

    private String methodName;

    private ClassMethodId classMethodId;

    private boolean staticMethod;

    private boolean staticChoiceMethod;

    private String lastType;

    private int naturalVararg;

    private int anc;

    public RendFctOperation(FctOperation _fct) {
        super(_fct);
        methodName = _fct.getMethodName();
        classMethodId = _fct.getClassMethodId();
        staticMethod = _fct.isStaticMethod();
        staticChoiceMethod = _fct.isStaticChoiceMethod();
        lastType = _fct.getLastType();
        naturalVararg = _fct.getNaturalVararg();
        anc = _fct.getAnc();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = getArgument(previous_, arguments_, _conf);
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getContextEl().hasException()) {
                return;
            }
            argres_ = getArgument(previous_, arguments_, _conf);
        }
        processCall(_nodes,_conf,argres_);
    }

    Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
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
            prev_.setStruct(PrimitiveTypeUtil.getParent(anc, classNameFound_, _previous.getStruct(), _conf));
            if (_conf.getContextEl().hasExceptionOrFailInit()) {
                Argument a_ = new Argument();
                return a_;
            }
            if (prev_.getStruct() instanceof ArrayStruct) {
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                return ExecInvokingOperation.callPrepare(_conf, classNameFound_, methodId_, prev_, firstArgs_, null);
            }
            String base_ = Templates.getIdFromAllTypes(classNameFound_);
            if (staticChoiceMethod) {
                String argClassName_ = prev_.getObjectClassName(_conf.getContextEl());
                String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
                lastType_ = Templates.quickFormat(fullClassNameFound_, lastType_, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                methodId_ = classMethodId.getConstraints();
            } else {
                Struct previous_ = prev_.getStruct();
                ContextEl context_ = _conf.getContextEl();
                ClassMethodId methodToCall_ = ExecInvokingOperation.polymorph(context_, previous_, classMethodId);
                String argClassName_ = stds_.getStructClassName(previous_, context_);
                String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
                lastType_ = Templates.quickFormat(fullClassNameFound_, lastType_, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                methodId_ = methodToCall_.getConstraints();
                classNameFound_ = methodToCall_.getClassName();
            }
        } else {
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            classNameFound_ = classMethodId.getClassName();
            if (ExecInvokingOperation.hasToExit(_conf, classNameFound_)) {
                return Argument.createVoid();
            }
        }
        return ExecInvokingOperation.callPrepare(_conf, classNameFound_, methodId_, prev_, firstArgs_, null);
    }

}
