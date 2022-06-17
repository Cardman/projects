package code.maths.montecarlo;

import code.maths.LgInt;
import code.maths.NumDiffDenNum;
import code.maths.Rate;
import code.maths.litteralcom.MathExpUtil;
import code.util.CustList;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public final class MonteCarloUtil {
    private static final int NB_RAND = 4;

    private MonteCarloUtil() {
    }

    public static MonteCarloBoolean booleanLaw(Rate _rateEnabled) {
        MonteCarloBoolean loi_ = new MonteCarloBoolean();
        if (_rateEnabled.greaterOrEqualsOne()){
            loi_.addQuickEvent(BoolVal.TRUE,LgInt.one());
            return loi_;
        }
        if (!_rateEnabled.isZeroOrGt()) {
            loi_.addQuickEvent(BoolVal.FALSE,LgInt.one());
            return loi_;
        }
        if (_rateEnabled.isZero()) {
            loi_.addQuickEvent(BoolVal.FALSE,LgInt.one());
            return loi_;
        }
        NumDiffDenNum p_ = _rateEnabled.getNumDiffDenNum();
        loi_.addQuickEvent(BoolVal.TRUE,p_.getNumerator());
        loi_.addQuickEvent(BoolVal.FALSE,p_.getDiffDenNumerator());
        return loi_;
    }

    static CustList<LgInt> randomNumbersSeed(LgInt _lgInt, AbstractGenerator _gene, CustomSeedGene _cust) {
        CustList<LgInt> numbers_ = new CustList<LgInt>();
        for(int i = IndexConstants.FIRST_INDEX; i < NB_RAND; i++){
//          numbers_.add(MAX_RANDOM.multiply(randomDouble()));
            numbers_.add(randomLgInt(_lgInt, _gene, _cust));
        }
        return numbers_;
    }

    public static LgInt randomLgInt(LgInt _excludeMax, AbstractGenerator _gene) {
        return randomLgInt(_excludeMax, _gene, _gene.seed());
    }

    public static LgInt randomLgInt(LgInt _excludeMax, AbstractGenerator _gene, CustomSeedGene _cust) {
        return Rate.multiply(randomRate(_gene,_cust), new Rate(_excludeMax)).intPart();
    }

    private static Rate randomRate(AbstractGenerator _gene, CustomSeedGene _cust) {
        return new Rate(randomInt(_gene,_cust), Integer.MAX_VALUE + 1L);
    }

    private static int randomInt(AbstractGenerator _gene, CustomSeedGene _cust) {
        return (int)randomLong(Integer.MAX_VALUE + 1L,_gene,_cust);
    }

    /**@param _excludeMax the maximum of possible returned values
    @return an long from 0 inclusive to the argument excluded*/
    public static long randomLong(long _excludeMax, AbstractGenerator _gene) {
        return randomLong(_excludeMax, _gene, _gene.seed());
    }
    public static long randomLong(long _excludeMax, AbstractGenerator _gene, CustomSeedGene _cust) {
        return randomLong(_excludeMax, _cust.pick(_gene));
    }
    public static long randomLong(long _excludeMax, double _gene) {
        return (long) (_gene * MathExpUtil.toDouble(_excludeMax));
    }

    static LgInt maxNumber(LgInt _max) {
        CustList<LgInt> numbers_ = new CustList<LgInt>();
        for(int i = IndexConstants.FIRST_INDEX; i< NB_RAND; i++){
            numbers_.add(new LgInt(Long.MAX_VALUE));
        }
        return randomNumberSe(numbers_,_max);
    }

    static LgInt randomNumberSe(CustList<LgInt> _numbers, LgInt _max) {
        LgInt alea_=LgInt.zero();
        for(LgInt i: _numbers){
            alea_.multiplyBy(_max);
            alea_.addNb(i);
        }
        return alea_;
    }
}
