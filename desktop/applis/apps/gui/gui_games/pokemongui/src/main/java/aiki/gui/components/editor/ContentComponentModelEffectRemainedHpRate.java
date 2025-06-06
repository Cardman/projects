package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectRemainedHpRate {

    private GeneComponentModelRate rateHp;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        rateHp = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffremainedhprate.M_P_56_RATE,rateHp.geneRate()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_REMAINEDHPRATE, _key,_input);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectRemainedHpRate _edited) {
        _edited.setRateHp(rateHp.valueRate());
    }
    void feedForm(EffectRemainedHpRate _edited) {
        rateHp.valueRate(_edited.getRateHp());
    }

    public GeneComponentModelRate getRateHp() {
        return rateHp;
    }
}
