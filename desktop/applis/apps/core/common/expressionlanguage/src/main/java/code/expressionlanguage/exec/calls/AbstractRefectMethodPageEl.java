package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.core.StringUtil;

public abstract class AbstractRefectMethodPageEl extends AbstractRefectCommonMethodPageEl {

    private boolean calledMethod;


    private Argument rightArg;
    private final ExecMemberCallingsBlock callee;
    private final ArrayRefState arrRef;

    protected AbstractRefectMethodPageEl(Argument _instance, MethodMetaInfo _metaInfo, AbstractPreparer _preparer, ArrayRefState _a) {
        super(_instance, _metaInfo, _preparer,false);
        callee = _metaInfo.getCallee();
        arrRef = _a;
    }

    protected AbstractRefectMethodPageEl(Argument _instance, MethodMetaInfo _metaInfo, AbstractPreparer _preparer, ArrayRefState _a, AbstractParamReflectCheckerStepping _abParam) {
        super(_instance, _metaInfo, _preparer,false,_abParam);
        callee = _metaInfo.getCallee();
        arrRef = _a;
    }

    ExecMemberCallingsBlock getCallee() {
        return callee;
    }

    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (!keep(_context, _stack, getInstance())) {
            return false;
        }
        if (!calledMethod && !checkCallPhase(_context, _stack)) {
            return false;
        }
        return callPhase(_context, _stack);
    }

    private boolean checkCallPhase(ContextEl _context, StackCall _stack) {
        LgNames stds_ = _context.getStandards();
        calledMethod = true;
        MethodId mid_ = getMetaInfo().getRealId();
        ArrayStruct arr_ = getArrRef().getArray();
        if (getArrRef().isFalseArr()) {
            String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_, _stack)));
            return false;
        }
        if (getMetaInfo().isExpCast()) {
            if (arr_.getLength() + 1 != mid_.getParametersTypesLength()) {
                String null_;
                null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(arr_.getLength() + 1, mid_.getParametersTypesLength()).toString(), null_, _stack)));
                return false;
            }
        } else if (!StringUtil.quickEq(mid_.getName(),"[]=")) {
            if (arr_.getLength() != mid_.getParametersTypesLength()) {
                String null_;
                null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(arr_.getLength(), mid_.getParametersTypesLength()).toString(), null_, _stack)));
                return false;
            }
        } else {
            if (arr_.getLength() != mid_.getParametersTypesLength() + 1) {
                String null_;
                null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(arr_.getLength(), mid_.getParametersTypesLength() + 1).toString(), null_, _stack)));
                return false;
            }
            rightArg = new Argument(arr_.get(arr_.getLength()-1));
        }
        return true;
    }

    @Override
    protected boolean checkParams(ContextEl _context, StackCall _stack) {
        return false;
    }

    @Override
    protected boolean postArg(StackCall _stack) {
        return true;
    }

    Argument prepareCall(ContextEl _context, StackCall _stack) {
        return prepare(_context, arrRef, rightArg, _stack);
    }
    abstract Argument prepare(ContextEl _context, ArrayRefState _args, Argument _right, StackCall _stack);

    @Override
    public int getRef() {
        return arrRef.getRef();
    }

    public ArrayRefState getArrRef() {
        return arrRef;
    }
}
