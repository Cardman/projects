package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.NumDiffDenNum;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.CustList;
import code.util.EqList;
import code.util.ints.Listable;

public abstract class AbMonteCarlo<E> implements IntMonteCarlo {

    private static final int NB_RAND = 4;

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
        return (int)randomLong(Integer.MAX_VALUE + 1l);
    }

    /**@param _excludeMax the maximum of possible returned values
    @return an long from 0 inclusive to the argument excluded*/
    public static long randomLong(long _excludeMax) {
        return (long) (Math.random() * _excludeMax);
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
