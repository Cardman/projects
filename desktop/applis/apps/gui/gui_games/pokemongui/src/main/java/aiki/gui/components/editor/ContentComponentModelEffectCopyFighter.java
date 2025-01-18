package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;

public final class ContentComponentModelEffectCopyFighter {

    private GeneComponentModelInt ppForMoves;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        ppForMoves = new GeneComponentModelInt(_core.getProgramInfos());
        selected_.add(ppForMoves.geneInt());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectCopyFighter _edited) {
        _edited.setPpForMoves(ppForMoves.valueInt());
    }
    void feedForm(EffectCopyFighter _edited) {
        ppForMoves.valueInt(_edited.getPpForMoves());
    }

    public GeneComponentModelInt getPpForMoves() {
        return ppForMoves;
    }
}
