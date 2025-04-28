package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectWinMoney {

    private GeneComponentModelRate winningRateBySumTargetUser;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        winningRateBySumTargetUser = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffwinmoney.M_P_70_EFFECT,winningRateBySumTargetUser.geneRate()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_WINMONEY, _key,_input);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectWinMoney _edited) {
        _edited.setWinningRateBySumTargetUser(winningRateBySumTargetUser.valueRate());
    }
    void feedForm(EffectWinMoney _edited) {
        winningRateBySumTargetUser.valueRate(_edited.getWinningRateBySumTargetUser());
    }

    public GeneComponentModelRate getWinningRateBySumTargetUser() {
        return winningRateBySumTargetUser;
    }
}
