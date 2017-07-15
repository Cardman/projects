package aiki.fight.pokemon;
import code.util.StringList;
import code.util.ints.Equallable;

public final class TrainerPlaceNames implements Equallable<TrainerPlaceNames> {

    private final String trainer;

    private final String place;

    public TrainerPlaceNames(String _trainer, String _place) {
        trainer = _trainer;
        place = _place;
    }

    public TrainerPlaceNames(TrainerPlaceNames _p) {
        this(_p.trainer, _p.place);
    }

    public String getTrainer() {
        return trainer;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public boolean eq(TrainerPlaceNames _g) {
        if (!StringList.quickEq(trainer, _g.trainer)) {
            return false;
        }
        if (!StringList.quickEq(place, _g.place)) {
            return false;
        }
        return true;
    }
}
