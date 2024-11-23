package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.maths.*;

public final class ContentComponentModelEffectAlly {

    private GeneComponentModelRate multAllyDamage;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        multAllyDamage = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(multAllyDamage.geneRate(Rate.zero()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectAlly _edited) {
        _edited.setMultAllyDamage(multAllyDamage.valueRate());
    }
    void feedForm(EffectAlly _edited) {
        multAllyDamage.valueRate(_edited.getMultAllyDamage());
    }

    public GeneComponentModelRate getMultAllyDamage() {
        return multAllyDamage;
    }
}
