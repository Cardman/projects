package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GraphicComboStruct;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.utilcompo.CustAliases;
import code.gui.initialize.AbstractGraphicComboBoxGenerator;
import code.util.StringList;

public final class DfCombo extends DfCompoCtor {
    private final String aliasCombo;

    public DfCombo(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasCombo) {
        super(_custAliases, _guiEx);
        this.aliasCombo = _aliasCombo;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        GuiExecutingBlocks guiEx_ = ((LgNamesGui) _cont.getStandards()).getGuiExecutingBlocks();
        AbstractGraphicComboBoxGenerator geneComboBox_ = guiEx_.getFrames().getGeneComboBox();
        return new ArgumentWrapper(new GraphicComboStruct(aliasCombo, geneComboBox_.createCombo(guiEx_.getImageFactory(),new StringList(),-1, guiEx_.getCompoFactory())));
    }
}
