package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectCopyFighter {

    private GeneComponentModelLong ppForMoves;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        ppForMoves = new GeneComponentModelLong(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffcopyfighter.M_P_42_PP_MOVES_INTRO,ppForMoves.geneLong()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }

    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_COPYFIGHTER, _key,_input);
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
