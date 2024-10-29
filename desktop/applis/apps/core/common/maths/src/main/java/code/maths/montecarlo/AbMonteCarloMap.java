package code.maths.montecarlo;

import code.maths.LgInt;
import code.util.AbsMap;
import code.util.CustList;
import code.util.core.IntIndexOfEntryUtil;
import code.util.ints.Listable;

public abstract class AbMonteCarloMap<E> extends AbMonteCarlo<E> {

    protected void build(AbsMap<E, LgInt> _other) {
        int len_ = _other.size();
        for (int i = 0; i < len_; i++) {
            addQuickEvent(_other.getKey(i), _other.getValue(i));
        }
    }
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
    public CustList<E> eventsDiff() {
        return new IntIndexOfEntryUtil<E>(this).differentKeys();
    }

    @Override
    public LgInt sum() {
        LgInt somme_= LgInt.zero();
        for (LgInt i:getLaw().values()) {
            somme_.addNb(i);
        }
        return somme_;
    }
    @Override
    public boolean containsEvent(E _event) {
        return getLaw().contains(_event);
    }
    @Override
    public int nbEvents() {
        return size();
    }

    @Override
    public int size() {
        return getLaw().size();
    }

    @Override
    public int indexOfEntry(E _key, int _from) {
        return getLaw().indexOfEntry(_key, _from);
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

    public LgInt rate(E _event) {
        LgInt somme_= LgInt.zero();
        for (LgInt i:getLaw().valuesKey(_event)) {
            somme_.addNb(i);
        }
        return somme_;
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
