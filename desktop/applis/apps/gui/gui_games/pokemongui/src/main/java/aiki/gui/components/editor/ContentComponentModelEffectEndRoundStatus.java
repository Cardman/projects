package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectEndRoundStatus {
    private GeneComponentModelRate inflictedRateHpTarget;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        inflictedRateHpTarget = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEndroundStatus.M_P_11_INFLICTED_RATE_HP_TARGET_INTRO,inflictedRateHpTarget.geneRate()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.ENDROUND_STATUS, _key,_input);
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
