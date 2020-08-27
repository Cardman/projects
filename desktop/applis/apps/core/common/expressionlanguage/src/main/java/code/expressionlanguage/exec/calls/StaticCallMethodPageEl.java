package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundCast;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.analyze.opers.ExplicitOperation;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.util.CustList;

public final class StaticCallMethodPageEl extends AbstractRefectMethodPageEl {

    @Override
    boolean initType(ContextEl _cont) {
        return initDefault(_cont);
    }

    @Override
    boolean isAbstract(ContextEl _cont) {
        return false;
    }

    @Override
    boolean isPolymorph(ContextEl _cont) {
        return false;
    }

    @Override
    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right) {
        String res_ = ExecTemplates.correctClassPartsDynamicNotWildCard(_className, _context);
        if (res_.isEmpty()) {
            String null_;
            null_ = _context.getStandards().getAliasIllegalArg();
            _context.setException(new ErrorStruct(_context,null_));
            return Argument.createVoid();
        }
        return prepareStaticCall(getMethodToCallType(),getMethodToCallBody(),_args,res_,_context.getLastPage(),_context);
    }
    private static Argument prepareStaticCall(ExecRootBlock _rootBlock,ExecNamedFunctionBlock _castOpId, CustList<Argument> _arguments, String _className,
                                              PageEl _page, ContextEl _conf) {
        if (!ExplicitOperation.customCast(_className)) {
            LgNames stds_ = _conf.getStandards();
            String null_;
            null_ = stds_.getAliasIllegalArg();
            _conf.setException(new ErrorStruct(_conf,null_));
            return Argument.createVoid();
        }
        return checkStaticCall(_rootBlock,_castOpId, _arguments, _className, _page, _conf);
    }

    private static Argument checkStaticCall(ExecRootBlock _rootBlock, ExecNamedFunctionBlock _castOpId, CustList<Argument> _arguments,
                                            String _className, PageEl _page, ContextEl _conf) {
        String paramName_ = _page.formatVarType(_className, _conf);
        if (!ExecTemplates.okArgs(_rootBlock,_castOpId,true, paramName_,_arguments, _conf, null)) {
            return Argument.createVoid();
        }
        _conf.setCallingState(new CustomFoundCast(paramName_,_rootBlock,_castOpId,_arguments));
        return Argument.createVoid();

    }
    @Override
    public String formatVarType(String _varType, ContextEl _cont) {
        return _varType;
    }
}
