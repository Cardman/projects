package code.maths;
import code.maths.litteralcom.MathExpUtil;
import code.util.*;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class Rate implements Displayable {

    public static final char SEP_NUM_DEN_CHAR = '/';

    public static final char MINUS_CHAR = '-';

    public static final String SEP_NUM_DEN = "/";

    public static final String CST_MINUS = "-";

    public static final int CENT = 100;

    private static final char SEP_INT_DEC = '.';

    private static final char ZERO = '0';
    private static final String EMPTY_STRING = "";

    private static final String POWER = "E";

    /** */
    private LgInt numerateur;

    /** */
    private LgInt denominateur;

    private Rate() {
    }

    public Rate(String _chaine) {
        String tauxPris_ = _chaine;
        // Ajout de 0 devant le . si ce n'est pas fait
        if (tauxPris_.startsWith(Character.toString(SEP_INT_DEC))) {
            tauxPris_ = StringUtil.concat(Character.toString(ZERO), tauxPris_);
        }
        // Ajout de 0 devant le . et derriere le - si ce n'est pas fait
        if (tauxPris_.startsWith(StringUtil.concat(CST_MINUS,Character.toString(SEP_INT_DEC)))) {
            tauxPris_ = StringUtil.concat(CST_MINUS, Character.toString(ZERO), tauxPris_.substring(1));
        }
        if (tauxPris_.endsWith(Character.toString(SEP_INT_DEC))) {
            numerateur = new LgInt(StringUtil.removeStrings(tauxPris_, Character.toString(SEP_INT_DEC)));
            denominateur = LgInt.one();
        } else if (tauxPris_.contains(Character.toString(SEP_INT_DEC))) {
            // Un caractere au moins precede le .
            int nbChiffresApresVirgule_ = tauxPris_.length() - 1 - tauxPris_.indexOf(SEP_INT_DEC);
            // Pour calculer nbChiffresApresVirgule_, il faut enlever les chiffres avant la virgule et la virgule
            numerateur = new LgInt(StringUtil.removeStrings(tauxPris_, Character.toString(SEP_INT_DEC)));
            denominateur = LgInt.powNb(new LgInt(LgInt.BASE_NUMER), new LgInt(nbChiffresApresVirgule_));
            simplifier();
        } else if (tauxPris_.indexOf(SEP_NUM_DEN_CHAR) > -1) {
            // Fraction classique
            StringList numDen_ = StringUtil.splitChar(tauxPris_, SEP_NUM_DEN_CHAR);
            numerateur = new LgInt(numDen_.first());
            denominateur = new LgInt(numDen_.last());
            if (denominateur.isZero()) {
                numerateur = LgInt.zero();
                denominateur = LgInt.one();
            }
            simplifier();
        } else {
            numerateur = new LgInt(_chaine);
            denominateur = LgInt.one();
        }
    }

    public Rate(long _numerateur) {
        numerateur = new LgInt(_numerateur);
        denominateur = LgInt.one();
    }

    public Rate(long _numerateur, long _denominateur) {
        this(new LgInt(_numerateur), new LgInt(_denominateur));
    }

    public Rate(LgInt _numerateur) {
        numerateur = new LgInt(_numerateur);
        denominateur = LgInt.one();
    }

    public Rate(LgInt _numerateur, LgInt _denominateur) {
        numerateur = new LgInt(_numerateur);
        denominateur = new LgInt(_denominateur);
        simplifier();
    }

    public Rate(Rate _autre) {
        numerateur = new LgInt(_autre.numerateur);
        denominateur = new LgInt(_autre.denominateur);
    }

    private static Rate quickNewRate(LgInt _numerateur, LgInt _denominateur) {
        Rate r_ = new Rate();
        r_.numerateur = new LgInt(_numerateur);
        r_.denominateur = new LgInt(_denominateur);
        r_.simplifier();
        return r_;
    }

    
    public static Rate newRate(String _string) {
        return new Rate(_string);
    }

    private void simplifier() {
        //setModified();
        LgInt pgcd_ = LgInt.pgcd(numerateur, denominateur);
        numerateur.divideBy(pgcd_);
        denominateur.divideBy(pgcd_);
        if (!denominateur.isZeroOrGt()) {
            denominateur.changeSignum();
            numerateur.changeSignum();
        }
    }

    public static Rate zero() {
        Rate zero_ = new Rate();
        zero_.numerateur = LgInt.zero();
        zero_.denominateur = LgInt.one();
        return zero_;
    }

    public static Rate one() {
        Rate one_ = new Rate();
        one_.numerateur = LgInt.one();
        one_.denominateur = LgInt.one();
        return one_;
    }

    public static Rate minusOne() {
        Rate one_ = new Rate();
        one_.numerateur = LgInt.minusOne();
        one_.denominateur = LgInt.one();
        return one_;
    }

    public static boolean isValid(String _string) {
        if (_string == null) {
            return false;
        }
        return matchesRate(_string);
    }

    private static boolean matchesRate(String _input) {
        if (_input.isEmpty()) {
            return false;
        }
        int i_ = IndexConstants.FIRST_INDEX;
        if (_input.charAt(i_) == MINUS_CHAR) {
            i_++;
        }
        if (i_ >= _input.length()) {
            return false;
        }
        if (_input.charAt(i_) == SEP_INT_DEC) {
            return loopDec(_input, i_);
        }
        if (!MathExpUtil.isDigit(_input.charAt(i_))) {
            return false;
        }
        return loopFrac(_input, i_);
    }

    private static boolean loopFrac(String _input, int _i) {
        int i_ = _i;
        while (true) {
            if (i_ >= _input.length()) {
                return true;
            }
            if (!MathExpUtil.isDigit(_input.charAt(i_))) {
                if (_input.charAt(i_) == SEP_NUM_DEN_CHAR || _input.charAt(i_) == SEP_INT_DEC) {
                    break;
                }
                return false;
            }
            i_++;
        }
        if (_input.charAt(i_) == SEP_INT_DEC) {
            return loopDec(_input, i_);
        }
        //_input.charAt(i_) == SEP_NUM_DEN_CHAR
//        i_++;
        if (i_ + 1 >= _input.length()) {
            return false;
        }
        i_++;
        if (_input.charAt(i_) == MINUS_CHAR) {
            i_++;
        }
//        if (i_ >= _input.length()) {
//            return false;
//        }
        return loopDigits(_input, i_);
    }

    private static boolean loopDec(String _input, int _i) {
        int i_ = _i;
        i_++;
        while (i_ < _input.length()) {
            if (!MathExpUtil.isDigit(_input.charAt(i_))) {
                return false;
            }
            i_++;
        }
        return true;
    }

    private static boolean loopDigits(String _input, int _i) {
        int i_ = _i;
        while (true) {
            if (i_ >= _input.length()) {
                return false;
            }
            if (_input.charAt(i_) != ZERO) {
                if (!MathExpUtil.isDigit(_input.charAt(i_))) {
                    return false;
                }
                break;
            }
            i_++;
        }
        //_input.charAt(i_) is a digit
        while (i_ < _input.length()) {
            if (!MathExpUtil.isDigit(_input.charAt(i_))) {
                return false;
            }
            i_++;
        }
        return true;
    }
    public Rate fact() {
        Rate abs_ = absNb();
        LgInt intPart_ = abs_.intPart();
        if (abs_.isInteger()) {
            return new Rate(intPart_.fact());
        }
        LgInt prev_ = LgInt.minus(intPart_,LgInt.one());
        Rate res_ = new Rate(prev_.fact());
        res_.multiplyBy(abs_);
        return res_;
    }
    /**
        This method changes the current rate by affecting it an other rate.

        @param _other
            an other rate to affect
        @throws NullPointerException
            if the argument is null.
    */
    public void affect(Rate _other) {
        //setModified();
        numerateur.affect(_other.numerateur);
        denominateur.affect(_other.denominateur);
    }

    public void affectZero() {
        //setModified();
        numerateur.affectZero();
        denominateur.getGrDigits().clear();
        denominateur.getGrDigits().add(1L);
    }

    public boolean isInteger() {
        Longs numbers_ = denominateur.getGrDigits();
        return numbers_.size() == 1 && numbers_.first() == 1L;
    }

    public Rate inv() {
        Rate inv_ = Rate.zero();
        inv_.numerateur.setSignum(numerateur.isSignum());
        inv_.numerateur.setGrDigits(new Longs(denominateur.getGrDigits()));
        inv_.denominateur.setSignum(LgInt.SIGNE_POSITIF);
        inv_.denominateur.setGrDigits(new Longs(numerateur.getGrDigits()));
        return inv_;
    }

    public void invertNb() {
        Longs digitsNum_ = numerateur.getGrDigits();
        numerateur.setGrDigits(denominateur.getGrDigits());
        denominateur.setGrDigits(digitsNum_);
    }

    public void changeSignum() {
        //setModified();
        if (!isZero()) {
            numerateur.changerSigne();
        }
    }

    public Rate opposNb() {
        Rate opp_ = Rate.zero();
        if (isZero()) {
            return opp_;
        }
        opp_.numerateur.setSignum(!numerateur.isSignum());
        opp_.numerateur.setGrDigits(new Longs(numerateur.getGrDigits()));
        opp_.denominateur.setSignum(LgInt.SIGNE_POSITIF);
        opp_.denominateur.setGrDigits(new Longs(denominateur.getGrDigits()));
        return opp_;
    }

    public Rate absNb() {
        Rate abs_ = Rate.zero();
        abs_.numerateur.setSignum(LgInt.SIGNE_POSITIF);
        abs_.numerateur.setGrDigits(new Longs(numerateur.getGrDigits()));
        abs_.denominateur.setSignum(LgInt.SIGNE_POSITIF);
        abs_.denominateur.setGrDigits(new Longs(denominateur.getGrDigits()));
        return abs_;
    }

    public boolean isZero() {
        return numerateur.isZero();
    }

    public boolean isZeroOrGt() {
        return numerateur.isZeroOrGt();
    }

    public boolean isZeroOrLt() {
        return numerateur.isZeroOrLt();
    }

    public NumDiffDenNum getNumDiffDenNum() {
        LgInt diff_ = LgInt.minus(denominateur, numerateur);
        return new NumDiffDenNum(getNumeratorCopy(), diff_);
    }

    public static LgInt getPpcmDens(CustList<Rate> _numbers, int _maxIncludedIndex) {
        Rate mainRate_=_numbers.first();
        LgInt ppcmDenom_=mainRate_.denominateur;
        for(int i = 1; i<= _maxIncludedIndex; i++) {
            ppcmDenom_=LgInt.ppcm(ppcmDenom_, _numbers.get(i).denominateur);
        }
        return ppcmDenom_;
    }

    public static boolean eq(CustList<Rate> _one, CustList<Rate> _two) {
        if (_one.size() != _two.size()) {
            return false;
        }
        int lenRate_ = _one.size();
        for (int i = 0; i < lenRate_; i++) {
            if (!_one.get(i).eq(_two.get(i))) {
                return false;
            }
        }
        return true;
    }
    public LgInt getNumeratorCopy() {
        return new LgInt(numerateur);
    }

    public LgInt getDenominatorCopy() {
        return new LgInt(denominateur);
    }

    public CustList<LgInt> getDividersNumerator() {
        return numerateur.getDividers();
    }

    public LgInt getNumerator() {
        return numerateur;
    }

    public LgInt getDenominator() {
        return denominateur;
    }

    public Rate rootAbs(LgInt _expo) {
        LgInt num_ = LgInt.multiply(numerateur, LgInt.powNb(denominateur, LgInt.minus(_expo, LgInt.one())))
                .rootAbs(_expo);
        num_.setSignum(LgInt.SIGNE_POSITIF);
        return quickNewRate(num_, denominateur);
    }

    public String evaluatePoint(int _numberDec) {
        if (isZero()) {
            return Character.toString(ZERO);
        }
        return evaluatePointNonZero(_numberDec);
    }

    private String evaluatePointNonZero(int _numberDec) {
        if (_numberDec < 0) {
            return EMPTY_STRING;
        }
        Rate abs_ = absNb();
        if (isInteger() || _numberDec == 0) {
            return evaluatePointInt(abs_);
        }
        String signum_;
        if (!numerateur.isZeroOrGt()) {
            signum_ = CST_MINUS;
        } else {
            signum_ = EMPTY_STRING;
        }
        Rate rateOne_ = one();
        Rate copie_ = abs_.decimalPart();
        LgInt intTen_ = new LgInt(LgInt.BASE_NUMER);
        Rate rateTen_ = new Rate(intTen_);
        int puissance_ = 0;
        while (strLower(copie_, rateOne_)) {
            copie_.multiplyBy(rateTen_);
            puissance_++;
        }
        intTen_.growToPow(new LgInt(_numberDec));
        copie_.multiplyBy(new Rate(intTen_));
        int nbZeros_ = puissance_ - 1;
        LgInt intPart_ = abs_.intPart();
        String intPartStr_ = intPart_.toNumberString();
        String return_ = StringUtil.concat(signum_, intPart_.toNumberString());
        boolean zero_ = false;
        if (intPart_.isZero()) {
            zero_ = true;
        }
        return_ = StringUtil.concat(return_, Character.toString(SEP_INT_DEC));
        StringBuilder str_ = new StringBuilder();
        for (int i = IndexConstants.FIRST_INDEX; i < nbZeros_; i++) {
            str_.append(ZERO);
        }
        str_.append(copie_.intPart().toNumberString());
//        if (str_.length() > _numberDec) {
//            str_ = str_.substring(CustList.FIRST_INDEX, _numberDec);
//        }
        str_ = new StringBuilder(str_.substring(IndexConstants.FIRST_INDEX, _numberDec));
        if (!StringUtil.removeChars(str_.toString(), ZERO).isEmpty()) {
            zero_ = false;
        }
        if (zero_) {
            str_.insert(0,SEP_INT_DEC);
            str_.insert(0,intPartStr_);
            return str_.toString();
        }
        str_.insert(0,return_);
        return str_.toString();
    }

    private String evaluatePointInt(Rate _abs) {
        String signum_;
        if (!numerateur.isZeroOrGt()) {
            signum_ = CST_MINUS;
        } else {
            signum_ = EMPTY_STRING;
        }
        LgInt int_ = _abs.intPart();
        if (int_.isZero()) {
            int_ = LgInt.zero();
            signum_ = EMPTY_STRING;
        }
        return StringUtil.concat(signum_,int_.toNumberString());
    }

    public String evaluate(long _numberMeaningDigits) {
        if (isZero()) {
            return Character.toString(ZERO);
        }
        if (_numberMeaningDigits == 0) {
            return toLgInt().toNumberString();
        }
        if (_numberMeaningDigits < 0) {
            return EMPTY_STRING;
        }
        String signum_;
        if (!numerateur.isZeroOrGt()) {
            signum_ = CST_MINUS;
        } else {
            signum_ = EMPTY_STRING;
        }
        Rate rateOne_ = one();
        Rate copie_ = absNb();
        LgInt intTen_ = new LgInt(LgInt.BASE_NUMER);
        Rate rateTen_ = new Rate(intTen_);
        int puissance_ = 0;
        if (strLower(copie_, rateOne_)) {
            // S ecrit x.xxxxxE-xx ou x est un chiffre
            while (strLower(copie_, rateOne_)) {
                copie_.multiplyBy(rateTen_);
                puissance_--;
            }
        } else {
            while (greaterEq(copie_, rateTen_)) {
                copie_.divideBy(rateTen_);
                puissance_++;
            }
        }
        intTen_.growToPow(new LgInt(_numberMeaningDigits - 1L));
        copie_.numerateur.multiplyBy(intTen_);
        String retour_ = copie_.intPart().toNumberString();
        StringBuilder str_ = new StringBuilder();
        str_.append(signum_);
        str_.append(retour_.charAt(0));
        str_.append(SEP_INT_DEC);
        str_.append(retour_.substring(1));
        str_.append(POWER);
        str_.append(puissance_);
        return str_.toString();
    }

    @Override
    public String display() {
        return toNumberString();
    }

    
    public String toNumberString() {
        if (isInteger()) {
            return numerateur.toNumberString();
        }
        // fraction
        StringBuilder str_ = new StringBuilder(numerateur.toNumberString());
        str_.append(SEP_NUM_DEN);
        str_.append(denominateur.toNumberString());
        return str_.toString();
    }

    public void multiplyBy(Rate _autre) {
        if (isZero() || _autre.isZero()) {
            affectZero();
            return;
        }
        numerateur.multiplyBy(_autre.numerateur);
        denominateur.multiplyBy(_autre.denominateur);
        simplifier();
    }

    public void removeNb(Rate _autre) {
        addNb(_autre.opposNb());
    }

    public void addNb(Rate _autre) {
        //setModified();
        if (_autre.isZero()) {
            return;
        }
        LgInt ppcmDen_ = LgInt.ppcm(denominateur, _autre.denominateur);
        numerateur.multiplyBy(ppcmDen_);
        numerateur.divideBy(denominateur);
        LgInt prod_ = LgInt.multiply(_autre.numerateur, ppcmDen_);
        prod_.divideBy(_autre.denominateur);
        numerateur.addNb(prod_);
        denominateur.affect(ppcmDen_);
        simplifier();
    }

    public void divideBy(Rate _autre) {
        multiplyBy(_autre.inv());
    }

    public Rate powNb(LgInt _expo) {
        Rate puis_ = new Rate(this);
        puis_.numerateur.growToPow(_expo.absNb());
        puis_.denominateur.growToPow(_expo.absNb());
        if (!_expo.isZeroOrGt()) {
            puis_.invertNb();
        }
        return puis_;
    }

    public static Rate powNb(Rate _base, Rate _exposant) {
        // exposant entier
        if (_exposant.isInteger()) {
            // exposant entier non nul
            if (!_exposant.isZero()) {
                return _base.powNb(_exposant.getNumerator());
            }
            return one();
        }
        if (_base.isZero()) {
            return zero();
        }
        // exposant non entier
        LgInt num_ = _exposant.getNumerator();
        LgInt den_ = _exposant.getDenominator();
        Rate resTaux_ = _base.powNb(num_).rootAbs(den_);
        //base strictement negative
        if (!_base.isZeroOrGt() && den_.remainByBase() % 2 == 1 && num_.remainByBase() % 2 == 1) {
            resTaux_.changeSignum();
        }
        return resTaux_;
    }

    public static Rate plus(Rate _celuiCi, Rate _autre) {
        Rate somme_ = new Rate(_celuiCi);
        somme_.addNb(_autre);
        return somme_;
    }

    public static Rate multiply(Rate _celuiCi, Rate _autre) {
        Rate produit_ = new Rate(_celuiCi);
        produit_.multiplyBy(_autre);
        return produit_;
    }

    public static Rate divide(Rate _celuiCi, Rate _autre) {
        Rate quotient_ = new Rate(_celuiCi);
        quotient_.divideBy(_autre);
        return quotient_;
    }

    public static Rate minus(Rate _celuiCi, Rate _autre) {
        return plus(_celuiCi, _autre.opposNb());
    }

    public Rate decimalPart() {
        return minus(this, new Rate(intPart()));
    }

    public LgInt percent() {
        Rate r_ = new Rate();
        r_.numerateur = numerateur.multiplyBy(CENT);
        r_.denominateur = denominateur;
        return r_.intPart();
//        return multiply(this, CENT).intPart();
    }

    public LgInt intPart() {
        return LgInt.divide(numerateur, denominateur);
    }

    public LgInt toLgInt() {
        if (numerateur.isZeroOrGt()) {
            return LgInt.divide(numerateur, denominateur);
        }
        LgInt int_ = numerateur.opposNb();
        int_.divideBy(denominateur);
        int_.changeSignum();
        return int_;
    }

    public long ll() {
        if (numerateur.isZeroOrGt()) {
            return intPart().remainByBase();
        }
        return -intPart().remainByBase();
    }

    public static boolean eq(Rate _tx1,Rate _tx2) {
        return _tx1.eq(_tx2);
    }

    public boolean eq(Rate _autre) {
        if (!numerateur.eq(_autre.numerateur)) {
            return false;
        }
        return denominateur.eq(_autre.denominateur);
    }

    public boolean greaterThanOne() {
        if (!isZeroOrGt()) {
            return false;
        }
        return LgInt.strGreater(numerateur, denominateur);
    }

    public boolean greaterOrEqualsOne() {
        if (!isZeroOrGt()) {
            return false;
        }
        return LgInt.greaterEq(numerateur, denominateur);
    }

    public boolean lowerThanOne() {
        if (!isZeroOrGt()) {
            return true;
        }
        return LgInt.strLower(numerateur, denominateur);
    }

    public boolean lowerOrEqualsOne() {
        if (!isZeroOrGt()) {
            return true;
        }
        return LgInt.lowerEq(numerateur, denominateur);
    }

    public static boolean strGreater(Rate _celuiCi, Rate _autre) {
        if (_celuiCi.getNumerator().isZero() && _autre.getNumerator().isZero()) {
            return false;
        }
        if (_celuiCi.getNumerator().isZeroOrGt() && !_autre.getNumerator().isZeroOrGt()) {
            return true;
        }
        if (!_celuiCi.getNumerator().isZeroOrGt() && _autre.getNumerator().isZeroOrGt()) {
            return false;
        }
        return LgInt.strGreater(LgInt.multiply(_celuiCi.getNumerator(), _autre.getDenominator()),
                LgInt.multiply(_celuiCi.getDenominator(), _autre.getNumerator()));
    }

    public static boolean different(Rate _celuiCi, Rate _autre) {
        return !_celuiCi.eq(_autre);
    }

    public static boolean lowerEq(Rate _celuiCi, Rate _autre) {
        return !strGreater(_celuiCi, _autre);
    }

    public static boolean greaterEq(Rate _celuiCi, Rate _autre) {
        return !strLower(_celuiCi, _autre);
    }

    public static boolean strLower(Rate _celuiCi, Rate _autre) {
        if (_celuiCi.getNumerator().isZero() && _autre.getNumerator().isZero()) {
            return false;
        }
        if (!_celuiCi.getNumerator().isZeroOrGt() && _autre.getNumerator().isZeroOrGt()) {
            return true;
        }
        if (_celuiCi.getNumerator().isZeroOrGt() && !_autre.getNumerator().isZeroOrGt()) {
            return false;
        }
        return LgInt.strLower(LgInt.multiply(_celuiCi.getNumerator(), _autre.getDenominator()),
                LgInt.multiply(_celuiCi.getDenominator(), _autre.getNumerator()));
    }

    /** */
    public long signumToLong() {
        if (isZero()) {
            return 0L;
        }
        if (isZeroOrGt()) {
            return 1L;
        }
        return -1L;
    }

    public Rate signum() {
        if (isZero()) {
            return zero();
        }
        if (isZeroOrGt()) {
            return one();
        }
        return minusOne();
    }

    public boolean inRange(Rate _min, Rate _max) {
        return matchMin(_min) && matchMax(_max);
    }

    private boolean matchMax(Rate _max) {
        return _max == null || _max.cmp(this) >= 0;
    }

    private boolean matchMin(Rate _min) {
        return _min == null || _min.cmp(this) <= 0;
    }
    public int cmp(Rate _autre) {
        if (strGreater(this, _autre)) {
            return 1;
        }
        if (eq(_autre)) {
            return 0;
        }
        return -1;
    }
}
