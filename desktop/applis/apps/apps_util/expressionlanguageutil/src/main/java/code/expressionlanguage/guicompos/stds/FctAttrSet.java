package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;

public final class FctAttrSet implements StdCaller {
    private final GuiExecutingBlocks executingBlocks;

    public FctAttrSet(GuiExecutingBlocks _e) {
        this.executingBlocks = _e;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new AttrSetStruct(executingBlocks.getCompoFactory()));
    }
}
