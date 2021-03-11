package code.maths.matrix;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class Polynom implements Displayable {

    private static final String SEPARATOR = " ";
    private CustList<Rate> numbers = new CustList<Rate>();

    public Polynom() {
        this(Rate.zero());
    }

    public Polynom(Rate _rate) {
        this(_rate, 1);
    }

    public Polynom(Rate _rate, int _repeat) {
        for(int i=0;i<_repeat;i++) {
            numbers.add(new Rate(_rate));
        }
    }

    public Polynom(Polynom _p) {
        affect(_p);
    }

    public Polynom(CustList<Rate> _ls) {
        numbers = new CustList<Rate>(_ls);
        removeBeginZeros(numbers);
    }

    public void affect(Polynom _o) {
        numbers.clear();
        for (Rate n: _o.numbers) {
            numbers.add(new Rate(n));
        }
    }

    
    public static Polynom newPolynom(String _arg) {
        CustList<Rate> rates_ = new CustList<Rate>();
        for (String s: StringUtil.splitStrings(_arg, SEPARATOR)) {
            rates_.add(new Rate(s));
        }
        return new Polynom(rates_);
    }

    /**@return the integer 0*/
    public static Polynom zero() {
        return new Polynom();
    }

    /**@return the integer 1*/
    public static Polynom one() {
        CustList<Rate> rates_ = new CustList<Rate>(new CollCapacity(1));
        rates_.add(Rate.one());
        return new Polynom(rates_);
    }

    public static Polynom interpolation(CustList<RateImage> _imgs) {
        if (_imgs.isEmpty()) {
            return zero();
        }
        int dg_=_imgs.size()-1;
        Matrix inv_ = new Matrix();
        Matrix vectImg_ = new Matrix();
        for(RateImage c:_imgs) {
            Vect pws_ = new Vect();
            for(int e=dg_;e>-1;e--) {
                pws_.add(Rate.powNb(c.getRate(), new Rate(e)));
            }
            inv_.addLineRef(pws_);
            Vect i_ = new Vect();
            i_.add(c.getValue());
            vectImg_.addLineRef(i_);
        }
        Matrix nearlyInv_=inv_.inv();
        Matrix solut_=nearlyInv_.multMatrix(vectImg_);
        Polynom interPol_ = new Polynom(Rate.zero(),dg_+1);
        for(int i=0;i<=dg_;i++) {
            interPol_.set(i, solut_.cell(i,0));
        }
        return interPol_;
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
    public static Polynom ppcm(Polynom _a, Polynom _b) {
        if (_a.isZero() && _b.isZero()) {
            return zero();
        }
        Polynom res_ = _a.dividePolynom(pgcd(_a, _b));
        res_.multiplyBy(_b);
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
    public static Polynom pgcd(Polynom _a, Polynom _b) {
        if (_b.isZero()) {
            return new Polynom(_a);
        }
        Polynom reste_ = _a.remainPolynom(_b);
        Polynom b_ = _b;
        while (!reste_.isZero()) {
            Polynom a_ = b_;
            b_ = reste_;
            reste_ = a_.remainPolynom(b_);
        }
        return b_;
    }

    public static Polynom powNb(Polynom _celuiCi, LgInt _autre) {
        Polynom puis_ = new Polynom(_celuiCi);
        puis_.growToPow(_autre);
        return puis_;
    }

    public void growToPow(LgInt _expo) {
        Polynom copie_ = new Polynom(this);
        numbers.clear();
        numbers.add(Rate.one());
        LgInt e = LgInt.zero();
        while (LgInt.strLower(e, _expo)) {
            multiplyBy(copie_);
            e.increment();
        }
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
    public void divideBy(Polynom _autre) {
        affect(divisionEuclidienne(_autre).getQuot());
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
    public void remainBy(Polynom _autre) {
        affect(divisionEuclidienne(_autre).getMod());
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
    public void multiplyBy(Polynom _autre) {
        if (isZero() || _autre.isZero()) {
            affectZero();
            return;
        }
        affect(multiplyPolynom(_autre));
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
    public void removeNb(Polynom _autre) {
        addPol(_autre.minusPolynom());
    }

    /**
    Cette methode change l'entier courant en l'annulant.
    */
    public void affectZero() {
        numbers.clear();
        numbers.add(Rate.zero());
    }

    public static boolean eq(Polynom _tx1,Polynom _tx2) {
        return _tx1.isEqualTo(_tx2);
    }

    public boolean isEqualTo(Polynom _o) {
        return Rate.eq(numbers,_o.numbers);
    }

    public boolean eq(Polynom _o) {
        return Rate.eq(numbers,_o.numbers);
    }

    public int size() {
        return numbers.size();
    }

    private static QuotModPolynom polyLongDiv(Polynom _n, Polynom _d) {
        Polynom rem_ = new Polynom(_n);
        Polynom quot_ = new Polynom();
        while (rem_.size() >= _d.size()) {
            if (rem_.isZero()) {
                break;
            }
            int diff_ = rem_.size() - _d.size();
            Rate r_ = Rate.divide(rem_.get(0),_d.get(0));
            Polynom mon_ = one().prodMonom(r_,diff_);
            quot_ = quot_.addPolynom(mon_);
            rem_ = rem_.minusPolynom(mon_.multiplyPolynom(_d));
        }
        return new QuotModPolynom(quot_, rem_);
    }

    public Rate get(int _index) {
        return numbers.get(_index);
    }

    public static IdBezoutPol idBezoutPgcdPpcm(Polynom _a,Polynom _b) {
        Polynom r0_ = _a;
        Polynom r1_ = _b;
        Polynom u0_ = one();
        Polynom u1_ = zero();
        Polynom v0_ = zero();
        Polynom v1_ = one();
        while (!r1_.isZero()) {
            QuotModPolynom qr_ = r0_.divisionEuclidienne(r1_);
            Polynom q_ = qr_.getQuot();
            Polynom r2_ = qr_.getMod();
            Polynom u2_ = u0_.minusPolynom(q_.multiplyPolynom(u1_));
            Polynom v2_ = v0_.minusPolynom(q_.multiplyPolynom(v1_));
            u0_ = u1_;
            v0_ = v1_;
            r0_ = r1_;
            u1_ = u2_;
            v1_ = v2_;
            r1_ = r2_;
        }
        u0_ = possibleSetZeroPol(_a, u0_);
        v0_ = possibleSetZeroPol(_b, v0_);
        return buildResult(_a, _b, r0_, u0_, v0_);
    }

    private static IdBezoutPol buildResult(Polynom _a, Polynom _b, Polynom _r0, Polynom _u0, Polynom _v0) {
        if (_r0.isZero()) {
            return new IdBezoutPol(_u0, _v0, _r0,zero());
        }
        return new IdBezoutPol(_u0, _v0, _r0, _a.dividePolynom(_r0).multiplyPolynom(_b));
    }

    private static Polynom possibleSetZeroPol(Polynom _inPol, Polynom _foundPol) {
        if (_inPol.isZero()) {
            return one();
        }
        return _foundPol;
    }

    public CustList<Polynom> factor() {
        CustList<RootPol> roots_=racines();
        CustList<Polynom> polynoms_ = new CustList<Polynom>();
        Polynom copy_= new Polynom(this);
        for(RootPol r : roots_) {
            CustList<Rate> rates_ = new CustList<Rate>(new CollCapacity(2));
            rates_.add(Rate.one());
            rates_.add(r.getValue().opposNb());
            Polynom copyOne_=new Polynom(rates_);
            for(int i=0;i<r.getDegree();i++) {
                polynoms_.add(copyOne_);
                copy_=copy_.divisionEuclidienne(copyOne_).getQuot();
            }
        }
        if (!copy_.isZero() && copy_.notNullDg() > 0) {
            polynoms_.add(copy_);
        }
        return polynoms_;
    }
    public CustList<RootPol> racines() {
        if(!isZero()&&numbers.last().isZero()) {
            return withZeroRoots();
        }
        return nonZeroRoots();
    }

    private CustList<RootPol> withZeroRoots() {
        Polynom copy_ = new Polynom();
        copy_.numbers.remove(0);
        boolean written_=false;
        int multZero_=0;
        for(long i=notNullDg();i>-1;i--) {
            if(!numbers.get((int) i).isZero()) {
                written_=true;
            } else {
                if (!written_) {
                    multZero_++;
                }
            }
            if(written_) {
                copy_.numbers.add(0, get((int) i));
            }
        }
        CustList<RootPol> r_ = new CustList<RootPol>();
        r_.add(new RootPol(Rate.zero(),multZero_));
        r_.addAllElts(copy_.nonZeroRoots());
        return r_;
    }

    private CustList<RootPol> nonZeroRoots() {
        CustList<RootPol> r_ = new CustList<RootPol>();
        if (isZero()) {
            return r_;
        }
        long deg_=notNullDg();
        LgInt ppcmDenom_ = Rate.getPpcmDens(numbers);
        CustList<Rate> rates_ = new CustList<Rate>();
        for(Rate r:numbers) {
            rates_.add(Rate.multiply(r, new Rate(ppcmDenom_)));
        }
        Polynom polEnt_ = new Polynom(rates_);
        CustList<LgInt> mainDivs_=polEnt_.get(0).getDividersNumerator();
        CustList<LgInt> cstDivs_=polEnt_.numbers.last().getDividersNumerator();
        CustList<Rate> ratesPair_ = new CustList<Rate>();
        CustList<Rate> ratesImpair_ = new CustList<Rate>();
        if(deg_%2==0) {
            feedPol(deg_, polEnt_, ratesPair_, 0);
            feedPol(deg_, polEnt_, ratesImpair_, 1);
        } else {
            feedPol(deg_, polEnt_, ratesImpair_, 0);
            feedPol(deg_, polEnt_, ratesPair_, 1);
        }
        Polynom pairPol_ = new Polynom(ratesPair_);
        Polynom impairPol_ = new Polynom(ratesImpair_);
        return loop(r_, deg_, mainDivs_, cstDivs_, pairPol_, impairPol_);
    }

    private static void feedPol(long _deg, Polynom _polEnt, CustList<Rate> _pol, int _start) {
        for (int i = _start; i <= _deg; i++) {
            if (i % 2 == _start) {
                _pol.add(_polEnt.get(i));
            } else {
                _pol.add(Rate.zero());
            }
        }
    }

    private static CustList<RootPol> loop(CustList<RootPol> _r, long _deg, CustList<LgInt> _mainDivs, CustList<LgInt> _cstDivs, Polynom _pairPol, Polynom _impairPol) {
        ProcRoot pr_ = new ProcRoot();
        for(LgInt m: _mainDivs) {
            for(LgInt c: _cstDivs) {
                if (pr_.exit(c,m,_r,_deg, _pairPol,_impairPol)) {
                    return _r;
                }
            }
        }
        return lastLoop(_r, _deg, _pairPol, _impairPol, pr_.getMult());
    }

    private static CustList<RootPol> lastLoop(CustList<RootPol> _r, long _deg, Polynom _pairPol, Polynom _impairPol, int _mult) {
        Polynom pairPol_ = _pairPol;
        Polynom impairPol_ = _impairPol;
        int mult_ = _mult;
        for(int i = 1; i< _deg; i++) {
            pairPol_ = pairPol_.derivee();
            impairPol_ = impairPol_.derivee();
            for(int j = 0; j< _r.size(); j++) {
                if(_r.get(j).getDegree()==i) {
                    Rate value_ = _r.get(j).getValue();
                    Rate image_= pairPol_.image(value_);
                    Rate imageTwo_= impairPol_.image(value_);
                    if(Rate.eq(image_,imageTwo_.opposNb())) {
                        mult_++;
                        _r.get(j).setDegree(_r.get(j).getDegree() + 1);
                        if(mult_ == _deg) {
                            return _r;
                        }
                    }
                }
            }
        }
        return _r;
    }

    public Rate image(Rate _x) {
        Rate y_ = Rate.zero();
        if (isZero()) {
            return y_;
        }
        long dg_=notNullDg();
        for(int i=0;i<dg_;i++) {
            y_.addNb(get(i));
            y_.multiplyBy(_x);
        }
        y_.addNb(get((int) dg_));
        return y_;
    }

    public Matrix image(Matrix _m) {
        Matrix id_ = new Matrix();
        Vect line_ = new Vect();
        line_.add(Rate.one());
        int nbLines_=_m.nbLines();
        for(int i=1;i<nbLines_;i++) {
            line_.add(Rate.zero());
        }
        id_.addLineRef(line_);
        for(int i=1;i<nbLines_;i++) {
            Vect n_ = new Vect(line_);
            n_.swapIndexes(i,i - 1);
            id_.addLineRef(n_);
            line_ = n_;
        }
        Matrix m_ = new Matrix();
        for(int i=0;i<nbLines_;i++) {
            line_=new Vect();
            for(int j=0;j<nbLines_;j++) {
                line_.add(Rate.zero());
            }
            m_.addLineRef(line_);
        }
        if (isZero()) {
            return m_;
        }
        long dg_=notNullDg();
        for(int i=0;i<dg_;i++) {
            m_ = m_.addMatrix(id_.multMatrix(get(i)));
            m_ = m_.multMatrix(_m);
        }
        m_ = m_.addMatrix(id_.multMatrix(get((int) dg_)));
        return m_;
    }

    public FractPol comp(FractPol _o) {
        FractPol p_ = new FractPol(zero());
        if (isZero()) {
            return p_;
        }
        if (_o.isZero()) {
            Rate rate_ = numbers.last();
            if (!rate_.isZero()) {
                p_.addNb(new FractPol(one().prodMonom(rate_,0)));
            }
            return p_;
        }
        long dg_=notNullDg();
        for(int i=0;i<=dg_;i++) {
            Rate rate_ = get(i);
            if (!rate_.isZero()) {
                FractPol pw_ = new FractPol(one().prodMonom(rate_, 0));
                p_.addNb(FractPol.multiply(_o.powNb(new LgInt(dg_-i)),pw_));
            }
        }
        return p_;
    }
    public Polynom comp(Polynom _o) {
        Polynom p_ = new Polynom();
        if (isZero()) {
            return p_;
        }
        if (_o.isZero()) {
            Rate rate_ = numbers.last();
            if (!rate_.isZero()) {
                p_.addPol(one().prodMonom(rate_,0));
            }
            return p_;
        }
        long dg_=notNullDg();
        for(int i=0;i<=dg_;i++) {
            Rate rate_ = get(i);
            if (!rate_.isZero()) {
                p_.addPol(_o.pow(dg_-i).prodMonom(rate_,0));
            }
        }
        return p_;
    }

    public QuotModPolynom divisionEuclidienne(Polynom _div) {
        if (_div.isZero()) {
            return new QuotModPolynom(new Polynom(),new Polynom());
        }
        return polyLongDiv(this,_div);
    }
    public Polynom derivee() {
        if(isZero()) {
            return new Polynom();
        }
        CustList<Rate> rates_ = new CustList<Rate>();
        long deg_=notNullDg();
        for(int i=0;i<deg_;i++) {
            rates_.add(Rate.multiply(get(i), new Rate(deg_-i)));
        }
        return new Polynom(rates_);
    }

    public Polynom dividePolynom(Polynom _o) {
        return divisionEuclidienne(_o).getQuot();
    }

    public Polynom remainPolynom(Polynom _o) {
        return divisionEuclidienne(_o).getMod();
    }

    public Polynom pow(long _dg) {
        Polynom p_ = one();
        for (long i = 0; i < _dg; i++) {
            p_.multiplyBy(this);
        }
        return p_;
    }
    public Polynom multiplyPolynom(Polynom _o) {
        if(isZero()||_o.isZero()) {
            return new Polynom();
        }
        int sizeTwo_=_o.size();
        long dgTwo_ = sizeTwo_ - 1L;
        Polynom pol_ = new Polynom();
        for(int i=0;i<sizeTwo_;i++) {
            Rate rate_ = _o.get(i);
            if(!rate_.isZero()) {
                pol_.addPol(prodMonom(rate_,dgTwo_-i));
            }
        }
        removeBeginZeros(pol_.numbers);
        return pol_;
    }

    public Polynom minusPolynom(Polynom _p) {
        return addPolynom(_p.minusPolynom());
    }

    public Polynom minusPolynom() {
        int len_ = size();
        CustList<Rate> rates_ = new CustList<Rate>(new CollCapacity(len_));
        for(int i=0;i<len_;i++) {
            rates_.add(get(i).opposNb());
        }
        return new Polynom(rates_);
    }

    public void addPol(Polynom _o) {
        Polynom pol_ = addPolynom(_o);
        numbers = pol_.numbers;
    }

    public Polynom addPolynom(Polynom _o) {
        if(isZero()) {
            if(_o.isZero()) {
                return new Polynom();
            }
            return new Polynom(_o);
        }
        if(_o.isZero()) {
            return new Polynom(this);
        }
        return procDefault(_o);
    }

    private Polynom procDefault(Polynom _o) {
        CustList<Rate> numbers_ = new CustList<Rate>();
        CustList<Rate> numbersOne_ = new CustList<Rate>();
        CustList<Rate> numbersTwo_ = new CustList<Rate>();
        int first_ = size();
        int second_ = _o.size();
        int dg_ = Math.max(first_, second_);
        for (int i = 0; i < dg_; i++) {
            numbers_.add(Rate.zero());
        }
        for (int i = 0; i < first_; i++) {
            numbersOne_.add(get(i));
        }
        for (int i = 0; i < second_; i++) {
            numbersTwo_.add(_o.get(i));
        }
        for (int i = first_; i< dg_;i++) {
            numbersOne_.add(0,Rate.zero());
        }
        for (int i = second_; i< dg_;i++) {
            numbersTwo_.add(0,Rate.zero());
        }
        for (int i = 0; i < dg_; i++) {
            numbers_.set(i,Rate.plus(numbersOne_.get(i),numbersTwo_.get(i)));
        }
        return new Polynom(numbers_);
    }

    private static void removeBeginZeros(CustList<Rate> _numbers) {
        while (_numbers.size() > 1 && _numbers.first().isZero()) {
            _numbers.remove(IndexConstants.FIRST_INDEX);
        }
        if (_numbers.isEmpty()) {
            _numbers.add(Rate.zero());
        }
    }

    public void set(int _index, Rate _nb) {
        numbers.set(_index, _nb);
    }

    public Polynom prodMonom(Rate _rate, long _deg) {
        CustList<Rate> rates_ = new CustList<Rate>();
        long deg_ = notNullDg();
        for(int i=0;i<=deg_;i++) {
            rates_.add(Rate.multiply(get(i), _rate));
        }
        for(int i=0;i<_deg;i++) {
            rates_.add(Rate.zero());
        }
        return new Polynom(rates_);
    }

    public long dg(){
        if(size()== IndexConstants.ONE_ELEMENT&&numbers.first().isZero()) {
            return -1L;
        }
        return notNullDg();
    }

    private long notNullDg() {
        return numbers.size() - 1L;
    }

    public boolean isZero() {
        return getNumbers().first().isZero();
    }

    public CustList<Rate> getNumbers() {
        return numbers;
    }
    @Override
    public String display() {
        StringBuilder return_ = new StringBuilder();
        int size_ = numbers.size();
        for (int i=0;i<size_;i++) {
            return_.append(numbers.get(i).toNumberString());
            return_.append(SEPARATOR);
        }
        return return_.toString().trim();
    }
}
