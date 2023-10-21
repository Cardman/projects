package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GraphicComboStruct;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;

public final class FctCombo1 extends FctCompoCtor {
    private final String aliasCombo;
    public FctCombo1(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasCombo) {
        super(_custAliases, _guiEx);
        this.aliasCombo = _aliasCombo;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new GraphicComboStruct(aliasCombo, _cont,DfCombo.gene((LgNamesGui) _cont.getStandards(),newList(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct()), 0)));
    }
}
