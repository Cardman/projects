package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
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
    private boolean instanceClass;
    private CustList<Argument> args = new CustList<Argument>();
    private Argument instance;
    private Argument rightArg;

    protected boolean initDefault(ContextEl _cont) {
        MethodMetaInfo method_ = ApplyCoreMethodUtil.getMethod(getGlobalArgument().getStruct());
        return method_.isWideStatic()&&_cont.hasToExit(method_.getClassName());
    }

    @Override
    public boolean checkCondition(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        MethodMetaInfo method_ = ApplyCoreMethodUtil.getMethod(getGlobalArgument().getStruct());
        if (!initClass) {
            initClass = true;
            if (initType(_context)) {
                setWrapException(true);
                return false;
            }
        }
        setWrapException(false);
        if (methodToCall == null) {
            if (!method_.isWideStatic()) {
                Argument instance_ = getArguments().first();
                if (instance_.isNull()) {
                    String null_;
                    null_ = stds_.getAliasNullPe();
                    _context.setException(new ErrorStruct(_context,null_));
                    return false;
                }
            }
            if (isAbstract(_context)) {
                String null_;
                null_ = stds_.getAliasIllegalArg();
                _context.setException(new ErrorStruct(_context,null_));
                return false;
            }
            String className_ = method_.getClassName();
            if (isPolymorph(_context)) {
                Struct instance_ = getArguments().first().getStruct();
                ClassMethodId clId_ = new ClassMethodId(className_, method_.getRealId());
                methodToCall = ExecInvokingOperation.polymorph(_context, instance_, clId_);
            } else {
                methodToCall = new ClassMethodId(className_, method_.getRealId());
            }
        }
        if (!calledMethod) {
            calledMethod = true;
            MethodId mid_ = methodToCall.getConstraints();
            if (method_.isWideStatic()) {
                instance = new Argument();
            } else {
                instance = getArguments().first();
            }
            Struct struct_ = getArguments().last().getStruct();
            if (!(struct_ instanceof ArrayStruct)) {
                String null_;
                null_ = stds_.getAliasNullPe();
                _context.setException(new ErrorStruct(_context,null_));
                return false;
            }
            for (Struct a: ((ArrayStruct)struct_).getInstance()) {
                Argument a_ = new Argument();
                a_.setStruct(a);
                args.add(a_);
            }
            if (method_.isExpCast()) {
                if (args.size() + 1 != mid_.getParametersTypes().size()) {
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _context.setException(new ErrorStruct(_context,null_));
                    return false;
                }
            } else if (!StringList.quickEq(mid_.getName(),"[]=")) {
                if (args.size() != mid_.getParametersTypes().size()) {
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _context.setException(new ErrorStruct(_context,null_));
                    return false;
                }
            } else {
                if (args.size() != mid_.getParametersTypes().size() + 1) {
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _context.setException(new ErrorStruct(_context,null_));
                    return false;
                }
                rightArg = args.last();
                args = args.mid(0,args.size()-1);
            }
            setWrapException(false);
            String className_ = methodToCall.getClassName();
            Argument arg_ = prepare(_context, className_, mid_, instance, args, rightArg);
            if (_context.getCallingState() instanceof NotInitializedClass) {
                instanceClass = true;
                setWrapException(true);
                return false;
            }
            if (_context.callsOrException()) {
                setWrapException(_context.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        if (instanceClass) {
            String className_ = methodToCall.getClassName();
            MethodId mid_ = methodToCall.getConstraints();
            Argument arg_ = prepare(_context, className_, mid_, instance, args, rightArg);
            setReturnedArgument(arg_);
        }
        return true;
    }

    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right) {
        return ExecInvokingOperation.callPrepare(new DefaultExiting(_context), _context, _className, _mid, _instance, _args, _right);
    }

    abstract boolean initType(ContextEl _context);
    abstract boolean isAbstract(ContextEl _context);
    abstract boolean isPolymorph(ContextEl _context);
}
