package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.CheckBoxStruct;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;

public final class FctCheckBox1 extends FctCompoCtor {
    private final String aliasCheckBox;
    public FctCheckBox1(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasCheckBox) {
        super(_custAliases, _guiEx);
        this.aliasCheckBox = _aliasCheckBox;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new CheckBoxStruct(aliasCheckBox,_firstArgs.getArgumentWrappers().get(0).getValue().getStruct(),_guiEx.getCompoFactory()));
    }
}
