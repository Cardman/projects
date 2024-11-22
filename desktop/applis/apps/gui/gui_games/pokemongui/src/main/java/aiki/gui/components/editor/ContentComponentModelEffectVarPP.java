package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;

public final class ContentComponentModelEffectVarPP {

    private GeneComponentModelInt deletePp;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        deletePp = new GeneComponentModelInt(_core.getProgramInfos());
        selected_.add(deletePp.geneInt());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectVarPP _edited) {
        _edited.setDeletePp((short) deletePp.valueInt());
    }
    void feedForm(EffectVarPP _edited) {
        deletePp.valueInt(_edited.getDeletePp());
    }

    public GeneComponentModelInt getDeletePp() {
        return deletePp;
    }
}
