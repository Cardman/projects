package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;

public final class ContentComponentModelEffectWinMoney {

    private GeneComponentModelRate winningRateBySumTargetUser;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        winningRateBySumTargetUser = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(winningRateBySumTargetUser.geneRate());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
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
