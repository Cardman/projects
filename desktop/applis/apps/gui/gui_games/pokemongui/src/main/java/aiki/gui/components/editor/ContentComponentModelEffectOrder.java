package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectOrder {

    private AbsCustCheckBox targetAttacksLast;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        targetAttacksLast = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffclone.M_P_40_EFFECT_0,targetAttacksLast));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_ORDER, _key,_input);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectOrder _edited) {
        _edited.setTargetAttacksLast(targetAttacksLast.isSelected());
    }
    void feedForm(EffectOrder _edited) {
        targetAttacksLast.setSelected(_edited.getTargetAttacksLast());
    }

    public AbsCustCheckBox getTargetAttacksLast() {
        return targetAttacksLast;
    }
}
