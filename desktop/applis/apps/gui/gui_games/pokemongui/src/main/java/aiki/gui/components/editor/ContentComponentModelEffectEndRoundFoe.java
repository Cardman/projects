package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectEndRoundFoe {
    private GeneComponentModelRate inflictedRateHpTarget;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        inflictedRateHpTarget = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(SubscribedTranslationList.line(_core.getProgramInfos(),MessagesPkBean.ENDROUND_FOE,MessagesDataEndroundFoe.M_P_5_FOE_INTRO,inflictedRateHpTarget.geneRate()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectEndRoundFoe _edited) {
        _edited.setInflictedRateHpTarget(inflictedRateHpTarget.valueRate());
    }
    void feedForm(EffectEndRoundFoe _edited) {
        inflictedRateHpTarget.valueRate(_edited.getInflictedRateHpTarget());
    }

    public GeneComponentModelRate getInflictedRateHpTarget() {
        return inflictedRateHpTarget;
    }
}
