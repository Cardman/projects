package code.maths.matrix;
import code.maths.LgInt;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class FractPol implements Equallable<FractPol>, Displayable {

    private static final String SEP_NUM_DEN = "//";

    private Polynom numerateur;

    private Polynom denominateur;

    private FractPol() {
    }

    public FractPol(Polynom _numerateur) {
        numerateur = new Polynom(_numerateur);
        denominateur = Polynom.one();
    }

    public FractPol(Polynom _numerateur, Polynom _denominateur) {
        numerateur = new Polynom(_numerateur);
        denominateur = new Polynom(_denominateur);
        simplifier();
    }

    public FractPol(FractPol _autre) {
        numerateur = new Polynom(_autre.numerateur);
        denominateur = new Polynom(_autre.denominateur);
    }

    
    public static FractPol newFract(String _arg) {
        StringList args_ = StringList.splitStrings(_arg, SEP_NUM_DEN);
        return new FractPol(Polynom.newPolynom(args_.first()), Polynom.newPolynom(args_.last()));
    }


    private void simplifier() {
        //setModified();
        Polynom pgcd_ = Polynom.pgcd(numerateur, denominateur);
        numerateur.divideBy(pgcd_);
        denominateur.divideBy(pgcd_);
    }

    public static FractPol zero() {
        FractPol zero_ = new FractPol();
        zero_.numerateur = Polynom.zero();
        zero_.denominateur = Polynom.one();
        return zero_;
    }

    public static FractPol one() {
        FractPol one_ = new FractPol();
        one_.numerateur = Polynom.one();
        one_.denominateur = Polynom.one();
        return one_;
    }

    /**
        This method changes the current rate by affecting it an other rate.

        @param _other
            an other rate to affect
        @throws NullPointerException
            if the argument is null.
    */
    public void affect(FractPol _other) {
        //setModified();
        numerateur.affect(_other.numerateur);
        denominateur.affect(_other.denominateur);
    }

    public void affectZero() {
        //setModified();
        numerateur.affectZero();
        denominateur.affect(Polynom.one());
    }

    public boolean isInteger() {
        return denominateur.isEqualTo(Polynom.one());
    }

    public FractPol inv() {
        return new FractPol(denominateur, numerateur);
    }

    public void invertNb() {
        Polynom digitsNum_ = numerateur;
        numerateur = denominateur;
        denominateur = digitsNum_;
    }

    public void changeSignum() {
        //setModified();
        if (!isZero()) {
            numerateur.changeSignum();
        }
    }

    public FractPol opposNb() {
        FractPol opp_ = FractPol.zero();
        if (isZero()) {
            return opp_;
        }
        return new FractPol(numerateur.minusPolynom(), denominateur);
    }

    public boolean isZero() {
        return numerateur.isZero();
    }

    public Polynom getNumerator() {
        return numerateur;
    }

    public Polynom getDenominator() {
        return denominateur;
    }

    public void multiplyBy(FractPol _autre) {
        if (isZero() || _autre.isZero()) {
            affectZero();
            return;
        }
        numerateur.multiplyBy(_autre.numerateur);
        denominateur.multiplyBy(_autre.denominateur);
        simplifier();
    }

    public void removeNb(FractPol _autre) {
        addNb(_autre.opposNb());
    }

    public void addNb(FractPol _autre) {
        //setModified();
        if (_autre.isZero()) {
            return;
        }
        Polynom ppcmDen_ = Polynom.ppcm(denominateur, _autre.denominateur);
        numerateur.multiplyBy(ppcmDen_);
        numerateur.divideBy(denominateur);
        Polynom prod_ = _autre.numerateur.multiplyPolynom(ppcmDen_);
        prod_.divideBy(_autre.denominateur);
        numerateur.addPol(prod_);
        denominateur.affect(ppcmDen_);
        simplifier();
    }

    public void divideBy(FractPol _autre) {
        multiplyBy(_autre.inv());
    }

    public FractPol powNb(LgInt _expo) {
        FractPol puis_ = new FractPol(this);
        puis_.numerateur.growToPow(_expo.absNb());
        puis_.denominateur.growToPow(_expo.absNb());
        if (!_expo.isZeroOrGt()) {
            puis_.invertNb();
        }
        return puis_;
    }

    public static FractPol plus(FractPol _celuiCi, FractPol _autre) {
        FractPol somme_ = new FractPol(_celuiCi);
        somme_.addNb(_autre);
        return somme_;
    }

    public static FractPol multiply(FractPol _celuiCi, FractPol _autre) {
        FractPol produit_ = new FractPol(_celuiCi);
        produit_.multiplyBy(_autre);
        return produit_;
    }

    public static FractPol divide(FractPol _celuiCi, FractPol _autre) {
        FractPol quotient_ = new FractPol(_celuiCi);
        quotient_.divideBy(_autre);
        return quotient_;
    }

    public static FractPol minus(FractPol _celuiCi, FractPol _autre) {
        return plus(_celuiCi, _autre.opposNb());
    }

    public Polynom intPart() {
        return numerateur.dividePolynom(denominateur);
    }

    public static boolean eq(FractPol _tx1,FractPol _tx2) {
        if (_tx1 == null) {
            return _tx2 == null;
        }
        return _tx1.isEqualTo(_tx2);
    }

    @Override
    public boolean eq(FractPol _autre) {
        if (!numerateur.isEqualTo(_autre.numerateur)) {
            return false;
        }
        if (!denominateur.isEqualTo(_autre.denominateur)) {
            return false;
        }
//        return numerateur.eq(_autre.numerateur) && denominateur.eq(_autre.denominateur);
        return true;
    }

    public boolean isEqualTo(FractPol _autre) {
        if (!numerateur.isEqualTo(_autre.numerateur)) {
            return false;
        }
        if (!denominateur.isEqualTo(_autre.denominateur)) {
            return false;
        }
//        return numerateur.eq(_autre.numerateur) && denominateur.eq(_autre.denominateur);
        return true;
    }

    
    @Override
    public String display() {
        if (isInteger()) {
            return numerateur.display();
        }
        // fraction
        StringBuilder str_ = new StringBuilder();
        str_.append(numerateur.display());
        str_.append(SEP_NUM_DEN);
        str_.append(denominateur.display());
        return str_.toString();
    }
}
