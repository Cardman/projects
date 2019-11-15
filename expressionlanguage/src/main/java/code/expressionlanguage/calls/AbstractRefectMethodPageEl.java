package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public abstract class AbstractRefectMethodPageEl extends AbstractReflectPageEl {

    private boolean initClass;
    private ClassMethodId methodToCall;
    private boolean calledMethod;
    @Override
    public boolean checkCondition(ContextEl _context) {
        MethodMetaInfo method_ = (MethodMetaInfo) getGlobalArgument().getStruct();
        if (!initClass) {
            initClass = true;
            if (initType()) {
                if (ExecInvokingOperation.hasToExit(_context, method_.getClassName())) {
                    setWrapException(true);
                    return false;
                }
            }
        }
        setWrapException(false);
        if (methodToCall == null) {
            LgNames stds_ = _context.getStandards();
            if (!method_.isWideStatic()) {
                Argument instance_ = getArguments().first();
                if (instance_.isNull()) {
                    String null_;
                    null_ = stds_.getAliasNullPe();
                    _context.setException(new ErrorStruct(_context,null_));
                    return false;
                }
            }
            if (isAbstract()) {
                String null_;
                null_ = stds_.getAliasIllegalArg();
                _context.setException(new ErrorStruct(_context,null_));
                return false;
            }
            String className_ = method_.getClassName();
            if (isPolymorph()) {
                Struct instance_ = getArguments().first().getStruct();
                ClassMethodId clId_ = new ClassMethodId(className_, method_.getRealId());
                methodToCall = ExecInvokingOperation.polymorph(_context, instance_, clId_);
            } else {
                methodToCall = new ClassMethodId(className_, method_.getRealId());
            }
        }
        if (!calledMethod) {
            calledMethod = true;
            String className_ = methodToCall.getClassName();
            MethodId mid_ = methodToCall.getConstraints();
            Argument instance_;
            if (method_.isWideStatic()) {
                instance_ = new Argument();
            } else {
                instance_ = getArguments().first();
            }
            CustList<Argument> args_ = new CustList<Argument>();
            Struct struct_ = getArguments().last().getStruct();
            if (!(struct_ instanceof ArrayStruct)) {
                LgNames stds_ = _context.getStandards();
                String null_;
                null_ = stds_.getAliasNullPe();
                _context.setException(new ErrorStruct(_context,null_));
                return false;
            }
            for (Struct a: ((ArrayStruct)struct_).getInstance()) {
                Argument a_ = new Argument();
                a_.setStruct(a);
                args_.add(a_);
            }
            Argument right_ = null;
            if (!StringList.quickEq(mid_.getName(),"[]=")) {
                if (args_.size() != mid_.getParametersTypes().size()) {
                    LgNames stds_ = _context.getStandards();
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _context.setException(new ErrorStruct(_context,null_));
                    return false;
                }
            } else {
                if (args_.size() != mid_.getParametersTypes().size() + 1) {
                    LgNames stds_ = _context.getStandards();
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _context.setException(new ErrorStruct(_context,null_));
                    return false;
                }
                right_ = args_.last();
                args_ = args_.mid(0,args_.size()-1);
            }
            setWrapException(false);
            Argument arg_ = prepare(_context, className_, mid_, instance_, args_, right_);
            if (_context.getCallingState() instanceof NotInitializedClass) {
                setWrapException(true);
                return false;
            }
            if (_context.callsOrException()) {
                setWrapException(_context.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right) {
        return ExecInvokingOperation.callPrepare(_context, _className, _mid, _instance, _args, _right);
    }

    abstract boolean initType();
    abstract boolean isAbstract();
    abstract boolean isPolymorph();
}
