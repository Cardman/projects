package code.maths.montecarlo;
import code.maths.LgInt;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.ints.Listable;

public abstract class AbMonteCarlo<E> implements IntMonteCarlo {

    public E editNumber(LgInt _lgInt,AbstractGenerator _gene) {
        return editNumber(_lgInt, _gene, new CustomSeedGene());
    }
    public E editNumber(LgInt _lgInt,AbstractGenerator _gene, CustomSeedGene _cust) {
        Listable<E> cles_ = events();
        if(cles_.size() == IndexConstants.SECOND_INDEX - IndexConstants.FIRST_INDEX){
            return cles_.first();
        }
        return editNumberSeed(MonteCarloUtil.randomNumberSe(MonteCarloUtil.randomNumbersSeed(_lgInt,_gene,_cust),_lgInt));
    }

    E editNumberSeed(LgInt _randomNumber) {
        LgInt sum_ = LgInt.zero();
        int nbEvts_ = nbEvents();
        for (int i = 0; i < nbEvts_; i++) {
            sum_.addNb(getFreq(i));
        }
        LgInt random_ = LgInt.remain(_randomNumber, sum_);
        sum_.affectZero();
        int i_ = 0;
        while (LgInt.greaterEq(random_,sum_)) {
            sum_.addNb(getFreq(i_));
            i_++;
        }
        i_--;
        return getEvent(i_);
    }
    public boolean checkEvents() {
        int len_ = nbEvents();
        for (int i = 0; i < len_; i++){
            if (getFreq(i).isZeroOrGt()) {
                continue;
            }
            return false;
        }
        if (len_ > 0) {
            return !isZero();
        }
        return true;
    }
    public abstract E getEvent(int _index);
    public abstract LgInt getFreq(int _index);
    public abstract CustList<E> events();

    public abstract LgInt sum();

    public final boolean isZero() {
        return sum().isZero();
    }


}
