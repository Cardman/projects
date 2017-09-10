package code.maths;
import code.maths.exceptions.BadDivisionException;
import code.maths.exceptions.FormatException;
import code.maths.exceptions.NegatifExposantException;
import code.serialize.CheckedData;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.PairEq;
import code.util.StringList;
import code.util.ints.Cmp;
import code.xml.FromAndToString;

@CheckedData
public final class Rate implements Cmp<Rate> {

    //extends ViewAdapter

    public static final char SEP_NUM_DEN_CHAR = '/';

    public static final char MINUS_CHAR = '-';

    public static final String SEP_NUM_DEN = "/";

    public static final String MINUS = "-";

    public static final int CENT = 100;

    private static final char SEP_INT_DEC = '.';

    private static final char ZERO = '0';
    private static final String EMPTY_STRING = "";

//    private static final String REG_EXP_RATE = "^(-?([0-9]+(/-?0*[1-9][0-9]*|\\.[0-9]*)?|\\.[0-9]*))$";

    private static final String POWER = "E";

//    private static final Rate CENT = new Rate(100);

    /** */
    private LgInt numerateur;

    /** */
    private LgInt denominateur;

    private Rate() {
    }

    public Rate(String _chaine) {
//        if (!Pattern.matches(REG_EXP_RATE, _chaine)) {
//            throw new FormatException(_chaine);
//        }
        if (!matchesRate(_chaine)) {
            throw new FormatException(_chaine);
        }
        String tauxPris_ = _chaine;
        // Ajout de 0 devant le . si ce n'est pas fait
        if (tauxPris_.startsWith(String.valueOf(SEP_INT_DEC))) {
            tauxPris_ = String.valueOf(ZERO) + tauxPris_;
        }
        // Ajout de 0 devant le . et derriere le - si ce n'est pas fait
        if (tauxPris_.startsWith(MINUS+SEP_INT_DEC)) {
            tauxPris_ = MINUS + ZERO + tauxPris_.substring(1);
        }
        if (tauxPris_.endsWith(String.valueOf(SEP_INT_DEC))) {
            numerateur = new LgInt(StringList.removeStrings(tauxPris_, String.valueOf(SEP_INT_DEC)));
            denominateur = LgInt.one();
        } else if (tauxPris_.contains(String.valueOf(SEP_INT_DEC))) {
            // Un caractere au moins precede le .
            int nbChiffresApresVirgule_ = tauxPris_.length() - 1 - tauxPris_.indexOf(SEP_INT_DEC);
            // Pour calculer nbChiffresApresVirgule_, il faut enlever les chiffres avant la virgule et la virgule
            numerateur = new LgInt(StringList.removeStrings(tauxPris_, String.valueOf(SEP_INT_DEC)));
            denominateur = LgInt.powNb(new LgInt(LgInt.BASE_NUMER), new LgInt(nbChiffresApresVirgule_));
            simplifier();
        } else if (tauxPris_.contains(SEP_NUM_DEN)) {
            // Fraction classique
            StringList numDen_ = StringList.splitStrings(tauxPris_, SEP_NUM_DEN);
            numerateur = new LgInt(numDen_.first());
            denominateur = new LgInt(numDen_.last());
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
        long numerateur_ = _numerateur;
        long denominateur_ = _denominateur;
        if (_denominateur < 0) {
            numerateur_ = -_numerateur;
            denominateur_ = -_denominateur;
        }
        numerateur = new LgInt(numerateur_);
        denominateur = new LgInt(denominateur_);
        simplifier();
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

    @FromAndToString
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
//        if (!Pattern.matches(REG_EXP_RATE, _string)) {
//            return false;
//        }
        if (!matchesRate(_string)) {
            return false;
        }
        return true;
    }

    private static boolean matchesRate(String _input) {
        if (_input.isEmpty()) {
            return false;
        }
        int i_ = CustList.FIRST_INDEX;
        if (_input.charAt(i_) == MINUS_CHAR) {
            i_ ++;
        }
        if (i_ >= _input.length()) {
            return false;
        }
        if (_input.charAt(i_) == SEP_INT_DEC) {
            i_++;
            while (true) {
                if (i_ >= _input.length()) {
                    break;
                }
                if (!Character.isDigit(_input.charAt(i_))) {
                    return false;
                }
                i_++;
            }
            return true;
        }
        if (!Character.isDigit(_input.charAt(i_))) {
            return false;
        }
        while (true) {
            if (i_ >= _input.length()) {
                return true;
            }
            if (!Character.isDigit(_input.charAt(i_))) {
                if (_input.charAt(i_) == SEP_NUM_DEN_CHAR) {
                    break;
                }
                if (_input.charAt(i_) == SEP_INT_DEC) {
                    break;
                }
                return false;
            }
            i_++;
        }
        if (_input.charAt(i_) == SEP_INT_DEC) {
            i_++;
            while (true) {
                if (i_ >= _input.length()) {
                    break;
                }
                if (!Character.isDigit(_input.charAt(i_))) {
                    return false;
                }
                i_++;
            }
            return true;
        }
        //_input.charAt(i_) == SEP_NUM_DEN_CHAR
//        i_++;
        if (i_ + 1 >= _input.length()) {
            return false;
        }
        i_++;
        if (_input.charAt(i_) == MINUS_CHAR) {
            i_ ++;
        }
//        if (i_ >= _input.length()) {
//            return false;
//        }
        while (true) {
            if (i_ >= _input.length()) {
                return false;
            }
            if (_input.charAt(i_) != ZERO) {
                if (!Character.isDigit(_input.charAt(i_))) {
                    return false;
                }
                break;
            }
            i_++;
        }
        //_input.charAt(i_) is a digit
        while (true) {
            if (i_ >= _input.length()) {
                break;
            }
            if (!Character.isDigit(_input.charAt(i_))) {
                return false;
            }
            i_++;
        }
        return true;
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
        denominateur.getGrDigits().add(1l);
    }

    public boolean isInteger() {
        Numbers<Long> numbers_ = denominateur.getGrDigits();
        return numbers_.size() == 1 && numbers_.first().longValue() == 1L;
    }

    public Rate inv() {
        if (isZero()) {
            throw new BadDivisionException();
        }
        Rate inv_ = Rate.zero();
        inv_.numerateur.setSignum(numerateur.getSignum());
        inv_.numerateur.setGrDigits(new Numbers<Long>(denominateur.getGrDigits()));
        inv_.denominateur.setSignum(LgInt.SIGNE_POSITIF);
        inv_.denominateur.setGrDigits(new Numbers<Long>(numerateur.getGrDigits()));
        return inv_;
    }

    public void invertNb() {
        //setModified();
        if (isZero()) {
            throw new BadDivisionException();
        }
        Numbers<Long> digitsNum_ = numerateur.getGrDigits();
        numerateur.setGrDigits(denominateur.getGrDigits());
        denominateur.setGrDigits(digitsNum_);
    }

    public void changeSignum() {
        //setModified();
        if (!isZero()) {
            numerateur.setSignum(!numerateur.getSignum());
        }
    }

    public Rate opposNb() {
        Rate opp_ = Rate.zero();
        if (isZero()) {
            return opp_;
        }
        opp_.numerateur.setSignum(!numerateur.getSignum());
        opp_.numerateur.setGrDigits(new Numbers<Long>(numerateur.getGrDigits()));
        opp_.denominateur.setSignum(LgInt.SIGNE_POSITIF);
        opp_.denominateur.setGrDigits(new Numbers<Long>(denominateur.getGrDigits()));
        return opp_;
    }

    public Rate absNb() {
        Rate abs_ = Rate.zero();
        abs_.numerateur.setSignum(LgInt.SIGNE_POSITIF);
        abs_.numerateur.setGrDigits(new Numbers<Long>(numerateur.getGrDigits()));
        abs_.denominateur.setSignum(LgInt.SIGNE_POSITIF);
        abs_.denominateur.setGrDigits(new Numbers<Long>(denominateur.getGrDigits()));
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
        NumDiffDenNum p_;
        LgInt diff_ = LgInt.minus(denominateur, numerateur);
        p_ = new NumDiffDenNum(getNumeratorCopy(), diff_);
        return p_;
    }

    public static LgInt getPpcm(EqList<Rate> _numbers, int _maxIncludedIndex) {
        Rate mainRate_=_numbers.first();
        LgInt ppcmDenom_=mainRate_.denominateur;
        long deg_=_maxIncludedIndex;
        for(int i=1;i<=deg_;i++) {
            ppcmDenom_=LgInt.ppcm(ppcmDenom_, _numbers.get(i).denominateur);
        }
        return ppcmDenom_;
    }

    public LgInt getNumeratorCopy() {
        return new LgInt(numerateur);
    }

    public LgInt getDenominatorCopy() {
        return new LgInt(denominateur);
    }

    public String getNumeratorString() {
        return numerateur.toString();
    }

    public String getDenominatorString() {
        return denominateur.toString();
    }

    public EqList<LgInt> getDividersNumerator() {
        return numerateur.getDividers();
    }

    LgInt getNumerator() {
        return numerateur;
    }

    LgInt getDenominator() {
        return denominateur;
    }

    public Rate rootAbs(LgInt _expo) {
        LgInt num_ = LgInt.multiply(numerateur, LgInt.powNb(denominateur, LgInt.minus(_expo, LgInt.one())))
                .rootAbs(_expo);
        num_.setSignum(LgInt.SIGNE_POSITIF);
        return new Rate(num_, denominateur);
    }

    public String evaluatePoint(int _numberDec) {
        if (isZero()) {
            return String.valueOf(ZERO);
        }
        if (_numberDec < 0) {
            throw new NegatifExposantException();
        }
        Rate abs_ = absNb();
        if (isInteger() || _numberDec == 0) {
            String signum_ = EMPTY_STRING;
            if (!numerateur.isZeroOrGt()) {
                signum_ += MINUS;
            }
            LgInt int_ = abs_.intPart();
            if (int_.isZero()) {
                int_ = LgInt.zero();
                signum_ = EMPTY_STRING;
            }
            return signum_+int_;
        }
        String signum_ = EMPTY_STRING;
        if (!numerateur.isZeroOrGt()) {
            signum_ += MINUS;
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
        String intPartStr_ = intPart_.toString();
        String return_ = signum_ + intPart_;
        boolean zero_ = false;
        if (intPart_.isZero()) {
            zero_ = true;
        }
        return_ += SEP_INT_DEC;
        String str_ = EMPTY_STRING;
        for (int i = CustList.FIRST_INDEX; i < nbZeros_; i++) {
            str_ += ZERO;
        }
        str_ += copie_.intPart().toString();
//        if (str_.length() > _numberDec) {
//            str_ = str_.substring(CustList.FIRST_INDEX, _numberDec);
//        }
        str_ = str_.substring(CustList.FIRST_INDEX, _numberDec);
        if (!StringList.removeChars(str_, ZERO).isEmpty()) {
            zero_ = false;
        }
        if (zero_) {
            return intPartStr_ + SEP_INT_DEC + str_;
        }
        return return_ + str_;
    }

    public String evaluate(int _numberMeaningDigits) {
        if (isZero()) {
            return String.valueOf(ZERO);
        }
        if (_numberMeaningDigits == 0) {
            return toLgInt().toString();
        }
        if (_numberMeaningDigits < 0) {
            throw new NegatifExposantException();
        }
        String signum_ = EMPTY_STRING;
        if (!numerateur.isZeroOrGt()) {
            signum_ += MINUS;
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
        intTen_.growToPow(new LgInt(_numberMeaningDigits - 1));
        copie_.numerateur.multiplyBy(intTen_);
        String retour_ = copie_.intPart().toString();
        retour_ = EMPTY_STRING + retour_.charAt(0) + String.valueOf(SEP_INT_DEC) + retour_.substring(1);
        return signum_ + retour_ + POWER + puissance_;
    }

    @FromAndToString
    @Override
    public String toString() {
        if (isInteger()) {
            return numerateur.toString();
        }
        // fraction
        return numerateur+SEP_NUM_DEN+denominateur;
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
        if (!_base.isZeroOrGt()) {
            //base strictement negative
            if (den_.remainByBase() % 2 == 1) {
                if (num_.remainByBase() % 2 == 1) {
                    resTaux_.changeSignum();
                }
            }
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
        if (_tx1 == null) {
            return _tx2 == null;
        }
        return _tx1.eq(_tx2);
    }

    @Override
    public boolean eq(Rate _autre) {
        if (!numerateur.eq(_autre.numerateur)) {
            return false;
        }
        if (!denominateur.eq(_autre.denominateur)) {
            return false;
        }
//        return numerateur.eq(_autre.numerateur) && denominateur.eq(_autre.denominateur);
        return true;
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

    public static PairEq<Rate,Rate> minMax(Rate _a, Rate _b) {
        if (strGreater(_a, _b)) {
            return new PairEq<Rate,Rate>(_b, _a);
        }
        return new PairEq<Rate,Rate>(_a, _b);
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
        if (_min == null) {
            if (_max == null) {
                return true;
            }
            if (_max.cmp(this) < 0) {
                return false;
            }
            return true;
        }
        if (_max != null) {
            if (_max.cmp(this) < 0) {
                return false;
            }
        }
        if (_min.cmp(this) > 0) {
            return false;
        }
        return true;
    }

    @Override
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
