package aiki.comparators;
import aiki.fight.pokemon.TrainerPlaceNames;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorTrainerPlaceNames implements Comparing<TrainerPlaceNames> {

    @Override
    public int compare(TrainerPlaceNames _o1, TrainerPlaceNames _o2) {
        int res_ = StringUtil.compareStrings(_o1.getTrainer(),_o2.getTrainer());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        return StringUtil.compareStrings(_o1.getPlace(),_o2.getPlace());
    }

}
