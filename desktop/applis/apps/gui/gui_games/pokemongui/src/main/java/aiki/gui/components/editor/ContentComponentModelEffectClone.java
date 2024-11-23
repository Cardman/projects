package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.maths.*;

public final class ContentComponentModelEffectClone {

    private GeneComponentModelRate hpRateClone;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        hpRateClone = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(hpRateClone.geneRate(Rate.zero()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectClone _edited) {
        _edited.setHpRateClone(hpRateClone.valueRate());
    }
    void feedForm(EffectClone _edited) {
        hpRateClone.valueRate(_edited.getHpRateClone());
    }

    public GeneComponentModelRate getHpRateClone() {
        return hpRateClone;
    }
}
