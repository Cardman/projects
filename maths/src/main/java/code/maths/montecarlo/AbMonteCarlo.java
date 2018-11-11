package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.NumDiffDenNum;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Listable;

public abstract class AbMonteCarlo<E> implements IntMonteCarlo {

    private static final int NB_RAND = 4;

    public static CustList<Object> suffledElts(Object... _list) {
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

    public static StringList suffledStrings(String... _list) {
        StringList list_ = new StringList(_list);
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
        StringList newList_ = new StringList();
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
        loi_.addEvent(true,p_.getNumerator());
        loi_.addEvent(false,p_.getDiffDenNumerator());
        return loi_;
    }
    public E editNumber(LgInt _lgInt) {
        Listable<E> cles_ = events();
        if(cles_.size() == CustList.SECOND_INDEX - CustList.FIRST_INDEX){
            return cles_.first();
        }
        if (cles_.isEmpty()) {
            return null;
        }
        return editNumberSeed(randomNumberSe(randomNumbersSeed(_lgInt),_lgInt));
    }
    private static EqList<LgInt> randomNumbersSeed(LgInt _lgInt) {
        EqList<LgInt> numbers_ = new EqList<LgInt>();
        for(int i = CustList.FIRST_INDEX; i < NB_RAND; i++){
//          numbers_.add(MAX_RANDOM.multiply(randomDouble()));
            numbers_.add(randomLgIntSeed(_lgInt));
        }
        return numbers_;
    }

    public static LgInt randomLgIntSeed(LgInt _lgInt) {
        return randomLgInt(_lgInt);
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

    protected static LgInt maxNumber(LgInt _max) {
        EqList<LgInt> numbers_ = new EqList<LgInt>();
        for(int i = CustList.FIRST_INDEX; i< NB_RAND; i++){
            numbers_.add(new LgInt(Long.MAX_VALUE));
        }
        return randomNumberSe(numbers_,_max);
    }

    static LgInt randomNumberSe(EqList<LgInt> _numbers,LgInt _max) {
        LgInt alea_=LgInt.zero();
        for(LgInt i: _numbers){
            alea_.multiplyBy(_max);
            alea_.addNb(i);
        }
        return alea_;
    }

    E editNumberSeed(LgInt _randomNumber) {
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

    protected abstract AbsMap<E,LgInt> getLaw();

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

    public Listable<E> events() {
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
            if (sum().isZero()) {
                return false;
            }
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
