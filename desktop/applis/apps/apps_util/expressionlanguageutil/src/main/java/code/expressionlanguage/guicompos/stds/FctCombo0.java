package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GraphicComboStruct;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.gui.initialize.AbstractGraphicComboBoxGenerator;
import code.util.StringList;

public final class FctCombo0 extends FctCompoCtor {
    private final String aliasCombo;
    public FctCombo0(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasCombo) {
        super(_custAliases, _guiEx);
        this.aliasCombo = _aliasCombo;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractGraphicComboBoxGenerator geneComboBox_ = _guiEx.getFrames().getGeneComboBox();
        return new ArgumentWrapper(new GraphicComboStruct(aliasCombo, geneComboBox_.createCombo(_guiEx.getImageFactory(),new StringList(),-1, _guiEx.getCompoFactory())));
    }
}
