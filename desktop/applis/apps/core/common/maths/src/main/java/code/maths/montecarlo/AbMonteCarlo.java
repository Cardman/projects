package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.AbsMap;
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
            sum_.addNb(rate(getLaw().getKey(i_)));
            i_++;
        }
        i_--;
        return getLaw().getKey(i_);
    }

    public abstract AbsMap<E,LgInt> getLaw();

    public boolean isValid() {
        if (getLaw().isEmpty()) {
            return false;
        }
        for (LgInt i:getLaw().values()) {
            if (i.isZero()) {
                return false;
            }
            if (!i.isZeroOrGt()) {
                return false;
            }
        }
        return true;
    }

    public LgInt sum() {
        LgInt somme_= LgInt.zero();
        for (LgInt i:getLaw().values()) {
            somme_.addNb(i);
        }
        return somme_;
    }

    @Override
    public int nbEvents() {
        return getLaw().size();
    }

    public CustList<E> events() {
        return getLaw().getKeys();
    }

    public boolean containsEvent(E _event) {
        return getLaw().contains(_event);
    }

    public final boolean isZero() {
        return sum().isZero();
    }

    public final Rate normalizedRate(E _event) {
        LgInt sum_ = sum();
        return new Rate(rate(_event), sum_);
    }

    public LgInt rate(E _event) {
        return getLaw().getVal(_event);
    }

    public void addEvent(E _event,LgInt _probaRelative){
        getLaw().put(_event, _probaRelative);
    }

    public void addQuickEvent(E _event,LgInt _probaRelative){
        getLaw().addEntry(_event, _probaRelative);
    }

    public boolean checkEvents() {
        Listable<E> cles_= events();
        for (E e: cles_) {
            LgInt integer_ = rate(e);
            if (integer_.isZeroOrGt()) {
                continue;
            }
            return false;
        }
        if (!getLaw().isEmpty()) {
            return !sum().isZero();
        }
        return true;
    }

    public void deleteZeroEvents() {
        Listable<E> cles_= events();
        Listable<E> deletedKeys_ = new CustList<E>();
        for (E e: cles_) {
            LgInt integer_ = rate(e);
            if (integer_.isZeroOrGt()) {
                if (!integer_.isZero()) {
                    continue;
                }
            }
            deletedKeys_.add(e);
        }
        for (E e:deletedKeys_) {
            getLaw().removeKey(e);
        }
    }
}
