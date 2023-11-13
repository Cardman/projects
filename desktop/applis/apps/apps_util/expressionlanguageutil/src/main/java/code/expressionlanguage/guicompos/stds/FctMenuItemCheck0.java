package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.guicompos.MenuStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;

public final class FctMenuItemCheck0 extends FctCompoCtor {
    public FctMenuItemCheck0(CustAliases _custAliases, GuiExecutingBlocks _guiEx) {
        super(_custAliases, _guiEx);
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new MenuStruct(((LgNamesGui) _cont.getStandards()).getGuiAliases().getAliasMenuItemCheck(),_guiEx.getCompoFactory().newCheckBoxMenuItem()));
    }
}
