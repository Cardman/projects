package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.ConstructorId;
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
            String null_;
            null_ = stds_.getContent().getCoreNames().getAliasAbstractTypeErr();
            _context.setCallingState(new ErrorStruct(_context,className_,null_));
            return false;
        }
        boolean static_ = type_.isStaticType();
        String res_ = ExecTemplates.correctClassPartsDynamicWildCard(className_,_context);
        if (res_.isEmpty()) {
            String null_;
            null_ = stds_.getContent().getCoreNames().getAliasIllegalType();
            _context.setCallingState(new ErrorStruct(_context,className_,null_));
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
            Struct struct_ = getArguments().last().getStruct();
            if (!(struct_ instanceof ArrayStruct)) {
                String null_;
                null_ = stds_.getContent().getCoreNames().getAliasNullPe();
                _context.setCallingState(new ErrorStruct(_context,null_));
                return false;
            }
            CustList<Argument> args_ = new CustList<Argument>();
            for (Struct a: ((ArrayStruct)struct_).getInstance()) {
                Argument a_ = new Argument(a);
                args_.add(a_);
            }
            Argument previous_;
            if (static_) {
                if (args_.size() != mid_.getParametersTypes().size()) {
                    String null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _context.setCallingState(new ErrorStruct(_context,ExecTemplates.countDiff(args_.size(),mid_.getParametersTypes().size()).toString(),null_));
                    return false;
                }
                previous_ = Argument.createVoid();
            } else {
                if (args_.size() != 1 + mid_.getParametersTypes().size()) {
                    String null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _context.setCallingState(new ErrorStruct(_context,ExecTemplates.countDiff(args_.size(),1 + mid_.getParametersTypes().size()).toString(),null_));
                    return false;
                }
                previous_ = args_.first();
                args_ = args_.mid(1);
            }
            Argument arg_;
            ExecRootBlock execSuperClass_ = metaInfo.getDeclaring();
            if (execSuperClass_ != null) {
                arg_ = ExecInvokingOperation.instancePrepareCust(_context, res_, execSuperClass_, metaInfo.getCallee(), previous_, args_, "", -1);
            } else {
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
