package aiki.comparators;
import code.util.ints.Comparing;

import aiki.fight.pokemon.TrainerPlaceNames;
import code.util.CustList;

public final class ComparatorPairStringNumber implements Comparing<TrainerPlaceNames> {

    @Override
    public int compare(TrainerPlaceNames _o1, TrainerPlaceNames _o2) {
        int res_ = _o1.getTrainer().compareTo(_o2.getTrainer());
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        return _o1.getPlace().compareTo(_o2.getPlace());
    }

}
