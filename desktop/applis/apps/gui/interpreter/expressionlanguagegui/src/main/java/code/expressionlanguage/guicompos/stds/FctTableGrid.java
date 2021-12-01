package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.TableStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;

public final class FctTableGrid extends FctCompoCtor {
    private final String aliasTableGui;

    public FctTableGrid(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasTableGui) {
        super(_custAliases, _guiEx);
        this.aliasTableGui = _aliasTableGui;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new TableStruct(aliasTableGui, _firstArgs.getArgumentWrappers().get(0).getValue().getStruct(), _guiEx.getCompoFactory()));
    }
}
