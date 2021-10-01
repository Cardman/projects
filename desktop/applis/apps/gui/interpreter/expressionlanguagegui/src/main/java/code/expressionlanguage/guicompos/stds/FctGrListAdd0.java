package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GraphicListStruct;
import code.expressionlanguage.guicompos.GuiAliases;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctGrListAdd0 implements StdCaller {
    private final GuiAliases aliases;
    private final GuiExecutingBlocks guiEx;
    public FctGrListAdd0(GuiAliases _aliases, GuiExecutingBlocks _guiEx) {
        aliases = _aliases;
        guiEx = _guiEx;
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        GraphicListStruct inst_ = (GraphicListStruct) _instance;
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        inst_.add(aliases, _cont, guiEx, _stackCall, ((NumberStruct)argumentWrappers_.get(0).getValue().getStruct()).intStruct(),argumentWrappers_.get(1).getValue().getStruct());
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
