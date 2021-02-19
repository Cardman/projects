package code.maths.montecarlo;
import code.maths.LgInt;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.ints.Listable;

public abstract class AbMonteCarlo<E> implements IntMonteCarlo {

    public E editNumber(LgInt _lgInt,AbstractGenerator _gene) {
        Listable<E> cles_ = events();
        if(cles_.size() == IndexConstants.SECOND_INDEX - IndexConstants.FIRST_INDEX){
            return cles_.first();
        }
        return editNumberSeed(MonteCarloUtil.randomNumberSe(MonteCarloUtil.randomNumbersSeed(_lgInt,_gene),_lgInt));
    }

    E editNumberSeed(LgInt _randomNumber) {
        LgInt sum_ = sum();
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

    public abstract E getEvent(int _index);
    public abstract LgInt getFreq(int _index);
    public abstract CustList<E> events();

    public abstract LgInt sum();

    public final boolean isZero() {
        return sum().isZero();
    }



    public abstract void addEvent(E _event,LgInt _probaRelative);

    public abstract void addQuickEvent(E _event,LgInt _probaRelative);

}
