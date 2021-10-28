package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.SliderStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;

public final class FctSlider0 extends FctCompoCtor {
    private final String aliasSlider;
    public FctSlider0(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasSlider) {
        super(_custAliases, _guiEx);
        this.aliasSlider = _aliasSlider;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new SliderStruct(aliasSlider,_guiEx.getWindow().getCompoFactory()));
    }
}
