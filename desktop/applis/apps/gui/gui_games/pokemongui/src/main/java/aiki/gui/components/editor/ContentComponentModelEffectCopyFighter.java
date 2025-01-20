package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;

public final class ContentComponentModelEffectCopyFighter {

    private GeneComponentModelLong ppForMoves;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        ppForMoves = new GeneComponentModelLong(_core.getProgramInfos());
        selected_.add(ppForMoves.geneLong());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectCopyFighter _edited) {
        _edited.setPpForMoves(ppForMoves.valueLong());
    }
    void feedForm(EffectCopyFighter _edited) {
        ppForMoves.valueLong(_edited.getPpForMoves());
    }

    public GeneComponentModelLong getPpForMoves() {
        return ppForMoves;
    }
}
