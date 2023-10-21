package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GraphicComboStruct;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.guicompos.StructScrollCustomComboList;
import code.expressionlanguage.utilcompo.CustAliases;
import code.util.StringList;

public final class DfCombo extends DfCompoCtor {
    private final String aliasCombo;

    public DfCombo(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasCombo) {
        super(_custAliases, _guiEx);
        this.aliasCombo = _aliasCombo;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new GraphicComboStruct(aliasCombo,_cont,gene((LgNamesGui) _cont.getStandards(),new StringList(),-1)));
    }

    public static StructScrollCustomComboList gene(LgNamesGui _guiEx, StringList _elts, int _index) {
        GuiExecutingBlocks guiEx_ = _guiEx.getGuiExecutingBlocks();
        StructScrollCustomComboList scr_ = new StructScrollCustomComboList(guiEx_.getCompoFactory(),guiEx_.getImageFactory(),_guiEx.getGuiAliases().getAliasActionListener());
        for (String s: _elts) {
            scr_.add(s);
        }
        scr_.select(_index);
        return scr_;
    }
}
