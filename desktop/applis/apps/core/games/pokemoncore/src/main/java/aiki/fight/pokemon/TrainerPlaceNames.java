package aiki.fight.pokemon;

import aiki.comparators.ComparatorTrainerPlaceNames;
import code.util.CustList;
import code.util.core.SortConstants;

public final class TrainerPlaceNames {

    private final String trainer;

    private final String place;

    public TrainerPlaceNames(String _trainer, String _place) {
        trainer = _trainer;
        place = _place;
    }

    public static boolean containsTrPlacNames(CustList<TrainerPlaceNames> _list, TrainerPlaceNames _t) {
        for (TrainerPlaceNames t: _list) {
            if (eq(_t, t)) {
                return true;
            }
        }
        return false;
    }

    private static boolean eq(TrainerPlaceNames _current, TrainerPlaceNames _g) {
        return new ComparatorTrainerPlaceNames().compare(_current, _g) == SortConstants.EQ_CMP;
//        if (!StringUtil.quickEq(_current.getTrainer(), _g.getTrainer())) {
//            return false;
//        }
//        return StringUtil.quickEq(_current.getPlace(), _g.getPlace());
    }

    public String getTrainer() {
        return trainer;
    }

    public String getPlace() {
        return place;
    }

}
