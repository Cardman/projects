package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;

public final class ContentComponentModelEffectEndRoundPositionRelation {
    private GeneComponentModelRate healHp;
    private AbsPanel form;
    AbsPanel effectForm(AbstractProgramInfos _core) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        healHp = new GeneComponentModelRate(_core);
        selected_.add(healHp.geneRate());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectEndRoundPositionRelation _edited) {
        _edited.setHealHp(healHp.valueRate());
    }
    void feedForm(EffectEndRoundPositionRelation _edited) {
        healHp.valueRate(_edited.getHealHp());
    }

    public GeneComponentModelRate getHealHp() {
        return healHp;
    }

}
