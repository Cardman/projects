package code.maths;
import code.maths.exceptions.BadDivisionException;
import code.maths.exceptions.FormatException;
import code.maths.exceptions.NegatifExposantException;
import code.maths.exceptions.NegativeNumberException;
import code.serialize.CheckedData;
import code.sml.FromAndToString;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.Numbers;
import code.util.PairEq;
import code.util.SortableCustList;
import code.util.StringList;
import code.util.TreeMap;
import code.util.ints.Cmp;

/**
Classe modelisant des entiers longs qui sont une extension du type <i>long</i>.<br/>
Ces entiers sont decoupes en groupes de chiffres et possedent un signe.<br/>
Attention a eviter d'utiliser des nombres d'un milliard de chiffres, car la memoire vive est limitee.
 */
@CheckedData
public final class LgInt implements Cmp<LgInt> {

    //extends ViewAdapter

    /** BASE vaut 10<sup>9</sup> */
//    public static final long BASE = base();
    public static final long BASE = 1000000000l;
//    static final int LOG_BASE = logBase();
    static final int LOG_BASE = 9;


    static final int BASE_NUMER = 10;

    static final boolean SIGNE_POSITIF = false;

    private static final char MINUS_CHAR = '-';

    private static final String MINUS = "-";

    private static final String COMMA = ",";

    private static final char ZERO = '0';
    private static final String EMPTY_STRING = "";

//    private static final String REG_EXP_INT = "^-?[0-9]+$";
    /**
    Un entier est represente par un groupe de chiffres tous inferieurs a la base (10^9).
    */
    private Numbers<Long> grDigits;

    /**
    signe valant true si et seulement si le nombre entier est positif ou nul.
    */
    private boolean signum;

    private LgInt() {
        grDigits = new Numbers<Long>();
    }

    /**
        construit un nombre entier a partir d'un autre

        @param _autre un autre entier
        @throws NullPointerException
        si l'argument est nul.
    */
    public LgInt(LgInt _autre) {
        affecterSansSigne(_autre);
        signum = _autre.signum;
    }

    /**
        construit un nombre entier a partir d'une chaine de caracteres

        @param _chaine chaine de caracteres respectant l'expression reguliere ^\-[0-9]+$
        @throws FormatException
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
        if (!matchesLgInt(_chaine)) {
            throw new FormatException(_chaine);
        }
//        if (!Pattern.matches(REG_EXP_INT, _chaine)) {
//            throw new FormatException(_chaine);
//        }
        grDigits = new Numbers<Long>();
        int powerTen_ = LOG_BASE;
        String nbLu_ = chaineValeurAbsolue(_chaine);
        // Suppression des 0 au debut du nombre sauf s'il reste un 0 sans autre
        // chiffre
        while (true) {
            if (nbLu_.length() == 1) {
                break;
            }
            if (nbLu_.charAt(CustList.FIRST_INDEX) != ZERO) {
                break;
            }
            nbLu_ = nbLu_.substring(CustList.SECOND_INDEX);
        }
        int firstInd_ = nbLu_.length() - 1;
        while (firstInd_ >= CustList.FIRST_INDEX) {
            String nbLuBis_;
            if (nbLu_.length() >= powerTen_) {
                nbLuBis_ = nbLu_.substring(nbLu_.length() - powerTen_, nbLu_.length());
                if (nbLu_.length() > powerTen_) {
                    // nbLu_.resize(nbLu_.length()-puissance10_)
                    nbLu_ = nbLu_.substring(CustList.FIRST_INDEX, nbLu_.length() - powerTen_);
                }
            } else {
                nbLuBis_ = nbLu_;
            }
            grDigits.add(CustList.FIRST_INDEX, Long.parseLong(nbLuBis_));
            firstInd_ -= powerTen_;
        }
        if (isZero()) {
            signum = SIGNE_POSITIF;
        } else {
            signum = !_chaine.startsWith(MINUS) == SIGNE_POSITIF;
        }
    }

    /**
    construit un nombre entier a partir d'un entier de type <i>long</i>

    @param _entierL
                un entier de type <i>long</i>
    */
    public LgInt(long _entierL) {
        grDigits = new Numbers<Long>();
        long nombre_;
        if (_entierL < 0) {
            nombre_ = -_entierL;
        } else {
            nombre_ = _entierL;
        }
        long quotient_;
        long reste_;
        quotient_ = nombre_ / BASE;
        reste_ = nombre_ - quotient_ * BASE;
        grDigits.add(reste_);
        while (quotient_ > BASE) {
            nombre_ = quotient_;
            quotient_ = nombre_ / BASE;
            reste_ = nombre_ - quotient_ * BASE;
            grDigits.add(CustList.FIRST_INDEX, reste_);
        }
        if (quotient_ != 0) {
            grDigits.add(CustList.FIRST_INDEX, quotient_);
        }
        signum = (_entierL >= 0) == SIGNE_POSITIF;
    }

    private LgInt(Numbers<Long> _grChiffre, boolean _signe) {
        grDigits = new Numbers<Long>(_grChiffre);
        signum = _signe;
    }

    @FromAndToString
    public static LgInt newLgInt(String _string) {
        return new LgInt(_string);
    }

    /**@return the integer 0*/
    public static LgInt zero() {
        LgInt one_ = new LgInt();
        one_.grDigits.add(0l);
        one_.signum = SIGNE_POSITIF;
        return one_;
    }

    /**@return the integer 1*/
    public static LgInt one() {
        LgInt one_ = new LgInt();
        one_.grDigits.add(1l);
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
        l_.grDigits = new Numbers<Long>();
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
            l_.grDigits.add(CustList.FIRST_INDEX, reste_);
        }
        if (quotient_ != 0) {
            l_.grDigits.add(CustList.FIRST_INDEX, quotient_);
        }
        l_.signum = SIGNE_POSITIF;
        return l_;
    }

    /**@return the integer -1*/
    public static LgInt minusOne() {
        LgInt one_ = new LgInt();
        one_.grDigits.add(1l);
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
        if (!matchesLgInt(_string)) {
            return false;
        }
        return true;
    }

    private static boolean matchesLgInt(String _input) {
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
        if (!Character.isDigit(_input.charAt(i_))) {
            return false;
        }
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

    public boolean isPrime(){
        LgInt abs_=absNb();
        LgInt nbTwo_ = new LgInt(2);
        LgInt r_=rootAbs(nbTwo_);
        LgInt i_=nbTwo_;
        while (lowerEq(i_, r_)) {
            if (remain(abs_, i_).isZero()) {
                return false;
            }
            i_.increment();
        }
        return true;
    }

    public PairEq<PairEq<LgInt,LgInt>,PairEq<LgInt,LgInt>> identiteBezoutPgcdPpcm(LgInt _b) {
        PairEq<PairEq<LgInt,LgInt>,PairEq<LgInt,LgInt>> r_;
        r_ = new PairEq<PairEq<LgInt,LgInt>,PairEq<LgInt,LgInt>>();
        r_.setFirst(new PairEq<LgInt,LgInt>(zero(),zero()));
        r_.setSecond(new PairEq<LgInt,LgInt>(zero(),zero()));
        if (_b.isZero()) {
            return r_;
        }
        EqList<LgInt> quot_ = new EqList<LgInt>();
        PairEq<LgInt,LgInt> qr_ = new PairEq<LgInt,LgInt>();
        boolean greater_ = strGreater(this, _b);
        if(greater_) {
            qr_=divisionEuclidienneGeneralise(_b);
        } else {
            qr_=_b.divisionEuclidienneGeneralise(this);
        }
        LgInt rem_=qr_.getSecond();
        if(rem_.isZero()) {
            if(greater_) {
                r_.getFirst().setFirst(zero());
                r_.getFirst().setSecond(one());
            } else {
                r_.getFirst().setFirst(one());
                r_.getFirst().setSecond(zero());
            }
            PairEq<LgInt,LgInt> minMax_=minMax(this,_b);
            r_.getSecond().setFirst(new LgInt(minMax_.getFirst()));
            r_.getSecond().setSecond(new LgInt(minMax_.getSecond()));
            return r_;
        }
        LgInt a_=new LgInt(this);
        LgInt b_=_b;
        quot_.add(0, qr_.getFirst());
        while(!rem_.isZero()) {
            a_=b_;
            b_=rem_;
            qr_=a_.divisionEuclidienneGeneralise(b_);
            quot_.add(0, qr_.getFirst());
            rem_=qr_.getSecond();
        }
        r_.getFirst().setFirst(one());
        r_.getFirst().setSecond(quot_.get(1));
        int len_ = quot_.size();
        for(int i=2;i<len_;i++) {
            if(i%2==0) {
                r_.getFirst().getFirst().addNb(multiply(quot_.get(i), r_.getFirst().getSecond()));
            } else {
                r_.getFirst().getSecond().addNb(multiply(quot_.get(i), r_.getFirst().getFirst()));
            }
        }
        r_.getFirst().getSecond().changeSignum();
        if(quot_.size()%2==1) {
            LgInt t_=r_.getFirst().getFirst();
            r_.getFirst().setFirst(r_.getFirst().getSecond());
            r_.getFirst().setSecond(t_);
        }
        r_.getSecond().setFirst(new LgInt(b_));
        r_.getSecond().setSecond(multiply(divide(this, b_), _b));
        return r_;
    }

    public Decomposition decompoPrim() {
        LgInt copy_=absNb();
        EqList<PairEq<LgInt,LgInt>> divs_ = new EqList<PairEq<LgInt,LgInt>>();
        LgInt one_ = one();
        LgInt two_ = new LgInt(2);
        PairEq<LgInt,LgInt> p_ = new PairEq<LgInt,LgInt>();
        p_.setFirst(zero());
        p_.setSecond(zero());
        while (true) {
            if (copy_.eq(one())) {
                break;
            }
            if(copy_.isPrime()) {
                p_.setFirst(copy_);
                p_.setSecond(one_);
                divs_.add(p_);
                break;
            }
            p_.setFirst(two_);
            p_.setSecond(zero());
            while (true) {
                PairEq<LgInt,LgInt> qr_=copy_.divisionEuclidienneGeneralise(two_);
                if(!qr_.getSecond().isZeroOrLt()) {
                    break;
                }
                p_.getSecond().increment();
                copy_=qr_.getFirst();
            }
            divs_.add(p_);
        }
        return new Decomposition(getSignum(), divs_);
    }

    public EqList<LgInt> getDividers() {
        EqList<LgInt> divs_ = new EqList<LgInt>();
        LgInt rootAbs_=rootAbs(new LgInt(2));
        LgInt abs_=absNb();
        LgInt init_ = new LgInt(2);
        divs_.add(LgInt.one());
        divs_.add(abs_);
        while (true) {
            if(LgInt.strGreater(init_, rootAbs_)) {
                break;
            }
            PairEq<LgInt,LgInt> qr_=abs_.divisionEuclidienneGeneralise(init_);
            if(qr_.getSecond().isZero()) {
                divs_.add(init_);
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
    public static TreeMap<SortableCustList<LgInt>,LgInt> seqAmong(
            EqList<LgInt> _repartitions,
            LgInt _sommeTotale) {
//        TreeMap<CustList<LgInt>,LgInt> loiProba_ = new TreeMap<new>(new Comparator<CustList<LgInt>>() {
//            @Override
//            public int compare(CustList<LgInt> _o1, CustList<LgInt> _o2) {
//                int res_ = Integer.compare(_o1.size(), _o2.size());
//                if (res_ != 0) {
//                    return res_;
//                }
//                int len_;
//                len_ = _o1.size();
//                for (int i = CustList.FIRST_INDEX; i <len_; i++) {
//                    res_ = _o1.get(i).compareTo(_o2.get(i));
//                    if (res_ != 0) {
//                        return res_;
//                    }
//                }
//                return 0;
//            }
//        });
        TreeMap<SortableCustList<LgInt>,LgInt> loiProba_ = new TreeMap<SortableCustList<LgInt>,LgInt>(new ComparatorEvents());
        int i_ = CustList.FIRST_INDEX;
        int nbIterations_ = _repartitions.size();
        EqList<SortableCustList<LgInt>> repartitionsPossibles_ = new EqList<SortableCustList<LgInt>>();
        repartitionsPossibles_.add(new SortableCustList<LgInt>());
        EqList<SortableCustList<LgInt>> repartitionsPossiblesLoc_ = new EqList<SortableCustList<LgInt>>();
        boolean modif_=true;
        while (modif_) {
            if (i_ >= nbIterations_) {
                break;
            }
            modif_=false;
            repartitionsPossiblesLoc_.clear();
            LgInt terme_ = _repartitions.get(i_);
            for (SortableCustList<LgInt> l: repartitionsPossibles_) {
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
                    if (i_ == nbIterations_-1) {
                        if (!sommeLoc_.eq(_sommeTotale)) {
                            event_.increment();
                            continue;
                        }
                    }
                    SortableCustList<LgInt> l_ = new SortableCustList<LgInt>(l);
                    l_.add(new LgInt(event_));
                    repartitionsPossiblesLoc_.add(l_);
                    event_.increment();
                }
            }
            if (!repartitionsPossiblesLoc_.isEmpty()) {
                modif_ = true;
                repartitionsPossibles_ = new EqList<SortableCustList<LgInt>>(repartitionsPossiblesLoc_);
            }
            i_++;
        }
        for (SortableCustList<LgInt> l: repartitionsPossibles_) {
            l.sort();
        }
        LgInt one_ = one();
        for (SortableCustList<LgInt> l: repartitionsPossibles_) {
            boolean present_ = false;
            for (EntryCust<SortableCustList<LgInt>,LgInt> lTwo_: loiProba_.entryList()) {
                if(!l.eq(lTwo_.getKey())) {
                    continue;
                }
                lTwo_.getValue().increment();
                present_ = true;
                break;
            }
            if (present_) {
                continue;
            }
            loiProba_.put(l, one_);
        }
        return loiProba_;
    }

    public static PairEq<LgInt,LgInt> minMax(LgInt _a, LgInt _b) {
        if (strGreater(_a, _b)) {
            return new PairEq<LgInt,LgInt>(_b, _a);
        }
        return new PairEq<LgInt,LgInt>(_a, _b);
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
    @throws NegatifExposantException
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
    @throws NullPointerException
                si un des arguments est null.
    @throws NegativeNumberException
                si un des arguments est strictement negatif.
    @return le nombre de combinaisons de <i>_nombre</i> parmi <i>_nombreTotalElements</i>
    */
    public static LgInt among(LgInt _nombre, LgInt _nombreTotalElements) {
        String erreurs_ = EMPTY_STRING;
        if (!_nombre.isZeroOrGt()) {
            erreurs_ += _nombre.toString();
        }
        if (!StringList.quickEq(erreurs_,EMPTY_STRING)) {
            erreurs_ += COMMA;
        }
        if (!_nombreTotalElements.isZeroOrGt()) {
            erreurs_ += _nombreTotalElements.toString();
        }
        if (!StringList.quickEq(erreurs_,EMPTY_STRING)) {
            throw new NegativeNumberException(erreurs_);
        }
        if (_nombre.plusGrandQue(_nombreTotalElements)) {
            // <=> this > _nombreTotalElements
            return LgInt.zero();
        }
        LgInt cardinal_ = LgInt.one();
        int nombre_;
        LgInt pgcd_;
        EqList<LgInt> numerateur_ = new EqList<LgInt>();
        EqList<LgInt> denominateur_ = new EqList<LgInt>();
        LgInt diffTotalPartiel_ = minus(_nombreTotalElements, _nombre);
        long absBase_ = _nombre.remainByBase();
        long diffAbsBase_ = diffTotalPartiel_.remainByBase();
        nombre_ = (int) Math.min(absBase_, diffAbsBase_);
        // nombre_ < _nombreTotalElements / 2
        for (int i = CustList.FIRST_INDEX; i < nombre_; i++) {
            LgInt temp_ = new LgInt(i);
            numerateur_.add(minus(_nombreTotalElements, temp_));
            denominateur_.add(minus(new LgInt(nombre_), temp_));
        }
        int nbPgcd_ = nombre_ - 1;
        for (int i = CustList.FIRST_INDEX; i < nbPgcd_; i++) {
            int j = 0;
            while (different(denominateur_.get(i), one())) {
                pgcd_ = pgcd(numerateur_.get(j), denominateur_.get(i));
                numerateur_.set(j, divide(numerateur_.get(j), pgcd_));
                denominateur_.set(i, divide(denominateur_.get(i), pgcd_));
                j ++;
            }
        }
        for (int i = CustList.FIRST_INDEX; i < nombre_; i++) {
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
        LgInt a_ = _a;
        LgInt b_ = _b;
        while (!reste_.isZero()) {
            a_ = b_;
            b_ = reste_;
            reste_ = remain(a_, b_);
        }
        return b_;
    }

    public static boolean eq(LgInt _one, LgInt _two) {
        if (_one == null) {
            return _two == null;
        }
        return _one.eq(_two);
    }

    public static boolean sameSignum(LgInt _one, LgInt _two) {
        if (_one.isZeroOrGt()) {
            return _two.isZeroOrGt();
        }
        return !_two.isZeroOrGt();
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
        long div_ = BASE_NUMER * BASE_NUMER;
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
    Numbers<Long> getGrDigits() {
        return grDigits;
    }

    void setGrDigits(Numbers<Long> _grDigits) {
        //setModified();
        grDigits = _grDigits;
    }

    boolean getSignum() {
        return signum;
    }

    void setSignum(boolean _signum) {
        //setModified();
        signum = _signum;
    }

    private void affecterSansSigne(LgInt _autre) {
        //setModified();
        grDigits = new Numbers<Long>(_autre.grDigits);
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
        double tmp_;
        Numbers<Long> resultatChiffres_ = resultat_.grDigits;
        resultatChiffres_.clear();
        int len_;
        len_ = grDigits.size();
        for (int i = CustList.FIRST_INDEX; i <len_; i++) {
            resultatChiffres_.add(0L);
        }
        int nbDig_ = resultatChiffres_.size();
        int nbDigMinusOne_ = nbDig_;
        nbDigMinusOne_ --;
        for (int i = nbDigMinusOne_; i >= CustList.FIRST_INDEX; i--) {
            resultatChiffres_.set(i, (long) Math.floor(grDigits.get(i) * _db));
            tmp_ = (grDigits.get(i) * _db - resultatChiffres_.get(i)) * BASE;
            int j_ = i + 1;
            for (int j = j_; j < nbDig_; j++) {
                resultatChiffres_.set(j, resultatChiffres_.get(j) + (long) Math.floor(tmp_));
                tmp_ = (tmp_ - (long) Math.floor(tmp_)) * BASE;
            }
        }
        long retenue_ = 0;
        long quotient_;
        long reste_;
        for (int i = nbDigMinusOne_; i >= CustList.FIRST_INDEX; i--) {
            resultatChiffres_.set(i, resultatChiffres_.get(i) + retenue_);
            quotient_ = resultatChiffres_.get(i) / BASE;
            reste_ = resultatChiffres_.get(i) - BASE * quotient_;
            retenue_ = quotient_;
            resultatChiffres_.set(i, reste_);
        }
        if (retenue_ > 0) {
            resultatChiffres_.add(CustList.FIRST_INDEX, retenue_);
        }
        int taille_ = resultatChiffres_.size() - 1;
        for (int i = CustList.FIRST_INDEX; i < taille_; i++) {
            if (resultatChiffres_.first() != 0) {
                break;
            }
            resultatChiffres_.removeAt(CustList.FIRST_INDEX);
        }
        return resultat_;
    }

    private void multiplierParEntierPositif(LgInt _autre) {
        //setModified();
        LgInt resultat_ = LgInt.zero();
        LgInt tmp_;
        int longueur_ = grDigits.size();
        int longueurBis_ = _autre.grDigits.size();
        int expo_ = 0;
        if (longueur_ < longueurBis_) {
            int first_ = longueur_;
            first_ --;
            for (int i = first_; i >= CustList.FIRST_INDEX; i--) {
                tmp_ = _autre.multiplyBy(grDigits.get(i));
                for (int j = CustList.SIZE_EMPTY; j < expo_; j++) {
                    tmp_.grDigits.add(0l);
                }
                tmp_.removeBeginningZeros();
                expo_++;
                resultat_.ajouter(tmp_);
            }
        } else {
            int first_ = longueurBis_;
            first_ --;
            for (int i = first_; i >= CustList.FIRST_INDEX; i--) {
                tmp_ = multiplyBy(_autre.grDigits.get(i));
                for (int j = CustList.SIZE_EMPTY; j < expo_; j++) {
                    tmp_.grDigits.add(0l);
                }
                tmp_.removeBeginningZeros();
                expo_++;
                resultat_.ajouter(tmp_);
            }
        }
        grDigits.clear();
        grDigits.addAllElts(resultat_.grDigits);
    }

    private void ajouter(LgInt _autre) {
        //setModified();
        int retenue_ = 0;
        long somme_;
        long reste_;
        int longueur_ = grDigits.size();
        int longueurBis_ = _autre.grDigits.size();
        int i_ = longueur_ - 1;
        int j_ = longueurBis_ - 1;
        while (i_ >= CustList.FIRST_INDEX && j_ >= CustList.FIRST_INDEX) {
            somme_ = grDigits.get(i_) + _autre.grDigits.get(j_) + retenue_;
            if (somme_ < BASE) {
                grDigits.set(i_, somme_);
                retenue_ = 0;
            } else {
                reste_ = somme_ - BASE;
                grDigits.set(i_, reste_);
                retenue_ = 1;
            }
            i_--;
            j_--;
        }
        if (longueur_ > longueurBis_) {
            while (i_ >= CustList.FIRST_INDEX) {
                somme_ = grDigits.get(i_) + retenue_;
                if (somme_ < BASE) {
                    grDigits.set(i_, somme_);
                    retenue_ = 0;
                } else {
                    reste_ = somme_ - BASE;
                    grDigits.set(i_, reste_);
                    retenue_ = 1;
                }
                i_--;
            }
        } else if (longueur_ < longueurBis_) {
            while (j_ >= CustList.FIRST_INDEX) {
                somme_ = _autre.grDigits.get(j_) + retenue_;
                if (somme_ < BASE) {
                    grDigits.add(CustList.FIRST_INDEX, somme_);
                    retenue_ = 0;
                } else {
                    reste_ = somme_ - BASE;
                    grDigits.add(CustList.FIRST_INDEX, reste_);
                    retenue_ = 1;
                }
                j_--;
            }
        }
        if (retenue_ == 1) {
            grDigits.add(CustList.FIRST_INDEX, (long) retenue_);
        }
    }

    private void retrancher(LgInt _autre) {
        retrancherChiffres(_autre.grDigits);
    }

    private void retrancherChiffres(Numbers<Long> _chiffresAutre) {
        long retenue_ = 0;
        int longueur_ = grDigits.size();
        int longueurBis_ = _chiffresAutre.size();
        long diff_;
        int k_ = longueur_ - 1;
        int l_ = longueurBis_ - 1;
        while (k_ >= CustList.FIRST_INDEX && l_ >= CustList.FIRST_INDEX) {
            if (grDigits.get(k_) >= _chiffresAutre.get(l_) + retenue_) {
                diff_ = grDigits.get(k_) - _chiffresAutre.get(l_) - retenue_;
                retenue_ = 0;
            } else {
                diff_ = grDigits.get(k_) + BASE - _chiffresAutre.get(l_) - retenue_;
                retenue_ = 1;
            }
            grDigits.set(k_, diff_);
            k_--;
            l_--;
        }
        if (longueur_ > longueurBis_) {
            while (k_ >= CustList.FIRST_INDEX) {
                if (grDigits.get(k_) >= retenue_) {
                    diff_ = grDigits.get(k_) - retenue_;
                    retenue_ = 0;
                } else {
                    diff_ = grDigits.get(k_) + BASE - retenue_;
                    retenue_ = 1;
                }
                grDigits.set(k_, diff_);
                k_--;
            }
        }
        removeBeginningZeros();
    }

    private void retrancherAutrePuisAffecter(LgInt _autre) {
        //setModified();
        LgInt copieAutre_ = new LgInt(_autre);
        copieAutre_.retrancherChiffres(grDigits);
        grDigits = copieAutre_.grDigits;
    }

    private PairEq<Numbers<Long>, Numbers<Long>> divisionEuclidienne(LgInt _autre) {
        PairEq<Numbers<Long>, Numbers<Long>> quotientReste_ = new PairEq<Numbers<Long>, Numbers<Long>>();
        if (plusPetitQue(_autre)) {
            quotientReste_.setFirst(new Numbers<Long>());
            quotientReste_.getFirst().add(0l);
            quotientReste_.setSecond(new Numbers<Long>(grDigits));
            return quotientReste_;
        }
        int taille_ = grDigits.size();
        int tailleBis_ = _autre.grDigits.size();
        int nbNombres_;
        long chiffreMax_;
        LgInt reste_ = LgInt.zero();
        reste_.grDigits.clear();
        Numbers<Long> chiffresQuotient_ = new Numbers<Long>();
        int indiceChiffre_;
        if (grDigits.first() >= _autre.grDigits.first()) {
            chiffreMax_ = grDigits.first() / _autre.grDigits.first();
            /*
            chiffreMax_>=1 car grChiffres.first()>=_autre.grChiffres.first() et le quotient entier entre un dividende plus
            grand que le diviseur est >= 1 de plus chiffreMax_<=grChiffres.first()<_base_
            */
            nbNombres_ = taille_ - tailleBis_ + 1;
            for (int i = CustList.FIRST_INDEX; i < tailleBis_; i++) {
                reste_.grDigits.add(grDigits.get(i));
            }
            indiceChiffre_ = tailleBis_;
        } else {
            chiffreMax_ = (grDigits.first() * BASE + grDigits.get(CustList.SECOND_INDEX)) / _autre.grDigits.first();
            /*
            _base_>=_autre.grChiffres.first(), car _base_ est la _base_ de numérotation. grChiffres.first()>=1, car
            this>=_autre et _autre>0 => this>0 => grChiffres.first()>=1 donc
            grChiffres.first()x_base_>=_autre.grChiffres.first() donc
            grChiffres.first()x_base_+grChiffres[1]>=_autre.grChiffres.first() donc chiffreMax_>=1
            grChiffres.first()+1<=_autre.grChiffres.first() => grChiffres.first()x_base_+_base_<=_autre.grChiffres.first()w_base_
            => grChiffres.first()x_base_+_base_-1<_autre.grChiffres.first()x_base_ or grChiffres[1]<_base_ =>
            grChiffres[1]<=_base_ -1 => grChiffres. first()x_base_+grChiffres[1]<_autre.grChiffres.first()x_base_ =>
            chiffreMax_<_autre.grChiffres.first(), car _base_ | _autre.grChiffres.first()x_base_
            */
            nbNombres_ = taille_ - tailleBis_;
            for (int i = CustList.FIRST_INDEX; i <= tailleBis_; i++) {
                reste_.grDigits.add(grDigits.get(i));
            }
            indiceChiffre_ = tailleBis_ + 1;
        }
        // chiffreMax_>=1
        chiffresQuotient_.add(reste_.divisionTemporaire(_autre, chiffreMax_));
        reste_.retrancher(_autre.multiplyBy(chiffresQuotient_.last()));
        for (int i = CustList.SECOND_INDEX; i < nbNombres_; i++) {
            reste_.grDigits.add(grDigits.get(indiceChiffre_));
            reste_.removeBeginningZeros();
            if (reste_.plusPetitQue(_autre)) {
                chiffresQuotient_.add(0l);
            } else {
                if (reste_.grDigits.first() >= _autre.grDigits.first()) {
                    chiffreMax_ = reste_.grDigits.first() / _autre.grDigits.first();
                    /*
                    chiffreMax_>=1 car reste_.grChiffres.first()>=_autre.grChiffres.first() et le quotient entier entre un
                    dividende plus grand que le diviseur est >= 1
                    */
                } else {
                    chiffreMax_ = reste_.grDigits.first() * BASE + reste_.grDigits.get(CustList.SECOND_INDEX);
                    chiffreMax_ /= _autre.grDigits.first();
                    /*
                    _base_>=_autre.grChiffres.first(), car _base_ est la _base_ de numérotation. reste_.grChiffres.first()>=1,
                    car reste_>=_autre et _autre>0 => reste_>0 => grChiffres.first()>=1 donc
                    grChiffres.first() x _base_>=_autre.grChiffres.first() donc grChiffres
                    .first() x _base_+grChiffres[1]>=_autre.grChiffres.first() donc chiffreMax_>=1
                    */
                }
                // chiffreMax_>=1
                chiffresQuotient_.add(reste_.divisionTemporaire(_autre, chiffreMax_));
                reste_.retrancher(_autre.multiplyBy(chiffresQuotient_.last()));
            }
            indiceChiffre_++;
        }
        quotientReste_.setFirst(chiffresQuotient_);
        quotientReste_.setSecond(reste_.grDigits);
        return quotientReste_;
    }

    private long divisionTemporaire(LgInt _diviseur, long _max) {
        /*
        _max>=1, car pendant les appels de cette fonction passe le dernier argument avec une valeur >= 1 _min==0, car pendant
        les appels de cette fonction passe le premier argument avec une valeur == 0
        */
        long quotient_ = _max / 2;
        if (quotient_ == 0) {
            // quotient_==1, car _min=0 et _min=quotient_ => quotient_=0 =>
            // _min+_max <= 1 => _max <= 1 => _max = 1
            // De même _max = 1 => _min+_max/2 (division entière) = 0 =>
            // quotient_=0 => quotient_=_min
            return 1;
        }
        // _max>=2
        LgInt prod_ = _diviseur.multiplyBy(_max);
        if (superieurOuEgal(prod_)) {
            return _max;
        }
        long max_ = _max;
        long min_ = 0;
        /* _diviseur x _max=prod_>this */
        /* On a toujours _min x _diviseur<=this, car _min==0 */
        while (min_ < max_ - 1) {
            quotient_ = (min_ + max_) / 2;
            prod_ = _diviseur.multiplyBy(quotient_);
            if (!superieurOuEgal(prod_)) {
                max_ = quotient_;
            } else {
                min_ = quotient_;
            }
        }
        /*
        _max-1<=_min<_max, donc _max-1==_min et c'est le min qui doit etre retourne, car _min x _diviseur<=this et
        _diviseur x _max>this
        */
        return min_;
    }

    private boolean plusPetitQue(LgInt _autre) {
        if (grDigits.size() > _autre.grDigits.size()) {
            return false;
        }
        if (grDigits.size() < _autre.grDigits.size()) {
            return true;
        }
        int len_;
        len_ = grDigits.size();
        for (int i = CustList.FIRST_INDEX; i <len_; i++) {
            long entier_ = grDigits.get(i);
            long entierAutre_ = _autre.grDigits.get(i);
            if (entier_ < entierAutre_) {
                return true;
            }
            if (entier_ > entierAutre_) {
                return false;
            }
        }
        return false;
    }

    private boolean plusGrandQue(LgInt _autre) {
        if (grDigits.size() > _autre.grDigits.size()) {
            return true;
        }
        if (grDigits.size() < _autre.grDigits.size()) {
            return false;
        }
        int len_;
        len_ = grDigits.size();
        for (int i = CustList.FIRST_INDEX; i <len_; i++) {
            long entier_ = grDigits.get(i);
            long entierAutre_ = _autre.grDigits.get(i);
            if (entier_ < entierAutre_) {
                return false;
            }
            if (entier_ > entierAutre_) {
                return true;
            }
        }
        return false;
    }

    private boolean superieurOuEgal(LgInt _autre) {
        if (grDigits.size() > _autre.grDigits.size()) {
            return true;
        }
        if (grDigits.size() < _autre.grDigits.size()) {
            return false;
        }
        int len_;
        len_ = grDigits.size();
        for (int i = CustList.FIRST_INDEX; i <len_; i++) {
            long entier_ = grDigits.get(i);
            long entierAutre_ = _autre.grDigits.get(i);
            if (entier_ < entierAutre_) {
                return false;
            }
            if (entier_ > entierAutre_) {
                return true;
            }
        }
        return true;
    }

    private boolean inferieurOuEgal(LgInt _autre) {
        if (grDigits.size() > _autre.grDigits.size()) {
            return false;
        }
        if (grDigits.size() < _autre.grDigits.size()) {
            return true;
        }
        int len_;
        len_ = grDigits.size();
        for (int i = CustList.FIRST_INDEX; i <len_; i++) {
            long entier_ = grDigits.get(i);
            long entierAutre_ = _autre.grDigits.get(i);
            if (entier_ < entierAutre_) {
                return true;
            }
            if (entier_ > entierAutre_) {
                return false;
            }
        }
        return true;
    }

    /**
    Retourne l'egalite de deux entiers par leur signe et leurs chiffres

    @param _autre
                un autre entier, s'il est null alors <i>false</i> est toujours retourne
    @return <i>true</i> <==> les deux nombres entiers sont egaux.
    */
    @Override
    public boolean eq(LgInt _two) {
        if (signum != _two.signum) {
            return false;
        }
        return grDigits.eq(_two.grDigits);
    }

    LgInt multiplyBy(long _autre) {
        LgInt resultat_ = LgInt.zero();
        if (_autre == 0L) {
            return resultat_;
        }
        resultat_.grDigits.clear();
        long quotient_;
        long retenue_ = 0;
        long reste_;
        int first_ = grDigits.size();
        first_ --;
        for (int i = first_; i >= CustList.FIRST_INDEX; i--) {
            quotient_ = grDigits.get(i) * _autre + retenue_;
            retenue_ = quotient_ / BASE;
            reste_ = quotient_ - BASE * retenue_;
            resultat_.grDigits.add(CustList.FIRST_INDEX, reste_);
        }
        if (retenue_ > 0) {
            resultat_.grDigits.add(CustList.FIRST_INDEX, retenue_);
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
        while (true) {
            if (eq(init_)) {
                break;
            }
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
        LgInt moy_ = LgInt.zero();
        LgInt expo_ = LgInt.zero();
        while (plus(min_, nbOne_).plusPetitQue(max_)) {
            moy_ = divide(plus(max_, min_), nbTwo_);
            expo_ = powNb(moy_, _ordre);
            if (expo_.eq(absoluEnt_)) {
                return moy_;
            }
            if (expo_.plusPetitQue(absoluEnt_)) {
                min_ = moy_;
            } else {
                max_ = moy_;
            }
        }
        return min_;
    }

    /**
    Cette methode change l'entier courant en l'elevant a la puissance valant l'autre entier (si l'entier courant valait 3 et
    que l'autre entier valait 2, alors l'entier courant vaut 9)

    @param _expo
                l'exposant de la puissance
    @throws NullPointerException
                si l'argument est null.
    @throws NegatifExposantException
                si l'argument est strictement negatif.
    */
    public void growToPow(LgInt _expo) {
        //setModified();
        if (!_expo.isZeroOrGt()) {
            throw new NegatifExposantException(_expo.toString());
        }
        LgInt copie_ = new LgInt(this);
        grDigits.clear();
        grDigits.add(1l);
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
    public long remainByBase() {
        return grDigits.last();
    }

    public long remainByBaseWithSign() {
        return signumToLong() * grDigits.last();
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
        affect(divisionEuclidienneGeneralise(_autre).getFirst());
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
        affect(divisionEuclidienneGeneralise(_autre).getSecond());
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
        if (isZero() || _autre.isZero()) {
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
        return;
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
        if (!sameSignum(_autre)) {
            //numbers which have not a same signum
            if (grDigits.eq(_autre.grDigits)) {
                //opposite numbers
                affectZero();
                return;
            }
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
        return;
    }

    private PairEq<LgInt, LgInt> divisionEuclidienneGeneralise(LgInt _autre) {
        if (_autre.isZero()) {
            throw new BadDivisionException();
        }
        PairEq<LgInt, LgInt> quotientReste_ = new PairEq<LgInt, LgInt>();
        LgInt absolu_ = absNb();
        LgInt autreAbsolu_ = _autre.absNb();
        PairEq<Numbers<Long>, Numbers<Long>> quotientRestePositif_;
        quotientRestePositif_ = absolu_.divisionEuclidienne(autreAbsolu_);
        quotientReste_.setFirst(new LgInt(quotientRestePositif_.getFirst(), SIGNE_POSITIF));
        quotientReste_.setSecond(new LgInt(quotientRestePositif_.getSecond(), SIGNE_POSITIF));
        if (isZeroOrGt()) {
            LgInt int_ = quotientReste_.getFirst();
            if (!int_.isZero()) {
                int_.signum = _autre.signum;
            }
            return quotientReste_;
        }
        if (_autre.isZeroOrGt()) {
            if (!quotientReste_.getSecond().isZero()) {
                quotientReste_.setFirst(minus(minusOne(), quotientReste_.getFirst()));
                quotientReste_.setSecond(minus(_autre, quotientReste_.getSecond()));
            } else {
                quotientReste_.getFirst().changeSignum();
            }
            return quotientReste_;
        }
        if (!quotientReste_.getSecond().isZero()) {
            quotientReste_.getFirst().increment();
            quotientReste_.setSecond(minus(_autre.opposNb(), quotientReste_.getSecond()));
        }
        return quotientReste_;
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
        grDigits.add(0l);
        signum = SIGNE_POSITIF;
    }

    /**
    Cette methode ne change pas l'entier courant.<br/>
    L'entier courant est nul <==> il vaut 0.

    @return true <==> il vaut 0. (new LgInt())
    */
    public boolean isZero() {
        return grDigits.first().longValue() == 0L;
    }

    /**
    Cette methode change l'entier courant en le passant a l'oppose. L'entier 0 ne change pas.
    */
    public void changeSignum() {
        if (!isZero()) {
            signum = !signum;
        }
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

    /** */
    public LgInt signum() {
        if (isZero()) {
            return zero();
        }
        if (isZeroOrGt()) {
            return one();
        }
        return minusOne();
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

    private void removeBeginningZeros() {
        //setModified();
        while (grDigits.size() > 1 && grDigits.first() == 0) {
            grDigits.removeAt(CustList.FIRST_INDEX);
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
        affecterSansSigne(_autre);
        signum = _autre.signum;
    }

    public boolean inRange(LgInt _min, LgInt _max) {
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

    /**
    Cette methode ne change pas l'entier courant.<br/>

    @return la chaine de caracteres represantant l'entier comme ecrit de maniere naturelle.
    */
    @Override
    @FromAndToString
    public String toString() {
        String chaine_ = EMPTY_STRING;
        int indice_ = CustList.FIRST_INDEX;
        int powerTen_ = LOG_BASE;
        // puissance10 vaut log10(_base_)
        for (long c : grDigits) {
            if (indice_ > CustList.FIRST_INDEX) {
                if (c == 0) {
                    for (int i = CustList.FIRST_INDEX; i < powerTen_; i++) {
                        chaine_ += String.valueOf(ZERO);
                    }
                } else {
                    int puissance_ = 0;
                    long copie_ = c;
                    while (copie_ > 0) {
                        copie_ /= BASE_NUMER;
                        puissance_++;
                    }
                    int nbZeros_ = powerTen_ - puissance_;
                    for (int i = CustList.FIRST_INDEX; i < nbZeros_; i++) {
                        chaine_ += String.valueOf(ZERO);
                    }
                    chaine_ += c;
                }
            } else {
                chaine_ += c;
            }
            indice_++;
        }
        if (!isZeroOrGt()) {
            return MINUS + chaine_;
        }
        return chaine_;
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
    @Override
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
