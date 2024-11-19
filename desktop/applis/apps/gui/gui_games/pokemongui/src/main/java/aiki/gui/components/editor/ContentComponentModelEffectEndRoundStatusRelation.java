package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;

public final class ContentComponentModelEffectEndRoundStatusRelation {
    private GeneComponentModelRate thievedHpRateTargetToUser;
    private AbsPanel form;
    AbsPanel effectForm(AbstractProgramInfos _core) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        thievedHpRateTargetToUser = new GeneComponentModelRate(_core);
        selected_.add(thievedHpRateTargetToUser.geneRate(Rate.zero()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectEndRoundStatusRelation _edited) {
        _edited.setThievedHpRateTargetToUser(thievedHpRateTargetToUser.valueRate());
    }
    void feedForm(EffectEndRoundStatusRelation _edited) {
        thievedHpRateTargetToUser.valueRate(_edited.getThievedHpRateTargetToUser());
    }

    public GeneComponentModelRate getThievedHpRateTargetToUser() {
        return thievedHpRateTargetToUser;
    }
}
