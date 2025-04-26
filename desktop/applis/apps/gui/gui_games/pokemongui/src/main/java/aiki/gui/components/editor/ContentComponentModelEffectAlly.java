package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectAlly {

    private GeneComponentModelRate multAllyDamage;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        multAllyDamage = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffally.M_P_38_MUL_ALLY_DAMAGE_INTRO,multAllyDamage.geneRate()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }

    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_ALLY, _key,_input);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectAlly _edited) {
        _edited.setMultAllyDamage(multAllyDamage.valueRate());
    }
    void feedForm(EffectAlly _edited) {
        multAllyDamage.valueRate(_edited.getMultAllyDamage());
    }

    public GeneComponentModelRate getMultAllyDamage() {
        return multAllyDamage;
    }
}
