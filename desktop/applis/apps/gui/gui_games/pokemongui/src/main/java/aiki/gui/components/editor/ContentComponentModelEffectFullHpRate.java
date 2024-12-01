package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;

public final class ContentComponentModelEffectFullHpRate {

    private GeneComponentModelRate leftUserHp;
    private GeneComponentModelRate closestFoeDamageRateHp;
    private GeneComponentModelSubscribeString restoredHp;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        leftUserHp = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(leftUserHp.geneRate());
        closestFoeDamageRateHp = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(closestFoeDamageRateHp.geneRate());
        restoredHp = new GeneComponentModelSubscribeString(_core.getProgramInfos());
        selected_.add(restoredHp.geneEnum());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectFullHpRate _edited) {
        _edited.setLeftUserHp(leftUserHp.valueRate());
        _edited.setClosestFoeDamageRateHp(closestFoeDamageRateHp.valueRate());
        _edited.setRestoredHp(restoredHp.tryRet());
    }
    void feedForm(EffectFullHpRate _edited) {
        leftUserHp.valueRate(_edited.getLeftUserHp());
        closestFoeDamageRateHp.valueRate(_edited.getClosestFoeDamageRateHp());
        restoredHp.setupValue(_edited.getRestoredHp());
    }

    public GeneComponentModelSubscribeString getRestoredHp() {
        return restoredHp;
    }
}
