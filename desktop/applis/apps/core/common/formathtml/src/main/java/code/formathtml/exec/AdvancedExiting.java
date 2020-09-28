package code.formathtml.exec;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.InitClassState;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.structs.CausingErrorStruct;
import code.formathtml.Configuration;

public final class AdvancedExiting implements AbstractExiting {
    private final Configuration context;

    public AdvancedExiting(Configuration context) {
        this.context = context;
    }

    public static boolean hasToExit(String _className, ContextEl context) {
        return hasToExit(_className,null, context);
    }

    public static boolean hasToExit(String _className, Argument _arg, ContextEl context) {
        Classes classes_ = context.getClasses();
        String idCl_ = StringExpUtil.getIdFromAllTypes(_className);
        ExecRootBlock c_ = classes_.getClassBody(idCl_);
        if (c_ != null) {
            InitClassState res_ = context.getLocks().getState(context, idCl_);
            if (res_ == InitClassState.NOT_YET) {
                context.setCallingState(new NotInitializedClass(idCl_,c_, _arg));
                return true;
            }
            if (res_ == InitClassState.ERROR) {
                CausingErrorStruct causing_ = new CausingErrorStruct(idCl_, context);
                context.setException(causing_);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasToExit(String _className) {
        return hasToExit(_className,(Argument)null);
    }

    @Override
    public boolean hasToExit(String _className, Argument _arg) {
        return hasToExit(_className,_arg, context.getContext());
    }
}
