package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.ints.IntIndexOfEntry;
import code.util.ints.Listable;

public abstract class AbMonteCarlo<E> implements IntMonteCarlo, IntIndexOfEntry<E> {

    public E editNumber(LgInt _lgInt,AbstractGenerator _gene) {
        return editNumber(_lgInt, _gene, _gene.seed());
    }
    public E editNumber(LgInt _lgInt,AbstractGenerator _gene, CustomSeedGene _cust) {
        Listable<E> cles_ = events();
        if(cles_.size() == IndexConstants.SECOND_INDEX - IndexConstants.FIRST_INDEX){
            return cles_.first();
        }
        return editNumberSeed(MonteCarloUtil.randomNumberSe(MonteCarloUtil.randomNumbersSeed(_lgInt,_gene,_cust),_lgInt));
    }
    public E editNumber(LgInt _lgInt,AbstractGenerator _gene, CustomSeedGene _cust, AbsDoubleToStrConverter _conv, CustList<String> _rands) {
        Listable<E> cles_ = events();
        if(cles_.size() == IndexConstants.SECOND_INDEX - IndexConstants.FIRST_INDEX){
            return cles_.first();
        }
        return editNumberSeed(MonteCarloUtil.randomNumberSe(MonteCarloUtil.randomNumbersSeed(_lgInt,_gene,_cust,_conv,_rands),_lgInt));
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
    @Override
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
    public boolean isEmpty() {
        return nbEvents() == 0;
    }
    public abstract boolean containsEvent(E _event);
    public abstract E getEvent(int _index);
    public abstract LgInt getFreq(int _index);
    public abstract CustList<E> events();
    public abstract CustList<E> eventsDiff();

    @Override
    public E getKey(int _i) {
        return getEvent(_i);
    }

    public Rate normalizedRate(E _event) {
        LgInt sum_ = sum();
        return new Rate(rate(_event), sum_);
    }
    public abstract LgInt sum();
    public abstract LgInt rate(E _e);

    public final boolean isZero() {
        return sum().isZero();
    }
    public abstract void addEvent(E _event, LgInt _probaRelative);

    public abstract void addQuickEvent(E _event, LgInt _probaRelative);

}
