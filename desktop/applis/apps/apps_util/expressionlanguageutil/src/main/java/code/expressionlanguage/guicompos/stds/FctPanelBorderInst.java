package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.PanelBorderStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;

public final class FctPanelBorderInst extends FctCompoCtor {
    private final String aliasPanelBorder;

    public FctPanelBorderInst(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasPanelBorder) {
        super(_custAliases, _guiEx);
        this.aliasPanelBorder = _aliasPanelBorder;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(PanelBorderStruct.newBorder(aliasPanelBorder, _guiEx.getCompoFactory()));
    }
}
