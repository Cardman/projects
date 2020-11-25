package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ReflectConstructorPageEl extends AbstractReflectPageEl {
    
    private boolean initClass;
    private boolean calledMethod;
    private ConstructorMetaInfo metaInfo;

    public ReflectConstructorPageEl(CustList<Argument> _arguments, ConstructorMetaInfo _metaInfo) {
        super(_arguments);
        metaInfo = _metaInfo;
        setGlobalArgumentStruct(_metaInfo);
    }

    @Override
    public boolean checkCondition(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String className_ = metaInfo.getDeclaringClass();
        String id_ = StringExpUtil.getIdFromAllTypes(className_);
        GeneType type_ = _context.getClassBody(id_);
        if (ExecutingUtil.isAbstractType(type_)) {
            String null_ = stds_.getContent().getCoreNames().getAliasAbstractTypeErr();
            _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, className_, null_)));
            return false;
        }
        boolean static_ = type_.isStaticType();
        String res_ = ExecTemplates.correctClassPartsDynamicWildCard(className_,_context);
        if (res_.isEmpty()) {
            String null_;
            null_ = stds_.getContent().getCoreNames().getAliasIllegalType();
            _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, className_, null_)));
            return false;
        }
        if (!initClass) {
            initClass = true;
            if (static_ && _context.getExiting().hasToExit(res_)) {
                setWrapException(true);
                return false;
            }
        }
        setWrapException(false);
        if (!calledMethod) {
            calledMethod = true;
            ConstructorId mid_ = metaInfo.getRealId();
            Struct struct_ = ExecTemplates.getLastArgument(getArguments()).getStruct();
            if (!(struct_ instanceof ArrayStruct)) {
                String null_;
                null_ = stds_.getContent().getCoreNames().getAliasNullPe();
                _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_)));
                return false;
            }
            CustList<Argument> args_ = ((ArrayStruct)struct_).listArgs();
            Argument previous_;
            if (static_) {
                if (args_.size() != mid_.getParametersTypes().size()) {
                    String null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args_.size(), mid_.getParametersTypes().size()).toString(), null_)));
                    return false;
                }
                previous_ = Argument.createVoid();
            } else {
                if (args_.size() != 1 + mid_.getParametersTypes().size()) {
                    String null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args_.size(), 1 + mid_.getParametersTypes().size()).toString(), null_)));
                    return false;
                }
                previous_ = args_.first();
                args_ = args_.mid(1);
            }
            Argument arg_ = Argument.createVoid();
            ExecTypeFunction pair_ = metaInfo.getPair();
            ExecRootBlock execSuperClass_ = pair_.getType();
            if (execSuperClass_ != null) {
                ArgumentListCall l_ = ExecTemplates.wrapAndCallDirect(pair_,res_,previous_,args_,_context, null);
                arg_ = ExecInvokingOperation.instancePrepareCust(_context, res_, pair_, previous_, l_, "", -1);
            }
            if (metaInfo.getStandardType() != null) {
                arg_ = ExecInvokingOperation.instancePrepareStd(_context, res_, mid_, args_);
            }
            if (_context.callsOrException()) {
                setWrapException(_context.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

}
