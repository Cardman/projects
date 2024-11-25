package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;

public final class ContentComponentModelEffectEndRoundStatus {
    private GeneComponentModelRate inflictedRateHpTarget;
    private AbsPanel form;
    AbsPanel effectForm(AbstractProgramInfos _core) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        inflictedRateHpTarget = new GeneComponentModelRate(_core);
        selected_.add(inflictedRateHpTarget.geneRate());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectEndRoundStatus _edited) {
        _edited.setInflictedRateHpTarget(inflictedRateHpTarget.valueRate());
    }
    void feedForm(EffectEndRoundStatus _edited) {
        inflictedRateHpTarget.valueRate(_edited.getInflictedRateHpTarget());
    }

    public GeneComponentModelRate getInflictedRateHpTarget() {
        return inflictedRateHpTarget;
    }
}
