package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;

public final class FctTextPane extends FctCompoCtor {
    private final String aliasTextPane;
    public FctTextPane(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _a) {
        super(_custAliases, _guiEx);
        this.aliasTextPane = _a;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new TextPaneStruct(aliasTextPane,_guiEx.getCompoFactory()));
    }
}
