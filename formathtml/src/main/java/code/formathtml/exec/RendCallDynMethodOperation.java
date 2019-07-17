package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.CustomFoundConstructor;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.calls.util.CustomReflectMethod;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.CallDynMethodOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.util.CustList;

public final class RendCallDynMethodOperation extends RendInvokingOperation implements RendCalculableOperation {

    public RendCallDynMethodOperation(CallDynMethodOperation _call) {
        super(_call);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (RendDynOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument previous_ = getPreviousArg(this,_conf);
        Argument argres_ = ExecInvokingOperation.prepareCallDyn(previous_, arguments_, _conf);
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getContextEl().hasException()) {
                return;
            }
            argres_ = ExecInvokingOperation.prepareCallDyn(previous_, arguments_, _conf);
        }
        processCall(_conf,argres_);
    }

}
