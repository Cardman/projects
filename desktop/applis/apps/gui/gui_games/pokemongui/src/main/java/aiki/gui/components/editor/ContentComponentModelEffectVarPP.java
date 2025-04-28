package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectVarPP {

    private GeneComponentModelLong deletePp;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        deletePp = new GeneComponentModelLong(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffvarpp.M_P_69_EFFECT,deletePp.geneLong()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_VARPP, _key,_input);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectVarPP _edited) {
        _edited.setDeletePp(deletePp.valueLong());
    }
    void feedForm(EffectVarPP _edited) {
        deletePp.valueLong(_edited.getDeletePp());
    }

    public GeneComponentModelLong getDeletePp() {
        return deletePp;
    }
}
