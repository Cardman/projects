package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.guicompos.SeparatorStruct;
import code.expressionlanguage.utilcompo.CustAliases;

public final class DfSeparator extends DfCompoCtor {
    private final String aliasSep;

    public DfSeparator(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _alSep) {
        super(_custAliases, _guiEx);
        this.aliasSep = _alSep;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new SeparatorStruct(aliasSep, ((LgNamesGui) _cont.getStandards()).getGuiExecutingBlocks().getCompoFactory()));
    }
}
