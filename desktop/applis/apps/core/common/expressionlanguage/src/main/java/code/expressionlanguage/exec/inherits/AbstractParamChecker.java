package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.util.core.StringUtil;

public abstract class AbstractParamChecker {

    private static final String RETURN_LINE = "\n";
    public FormattedParameters checkParams(ExecRootBlock _rootBlock, String _classNameFound, Argument _previous, Cache _cache, ContextEl _conf, MethodAccessKind _kind, StackCall _stackCall) {
        LgNames stds_ = _conf.getStandards();
        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        String classFormat_ = _classNameFound;
        FormattedParameters f_ = new FormattedParameters();
        if (_kind == MethodAccessKind.INSTANCE) {
            String className_ = Argument.getNullableValue(_previous).getStruct().getClassName(_conf);
            classFormat_ = ExecInherits.getQuickFullTypeByBases(className_, classFormat_, _conf);
            if (classFormat_.isEmpty()) {
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(_classNameFound, className_), cast_, _stackCall)));
                return f_;
            }
        }
        Parameters parameters_ = check(_rootBlock, classFormat_, _cache, _conf, _stackCall);
        if (parameters_.getError() != null) {
            return f_;
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
        return f_;
    }

    private static String getBadCastMessage(String _classNameFound, String _className) {
        return StringUtil.concat(_className, RETURN_LINE, _classNameFound, RETURN_LINE);
    }
    public abstract Parameters check(ExecRootBlock _rootBlock, String _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall);
}
