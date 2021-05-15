package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;

public abstract class AbstractParamChecker {
    protected static final String RETURN_LINE = "\n";

    public Argument checkParams(ExecFormattedRootBlock _classNameFound, Argument _previous, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        FormattedParameters f_ = new FormattedParameters();
        ExecFormattedRootBlock classFormat_ = checkFormmattedParams(_classNameFound, _previous, _conf, _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return Argument.createVoid();
        }
        Parameters parameters_ = check(classFormat_, _cache, _conf, _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return Argument.createVoid();
        }
        //SwitchParamChecker
        /*Parameters parameters_ = f_.getParameters();
            _stack.setCallingState(new CustomFoundSwitch(_instance,f_.getFormattedClass(),getPair().getType(),(ExecAbstractSwitchMethod) callee_,parameters_.getCache(),new Argument(parameters_.getRefParameters().firstValue().getValue(_stack,_context))));*/
        //
        /*
        if (_state == CallPrepareState.METHOD) {
            return classFormat_;
        }
        Parameters parameters_ = f_.getParameters();
        if (_state == CallPrepareState.CTOR) {
            _stackCall.setCallingState(new CustomFoundConstructor(_classNameFound, _methodId, EMPTY_STRING, -1, _previous, parameters_, _kindCall));
            return f_;
        }
        _stackCall.setCallingState(new CustomFoundMethod(_classNameFound, _methodId, parameters_));
        return f_;*/
        f_.setParameters(parameters_);
        f_.setFormattedClass(classFormat_);
        return redirect(_conf,_classNameFound,_previous,_stackCall,f_);
    }

    public abstract ExecFormattedRootBlock checkFormmattedParams(ExecFormattedRootBlock _classNameFound, Argument _previous, ContextEl _conf, StackCall _stackCall);
    public abstract Argument redirect(ContextEl _conf, ExecFormattedRootBlock _classNameFound, Argument _previous, StackCall _stackCall, FormattedParameters _classFormat);

    public abstract Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall);
}
