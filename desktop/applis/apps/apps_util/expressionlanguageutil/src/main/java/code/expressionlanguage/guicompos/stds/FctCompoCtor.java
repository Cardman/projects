package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.util.StringList;

public abstract class FctCompoCtor implements StdCaller {
    private final CustAliases custAliases;
    private final GuiExecutingBlocks guiEx;

    protected FctCompoCtor(CustAliases _custAliases, GuiExecutingBlocks _guiEx) {
        this.custAliases = _custAliases;
        this.guiEx = _guiEx;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return inst(guiEx,_exit,_cont,_instance,_firstArgs,_stackCall);
    }
    protected static StringList newList(Struct _s) {
        if (!(_s instanceof ArrayStruct)) {
            return new StringList();
        }
        StringList l_ = new StringList();
        for (Struct s: ((ArrayStruct)_s).list()) {
            if (!(s instanceof StringStruct)) {
                continue;
            }
            l_.add(((StringStruct)s).getInstance());
        }
        return l_;
    }
    public abstract ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall);
}
