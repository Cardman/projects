package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectEndRoundStatusRelation {
    private GeneComponentModelRate thievedHpRateTargetToUser;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        thievedHpRateTargetToUser = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEndroundStatusrelation.M_P_12_THIEVED_HP_RATE_INTRO,thievedHpRateTargetToUser.geneRate()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.ENDROUND_STATUSRELATION, _key,_input);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectEndRoundStatusRelation _edited) {
        _edited.setThievedHpRateTargetToUser(thievedHpRateTargetToUser.valueRate());
    }
    void feedForm(EffectEndRoundStatusRelation _edited) {
        thievedHpRateTargetToUser.valueRate(_edited.getThievedHpRateTargetToUser());
    }

    public GeneComponentModelRate getThievedHpRateTargetToUser() {
        return thievedHpRateTargetToUser;
    }
}
