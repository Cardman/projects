package code.expressionlanguage;

import code.expressionlanguage.opers.InvokingOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.StdStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ReflectMethodPageEl extends AbstractReflectPageEl {
    
    private boolean initClass;
    private ClassMethodId methodToCall;
    private boolean calledMethod;
    @Override
    public boolean checkCondition(ContextEl _context) {
        MethodMetaInfo method_ = (MethodMetaInfo) getGlobalArgument().getStruct();
        if (!initClass) {
            initClass = true;
            if (method_.isStatic()) {
                if (InvokingOperation.hasToExit(_context, method_.getClassName())) {
                    setWrapException(true);
                    return false;
                }
            }
        }
        setWrapException(false);
        if (methodToCall == null) {
            LgNames stds_ = _context.getStandards();
            if (!method_.isStatic()) {
                Argument instance_ = getArguments().first();
                if (instance_.isNull()) {
                    String null_;
                    null_ = stds_.getAliasNullPe();
                    _context.setException(new StdStruct(new CustomError(_context.joinPages()),null_));
                    return false;
                }
            }
            if (!method_.isPolymorph() && method_.isAbstract()) {
                String null_;
                null_ = stds_.getAliasNullPe();
                _context.setException(new StdStruct(new CustomError(_context.joinPages()),null_));
                return false;
            }
            if (method_.isPolymorph() && !method_.isStatic() && !method_.getClassName().startsWith("[")) {
                Struct instance_ = getArguments().first().getStruct();
                ClassMethodId clId_ = new ClassMethodId(method_.getClassName(), method_.getRealId());
                methodToCall = InvokingOperation.polymorph(_context, instance_, clId_);
            } else {
                methodToCall = new ClassMethodId(method_.getClassName(), method_.getRealId());
            }
        }
        if (!calledMethod) {
            String className_ = methodToCall.getClassName();
            MethodId mid_ = methodToCall.getConstraints();
            Argument instance_;
            if (method_.isStatic()) {
                instance_ = new Argument();
            } else {
                instance_ = getArguments().first();
            }
            CustList<Argument> args_ = new CustList<Argument>();
            Struct struct_ = getArguments().last().getStruct();
            if (struct_.isNull()) {
                LgNames stds_ = _context.getStandards();
                String null_;
                null_ = stds_.getAliasNullPe();
                _context.setException(new StdStruct(new CustomError(_context.joinPages()),null_));
                return false;
            }
            for (Struct a: ((Struct[])struct_.getInstance())) {
                Argument a_ = new Argument();
                a_.setStruct(a);
                args_.add(a_);
            }
            if (args_.size() != mid_.getParametersTypes().size()) {
                LgNames stds_ = _context.getStandards();
                String null_;
                null_ = stds_.getAliasNullPe();
                _context.setException(new StdStruct(new CustomError(_context.joinPages()),null_));
                return false;
            }
            setWrapException(false);
            Argument arg_ = InvokingOperation.callPrepare(_context, className_, mid_, instance_, args_, -1);
            if (_context.getInitClass() != null) {
                setWrapException(true);
                return false;
            }
            calledMethod = true;
            if (_context.callsOrException()) {
                setWrapException(_context.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

}
