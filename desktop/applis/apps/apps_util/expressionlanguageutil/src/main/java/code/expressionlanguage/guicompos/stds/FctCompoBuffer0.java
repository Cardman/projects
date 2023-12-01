package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.stds.FctThreadSetPrio;

public final class FctCompoBuffer0 implements StdCaller {
    private final CustAliases custAliases;
    private final GuiExecutingBlocks guiEx;
    private final String id;

    public FctCompoBuffer0(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _i) {
        this.custAliases = _custAliases;
        this.guiEx = _guiEx;
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        FctThreadSetPrio.preCall(_stackCall, id);
        return new ArgumentWrapper(new StringStruct(guiEx.getStringBuffer().paste()));
    }

}
