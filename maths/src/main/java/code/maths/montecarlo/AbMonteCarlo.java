package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.NumDiffDenNum;
import code.maths.Rate;
import code.maths.exceptions.NegatifExposantException;
import code.maths.exceptions.ZeroSumEventException;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;

public abstract class AbMonteCarlo<E> {

    private static final LgInt MAX_RANDOM = LgInt.getMaxLongPlusOne();

    private static final int NB_RAND = 4;

    public static CustList<Object> suffledElts(Listable<? extends Object> _list) {
        CustList<Object> list_ = new CustList<Object>(_list);
        Numbers<Integer> indexes_ = new Numbers<Integer>();
        Numbers<Integer> indexesEdited_ = new Numbers<Integer>();
        int size_ = list_.size();
        for (int i = CustList.FIRST_INDEX; i < size_; i++) {
            indexes_.add(i);
        }
        while (!indexes_.isEmpty()) {
            long len_ = indexes_.size();
            int rem_ = randomInt(len_);
            //rem_ >= 0 && rem_ < len_
            indexesEdited_.add(indexes_.get(rem_));
            indexes_.removeAt(rem_);
        }
        CustList<Object> newList_ = new CustList<Object>();
        for (int i: indexesEdited_) {
            newList_.add(list_.get(i));
        }
        list_.clear();
        list_.addAllElts(newList_);
        return list_;
    }

    public static MonteCarloBoolean booleanLaw(Rate _rateEnabled) {
        MonteCarloBoolean loi_ = new MonteCarloBoolean();
        if (_rateEnabled.greaterOrEqualsOne()){
            loi_.addEvent(true,LgInt.one());
            return loi_;
        }
        if (!_rateEnabled.isZeroOrGt()) {
            loi_.addEvent(false,LgInt.one());
            return loi_;
        }
        if (_rateEnabled.isZero()) {
            loi_.addEvent(false,LgInt.one());
            return loi_;
        }
        NumDiffDenNum p_ = _rateEnabled.getNumDiffDenNum();
//        LgInt num_=_rateEnabled.getNumerator();
//        LgInt den_=_rateEnabled.getDenominator();
//        loi_.addEvent(true,num_);
//        loi_.addEvent(false,LgInt.minus(den_, num_));
        loi_.addEvent(true,p_.getNumerator());
        loi_.addEvent(false,p_.getDiffDenNumerator());
        return loi_;
    }
    public E editNumber() {
        Listable<E> cles_ = events();
        if(cles_.size() == CustList.SECOND_INDEX - CustList.FIRST_INDEX){
            return cles_.first();
        }
        if (cles_.isEmpty()) {
            return null;
        }
        return editNumber(randomNumber(randomNumbers()));
    }
    private static EqList<LgInt> randomNumbers() {
        EqList<LgInt> numbers_ = new EqList<LgInt>();
        for(int i = CustList.FIRST_INDEX; i < NB_RAND; i++){
//          numbers_.add(MAX_RANDOM.multiply(randomDouble()));
            numbers_.add(randomLgInt());
        }
        return numbers_;
    }

    static LgInt getMaxRandom() {
        return MAX_RANDOM;
    }

    public static LgInt randomLgInt() {
        return randomLgInt(MAX_RANDOM);
    }

    public static LgInt randomLgInt(LgInt _excludeMax) {
        return Rate.multiply(randomRate(), new Rate(_excludeMax)).intPart();
    }

    public static Rate randomRate() {
        return new Rate(randomInt(), Integer.MAX_VALUE + 1l);
    }

    public static int randomInt() {
        return randomInt(Integer.MAX_VALUE + 1l);
    }

    public static int randomInt(long _excludeMax) {
        long value_ = System.currentTimeMillis();
        return (int) (value_ % _excludeMax);
    }

    protected static LgInt maxNumber() {
        EqList<LgInt> numbers_ = new EqList<LgInt>();
        for(int i = CustList.FIRST_INDEX; i< NB_RAND; i++){
            numbers_.add(new LgInt(Long.MAX_VALUE));
        }
        return randomNumber(numbers_);
    }

    static LgInt randomNumber(EqList<LgInt> _numbers) {
        LgInt alea_=LgInt.zero();
        for(LgInt i: _numbers){
            alea_.multiplyBy(MAX_RANDOM);
            alea_.addNb(i);
        }
        return alea_;
    }

    E editNumber(LgInt _randomNumber) {
        LgInt sum_ = sum();
        LgInt random_ = LgInt.remain(_randomNumber, sum_);
        sum_.affectZero();
        for(E c: events()){
            sum_.addNb(rate(c));
            if(LgInt.strLower(random_, sum_)){
                return c;
            }
        }
        return null;
    }

    protected abstract ListableEntries<E,LgInt> getLaw();

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

    public Listable<E> events() {
        return getLaw().getKeys();
    }

    public boolean containsEvent(E _event) {
        return getLaw().contains(_event);
    }

    public LgInt rate(E _event) {
        return getLaw().getVal(_event);
    }

    public void addEvent(E _event,LgInt _probaRelative){
        getLaw().put(_event, _probaRelative);
    }

    public void checkEvents() {
        Listable<E> cles_= events();
        for (E e: cles_) {
            LgInt integer_ = rate(e);
            if (integer_.isZeroOrGt()) {
                continue;
            }
            throw new NegatifExposantException();
        }
        if (!getLaw().isEmpty()) {
            if (sum().isZero()) {
                throw new ZeroSumEventException();
            }
        }
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
    @Override
    public String toString() {
        return getLaw().toString();
    }
}
