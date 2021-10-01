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
import code.expressionlanguage.structs.Struct;

public final class FctGrListClearSelection implements StdCaller {
    private final GuiAliases aliases;
    private final GuiExecutingBlocks guiEx;
    public FctGrListClearSelection(GuiAliases _aliases, GuiExecutingBlocks _guiEx) {
        aliases = _aliases;
        guiEx = _guiEx;
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        GraphicListStruct inst_ = (GraphicListStruct) _instance;
        inst_.clearSelection(aliases, _cont, guiEx, _stackCall);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
