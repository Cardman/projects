package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.StringUtil;

public abstract class AbstractRefectMethodPageEl extends AbstractReflectPageEl {

    private boolean initClass;
    private String className = "";
    private ExecTypeFunction pair;
    private boolean calledMethod;
    private boolean calledAfter;
    private CustList<Argument> args = new CustList<Argument>();
    private Argument instance;
    private Argument rightArg;
    private MethodAccessKind accessKind;
    private String methodName = "";
    private MethodMetaInfo metaInfo;

    public AbstractRefectMethodPageEl(CustList<Argument> _arguments, MethodMetaInfo _metaInfo) {
        super(_arguments);
        setGlobalArgumentStruct(_metaInfo);
        metaInfo = _metaInfo;
    }

    protected boolean initDefault(ContextEl _cont) {
        return metaInfo.isWideStatic()&&_cont.getExiting().hasToExit(metaInfo.getClassName());
//        return method_.isWideStatic()&&ExecutingUtil.hasToExit(_cont,method_.getClassName());
    }

    @Override
    public boolean checkCondition(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        if (!initClass) {
            initClass = true;
            if (initType(_context)) {
                setWrapException(true);
                return false;
            }
        }
        setWrapException(false);
        if (pair == null) {
            if (metaInfo.isInstanceMethod()) {
                Argument instance_ = ExecTemplates.getFirstArgument(getArguments());
                if (instance_.isNull()) {
                    String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
                    _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_)));
                    return false;
                }
            }
            if (isAbstract(_context)) {
                String null_ = stds_.getContent().getCoreNames().getAliasIllegalArg();
                _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, metaInfo.getDisplayedString(_context).getInstance(), null_)));
                return false;
            }
            String className_ = metaInfo.getClassName();
            if (isPolymorph(_context)) {
                Struct instance_ = ExecTemplates.getFirstArgument(getArguments()).getStruct();
                ExecOverrideInfo polymorph_ = ExecInvokingOperation.polymorph(_context, instance_, metaInfo.getPair());
                className = polymorph_.getClassName();
                pair = polymorph_.getPair();
            } else {
                className = className_;
                pair = metaInfo.getPair();
            }
        }
        if (!calledMethod) {
            calledMethod = true;
            MethodId mid_ = metaInfo.getRealId();
            methodName = mid_.getName();
            accessKind = mid_.getKind();
            if (metaInfo.isWideStatic()) {
                instance = new Argument();
            } else {
                instance = ExecTemplates.getFirstArgument(getArguments());
            }
            Struct struct_ = ExecTemplates.getLastArgument(getArguments()).getStruct();
            if (!(struct_ instanceof ArrayStruct)) {
                String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
                _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_)));
                return false;
            }
            args.addAllElts(((ArrayStruct)struct_).listArgs());
            if (metaInfo.isExpCast()) {
                if (args.size() + 1 != mid_.getParametersTypes().size()) {
                    String null_;
                    null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args.size() + 1, mid_.getParametersTypes().size()).toString(), null_)));
                    return false;
                }
            } else if (!StringUtil.quickEq(mid_.getName(),"[]=")) {
                if (args.size() != mid_.getParametersTypes().size()) {
                    String null_;
                    null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args.size(), mid_.getParametersTypes().size()).toString(), null_)));
                    return false;
                }
            } else {
                if (args.size() != mid_.getParametersTypes().size() + 1) {
                    String null_;
                    null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args.size(), mid_.getParametersTypes().size() + 1).toString(), null_)));
                    return false;
                }
                rightArg = args.last();
                args = args.left(args.size()-1);
            }
        }
        if (!calledAfter) {
            setWrapException(false);
            MethodId mid_ = metaInfo.getRealId();
            Argument arg_ = prepare(_context, className, mid_, instance, args, rightArg);
            if (_context.getCallingState() instanceof NotInitializedClass) {
                setWrapException(true);
                return false;
            }
            calledAfter = true;
            if (_context.callsOrException()) {
                setWrapException(_context.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right) {
        ArgumentListCall l_ = ExecTemplates.wrapAndCallDirect(pair,_className,_instance,_args,_context, accessKind);
        return ExecInvokingOperation.callPrepare(_context.getExiting(), _context, _className,pair, _instance,metaInfo.getCache(), l_, _right,accessKind, methodName);
    }

    MethodMetaInfo getMetaInfo() {
        return metaInfo;
    }

    MethodAccessKind getAccessKind() {
        return accessKind;
    }

    String getMethodName() {
        return methodName;
    }

    ExecTypeFunction getPair() {
        return pair;
    }

    abstract boolean initType(ContextEl _context);
    abstract boolean isAbstract(ContextEl _context);
    abstract boolean isPolymorph(ContextEl _context);
}
