package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GraphicComboStruct;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.gui.initialize.AbstractGraphicComboBoxGenerator;
import code.util.CustList;

public final class FctCombo2 extends FctCompoCtor {
    private final String aliasCombo;
    public FctCombo2(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasCombo) {
        super(_custAliases, _guiEx);
        this.aliasCombo = _aliasCombo;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractGraphicComboBoxGenerator geneComboBox_ = _guiEx.getWindow().getFrames().getGeneComboBox();
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        return new ArgumentWrapper(new GraphicComboStruct(aliasCombo, geneComboBox_.createCombo(_guiEx.getWindow().getImageFactory(),newList(argumentWrappers_.get(1).getValue().getStruct()), ((NumberStruct)argumentWrappers_.get(0).getValue().getStruct()).intStruct(), _guiEx.getWindow().getCompoFactory())));
    }
}
