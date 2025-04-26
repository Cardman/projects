package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectEndRoundPositionRelation {
    private GeneComponentModelRate healHp;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        healHp = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEndroundPositionrelation.M_P_8_HEAL_HP_INTRO,healHp.geneRate()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.ENDROUND_POSITIONRELATION, _key,_input);
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
