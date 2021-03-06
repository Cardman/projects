package code.maths;
import code.maths.litteralcom.MathExpUtil;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;
import code.util.TreeMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Displayable;

/**
Classe modelisant des entiers longs qui sont une extension du type <i>long</i>.<br/>
Ces entiers sont decoupes en groupes de chiffres et possedent un signe.<br/>
Attention a eviter d'utiliser des nombres d'un milliard de chiffres, car la memoire vive est limitee.
 */
public final class LgInt implements Displayable {


    static final int BASE_NUMER = 10;

    static final boolean SIGNE_POSITIF = false;

    /** BASE vaut 10<sup>9</sup> */
    private static final long BASE = 1000000000L;

    private static final int LOG_BASE = 9;

    private static final char MINUS_CHAR = '-';

    private static final String MINUS = "-";


    private static final char ZERO = '0';

//    private static final String REG_EXP_INT = "^-?[0-9]+$";
    /**
    Un entier est represente par un groupe de chiffres tous inferieurs a la base (10^9).
    */
    private Longs grDigits;

    /**
    signe valant true si et seulement si le nombre entier est positif ou nul.
    */
    private boolean signum;

    private LgInt() {
        grDigits = new Longs();
    }

    /**
        construit un nombre entier a partir d'un autre

        @param _autre un autre entier
        @throws NullPointerException
        si l'argument est nul.
    */
    public LgInt(LgInt _autre) {
        affecter(_autre);
    }

    /**
        construit un nombre entier a partir d'une chaine de caracteres

        @param _chaine chaine de caracteres d'un tiret suivi d'au moins un chiffre
        @throws
        si la chaine de caracteres <i>_chaine</i> n'est pas telle que:<br/>
        <ul>
        <li>un tiret eventuel au debut ("-1" ou "1")</li>
        <li>une sequence non vide de chiffre a la fin <i>new LgInt("01").eq(new LgInt("1"))</i></li>
        </ul>
        Les zeros sucessifs places au debut de la sequence des chiffres sont supprimes Le tiret signifie que le
        nombre est negatif
        @throws NullPointerException
        si l'argument est nul
    */
    public LgInt(String _chaine) {
        grDigits = tryGetDigits(_chaine);
        if (isZero() || !_chaine.startsWith(MINUS)) {
            signum = SIGNE_POSITIF;
        } else {
            signum = !SIGNE_POSITIF;
        }
    }

    /**
    construit un nombre entier a partir d'un entier de type <i>long</i>

    @param _entierL
                un entier de type <i>long</i>
    */
    public LgInt(long _entierL) {
        grDigits = new Longs();
        long nombre_;
        if (_entierL == Long.MIN_VALUE) {
            nombre_ = -(_entierL+1);
            signum = !SIGNE_POSITIF;
        } else if (_entierL < 0) {
            nombre_ = -_entierL;
            signum = !SIGNE_POSITIF;
        } else {
            nombre_ = _entierL;
            signum = SIGNE_POSITIF;
        }
        long quotient_ = nombre_ / BASE;
        long reste_ = nombre_ - quotient_ * BASE;
        grDigits.add(reste_);
        while (quotient_ > BASE) {
            nombre_ = quotient_;
            quotient_ = nombre_ / BASE;
            reste_ = nombre_ - quotient_ * BASE;
            grDigits.add(IndexConstants.FIRST_INDEX, reste_);
        }
        if (quotient_ != 0) {
            grDigits.add(IndexConstants.FIRST_INDEX, quotient_);
        }
        if (_entierL == Long.MIN_VALUE) {
            grDigits.set(grDigits.getLastIndex(),grDigits.last()+1);
        }
    }

    private LgInt(Longs _grChiffre, boolean _signe) {
        grDigits = new Longs(_grChiffre);
        signum = _signe;
    }

    private static Longs tryGetDigits(String _string) {
        Longs grDigits_ = new Longs(new CollCapacity(_string.length() / LOG_BASE + 1));
        int powerTen_ = LOG_BASE;
        String nbLu_ = chaineValeurAbsolue(_string);
        // Suppression des 0 au debut du nombre sauf s'il reste un 0 sans autre
        // chiffre
        while (nbLu_.length() > 1 && nbLu_.charAt(IndexConstants.FIRST_INDEX) == ZERO) {
            nbLu_ = nbLu_.substring(IndexConstants.SECOND_INDEX);
        }
        int firstInd_ = nbLu_.length() - 1;
        while (firstInd_ >= IndexConstants.FIRST_INDEX) {
            String nbLuBis_;
            int chRestant_ = nbLu_.length() - powerTen_;
            if (chRestant_ > 0) {
                nbLuBis_ = nbLu_.substring(chRestant_);
                // nbLu_.resize(nbLu_.length()-puissance10_)
                nbLu_ = nbLu_.substring(IndexConstants.FIRST_INDEX, chRestant_);
            } else {
                nbLuBis_ = nbLu_;
            }
            grDigits_.add(IndexConstants.FIRST_INDEX, Math.max(0L, NumberUtil.parseInt(nbLuBis_)));
            firstInd_ -= powerTen_;
        }
        if (grDigits_.isEmpty()) {
            grDigits_.add(0L);
        }
        return grDigits_;
    }
    
    public static LgInt newLgInt(String _string) {
        return new LgInt(_string);
    }

    /**@return the integer 0*/
    public static LgInt zero() {
        LgInt one_ = new LgInt();
        one_.grDigits.add(0L);
        one_.signum = SIGNE_POSITIF;
        return one_;
    }

    /**@return the integer 1*/
    public static LgInt one() {
        LgInt one_ = new LgInt();
        one_.grDigits.add(1L);
        one_.signum = SIGNE_POSITIF;
        return one_;
    }

    /**This method is correct because
    <ul>
    <li>Long.MAX_VALUE >= BASE</li>
    <li>Long.MAX_VALUE % 2 == 1</li>
    <li>&forall; n natural number 2 power n % BASE &gt; 0</li>
    </ul>
    */
    public static LgInt getMaxLongPlusOne() {
        LgInt l_ = new LgInt();
        l_.grDigits = new Longs();
        long nombre_ = Long.MAX_VALUE;
        long quotient_;
        long reste_;
        quotient_ = nombre_ / BASE;
        reste_ = nombre_ - quotient_ * BASE;
        l_.grDigits.add(reste_ + 1);
        while (quotient_ > BASE) {
            nombre_ = quotient_;
            quotient_ = nombre_ / BASE;
            reste_ = nombre_ - quotient_ * BASE;
            l_.grDigits.add(IndexConstants.FIRST_INDEX, reste_);
        }
        l_.grDigits.add(IndexConstants.FIRST_INDEX, quotient_);
        l_.removeBeginningZeros();
        l_.signum = SIGNE_POSITIF;
        return l_;
    }

    /**@return the integer -1*/
    public static LgInt minusOne() {
        LgInt one_ = new LgInt();
        one_.grDigits.add(1L);
        one_.signum = !SIGNE_POSITIF;
        return one_;
    }

    public static boolean isValid(String _string) {
        if (_string == null) {
            return false;
        }
//        if (!Pattern.matches(REG_EXP_INT, _string)) {
//            return false;
//        }
        return matchesLgInt(_string);
    }

    private static boolean matchesLgInt(String _input) {
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
        if (!MathExpUtil.isDigit(_input.charAt(i_))) {
            return false;
        }
        while (i_ < _input.length()) {
            if (!MathExpUtil.isDigit(_input.charAt(i_))) {
                return false;
            }
            i_++;
        }
        return true;
    }

    public boolean isPrime(){
        LgInt abs_=absNb();
        if (abs_.eq(LgInt.one())) {
            return false;
        }
        LgInt nbTwo_ = new LgInt(2);
        while (lowerEq(multiply(nbTwo_,nbTwo_), abs_)) {
            if (remain(abs_, nbTwo_).isZero()) {
                return false;
            }
            nbTwo_.increment();
        }
        return true;
    }

    public static IdBezoutNb identiteBezoutPgcdPpcm(LgInt _a,LgInt _b) {
        LgInt r0_ = _a;
        LgInt r1_ = _b;
        LgInt u0_ = one();
        LgInt u1_ = zero();
        LgInt v0_ = zero();
        LgInt v1_ = one();
        while (!r1_.isZero()) {
            QuotModLgInt qr_ = r0_.divisionEuclidienneGeneralise(r1_);
            LgInt q_ = qr_.getQuot();
            LgInt r2_ = qr_.getMod();
            LgInt u2_ = minus(u0_,multiply(q_,u1_));
            LgInt v2_ = minus(v0_,multiply(q_,v1_));
            u0_ = u1_;
            v0_ = v1_;
            r0_ = r1_;
            u1_ = u2_;
            v1_ = v2_;
            r1_ = r2_;
        }
        u0_ = possibleSetZero(_a, u0_);
        v0_ = possibleSetZero(_b, v0_);
        return buildResult(_a, _b, r0_, u0_, v0_);
    }

    private static IdBezoutNb buildResult(LgInt _a, LgInt _b, LgInt _r0, LgInt _u0, LgInt _v0) {
        if (_r0.isZero()) {
            return new IdBezoutNb(_u0, _v0, _r0,zero());
        }
        return new IdBezoutNb(_u0, _v0, _r0,multiply(divide(_a, _r0), _b));
    }

    private static LgInt possibleSetZero(LgInt _in, LgInt _found) {
        if (_in.isZero()) {
            return one();
        }
        return _found;
    }

    public Decomposition decompoPrim() {
        LgInt copy_=absNb();
        CustList<PrimDivisor> divs_ = new CustList<PrimDivisor>();
        for (LgInt d: getDividers()) {
            if (!d.isPrime()) {
                continue;
            }
            PrimDivisor p_ = new PrimDivisor(d,zero());
            while (true) {
                QuotModLgInt qr_=copy_.divisionEuclidienneGeneralise(d);
                if(!qr_.getMod().isZeroOrLt()) {
                    break;
                }
                p_.getExponent().increment();
                copy_=qr_.getQuot();
            }
            divs_.add(p_);
        }
        if (absNb().eq(one())) {
            PrimDivisor p_ = new PrimDivisor(one(),one());
            divs_.add(p_);
        }
        return new Decomposition(isSignum(), divs_);
    }

    public CustList<LgInt> getDividers() {
        CustList<LgInt> divs_ = new CustList<LgInt>();
        if (isZero()) {
            return divs_;
        }
        LgInt abs_=absNb();
        LgInt init_ = new LgInt(2);
        divs_.add(LgInt.one());
        if (!abs_.eq(LgInt.one())) {
            divs_.add(abs_);
        }
        while (!LgInt.strGreater(multiply(init_,init_), abs_)) {
            QuotModLgInt qr_ = abs_.divisionEuclidienneGeneralise(init_);
            if (qr_.getMod().isZero()) {
                divs_.add(new LgInt(init_));
                if (!init_.eq(qr_.getQuot())) {
                    divs_.add(qr_.getQuot());
                }
            }
            init_.increment();
        }
        return divs_;
    }

    /**
    Cette methode peut etre utilisee pour calculer les repartitions
    possibles en taille d'une main de taille fixe <i>_sommeTotale</i>
    a l'aide des definitions sur le jeu de carte et des nombres totaux de cartes
    dans les couleurs <i>_repartitions</i>

    Cette methode retourne les sommes possibles fixes de nombre de termes fixes
    avec un minimum et un maximum donne pour chaque terme

    @param _repartitions
                termes de la somme
    @param _sommeTotale
                somme fixe
    @return les repartitions possibles ponderees
    */
    public static AbsMap<CustList<LgInt>,LgInt> seqAmong(
            CustList<LgInt> _repartitions,
            LgInt _sommeTotale) {
        int i_ = IndexConstants.FIRST_INDEX;
        int nbIterations_ = _repartitions.size();
        CustList<CustList<LgInt>> repartitionsPossibles_ = new CustList<CustList<LgInt>>();
        repartitionsPossibles_.add(new CustList<LgInt>());
        CustList<CustList<LgInt>> repartitionsPossiblesLoc_ = new CustList<CustList<LgInt>>();
        boolean modif_=true;
        while (modif_) {
            if (i_ >= nbIterations_) {
                break;
            }
            modif_=false;
            repartitionsPossiblesLoc_.clear();
            feedEvents(_repartitions, _sommeTotale, i_, nbIterations_, repartitionsPossibles_, repartitionsPossiblesLoc_);
            if (!repartitionsPossiblesLoc_.isEmpty()) {
                modif_ = true;
                repartitionsPossibles_ = new CustList<CustList<LgInt>>(repartitionsPossiblesLoc_);
            }
            i_++;
        }
        return buildSortedLaw(repartitionsPossibles_);
    }

    private static void feedEvents(CustList<LgInt> _repartitions, LgInt _sommeTotale, int _i, int _nbIterations, CustList<CustList<LgInt>> _repartitionsPossibles, CustList<CustList<LgInt>> _repartitionsPossiblesLoc) {
        LgInt terme_ = _repartitions.get(_i);
        for (CustList<LgInt> l: _repartitionsPossibles) {
            LgInt somme_ = LgInt.zero();
            for (LgInt e: l) {
                somme_.addNb(e);
            }
            LgInt event_ = LgInt.zero();
            while (LgInt.lowerEq(event_, terme_)) {
                LgInt sommeLoc_ = new LgInt(somme_);
                sommeLoc_.addNb(event_);
                if (strGreater(sommeLoc_, _sommeTotale)) {
                    break;
                }
                procIncr(_sommeTotale, _i, _nbIterations, _repartitionsPossiblesLoc, l, event_, sommeLoc_);
            }
        }
    }

    private static void procIncr(LgInt _sommeTotale, int _i, int _nbIterations, CustList<CustList<LgInt>> _repartitionsPossiblesLoc, CustList<LgInt> _l, LgInt _event, LgInt _sommeLoc) {
        if (_i == _nbIterations - 1 && !_sommeLoc.eq(_sommeTotale)) {
            _event.increment();
            return;
        }
        CustList<LgInt> l_ = new CustList<LgInt>(_l);
        l_.add(new LgInt(_event));
        _repartitionsPossiblesLoc.add(l_);
        _event.increment();
    }

    private static TreeMap<CustList<LgInt>, LgInt> buildSortedLaw(CustList<CustList<LgInt>> _repartitionsPossibles) {
        for (CustList<LgInt> l: _repartitionsPossibles) {
            l.sortElts(new ComparatorLgInt());
        }
        return buildLaw(_repartitionsPossibles);
    }

    private static TreeMap<CustList<LgInt>, LgInt> buildLaw(CustList<CustList<LgInt>> _repartitionsPossibles) {
        TreeMap<CustList<LgInt>,LgInt> loiProba_ = new TreeMap<CustList<LgInt>,LgInt>(new ComparatorEvents());
        LgInt one_ = one();
        for (CustList<LgInt> l: _repartitionsPossibles) {
            boolean present_ = false;
            for (EntryCust<CustList<LgInt>,LgInt> lTwo_: loiProba_.entryList()) {
                if (eq(l,lTwo_.getKey())) {
                    lTwo_.getValue().increment();
                    present_ = true;
                    break;
                }
            }
            if (present_) {
                continue;
            }
            loiProba_.put(l, one_);
        }
        return loiProba_;
    }

    public static boolean eq(CustList<LgInt> _one, CustList<LgInt> _two) {
        if (_one.size() != _two.size()) {
            return false;
        }
        int len_ = _one.size();
        for (int i = 0; i < len_; i++) {
            if (!_one.get(i).eq(_two.get(i))) {
                return false;
            }
        }
        return true;
    }
    /**
    Cette methode equivaut a l'operateur &gt;.<br/>

    @param _celuiCi
                l'entier a gauche du comparateur
    @param _autre
                l'entier a droite du comparateur
    @throws NullPointerException
                si un des arguments est null.
    @return la valeur booleenne de l'inegalite _celuiCi &gt; _autre.
    */
    public static boolean strGreater(LgInt _celuiCi, LgInt _autre) {
        if (!sameSignum(_celuiCi, _autre)) {
            return _celuiCi.isZeroOrGt();
        }
//        if (_celuiCi.isZeroOrGt() != _autre.isZeroOrGt()) {
//            return _celuiCi.isZeroOrGt();
//        }
        if (_celuiCi.isZeroOrGt()) {
            return !_celuiCi.inferieurOuEgal(_autre);
        }
        if (_celuiCi.eq(_autre)) {
            return false;
        }
        return _celuiCi.inferieurOuEgal(_autre);
    }

    /**
    Cette methode equivaut a l'operateur &ne;. (negation de eq)<br/>

    @param _celuiCi
                l'entier a gauche du comparateur
    @param _autre
                l'entier a droite du comparateur
    @throws NullPointerException
                si un des arguments est null.
    @return la valeur booleenne de l'inegalite _celuiCi &ne; _autre.
    @throws NullPointerException
                si un des arguments est null.
    */
    public static boolean different(LgInt _celuiCi, LgInt _autre) {
        return !_celuiCi.eq(_autre);
    }

    /**
    Cette methode equivaut a l'operateur &le;.<br/>

    @param _celuiCi
                l'entier a gauche du comparateur
    @param _autre
                l'entier a droite du comparateur
    @throws NullPointerException
                si un des arguments est null.
    @return la valeur booleenne de l'inegalite _celuiCi &le; _autre.
    */
    public static boolean lowerEq(LgInt _celuiCi, LgInt _autre) {
        return !strGreater(_celuiCi, _autre);
    }

    /**
    Cette methode equivaut a l'operateur &ge;.<br/>

    @param _celuiCi
                l'entier a gauche du comparateur
    @param _autre
                l'entier a droite du comparateur
    @throws NullPointerException
                si un des arguments est null.
    @return la valeur booleenne de l'inegalite _celuiCi &ge; _autre.
    */
    public static boolean greaterEq(LgInt _celuiCi, LgInt _autre) {
        return !strLower(_celuiCi, _autre);
    }

    /**
    Cette methode equivaut a l'operateur &lt;.<br/>

    @param _celuiCi
                l'entier a gauche du comparateur
    @param _autre
                l'entier a droite du comparateur
    @throws NullPointerException
                si un des arguments est null.
    @return la valeur booleenne de l'inegalite _celuiCi &lt; _autre.
    */
    public static boolean strLower(LgInt _celuiCi, LgInt _autre) {
        if (!sameSignum(_celuiCi, _autre)) {
            return _autre.isZeroOrGt();
        }
//        if (_celuiCi.isZeroOrGt() != _autre.isZeroOrGt()) {
//            return _autre.isZeroOrGt();
//        }
        if (_celuiCi.isZeroOrGt()) {
            return _celuiCi.plusPetitQue(_autre);
        }
        if (_celuiCi.eq(_autre)) {
            return false;
        }
        return !_celuiCi.plusPetitQue(_autre);
    }


    /**
    Cette methode calcule la puissance du premier entier au getSecond() entier

    @param _celuiCi
                la base
    @param _autre
                l'exposant
    @throws NullPointerException
                si un des arguments est null.
    @throws
                si l'argument est strictement negatif.
    @return la puissance du premier entier au getSecond() entier
    */
    public static LgInt powNb(LgInt _celuiCi, LgInt _autre) {
        LgInt puis_ = new LgInt(_celuiCi);
        puis_.growToPow(_autre);
        return puis_;
    }

    /**
    Cette methode calcule la somme des entiers passes en arguments

    @param _celuiCi

                un entier
    @param _autre
                un entier
    @throws NullPointerException
                si un des arguments est null.
    @return la somme des entiers

    */
    public static LgInt plus(LgInt _celuiCi, LgInt _autre) {
        LgInt somme_ = new LgInt(_celuiCi);
        somme_.addNb(_autre);
        return somme_;
    }

    /**
    Cette methode calcule le produit des entiers passes en arguments

    @param _celuiCi
                un entier
    @param _autre
                un entier
    @throws NullPointerException
                si un des arguments est null.
    @return le produit des entiers
    */
    public static LgInt multiply(LgInt _celuiCi, LgInt _autre) {
        LgInt produit_ = new LgInt(_celuiCi);
        produit_.multiplyBy(_autre);
        return produit_;
    }

    /**
    Cette methode calcule le quotient de la division euclidienne du premier entier par le getSecond() entier

    @param _celuiCi
                le dividende
    @param _autre
                le diviseur

    @throws NullPointerException
                si un des arguments est null.
    @throws ArithmeticException
                si l'argument vaut l'entier 0.
    @return la quotient de la division euclidienne du premier entier par le getSecond() entier
    */
    public static LgInt divide(LgInt _celuiCi, LgInt _autre) {
        LgInt quotient_ = new LgInt(_celuiCi);
        quotient_.divideBy(_autre);
        return quotient_;
    }

    /**
    Cette methode calcule le reste de la division euclidienne du premier entier par le getSecond() entier

    @param _celuiCi
                le dividende
    @param _autre
                le diviseur
    @throws NullPointerException
                si un des arguments est null.

    @throws ArithmeticException
                si l'argument vaut l'entier 0.

    @return la reste de la division euclidienne du premier entier par le getSecond() entier
    */
    public static LgInt remain(LgInt _celuiCi, LgInt _autre) {
        LgInt reste_ = new LgInt(_celuiCi);
        reste_.remainBy(_autre);
        return reste_;
    }

    /**
    Cette methode calcule la difference entre le premier entier et le getSecond() entier

    @param _celuiCi
                un entier
    @param _autre
                un entier
    @throws NullPointerException
                si un des arguments est null.
    @return la somme du premier entier et de l'oppose du getSecond() entier
    */
    public static LgInt minus(LgInt _celuiCi, LgInt _autre) {
        return plus(_celuiCi, _autre.opposNb());
    }

    /**
    Calcule le nombre de sous ensembles possibles de cardinal <i>_nombre</i> d'un ensemble de cardinal
    <i>_nombreTotalElements</i>

    @param _nombre
                le nombre d'elements a prendre pour en faire un sous ensemble
    @param _nombreTotalElements
                le nombre total d'elements dans l'ensemble principal
    @throws
                si un des arguments est null.
    @throws
                si un des arguments est strictement negatif.
    @return le nombre de combinaisons de <i>_nombre</i> parmi <i>_nombreTotalElements</i>
    */
    public static LgInt among(LgInt _nombre, LgInt _nombreTotalElements) {
        if (_nombre.plusGrandQue(_nombreTotalElements)) {
            // <=> this > _nombreTotalElements
            return LgInt.zero();
        }
        LgInt cardinal_ = LgInt.one();
        CustList<LgInt> numerateur_ = new CustList<LgInt>();
        CustList<LgInt> denominateur_ = new CustList<LgInt>();
        LgInt diffTotalPartiel_ = minus(_nombreTotalElements, _nombre);
        long absBase_ = _nombre.remainByBase();
        long diffAbsBase_ = diffTotalPartiel_.remainByBase();
        int nombre_ = (int) Math.min(absBase_, diffAbsBase_);
        // nombre_ < _nombreTotalElements / 2
        for (int i = IndexConstants.FIRST_INDEX; i < nombre_; i++) {
            LgInt temp_ = new LgInt(i);
            numerateur_.add(minus(_nombreTotalElements, temp_));
            denominateur_.add(minus(new LgInt(nombre_), temp_));
        }
        int nbPgcd_ = nombre_ - 1;
        for (int i = IndexConstants.FIRST_INDEX; i < nbPgcd_; i++) {
            LgInt den_ = denominateur_.get(i);
            for (int j = IndexConstants.FIRST_INDEX; j < nbPgcd_; j++) {
                if (!one().eq(den_)) {
                    LgInt num_ = numerateur_.get(j);
                    LgInt pgcd_ = pgcd(num_, den_);
                    numerateur_.set(j, divide(num_, pgcd_));
                    den_ = divide(den_, pgcd_);
                }
            }
        }
        for (int i = IndexConstants.FIRST_INDEX; i < nombre_; i++) {
            cardinal_.multiplyBy(numerateur_.get(i));
        }
        return cardinal_;
    }

    /**
    Calcule le plus petit multiple commun de deux nombres

    @param _a
                le premier argument du ppcm
    @param _b
                le deuxieme argument du ppcm
    @throws NullPointerException
                si un des arguments est null.
    @return le ppcm de deux nombres
    */
    public static LgInt ppcm(LgInt _a, LgInt _b) {
        if (_a.isZero() && _b.isZero()) {
            return zero();
        }
        LgInt res_ = divide(_a, pgcd(_a, _b));
        res_.multiplyBy(_b);
        res_.signum = SIGNE_POSITIF;
        return res_;
    }

    /**
    Calcule le plus grand diviseur commun de deux nombres

    @param _a
                le premier argument du ppcm
    @param _b
                le deuxieme argument du ppcm
    @throws NullPointerException
                si un des arguments est null.
    @return le pgcd de deux nombres
    */
    public static LgInt pgcd(LgInt _a, LgInt _b) {
        if (_b.isZero()) {
            return _a.absNb();
        }
        LgInt reste_ = remain(_a, _b);
        if (reste_.isZero()) {
            return _b.absNb();
        }
        LgInt b_ = _b;
        while (!reste_.isZero()) {
            LgInt a_ = b_;
            b_ = reste_;
            reste_ = remain(a_, b_);
        }
        return b_;
    }

    public static boolean sameSignum(LgInt _one, LgInt _two) {
        return _one.sameSignum(_two);
    }

    public boolean sameSignum(LgInt _two) {
        if (isZeroOrGt()) {
            return _two.isZeroOrGt();
        }
        return !_two.isZeroOrGt();
    }

    static long base() {
        long max_ = Long.MAX_VALUE;
        long copieBase_ = 1;
        long div_ = (long)BASE_NUMER * BASE_NUMER;
        long copieBaseBis_ = 1;
        while (max_ > 0) {
            max_ /= div_;
            copieBase_ = copieBaseBis_;
            copieBaseBis_ *= BASE_NUMER;
        }
        return copieBase_;
    }

    static int logBase() {
        int powerTen_ = 0;
        long copieBase_ = 1;
        while (copieBase_ < BASE) {
            copieBase_ *= BASE_NUMER;
            powerTen_++;
        }
        return powerTen_;
    }

    private static String chaineValeurAbsolue(String _chaine) {
        if (_chaine.startsWith(MINUS)) {
            return _chaine.substring(1);
        }
        return _chaine;
    }
    Longs getGrDigits() {
        return grDigits;
    }

    void setGrDigits(Longs _grDigits) {
        //setModified();
        grDigits = _grDigits;
    }

    boolean isSignum() {
        return signum;
    }

    void setSignum(boolean _signum) {
        //setModified();
        signum = _signum;
    }

    private void affecter(LgInt _autre) {
        grDigits = new Longs(_autre.grDigits);
        signum = _autre.signum;
    }

    /**
    Cette methode ne change pas l'objet courant et calcule le produit de l'entier courant par un nombre de type double en
    tronquant le resultat equivaut a E(<i>this</i> &#42; <i>db</i>) Exemple: <i>new LgInt(1).multiply(0.5).eq(new LgInt())</i>

    @param _db
                le nombre de type double facteur du produit
    @return la partie entiere du produit de l'entier courant par un nombre double
    */
    public LgInt multiply(double _db) {
        LgInt resultat_ = LgInt.zero();
        Longs resultatChiffres_ = resultat_.grDigits;
        resultatChiffres_.clear();
        int len_ = grDigits.size();
        for (int i = IndexConstants.FIRST_INDEX; i <len_; i++) {
            resultatChiffres_.add(0L);
        }
        int nbDig_ = resultatChiffres_.size();
        int nbDigMinusOne_ = nbDig_;
        nbDigMinusOne_--;
        for (int i = nbDigMinusOne_; i >= IndexConstants.FIRST_INDEX; i--) {
            double mult_ = (double) grDigits.get(i) * _db;
            long multLong_ = (long) mult_;
            resultatChiffres_.set(i, multLong_);
            double part_ = (double) multLong_;
            double tmp_ = (mult_ - part_) * (double) BASE;
            int j_ = i + 1;
            for (int j = j_; j < nbDig_; j++) {
                long tmpLong_ = (long)tmp_;
                resultatChiffres_.set(j, resultatChiffres_.get(j) + tmpLong_);
                double floor_ = (double)tmpLong_;
                tmp_ = (tmp_ - floor_) * (double)BASE;
            }
        }
        long retenue_ = 0;
        for (int i = nbDigMinusOne_; i >= IndexConstants.FIRST_INDEX; i--) {
            resultatChiffres_.set(i, resultatChiffres_.get(i) + retenue_);
            long quotient_ = resultatChiffres_.get(i) / BASE;
            long reste_ = resultatChiffres_.get(i) - BASE * quotient_;
            retenue_ = quotient_;
            resultatChiffres_.set(i, reste_);
        }
        if (retenue_ > 0) {
            resultatChiffres_.add(IndexConstants.FIRST_INDEX, retenue_);
        }
        int taille_ = resultatChiffres_.size() - 1;
        for (int i = IndexConstants.FIRST_INDEX; i < taille_; i++) {
            if (resultatChiffres_.first() != 0) {
                break;
            }
            resultatChiffres_.remove(IndexConstants.FIRST_INDEX);
        }
        return resultat_;
    }

    private void multiplierParEntierPositif(LgInt _autre) {
        //setModified();
        LgInt resultat_ = LgInt.zero();
        int expo_ = 0;
        int first_ = _autre.grDigits.size()-1;
        for (int i = first_; i >= IndexConstants.FIRST_INDEX; i--) {
            LgInt tmp_ = multiplyBy(_autre.grDigits.get(i));
            ajoutFinZerosNettoyage(expo_, tmp_);
            expo_++;
            resultat_.ajouter(tmp_);
        }
        grDigits.clear();
        grDigits.addAllElts(resultat_.grDigits);
    }

    private static void ajoutFinZerosNettoyage(int _expo, LgInt _tmp) {
        for (int j = IndexConstants.SIZE_EMPTY; j < _expo; j++) {
            _tmp.grDigits.add(0L);
        }
        _tmp.removeBeginningZeros();
    }

    private void ajouter(LgInt _autre) {
        //setModified();
        int longueurBis_ = _autre.grDigits.size();
        while (grDigits.size() < longueurBis_) {
            grDigits.add(IndexConstants.FIRST_INDEX, 0L);
        }
        grDigits.add(IndexConstants.FIRST_INDEX, 0L);
        int longueur_ = grDigits.size();
        int diff_ = longueur_ - longueurBis_;
        int j_ = longueurBis_ - 1;
        int retenue_ = 0;
        while (j_ >= IndexConstants.FIRST_INDEX) {
            int k_ = j_ + diff_;
            long ch_ = grDigits.get(k_) + _autre.grDigits.get(j_)+retenue_;
            grDigits.set(k_, sommeOuReste(ch_));
            retenue_ = retenueSomme(ch_);
            j_--;
        }
        int i_ = diff_-1;
        while (i_ >= IndexConstants.FIRST_INDEX) {
            long ch_ = grDigits.get(i_)+ retenue_;
            grDigits.set(i_, sommeOuReste(ch_));
            retenue_ = retenueSomme(ch_);
            i_--;
        }
        removeBeginningZeros();
    }

    private static long sommeOuReste(long _a) {
        if (_a < BASE) {
            return _a;
        }
        return _a - BASE;
    }
    private static int retenueSomme(long _a) {
        if (_a < BASE) {
            return 0;
        }
        return 1;
    }
    private void retrancher(LgInt _autre) {
        retrancherChiffres(_autre.grDigits);
    }

    private void retrancherChiffres(Longs _chiffresAutre) {
        int retenue_ = 0;
        grDigits.add(IndexConstants.FIRST_INDEX, 0L);
        int longueur_ = grDigits.size();
        int longueurBis_ = _chiffresAutre.size();
        int diff_ = longueur_ - longueurBis_;
        int l_ = longueurBis_ - 1;
        while (l_ >= IndexConstants.FIRST_INDEX) {
            int j_ = l_ + diff_;
            long ch_ = grDigits.get(j_) - _chiffresAutre.get(l_)-retenue_;
            grDigits.set(j_, diffOuReste(ch_));
            retenue_ = retenueDiff(ch_);
            l_--;
        }
        int k_ = diff_ - 1;
        while (k_ >= IndexConstants.FIRST_INDEX) {
            long ch_ = grDigits.get(k_)-retenue_;
            grDigits.set(k_, diffOuReste(ch_));
            retenue_ = retenueDiff(ch_);
            k_--;
        }
        removeBeginningZeros();
    }

    private static long diffOuReste(long _a) {
        if (_a < 0) {
            return _a + BASE;
        }
        return _a;
    }
    private static int retenueDiff(long _a) {
        if (_a < 0) {
            return 1;
        }
        return 0;
    }

    private void retrancherAutrePuisAffecter(LgInt _autre) {
        //setModified();
        LgInt copieAutre_ = new LgInt(_autre);
        copieAutre_.retrancher(this);
        grDigits = copieAutre_.grDigits;
    }

    private QuotMod divisionEuclidienne(LgInt _autre) {
        QuotMod quotientReste_ = new QuotMod();
        if (plusPetitQue(_autre)) {
            quotientReste_.setQuot(new Longs());
            quotientReste_.getQuot().add(0L);
            quotientReste_.setMod(new Longs(grDigits));
            return quotientReste_;
        }
        return divisionEuclidienne(_autre, quotientReste_);
    }

    private QuotMod divisionEuclidienne(LgInt _autre, QuotMod _quotientReste) {
        long first_ = _autre.grDigits.first();
        if (first_ <= 0) {
            _quotientReste.setQuot(new Longs());
            _quotientReste.getQuot().add(0L);
            _quotientReste.setMod(new Longs(grDigits));
            return _quotientReste;
        }
        int taille_ = grDigits.size();
        int tailleBis_ = _autre.grDigits.size();
        //taille_ >= tailleBis_
        int nbNombres_;
        long chiffreMax_ = nbChiffresMax(first_);
        LgInt reste_ = LgInt.zero();
        reste_.grDigits.clear();
        int indiceChiffre_;
        if (grDigits.first() >= first_) {
            nbNombres_ = taille_ - tailleBis_ + 1;
            for (int i = IndexConstants.FIRST_INDEX; i < tailleBis_; i++) {
                reste_.grDigits.add(grDigits.get(i));
            }
            indiceChiffre_ = tailleBis_;
            //indiceChiffre_ + nbNombres_ == taille_ + 1
        } else {
            //taille_ > tailleBis_
            nbNombres_ = taille_ - tailleBis_;
            for (int i = IndexConstants.FIRST_INDEX; i <= tailleBis_; i++) {
                reste_.grDigits.add(grDigits.get(i));
            }
            indiceChiffre_ = tailleBis_ + 1;
            //indiceChiffre_ + nbNombres_ == taille_ + 1
        }
        // chiffreMax_>=1
        // reste_ >= _autre
        long divDom_ = reste_.divisionTemporaire(_autre, chiffreMax_);
        Longs chiffresQuotient_ = new Longs(new CollCapacity(nbNombres_));
        chiffresQuotient_.add(divDom_);
        reste_.retrancher(_autre.multiplyBy(divDom_));
        //reste_ >= 0
        //indiceChiffre_ + nbNombres_ == taille_ + 1
        // et i < nbNombres_
        // => i + indiceChiffre_ - 1 < nbNombres_ + indiceChiffre_ - 1 = taille_
        // => i + indiceChiffre_ - 1 < taille_
        // or 0 <= i - 1 => indiceChiffre_ <= i + indiceChiffre_ - 1
        // => indiceChiffre_ <= i + indiceChiffre_ - 1 < taille_
        // => indiceChiffre_ < taille_
        for (int i = IndexConstants.SECOND_INDEX; i < nbNombres_; i++) {
            reste_.grDigits.add(grDigits.get(indiceChiffre_));
            reste_.removeBeginningZeros();
            if (reste_.plusPetitQue(_autre)) {
                chiffresQuotient_.add(0L);
            } else {
                chiffreMax_ = reste_.nbChiffresMax(first_);
                // chiffreMax_>=1
                // reste_ >= _autre
                long div_ = reste_.divisionTemporaire(_autre, chiffreMax_);
                chiffresQuotient_.add(div_);
                reste_.retrancher(_autre.multiplyBy(div_));
                //reste_ >= 0
            }
            indiceChiffre_++;
        }
        _quotientReste.setQuot(chiffresQuotient_);
        _quotientReste.setMod(reste_.grDigits);
        return _quotientReste;
    }

    private long nbChiffresMax(long _premierGroupe) {
        if (grDigits.first() >= _premierGroupe) {
            /*
            chiffreMax_>=1 car grChiffres.first()>=_autre.grChiffres.first()
            et a / b >= 1 <=> a >= b pour tout entier positif non nul
            de plus chiffreMax_<=grChiffres.first()<_base_
            */
            return grDigits.first() / _premierGroupe;
        }
        /*
        A:_base_>=_autre.grChiffres.first()>=0, car _base_ est la base de numerotation.
        grChiffres.first()>=1, car this>=_autre et _autre>0 => this>0 => B:grChiffres.first()>=1>=0
        donc grChiffres.first()x_base_>=_autre.grChiffres.first() par A et B
        donc grChiffres.first()x_base_+grChiffres[1]>=_autre.grChiffres.first()
        donc (grChiffres.first()x_base_+grChiffres[1])/_autre.grChiffres.first()>=1 (_autre.grChiffres.first()>0)
        donc chiffreMax_>=1
        grChiffres.first()<_autre.grChiffres.first()
        =>grChiffres.first()+1<=_autre.grChiffres.first()
        => grChiffres.first()x_base_+_base_<=_autre.grChiffres.first()x_base_ (_base_>=0)
        => grChiffres.first()x_base_+_base_-1<_autre.grChiffres.first()x_base_
        or grChiffres[1]<_base_ => grChiffres[1]<=_base_ -1
        donc grChiffres.first()x_base_+grChiffres[1]<=grChiffres.first()x_base_+_base_-1
        => grChiffres.first()x_base_+grChiffres[1]<_autre.grChiffres.first()x_base_
        => chiffreMax_<_autre.grChiffres.first(), car _base_ | _autre.grChiffres.first()x_base_
        */
        return (grDigits.first() * BASE + grDigits.get(IndexConstants.SECOND_INDEX)) / _premierGroupe;
    }
    private long divisionTemporaire(LgInt _diviseur, long _max) {
        /*
        _max>=1,
        car pendant les appels de cette fonction
        passe le dernier argument avec une valeur >= 1
        _min==0,
        car pendant les appels de cette fonction
        passe le premier argument avec une valeur == 0
        */
        //this >= _diviseur => this / _diviseur >= 1 > 0
        long quotient_ = _max / 2;
        if (quotient_ == 0) {
            // _max == 1, car _max >= 1 est toujours vrai
            // et _max >= 2 => _max / 2 >= 1 > 0
            // quotient_==1, car _min=0 et _min=quotient_ => quotient_=0 =>
            // _min+_max <= 1 => _max <= 1 => _max = 1
            // De meme _max = 1 => _min+_max/2 (division entiere) = 0 =>
            // quotient_=0 => quotient_=_min
            return 1;
        }
        // _max>=2
        LgInt prod_ = _diviseur.multiplyBy(_max);
        if (quickCmp(prod_) != SortConstants.NO_SWAP_SORT) {
            //this >= prod_
            return _max;
        }
        //this < prod_
        long max_ = _max;
        long min_ = 0;
        //_max > 0 = min_
        /* _diviseur x _max=prod_>this */
        /* On a toujours _min x _diviseur<=this, car _min==0 et 0 <= this */
        while (min_ < max_ - 1) {
            //dichotomie
            // min_ < max_ - 1 < max_
            // et min_ <= min_
            // => 2 * min_ < max_ - 1 + min_
            // => 2 * min_ <= max_ - 2 + min_
            // => (2 * min_)/2 <= (max_ - 2 + min_)/2
            // => min_ <= (max_ - 2 + min_)/2 < (max_ + min_)/2
            // min_ < max_ - 1 => min_ + 1 < max_
            // min_ + 1 < max_
            // et max_ <= max_
            // => max_ + min_ + 1 < 2 * max_
            // => max_ + min_ + 2 <= 2 * max_
            // => (max_ + min_ + 2)/2 <= (2 * max_)/2 = max_
            // => (max_ + min_ + 2)/2 <= max_
            // => (max_ + min_ )/2<(max_ + min_ + 2)/2 <= max_
            // => (max_ + min_ )/2< max_
            // ==> min_ < (max_ + min_ )/2< max_
            quotient_ = (min_ + max_) / 2;
            prod_ = _diviseur.multiplyBy(quotient_);
            int res_ = quickCmp(prod_);
            if (res_ == SortConstants.EQ_CMP) {
                //trouve => quotient_ est la valeur exacte
                return quotient_;
            }
            if (res_ == SortConstants.NO_SWAP_SORT) {
                //this<prod_ => on cherche un quotient plus petit
                max_ = quotient_;
            } else {
                //this>prod_ => on cherche un quotient plus grand
                min_ = quotient_;
            }
        }
        /*_max-1<=_min par fin de boucle et _min<_max par invariant
        donc _max-1<=_min<_max
        donc _max-1==_min (_min et _max sont entiers)
        et c'est le min qui doit etre retourne,
        car _min x _diviseur<=this et _diviseur x _max>this
        */
        return min_;
    }

    private boolean plusPetitQue(LgInt _autre) {
        int res_ = quickCmp(_autre);
        if (res_ != SortConstants.EQ_CMP) {
            return res_ == SortConstants.NO_SWAP_SORT;
        }
        return false;
    }

    private boolean plusGrandQue(LgInt _autre) {
        int res_ = quickCmp(_autre);
        if (res_ != SortConstants.EQ_CMP) {
            return res_ != SortConstants.NO_SWAP_SORT;
        }
        return false;
    }

    private int quickCmp(LgInt _autre) {
        if (grDigits.size() > _autre.grDigits.size()) {
            return SortConstants.SWAP_SORT;
        }
        if (grDigits.size() < _autre.grDigits.size()) {
            return SortConstants.NO_SWAP_SORT;
        }
        int len_;
        len_ = grDigits.size();
        for (int i = IndexConstants.FIRST_INDEX; i <len_; i++) {
            long entier_ = grDigits.get(i);
            long entierAutre_ = _autre.grDigits.get(i);
            if (entier_ < entierAutre_) {
                return SortConstants.NO_SWAP_SORT;
            }
            if (entier_ > entierAutre_) {
                return SortConstants.SWAP_SORT;
            }
        }
        return SortConstants.EQ_CMP;
    }

    private boolean inferieurOuEgal(LgInt _autre) {
        int res_ = quickCmp(_autre);
        if (res_ != SortConstants.EQ_CMP) {
            return res_ == SortConstants.NO_SWAP_SORT;
        }
        return true;
    }

    /**
    Retourne l'egalite de deux entiers par leur signe et leurs chiffres

    @param _autre
                un autre entier, s'il est null alors <i>false</i> est toujours retourne
    @return <i>true</i> <==> les deux nombres entiers sont egaux.
    */
    public boolean eq(LgInt _two) {
        if (signum != _two.signum) {
            return false;
        }
        return eq(grDigits,_two.grDigits);
    }


    private static boolean eq(Longs _f, Longs _s) {
        int len_ = _f.size();
        if (_s.size() != len_) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            long e_ = _f.get(i);
            long i_ = _s.get(i);
            if (e_ != i_) {
                return false;
            }
        }
        return true;
    }
    LgInt multiplyBy(long _autre) {
        LgInt resultat_ = LgInt.zero();
        if (_autre == 0L) {
            return resultat_;
        }
        resultat_.grDigits.clear();
        long retenue_ = 0;
        int first_ = grDigits.size()-1;
        for (int i = first_; i >= IndexConstants.FIRST_INDEX; i--) {
            long quotient_ = grDigits.get(i) * _autre + retenue_;
            retenue_ = quotient_ / BASE;
            long reste_ = quotient_ - BASE * retenue_;
            resultat_.grDigits.add(IndexConstants.FIRST_INDEX, reste_);
        }
        if (retenue_ > 0) {
            resultat_.grDigits.add(IndexConstants.FIRST_INDEX, retenue_);
        }
        resultat_.removeBeginningZeros();
        return resultat_;
    }


    /**
    Calcule le produit du signe du nombre courant par le reste de la division euclidienne de la valeur absolue du nombre
    courant par la base qui est de 10<sup>9</sup>

    @return le produit du signe du nombre courant par le reste de la division euclidienne de la valeur absolue du nombre
            courant par la base qui est de 10<sup>9</sup> <br/>
            Exemples:<br/>
            <ul>
            <li>new LgInt(1).ll().eq(new LgInt(1))</li>
            <li>new LgInt(-1).ll().eq(new LgInt(-1))</li>
            <li>new LgInt(1234567891).ll().eq(new LgInt(234567891))</li>
            <li>new LgInt(-1234567891).ll().eq(new LgInt(-234567891))</li>
            </ul>
    */
    public long ll() {
        if (isZeroOrGt()) {
            return remainByBase();
        }
        return -remainByBase();
    }


    /**
    Calcule la factorielle du nombre courant.<br/>
    Par convention 0! = 1

    @return la factorielle du nombre courant.
    */
    public LgInt fact() {
        // Attention a 40!
        LgInt res_ = one();
        LgInt init_ = zero();
        LgInt incr_ = one();
        while (!eq(init_)) {
            init_.addNb(incr_);
            res_.multiplierParEntierPositif(init_);
        }
        return res_;
    }


    /**
    Calcule la partie entiere de la racine <i>_ordre</i><sup>eme</sup> de la valeur absolue du nombre courant

    @param _ordre
                l'ordre de la racine (s'il vaut 2, alors on parle de racine carre)
    @throws NullPointerException
                si l'argument est null.
    @return la partie entiere de la racine <i>_ordre</i><sup>eme</sup> du nombre courant
    */
    public LgInt rootAbs(LgInt _ordre) {
        LgInt absoluEnt_ = absNb();
        if (_ordre.isZero()) {
            return absoluEnt_;
        }
        LgInt nbTwo_ = new LgInt(2);
        if (_ordre.plusPetitQue(nbTwo_)) {
            return absoluEnt_;
        }
        LgInt nbOne_ = LgInt.one();
        if (absoluEnt_.plusPetitQue(nbTwo_)) {
            return absoluEnt_;
        }
        LgInt max_ = absoluEnt_;
        // (absoluEnt_+nb1_)/nb2_
        LgInt min_ = LgInt.zero();
        while (plus(min_, nbOne_).plusPetitQue(max_)) {
            LgInt moy_ = divide(plus(max_, min_), nbTwo_);
            LgInt expo_ = powNb(moy_, _ordre);
            int res_ = absoluEnt_.quickCmp(expo_);
            if (res_ == SortConstants.EQ_CMP) {
                return moy_;
            }
            if (res_ == SortConstants.NO_SWAP_SORT) {
                max_ = moy_;
            } else {
                min_ = moy_;
            }
        }
        return min_;
    }

    /**
    Cette methode change l'entier courant en l'elevant a la puissance valant l'autre entier (si l'entier courant valait 3 et
    que l'autre entier valait 2, alors l'entier courant vaut 9)

    @param _expo
                l'exposant de la puissance
    @throws
                si l'argument est null.
    @throws
                si l'argument est strictement negatif.
    */
    public void growToPow(LgInt _expo) {
        LgInt copie_ = new LgInt(this);
        grDigits.clear();
        grDigits.add(1L);
        signum = SIGNE_POSITIF;
        LgInt e = LgInt.zero();
        while (strLower(e, _expo)) {
            multiplyBy(copie_);
            e.increment();
        }
    }

    /**
    Cette methode ne change pas l'entier courant. Elle retourne le reste de la division euclidienne de la valeur absolue de
    l'entier courant par la base (i.e., 10<sup>0</sup>)

    @return le reste de la division euclidienne de la valeur absolue de l'entier courant par la base (i.e., 10<sup>0</sup>)
    */
    long remainByBase() {
        return grDigits.last();
    }

    /**
    Cette methode change l'entier courant en le divisant par un autre entier, au sens de la division euclidienne (si l'entier
    courant valait 1 et que l'autre entier valait 2, alors l'entier courant vaut 0)

    @param _autre
                l'entier diviseur de la division euclidienne
    @throws NullPointerException
                si l'argument est null.
    @throws ArithmeticException
                si l'argument vaut l'entier 0.
    @return l'entier courant modifie
    */
    public void divideBy(LgInt _autre) {
        affect(divisionEuclidienneGeneralise(_autre).getQuot());
    }

    /**
    Cette methode change l'entier courant en lui affectant le reste de la division euclidienne par un autre entier (si
    l'entier courant valait 3 et que l'autre entier valait 2, alors l'entier courant vaut 1, 3 modulo 2 vaut 1)

    @param _autre
                l'entier diviseur de la division euclidienne
    @throws NullPointerException
                si l'argument est null.
    @throws ArithmeticException
                si l'argument vaut l'entier 0.
    @return l'entier courant modifie
    */
    public void remainBy(LgInt _autre) {
        affect(divisionEuclidienneGeneralise(_autre).getMod());
    }

    /**
    Cette methode change l'entier courant en le multipliant par un autre entier (si l'entier courant valait 1 et que l'autre
    entier valait 2, alors l'entier courant vaut 2)

    @param _autre
                l'entier facteur du produit
    @throws NullPointerException
                si l'argument est null.
    @return l'entier courant modifie
    */
    public void multiplyBy(LgInt _autre) {
        if (isZero()) {
            return;
        }
        if (_autre.isZero()) {
            affectZero();
            return;
        }
        multiplierParEntierPositif(_autre);
//        if (isZeroOrGt() == _autre.isZeroOrGt()) {
//            signum = SIGNE_POSITIF;
//        } else {
//            signum = !SIGNE_POSITIF;
//        }
        if (sameSignum(_autre)) {
            signum = SIGNE_POSITIF;
        } else {
            signum = !SIGNE_POSITIF;
        }
    }

    /**
    Cette methode change l'entier courant en le soustrayant d'un autre entier (si l'entier courant valait 1 et que l'autre
    entier valait 2, alors l'entier courant vaut -1)

    @param _autre
                l'entier facteur du produit
    @throws NullPointerException
                si l'argument est null.
    @return l'entier courant modifie
    */
    public void removeNb(LgInt _autre) {
        addNb(_autre.opposNb());
    }

    /**
    Calcule l'oppose du nombre courant.<br/>
    L'oppose de 0 est 0.

    @return l'oppose du nombre courant.
    */
    public LgInt opposNb() {
        LgInt resultat_ = new LgInt(this);
        if (isZero()) {
            resultat_.signum = SIGNE_POSITIF;
            return resultat_;
        }
        resultat_.signum = !signum;
        return resultat_;
    }

    /**
    Cette methode change l'entier courant en ajoutant 1 (si l'entier courant valait 4 alors l'entier courant vaut 5)

    @return l'entier courant modifie
    */
    public void increment() {
        addNb(one());
    }

    /**
    Cette methode change l'entier courant en soustrayant 1 (si l'entier courant valait 4 alors l'entier courant vaut 3)

    @return l'entier courant modifie
    */
    public void decrement() {
        addNb(minusOne());
    }

    /**
    Cette methode change l'entier courant en ajoutant un autre entier (si l'entier courant valait 1 et que l'autre entier
    valait 2, alors l'entier courant vaut 3)

    @param _autre
                l'entier ajoute
    @throws NullPointerException
                si l'argument est null.
    @return l'entier courant modifie
    */
    public void addNb(LgInt _autre) {
        if (_autre.isZero()) {
            return;
        }
        if (isZero()) {
            affect(_autre);
            return;
        }
        //numbers which have not a same signum
        if (!sameSignum(_autre) && eq(grDigits, _autre.grDigits)) {
            //opposite numbers
            affectZero();
            return;
        }
//        if (isZeroOrGt() == _autre.isZeroOrGt()) {
//            ajouter(_autre);
//            return;
//        }
        if (sameSignum(_autre)) {
            ajouter(_autre);
            return;
        }
        if (plusPetitQue(_autre)) {
            // <=> this=_autre-this
            retrancherAutrePuisAffecter(_autre);
            signum = _autre.signum;
            return;
        }
        retrancher(_autre);
    }

    private QuotModLgInt divisionEuclidienneGeneralise(LgInt _autre) {
        QuotModLgInt quotientReste_ = new QuotModLgInt();
        LgInt absolu_ = absNb();
        LgInt autreAbsolu_ = _autre.absNb();
        QuotMod quotientRestePositif_;
        quotientRestePositif_ = absolu_.divisionEuclidienne(autreAbsolu_);
        quotientReste_.setQuot(new LgInt(quotientRestePositif_.getQuot(), SIGNE_POSITIF));
        quotientReste_.setMod(new LgInt(quotientRestePositif_.getMod(), SIGNE_POSITIF));
        quotientReste_.getQuot().multiplyBy(multiply(signum(), _autre.signum()));
        if (isZeroOrGt()) {
            return quotientReste_;
        }
        if (!quotientReste_.getMod().isZero()) {
            quotientReste_.getQuot().addNb(_autre.signum().opposNb());
            quotientReste_.getMod().changeSignum();
            quotientReste_.getMod().addNb(_autre.absNb());
        }
        return quotientReste_;
    }

    public LgInt signum() {
        if (isZero()) {
            return zero();
        }
        if (isZeroOrGt()) {
            return one();
        }
        return minusOne();
    }

    /**
    Calcule la valeur absolue du nombre courant.<br/>
    La valeur absolue de 0 est 0.

    @return la valeur absolue du nombre courant.
    */
    public LgInt absNb() {
        LgInt resultat_ = new LgInt(this);
        resultat_.signum = SIGNE_POSITIF;
        return resultat_;
    }


    /**
    Cette methode change l'entier courant en l'annulant.
    */
    public void affectZero() {
        //setModified();
        grDigits.clear();
        grDigits.add(0L);
        signum = SIGNE_POSITIF;
    }

    /**
    Cette methode ne change pas l'entier courant.<br/>
    L'entier courant est nul <==> il vaut 0.

    @return true <==> il vaut 0. (new LgInt())
    */
    public boolean isZero() {
        return grDigits.first() == 0L;
    }

    /**
    Cette methode change l'entier courant en le passant a l'oppose. L'entier 0 ne change pas.
    */
    public void changeSignum() {
        if (!isZero()) {
            changerSigne();
        }
    }

    void changerSigne() {
        signum = !signum;
    }

    /**
    Cette methode ne change pas l'entier courant.<br/>
    Si l'entier courant vaut 0 alors le signe est positif

    @return <i>true</i> <==> l'entier courant est superieur ou egal a 0.
    */
    public boolean isZeroOrGt() {
        return SIGNE_POSITIF == signum;
    }

    public boolean isZeroOrLt() {
        if (isZero()) {
            return true;
        }
        return SIGNE_POSITIF != signum;
    }

    private void removeBeginningZeros() {
        //setModified();
        while (grDigits.size() > 1 && grDigits.first() == 0) {
            grDigits.remove(IndexConstants.FIRST_INDEX);
        }
    }

    /**
    Cette methode change l'entier courant en lui affectant un autre entier.

    @param _autre
                un autre entier a affecter
    @throws NullPointerException
                si l'argument est null.
    */
    public void affect(LgInt _autre) {
        affecter(_autre);
    }

    public boolean inRange(LgInt _min, LgInt _max) {
        return matchMin(_min) && matchMax(_max);
    }

    private boolean matchMax(LgInt _max) {
        return _max == null || _max.cmp(this) >= 0;
    }

    private boolean matchMin(LgInt _min) {
        return _min == null || _min.cmp(this) <= 0;
    }

    @Override
    public String display() {
        return toNumberString();
    }

    /**
    Cette methode ne change pas l'entier courant.<br/>

    @return la chaine de caracteres represantant l'entier comme ecrit de maniere naturelle.
    */
    
    public String toNumberString() {
        StringBuilder chaine_ = new StringBuilder();
        int indice_ = IndexConstants.FIRST_INDEX;
        // puissance10 vaut log10(_base_)
        for (long c : grDigits) {
            appendDigits(chaine_, indice_, c);
            indice_++;
        }
        if (!isZeroOrGt()) {
            return chaine_.insert(0, MINUS).toString();
        }
        return chaine_.toString();
    }

    private static void appendDigits(StringBuilder _chaine, int _indice, long _c) {
        if (_indice > IndexConstants.FIRST_INDEX) {
            if (_c == 0) {
                for (int i = IndexConstants.FIRST_INDEX; i < LOG_BASE; i++) {
                    _chaine.append(ZERO);
                }
            } else {
                String ch_ = Long.toString(_c);
                int nbZeros_ = LgInt.LOG_BASE - ch_.length();
                for (int i = IndexConstants.FIRST_INDEX; i < nbZeros_; i++) {
                    _chaine.append(ZERO);
                }
                _chaine.append(ch_);
            }
        } else {
            _chaine.append(_c);
        }
    }

    /**
    @param _autre
                un entier de comparaison a droite de l'operateur
    @throws NullPointerException
                si l'argument est null.
    @return 1 si l'entier courant est strictement plus grand que _autre,<br/>
            0 si l'entier courant vaut _autre,<br/>
            -1 sinon
    */
    public int cmp(LgInt _autre) {
        if (strGreater(this, _autre)) {
            return 1;
        }
        if (eq(_autre)) {
            return 0;
        }
        return -1;
    }
}
