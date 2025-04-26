package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectDamageRate {

    private GeneComponentModelRate rateDamage;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        rateDamage = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffdamagerate.M_P_46_RATE,rateDamage.geneRate()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_DAMAGERATE, _key,_input);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectDamageRate _edited) {
        _edited.setRateDamage(rateDamage.valueRate());
    }
    void feedForm(EffectDamageRate _edited) {
        rateDamage.valueRate(_edited.getRateDamage());
    }

    public GeneComponentModelRate getRateDamage() {
        return rateDamage;
    }
}
