package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectEndRound {
    private GeneComponentModelSubscribeString failEndRound;
    private GeneComponentModelLong endRoundRank;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        failEndRound = new GeneComponentModelSubscribeString(_core.getProgramInfos(),_core.getFacadeGame());
        selected_.add(line(_core,MessagesDataEffendround.M_P_47_REASONS,failEndRound.geneEnum()));
        failEndRound.addComplete();
        endRoundRank = new GeneComponentModelLong(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffendround.M_P_47_RANK_INTRO,endRoundRank.geneLong()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core,String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_ENDROUND, _key,_input);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectEndRound _edited) {
        _edited.setFailEndRound(failEndRound.tryRet());
        _edited.setEndRoundRank(endRoundRank.valueLong());
    }
    void feedForm(EffectEndRound _edited) {
        failEndRound.setupValue(_edited.getFailEndRound());
        endRoundRank.valueLong(_edited.getEndRoundRank());
    }

    public GeneComponentModelSubscribeString getFailEndRound() {
        return failEndRound;
    }

    public GeneComponentModelLong getEndRoundRank() {
        return endRoundRank;
    }
}
