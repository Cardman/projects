package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;


public final class EffectSwitchPointView extends Effect {

    private PointViewChangementType pointViewChangement;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        boolean checkTargetChoice_ = pointViewChangement == PointViewChangementType.MIRROR_AGAINST_THROWER || pointViewChangement == PointViewChangementType.ATTRACT_DAMAGES_MOVES;
        if (checkTargetChoice_) {
            DataInfoChecker.checkTarget(TargetChoice.LANCEUR,getTargetChoice(),_data);
            return;
        }
        if (pointViewChangement == PointViewChangementType.THIEF_BONUSES) {
            DataInfoChecker.checkTargetNot(TargetChoice.LANCEUR,getTargetChoice(),_data);
            return;
        }
        _data.setError(true);

    }

    public PointViewChangementType getPointViewChangement() {
        return pointViewChangement;
    }

    public void setPointViewChangement(
            PointViewChangementType _pointViewChangement) {
        pointViewChangement = _pointViewChangement;
    }
}
