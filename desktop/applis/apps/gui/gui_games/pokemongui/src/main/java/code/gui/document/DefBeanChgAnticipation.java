package code.gui.document;

import aiki.beans.*;
import aiki.game.fight.*;
import code.gui.*;

public final class DefBeanChgAnticipation extends BeanChgAnticipation {
    private final AbsCustCheckBox incrementing;
    private final GeneComponentModelRate damage;
    private final GeneComponentModelLong nbRounds;

    private final GeneComponentModelElt<TargetCoords> targetPosition;

    public DefBeanChgAnticipation(AbsCustCheckBox _f, GeneComponentModelRate _d, GeneComponentModelLong _c, GeneComponentModelElt<TargetCoords> _t) {
        incrementing = _f;
        damage = _d;
        nbRounds = _c;
        targetPosition = _t;
    }

    @Override
    public Anticipation valueAnt() {
        Anticipation ac_ = new Anticipation();
        ac_.setIncrementing(incrementing.isSelected());
        ac_.setDamage(damage.valueRate());
        ac_.setTargetPosition(targetPosition.tryRet());
        ac_.setNbRounds(nbRounds.valueLong());
        return ac_;
    }

    @Override
    public void valueAnt(Anticipation _v) {
        incrementing.setSelected(_v.isIncrementing());
        damage.valueRate(_v.getDamage());
        targetPosition.setupValue(_v.getTargetPosition());
        nbRounds.valueLong(_v.getNbRounds());
    }

}
