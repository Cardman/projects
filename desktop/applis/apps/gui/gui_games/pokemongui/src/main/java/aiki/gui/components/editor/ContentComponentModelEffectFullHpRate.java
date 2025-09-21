package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectFullHpRate {

    private GeneComponentModelRate leftUserHp;
    private GeneComponentModelSubscribeString restoredHp;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        leftUserHp = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEfffullhprate.M_P_48_LEFT_USER_HP_INTRO,leftUserHp.geneRate()));
        restoredHp = new GeneComponentModelSubscribeString(_core.getProgramInfos(),_core.getFacadeGame());
        selected_.add(line(_core,MessagesDataEfffullhprate.M_P_48_RESTORED_INTRO,restoredHp.geneEnum()));
        restoredHp.addComplete();
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_FULLHPRATE, _key,_input);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectFullHpRate _edited) {
        _edited.setLeftUserHp(leftUserHp.valueRate());
        _edited.setRestoredHp(restoredHp.tryRet());
    }
    void feedForm(EffectFullHpRate _edited) {
        leftUserHp.valueRate(_edited.getLeftUserHp());
        restoredHp.setupValue(_edited.getRestoredHp());
    }

    public GeneComponentModelSubscribeString getRestoredHp() {
        return restoredHp;
    }
}
