package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.MenuStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.initialize.AbsCompoFactory;

public final class FctMenuAddSeparator implements StdCaller {
    private final String aliasSep;
    private final AbsCompoFactory ex;

    public FctMenuAddSeparator(AbsCompoFactory _guiEx, String _alSep) {
        this.ex = _guiEx;
        this.aliasSep = _alSep;
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        MenuStruct inst_ = (MenuStruct) _instance;
        inst_.addSeparator(aliasSep,ex);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
