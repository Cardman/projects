package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.events.*;
import code.maths.*;
import code.util.*;

public final class ContentComponentModelEffectFullHpRate {

    private GeneComponentModelRate leftUserHp;
    private GeneComponentModelRate closestFoeDamageRateHp;
    private GeneComponentModelString restoredHp;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        leftUserHp = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(leftUserHp.geneRate(Rate.zero()));
        closestFoeDamageRateHp = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(closestFoeDamageRateHp.geneRate(Rate.zero()));
        restoredHp = new GeneComponentModelString(_core.getProgramInfos(), new StringList(), new DefValidateText());
        selected_.add(restoredHp.geneString());
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
        _edited.setRestoredHp(restoredHp.valueString());
    }
    void feedForm(EffectFullHpRate _edited) {
        leftUserHp.valueRate(_edited.getLeftUserHp());
        closestFoeDamageRateHp.valueRate(_edited.getClosestFoeDamageRateHp());
        restoredHp.valueString(_edited.getRestoredHp());
    }

    public GeneComponentModelString getRestoredHp() {
        return restoredHp;
    }
}
