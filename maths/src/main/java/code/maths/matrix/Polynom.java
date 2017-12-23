package code.maths.matrix;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.exceptions.NegatifExposantException;
import code.sml.FromAndToString;
import code.util.CustList;
import code.util.EqList;
import code.util.PairEq;
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
            numbers.add(_rate);
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

    @FromAndToString
    public static Polynom newPolynom(String _arg) {
        Polynom v_ = new Polynom();
        for (String s: StringList.splitStrings(_arg, SEPARATOR)) {
            v_.add(new Rate(s));
        }
        return v_;
    }

    /**@return the integer 0*/
    public static Polynom zero() {
        Polynom one_ = new Polynom();
        one_.numbers.add(Rate.zero());
        return one_;
    }

    /**@return the integer 1*/
    public static Polynom one() {
        Polynom one_ = new Polynom();
        one_.numbers.add(Rate.one());
        return one_;
    }

    public static Polynom interpolation(EqList<PairEq<Rate,Rate>> _imgs) {
        int dg_=_imgs.size()-1;
        Matrix inv_ = new Matrix();
        Matrix vectImg_ = new Matrix();
        for(PairEq<Rate,Rate> c:_imgs) {
            Vect pws_ = new Vect();
            for(int e=dg_;e>-1;e--) {
                pws_.add(Rate.powNb(c.getFirst(), new Rate(e)));
            }
            inv_.addLineRef(pws_);
//            mat_inv=mat_inv+puissances;
            Vect i_ = new Vect();
            i_.add(c.getSecond());
            vectImg_.addLineRef(i_);
//            vect_img=vect_img+image;
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
        if (reste_.isZero()) {
            return new Polynom(_b);
        }
        Polynom a_ = _a;
        Polynom b_ = _b;
        while (!reste_.isZero()) {
            a_ = b_;
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
        //setModified();
        if (!_expo.isZeroOrGt()) {
            throw new NegatifExposantException(_expo.toNumberString());
        }
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
        affect(divisionEuclidienne(_autre).getFirst());
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
        affect(divisionEuclidienne(_autre).getSecond());
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
        //setModified();
        numbers.clear();
        numbers.add(Rate.zero());
    }

    /**
    Cette methode change l'entier courant en le passant a l'oppose. L'entier 0 ne change pas.
    */
    public void changeSignum() {
        for (Rate r: numbers) {
            r.changeSignum();
        }
    }

    public static boolean eq(Polynom _tx1,Polynom _tx2) {
        if (_tx1 == null) {
            return _tx2 == null;
        }
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

    public Rate get(int _index) {
        return numbers.get(_index);
    }

    public PairEq<PairEq<Polynom,Polynom>,PairEq<Polynom,Polynom>> idBezoutPgcdPpcm(Polynom _b) {
        PairEq<PairEq<Polynom,Polynom>,PairEq<Polynom,Polynom> > coeffs_;
        coeffs_ = new PairEq<PairEq<Polynom,Polynom>,PairEq<Polynom,Polynom> >();
        coeffs_.setFirst(new PairEq<Polynom,Polynom>(new Polynom(),new Polynom()));
        coeffs_.setSecond(new PairEq<Polynom,Polynom>(new Polynom(),new Polynom()));
        if (_b.isZero()) {
            return coeffs_;
        }
        EqList<Polynom> quots_ = new EqList<Polynom>();
        PairEq<Polynom,Polynom> qrinit_;
        if(dg()>_b.dg()) {
            qrinit_=divisionEuclidienne(_b);
        } else {
            qrinit_=_b.divisionEuclidienne(this);
        }
        Polynom rem_=qrinit_.getSecond();
        if(rem_.isZero()) {
            if(dg()>_b.dg()) {
                coeffs_.getFirst().setFirst(new Polynom());
                coeffs_.getFirst().setSecond(new Polynom(Rate.one()));
                coeffs_.getSecond().setFirst(new Polynom(_b));
                coeffs_.getSecond().setSecond(new Polynom(this));
            } else {
                coeffs_.getFirst().setFirst(new Polynom(Rate.one()));
                coeffs_.getFirst().setSecond(new Polynom());
                coeffs_.getSecond().setFirst(new Polynom(this));
                coeffs_.getSecond().setSecond(new Polynom(_b));
            }
            return coeffs_;
        }
        Polynom at_= new Polynom(this);
        Polynom bt_=_b;
        quots_.add(0, qrinit_.getFirst());
        while(!rem_.isZero()) {
            at_=bt_;
            bt_=rem_;
            PairEq<Polynom,Polynom> qr_=at_.divisionEuclidienne(bt_);
            quots_.add(0, qr_.getFirst());
            rem_=qr_.getSecond();
        }
        coeffs_.getFirst().setFirst(new Polynom(Rate.one()));
        coeffs_.getFirst().setSecond(new Polynom(quots_.get(1)));
        for(int i=2;i<quots_.size();i++) {
            if(i%2==0) {
                coeffs_.getFirst().getFirst().addPol(quots_.get(i).multiplyPolynom(coeffs_.getFirst().getSecond()));
//                coeffs.getFirst().first=coeffs.getFirst().first+quotients.get(i)*coeffs.getFirst().second;
            } else {
                coeffs_.getFirst().getSecond().addPol(quots_.get(i).multiplyPolynom(coeffs_.getFirst().getFirst()));
//                coeffs.first.second=coeffs.first.second+quotients[i]*coeffs.first.first;
            }
        }
        coeffs_.getFirst().setSecond(coeffs_.getFirst().getSecond().minusPolynom());
//        coeffs.first.second=-coeffs.first.second;
        if(quots_.size()%2==1) {
            Polynom tmp_=coeffs_.getFirst().getFirst();
            coeffs_.getFirst().setFirst(coeffs_.getFirst().getSecond());
            coeffs_.getFirst().setSecond(tmp_);
        }
        coeffs_.getSecond().setFirst(bt_);
        coeffs_.getSecond().setSecond(divisionEuclidienne(bt_).getFirst().multiplyPolynom(_b));
        return coeffs_;
    }
//    QString Polynome::chaine_racines()const
//    {
//        QString retour="[";
//        QList<QPair<Taux,int> > racs=racines();
//        typedef QPair<Taux,int> Couple_racine_multiplicite;
//        foreach(Couple_racine_multiplicite racine,racs)
//        {
//            retour+="("+racine.first.chaine()+","+QString::number(racine.second)+")";
//        }
//        retour+="]";
//        return retour;
//    }
    public EqList<Polynom> factor() {
        EqList<RootPol> roots_=racines();
        EqList<Polynom> polynoms_ = new EqList<Polynom>();
        Polynom copy_= new Polynom(this);
        Polynom one_= new Polynom(Rate.one());
        for(RootPol r : roots_) {
            Polynom copyOne_=new Polynom(one_);
            copyOne_.add(r.getValue().opposNb());
            for(int i=0;i<r.getDegree();i++) {
                polynoms_.add(copyOne_);
                copy_=copy_.divisionEuclidienne(copyOne_).getFirst();
            }
        }
        polynoms_.add(copy_);
        return polynoms_;
    }
    public EqList<RootPol> racines() {
        if(numbers.last().isZero()) {
            Polynom copy_ = new Polynom();
            copy_.numbers.removeAt(0);
            boolean written_=false;
            int multZero_=0;
            //int nb_pas=0;
            for(long i=dg();i>-1;i--) {
                if(!numbers.get((int) i).isZero()) {
                    written_=true;
                } else if(!written_) {
                    multZero_++;
                }
                if(written_) {
                    copy_.numbers.add(0, get((int) i));
                    //nb_pas++;
                }
            }
            EqList<RootPol> r_ = new EqList<RootPol>();
            r_.add(new RootPol(Rate.zero(),multZero_));
            r_.addAllElts(copy_.nonZeroRoots());
            return r_;
        }
        return nonZeroRoots();
    }

    public EqList<RootPol> nonZeroRoots() {
        EqList<RootPol> r_ = new EqList<RootPol>();
        if (isZero()) {
            return r_;
        }
//        Rate mainRate_=numbers.first();
//        LgInt ppcmDenom_=mainRate_.getDenominatorCopy();
        long deg_=dg();
//        for(int i=1;i<=deg_;i++) {
//            ppcmDenom_=LgInt.ppcm(ppcmDenom_, numbers.get(i).getDenominatorCopy());
//        }
        LgInt ppcmDenom_ = Rate.getPpcm(numbers, (int) deg_);
        Polynom polEnt_ = new Polynom();
        for(Rate r:numbers) {
            polEnt_.add(Rate.multiply(r, new Rate(ppcmDenom_)));
        }
//        LgInt mainNum_=polEnt_.get(0).getNumerator();
//        LgInt cstRate_=polEnt_.get((int) deg_).getNumerator();
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
//                    candidat=-candidat;
                    RootPol rLoc_=new RootPol(cand_,1);
                    if(!r_.containsObj(rLoc_)) {
                        mult_++;
                        r_.add(rLoc_);
                        if(mult_==deg_) {
                            return r_;
                        }
                    }
                }
                if(Rate.eq(image_,imageTwo_.opposNb())) {
                    RootPol rLoc_=new RootPol(cand_,1);
                    if(!r_.containsObj(rLoc_)) {
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
//                        rac[j].second++;
                        if(mult_==deg_) {
                            return r_;
                        }
                    }
                }
            }
        }
        return r_;
    }

    public Rate image(Rate _x) {
        Rate y_ = Rate.zero();
        long dg_=dg();
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
        Rate tmp_ = Rate.zero();
        line_.add(Rate.one());
        int nbLines_=_m.nbLines();
        for(int i=1;i<nbLines_;i++) {
            line_.add(Rate.zero());
        }
        id_.addLineRef(line_);
        for(int i=1;i<nbLines_;i++) {
            tmp_=line_.get(i - 1);
            line_.set(i - 1, line_.get(i));
            line_.set(i, tmp_);
            id_.addLineRef(line_);
        }
        Matrix m_ = new Matrix();
        for(int i=0;i<nbLines_;i++) {
            line_=new Vect();
            for(int j=0;j<nbLines_;j++) {
                line_.add(Rate.zero());
            }
            m_.addLineRef(line_);
        }
        for(int i=0;i<dg_;i++) {
            m_ = m_.addMatrix(id_.multMatrix(get(i)));
            m_ = m_.multMatrix(_m);
//            image=image+(*this)[i]*I;
//            image=image*antecedent;
        }
        m_ = m_.addMatrix(id_.multMatrix(get((int) dg_)));
//        image=image+(*this)[degre]*I;
        return m_;
    }

    public Polynom comp(Polynom _o) {
        Polynom p_ = new Polynom();
        long dg_=dg();
        for(int i=0;i<=dg_;i++) {
            Polynom cst_ = new Polynom();
            cst_.add(get(i));
            p_.addPol(cst_);
            if(i<dg_) {
                p_ = p_.multiplyPolynom(_o);
            }
        }
        return p_;
    }

    public PairEq<Polynom,Polynom> divisionEuclidienne(Polynom _div) {
        if (_div.isZero()) {
            PairEq<Polynom,Polynom> qr_ = new PairEq<Polynom,Polynom>();
            qr_.setFirst(new Polynom());
            qr_.setSecond(new Polynom());
            return qr_;
        }
        Polynom dividende_= new Polynom(this);
        Polynom quot_ = new Polynom();
        long degBef_;
        long degAfter_;
        long degDiv_=_div.dg();
        while(dividende_.dg()>=degDiv_) {
            if(dividende_.isZero()) {
                break;
            }
            degBef_=dividende_.dg();
            Rate rate_= Rate.divide(dividende_.get(0), _div.get(0));
            dividende_ = dividende_.minusPolynom(_div.prodMonom(rate_, dividende_.dg()-degDiv_));
            quot_.add(rate_);
            degAfter_=dividende_.dg();
            long diffDg_=degBef_-degAfter_-1;
            for(int i=0;i<diffDg_;i++) {
                quot_.add(Rate.zero());
            }
        }
        PairEq<Polynom,Polynom> qr_ = new PairEq<Polynom,Polynom>();
        qr_.setFirst(quot_);
        qr_.setSecond(dividende_);
        return qr_;
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
        return divisionEuclidienne(_o).getFirst();
    }

    public Polynom remainPolynom(Polynom _o) {
        return divisionEuclidienne(_o).getSecond();
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
        long degSum_ = 0;
        Polynom pol_ = new Polynom();
        if(isZero()) {
            if(_o.isZero()) {
                return new Polynom();
            }
            return new Polynom(_o);
        }
        if(_o.isZero()) {
            return new Polynom(this);
        }
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
        //m_ est le minimum entre deg_1 et deg_2
        pol_=new Polynom(Rate.zero(),(int) (degSum_+1));
        for(int i=0;i<=m_;i++) {
            if(i>degSum_) {
                break;
            }
            pol_.set((int) (degSum_-i), Rate.plus(get((int) (degOne_-i)), _o.get((int) (degTwo_-i))));
//            pol[deg_somme_int-i]=(*this)[deg_1-i]+autre[deg_2-i];
        }
        if(degOne_>m_) {
            for(long i=m_+1;i<=degSum_;i++) {
//                pol[deg_somme_int-i]=(*this)[deg_1-i];
                pol_.setNb((int) (degSum_-i), get((int) (degOne_-i)));
            }
        } else {
            for(long i=m_+1;i<=degSum_;i++) {
                pol_.setNb((int) (degSum_-i), _o.get((int) (degTwo_-i)));
//                pol[deg_somme_int-i]=autre[deg_2-i];
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
            pol_.add(Rate.minus(get(i), _rate));
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
    @FromAndToString
    public String toString() {
        return display();
    }

    @FromAndToString
    @Override
    public String display() {
        if (numbers.isEmpty()) {
            return "";
        }
        StringBuilder return_ = new StringBuilder(numbers.first().toNumberString());
        int size_ = numbers.size();
        for (int i=1;i<size_;i++) {
            return_.append(SEPARATOR);
            return_.append(numbers.get(i).toNumberString());
        }
        return return_.toString();
    }
}
