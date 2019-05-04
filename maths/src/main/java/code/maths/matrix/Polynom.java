package code.maths.matrix;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class Polynom implements Equallable<Polynom>, Displayable {

    private static final String SEPARATOR = " ";
    private EqList<Rate> numbers = new EqList<Rate>();

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

    public void affect(Polynom _o) {
        numbers.clear();
        for (Rate n: _o.numbers) {
            numbers.add(new Rate(n));
        }
    }

    
    public static Polynom newPolynom(String _arg) {
        Polynom v_ = new Polynom();
        for (String s: StringList.splitStrings(_arg, SEPARATOR)) {
            v_.add(new Rate(s));
        }
        return v_;
    }

    /**@return the integer 0*/
    public static Polynom zero() {
        return new Polynom();
    }

    /**@return the integer 1*/
    public static Polynom one() {
        Polynom one_ = new Polynom();
        one_.add(Rate.one());
        return one_;
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
        return numbers.eq(_o.numbers);
    }

    @Override
    public boolean eq(Polynom _o) {
        return numbers.eq(_o.numbers);
    }

    public int size() {
        return numbers.size();
    }

    private static QuotModPolynom polyLongDiv(Polynom _n, Polynom _d) {
        Polynom rem_ = new Polynom(_n);
        Polynom quot_ = new Polynom();
        while (rem_.dg() >= _d.dg()) {
            int diff_ = (int) (rem_.dg() - _d.dg());
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
        if (_a.isZero()) {
            return new IdBezoutPol(one(),one(),_b,zero());
        }
        if (_b.isZero()) {
            return new IdBezoutPol(one(),one(),_a,zero());
        }
        Polynom r0_ = _a;
        Polynom r1_ = _b;
        Polynom u0_ = one();
        Polynom u1_ = zero();
        Polynom v0_ = zero();
        Polynom v1_ = one();
        while (true) {
            QuotModPolynom qr_ = r0_.divisionEuclidienne(r1_);
            Polynom q_ = qr_.getQuot();
            Polynom r2_ = qr_.getMod();
            Polynom u2_ = u0_.minusPolynom(q_.multiplyPolynom(u1_));
            Polynom v2_ = v0_.minusPolynom(q_.multiplyPolynom(v1_));
            if (r2_.isZero()) {
                break;
            }
            u0_ = u1_;
            v0_ = v1_;
            r0_ = r1_;
            u1_ = u2_;
            v1_ = v2_;
            r1_ = r2_;
        }
        return new IdBezoutPol(u1_,v1_,r1_,_a.dividePolynom(r1_).multiplyPolynom(_b));
    }

    public EqList<Polynom> factor() {
        CustList<RootPol> roots_=racines();
        EqList<Polynom> polynoms_ = new EqList<Polynom>();
        Polynom copy_= new Polynom(this);
        Polynom one_= new Polynom(Rate.one());
        for(RootPol r : roots_) {
            Polynom copyOne_=new Polynom(one_);
            copyOne_.add(r.getValue().opposNb());
            for(int i=0;i<r.getDegree();i++) {
                polynoms_.add(copyOne_);
                copy_=copy_.divisionEuclidienne(copyOne_).getQuot();
            }
        }
        if (copy_.dg() > 0) {
            polynoms_.add(copy_);
        }
        return polynoms_;
    }
    public CustList<RootPol> racines() {
        if(numbers.last().isZero()) {
            Polynom copy_ = new Polynom();
            copy_.numbers.removeAt(0);
            boolean written_=false;
            int multZero_=0;
            for(long i=dg();i>-1;i--) {
                if(!numbers.get((int) i).isZero()) {
                    written_=true;
                } else if(!written_) {
                    multZero_++;
                }
                if(written_) {
                    copy_.numbers.add(0, get((int) i));
                }
            }
            CustList<RootPol> r_ = new CustList<RootPol>();
            r_.add(new RootPol(Rate.zero(),multZero_));
            if (copy_.numbers.isEmpty()) {
                r_.clear();
                copy_.numbers.add(Rate.zero());
            }
            r_.addAllElts(copy_.nonZeroRoots());
            return r_;
        }
        return nonZeroRoots();
    }

    private CustList<RootPol> nonZeroRoots() {
        CustList<RootPol> r_ = new CustList<RootPol>();
        if (isZero()) {
            return r_;
        }
        long deg_=dg();
        LgInt ppcmDenom_ = Rate.getPpcmDens(numbers, (int) deg_);
        Polynom polEnt_ = new Polynom();
        for(Rate r:numbers) {
            polEnt_.add(Rate.multiply(r, new Rate(ppcmDenom_)));
        }
        EqList<LgInt> mainDivs_=polEnt_.get(0).getDividersNumerator();
        EqList<LgInt> cstDivs_=polEnt_.get((int) deg_).getDividersNumerator();
        Polynom pairPol_ = new Polynom();
        Polynom impairPol_ = new Polynom();
        if(deg_%2==0) {
            for(int i=0;i<=deg_;i++) {
                if(i%2==0) {
                    pairPol_.add(polEnt_.get(i));
                } else {
                    pairPol_.add(Rate.zero());
                }
            }
            for(int i=1;i<=deg_;i++) {
                if(i%2==1) {
                    impairPol_.add(polEnt_.get(i));
                } else {
                    impairPol_.add(Rate.zero());
                }
            }
        } else {
            for(int i=0;i<=deg_;i++) {
                if(i%2==0) {
                    impairPol_.add(polEnt_.get(i));
                } else {
                    impairPol_.add(Rate.zero());
                }
            }
            for(int i=1;i<=deg_;i++) {
                if(i%2==1) {
                    pairPol_.add(polEnt_.get(i));
                } else {
                    pairPol_.add(Rate.zero());
                }
            }
        }
        int mult_=0;
        for(LgInt m:mainDivs_) {
            for(LgInt c:cstDivs_) {
                Rate cand_=new Rate(c,m);
                Rate image_=pairPol_.image(cand_);
                Rate imageTwo_=impairPol_.image(cand_);
                if(Rate.eq(image_,imageTwo_)) {
                    cand_.changeSignum();
                    RootPol rLoc_=new RootPol(cand_,1);
                    if(!containsRoot(r_,cand_)) {
                        mult_++;
                        r_.add(rLoc_);
                        if(mult_==deg_) {
                            return r_;
                        }
                    }
                }
                if(Rate.eq(image_,imageTwo_.opposNb())) {
                    cand_=new Rate(c,m);
                    RootPol rLoc_=new RootPol(cand_,1);
                    if(!containsRoot(r_,cand_)) {
                        mult_++;
                        r_.add(rLoc_);
                        if(mult_==deg_) {
                            return r_;
                        }
                    }
                }
            }
        }
        for(int i=1;i<deg_;i++) {
            pairPol_=pairPol_.derivee();
            impairPol_=impairPol_.derivee();
            for(int j=0;j<r_.size();j++) {
                if(r_.get(j).getDegree()==i) {
                    Rate image_=pairPol_.image(r_.get(j).getValue());
                    Rate imageTwo_=impairPol_.image(r_.get(j).getValue());
                    if(Rate.eq(image_,imageTwo_.opposNb())) {
                        mult_++;
                        r_.get(j).setDegree(r_.get(j).getDegree() + 1);
                        if(mult_==deg_) {
                            return r_;
                        }
                    }
                }
            }
        }
        return r_;
    }

    private static boolean containsRoot(CustList<RootPol> _roots, Rate _value) {
        for (RootPol r: _roots) {
            if (r.getValue().eq(_value)) {
                return true;
            }
        }
        return false;
    }
    public Rate image(Rate _x) {
        Rate y_ = Rate.zero();
        long dg_=dg();
        if (dg_ < 0) {
            return y_;
        }
        for(int i=0;i<dg_;i++) {
            y_.addNb(get(i));
            y_.multiplyBy(_x);
        }
        y_.addNb(get((int) dg_));
        return y_;
    }

    public Matrix image(Matrix _m) {
        long dg_=dg();
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
        if (dg_ < 0) {
            return m_;
        }
        for(int i=0;i<dg_;i++) {
            m_ = m_.addMatrix(id_.multMatrix(get(i)));
            m_ = m_.multMatrix(_m);
        }
        m_ = m_.addMatrix(id_.multMatrix(get((int) dg_)));
        return m_;
    }

    public Polynom comp(Polynom _o) {
        Polynom p_ = new Polynom();
        long dg_=dg();
        for(int i=0;i<=dg_;i++) {
            Polynom cst_ = new Polynom();
            cst_.add(get(i));
            p_.addPol(cst_.multiplyPolynom(_o.pow(dg_-i)));
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
        Polynom pol_ = new Polynom();
        long deg_=dg();
        for(int i=0;i<deg_;i++) {
            pol_.add(Rate.multiply(get(i), new Rate(deg_-i)));
        }
        return pol_;
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
        long degOne_=dg();
        long degTwo_=_o.dg();
        long degProd_=degOne_+degTwo_;
        Polynom pol_ = new Polynom(Rate.zero(),(int) (degProd_+1));
        for(int i=0;i<=degTwo_;i++) {
            if(!_o.get(i).isZero()) {
                for(int j=0;j<=degOne_;j++) {
                    pol_.get(i + j).addNb(Rate.multiply(_o.get(i), get(j)));
                }
            }
        }
        return pol_;
    }

    public Polynom minusPolynom(Polynom _p) {
        return addPolynom(_p.minusPolynom());
    }

    public Polynom minusPolynom() {
        Polynom pol_ = new Polynom();
        int len_ = size();
        for(int i=0;i<len_;i++) {
            pol_.add(get(i).opposNb());
        }
        return pol_;
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
        long degSum_;
        if(dg()==_o.dg()||dg()>_o.dg()) {
            degSum_=dg();
            if(dg()==_o.dg()) {
                int len_ = size();
                for(int i=0;i<len_;i++) {
                    if(Rate.eq(get(i), _o.get(i).opposNb())) {
                        degSum_--;
                    } else {
                        break;
                    }
                }
            }
        } else {
            degSum_=_o.dg();
        }
        if(degSum_<0) {
            return new Polynom();
        }
        long degOne_=dg();
        long degTwo_=_o.dg();
        long m_ = Math.min(degOne_, degTwo_);
        //m_ est le minimum entre degOne_ et degTwo_
        Polynom pol_ = new Polynom(Rate.zero(), (int) (degSum_ + 1));
        for(int i=0;i<=m_;i++) {
            if(i>degSum_) {
                break;
            }
            pol_.set((int) (degSum_-i), Rate.plus(get((int) (degOne_-i)), _o.get((int) (degTwo_-i))));
        }
        if(degOne_>m_) {
            for(long i=m_+1;i<=degSum_;i++) {
                pol_.setNb((int) (degSum_-i), get((int) (degOne_-i)));
            }
        } else {
            for(long i=m_+1;i<=degSum_;i++) {
                pol_.setNb((int) (degSum_-i), _o.get((int) (degTwo_-i)));
            }
        }
        return pol_;
    }

    public void setNb(int _index, Rate _nb) {
        set(_index, new Rate(_nb));
    }

    public void set(int _index, Rate _nb) {
        numbers.set(_index, _nb);
    }

    public Polynom prodMonom(Rate _rate, long _deg) {
        Polynom pol_ = new Polynom();
        long deg_ = dg();
        for(int i=0;i<=deg_;i++) {
            pol_.add(Rate.multiply(get(i), _rate));
        }
        for(int i=0;i<_deg;i++) {
            pol_.add(Rate.zero());
        }
        return pol_;
    }

    public void add(Rate _nb) {
        if(size()==CustList.ONE_ELEMENT&&numbers.first().isZero()) {
            numbers.removeAt(CustList.FIRST_INDEX);
        }
        numbers.add(_nb);
    }

    public long dg(){
        if(size()==CustList.ONE_ELEMENT&&numbers.first().isZero()) {
            return -1;
        }
        return numbers.size() - 1;
    }

    public boolean isZero() {
        return numbers.first().isZero();
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
