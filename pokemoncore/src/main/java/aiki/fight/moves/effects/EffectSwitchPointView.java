package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.enums.TargetChoice;
import code.datacheck.CheckedData;
import code.util.annot.RwXml;


@CheckedData
@RwXml
public class EffectSwitchPointView extends Effect {

    private PointViewChangementType pointViewChangement;
    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        boolean checkTargetChoice_ = false;
        if (pointViewChangement == PointViewChangementType.MIRROR_AGAINST_THROWER) {
            checkTargetChoice_ = true;
        } else if (pointViewChangement == PointViewChangementType.ATTRACT_DAMAGES_MOVES) {
            checkTargetChoice_ = true;
        }
        if (checkTargetChoice_) {
            if (getTargetChoice() != TargetChoice.LANCEUR) {
                throw new DataException();
            }
            return;
        }
        if (pointViewChangement == PointViewChangementType.THIEF_BONUSES) {
            if (getTargetChoice() == TargetChoice.LANCEUR) {
                throw new DataException();
            }
            return;
        }
        throw new DataException();
//        switch (pointViewChangement) {
//        case MIRROR_AGAINST_THROWER:
//        case ATTRACT_DAMAGES_MOVES:
//            if (getTargetChoice() != TargetChoice.LANCEUR) {
//                throw new DataException();
//            }
//            return;
//        case THIEF_BONUSES:
//            if (getTargetChoice() == TargetChoice.LANCEUR) {
//                throw new DataException();
//            }
//            return;
//        default:
//            throw new DataException();
//        }
    }
    public PointViewChangementType getPointViewChangement() {
        return pointViewChangement;
    }
    public void setPointViewChangement(PointViewChangementType _pointViewChangement) {
        pointViewChangement = _pointViewChangement;
    }
}
