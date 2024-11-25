package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;

public final class ContentComponentModelEffectRemainedHpRate {

    private GeneComponentModelRate rateHp;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        rateHp = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(rateHp.geneRate());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectRemainedHpRate _edited) {
        _edited.setRateHp(rateHp.valueRate());
    }
    void feedForm(EffectRemainedHpRate _edited) {
        rateHp.valueRate(_edited.getRateHp());
    }

    public GeneComponentModelRate getRateHp() {
        return rateHp;
    }
}
