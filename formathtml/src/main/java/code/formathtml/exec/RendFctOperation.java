package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.calls.util.CallingState;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.FctOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

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

    public RendFctOperation(ClassArgumentMatching _res,
                            ClassMethodId _classMethodId,
                            int _child, int _order) {
        super(_child,_res,_order,true,null);
        classMethodId = _classMethodId;
        methodName = classMethodId.getConstraints().getName();
        naturalVararg = -1;
        lastType = "";
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = getArgument(previous_, arguments_, _conf);
        CallingState state_ = _conf.getContext().getCallingState();
        if (state_ instanceof NotInitializedClass) {
            NotInitializedClass statusInit_ = (NotInitializedClass) state_;
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContext());
            if (_conf.getContext().hasException()) {
                return;
            }
            argres_ = getArgument(previous_, arguments_, _conf);
        }
        processCall(_nodes,_conf,argres_);
    }

    Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf) {
        return _conf.getAdvStandards().getCommonFctArgument(this,_previous,_arguments,_conf);
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
