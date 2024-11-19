package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;

public final class ContentComponentModelEffectEndRoundSingleStatus {
    private AbsCustCheckBox incrementingDamageByRounds;
    private AbsPanel form;
    AbsPanel effectForm(AbstractProgramInfos _core) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        incrementingDamageByRounds = _core.getCompoFactory().newCustCheckBox();
        selected_.add(incrementingDamageByRounds);
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectEndRoundSingleStatus _edited) {
        _edited.setIncrementingDamageByRounds(incrementingDamageByRounds.isSelected());
    }
    void feedForm(EffectEndRoundSingleStatus _edited) {
        incrementingDamageByRounds.setSelected(_edited.isIncrementingDamageByRounds());
    }

    public AbsCustCheckBox getIncrementingDamageByRounds() {
        return incrementingDamageByRounds;
    }

}
