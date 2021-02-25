package code.maths.montecarlo;

import code.maths.LgInt;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.CustList;
import code.util.ints.Listable;

public abstract class AbMonteCarloMap<E> extends AbMonteCarlo<E> {

    public abstract AbsMap<E, LgInt> getLaw();

    @Override
    public E getEvent(int _index) {
        return getLaw().getKey(_index);
    }

    @Override
    public LgInt getFreq(int _index) {
        return getLaw().getValue(_index);
    }

    @Override
    public CustList<E> events() {
        return getLaw().getKeys();
    }
    @Override
    public LgInt sum() {
        LgInt somme_= LgInt.zero();
        for (LgInt i:getLaw().values()) {
            somme_.addNb(i);
        }
        return somme_;
    }
    public boolean containsEvent(E _event) {
        return getLaw().contains(_event);
    }
    @Override
    public int nbEvents() {
        return getLaw().size();
    }
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

    public Rate normalizedRate(E _event) {
        LgInt sum_ = sum();
        return new Rate(rate(_event), sum_);
    }
    public LgInt rate(E _event) {
        return getLaw().getVal(_event);
    }

    public void deleteZeroEvents() {
        Listable<E> cles_= events();
        Listable<E> deletedKeys_ = new CustList<E>();
        for (E e: cles_) {
            LgInt integer_ = rate(e);
            if (integer_.isZeroOrGt() && !integer_.isZero()) {
                continue;
            }
            deletedKeys_.add(e);
        }
        for (E e:deletedKeys_) {
            getLaw().removeKey(e);
        }
    }
    @Override
    public void addEvent(E _event, LgInt _probaRelative){
        getLaw().put(_event, _probaRelative);
    }

    @Override
    public void addQuickEvent(E _event, LgInt _probaRelative){
        getLaw().addEntry(_event, _probaRelative);
    }

}
