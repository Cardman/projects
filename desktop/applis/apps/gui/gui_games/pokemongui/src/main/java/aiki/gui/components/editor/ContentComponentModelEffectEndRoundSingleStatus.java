package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectEndRoundSingleStatus {
    private AbsCustCheckBox incrementingDamageByRounds;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        incrementingDamageByRounds = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataStatus.M_P_88_DAMAGE_INCREMENTED_TRUE,incrementingDamageByRounds));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.STATUS, _key,_input);
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
