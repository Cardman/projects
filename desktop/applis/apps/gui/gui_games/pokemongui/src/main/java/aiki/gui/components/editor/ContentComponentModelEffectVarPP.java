package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;

public final class ContentComponentModelEffectVarPP {

    private GeneComponentModelLong deletePp;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        deletePp = new GeneComponentModelLong(_core.getProgramInfos());
        selected_.add(deletePp.geneLong());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
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
