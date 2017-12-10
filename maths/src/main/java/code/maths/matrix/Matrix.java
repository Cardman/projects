package code.maths.matrix;
import code.maths.LgInt;
import code.maths.Rate;
import code.sml.FromAndToString;
import code.util.CustList;
import code.util.EqList;
import code.util.PairEq;
import code.util.PairNumber;
import code.util.StringList;
import code.util.ints.Equallable;

public final class Matrix implements Equallable<Matrix> {

    private static final String SEPARATOR = ";";
    private EqList<Vect> lines = new EqList<Vect>();

    public Matrix() {
    }

    public Matrix(Matrix _m) {
        affect(_m);
    }

    public void affect(Matrix _m) {
        lines.clear();
        for (Vect l: _m.lines) {
            lines.add(new Vect(l));
        }
    }

    @FromAndToString
    public static Matrix newMatrix(String _arg) {
        Matrix v_ = new Matrix();
        for (String s: StringList.splitStrings(_arg, SEPARATOR)) {
            v_.addLineRef(Vect.newVect(s));
        }
        return v_;
    }

    public int nbLines() {
        return lines.size();
    }

    public int nbCols() {
        if (lines.isEmpty()) {
            return CustList.SIZE_EMPTY;
        }
        return lines.first().size();
    }

    public Rate cell(int _i, int _j) {
        return lines.get(_i).get(_j);
    }

    public Matrix passMat() {
        Matrix mat_ = new Matrix();
        EqList<PairEq<Rate,PairNumber<Integer,Integer>>> ownValues_=diagTrig().getRates();
        for(PairEq<Rate,PairNumber<Integer,Integer>> t: ownValues_) {
            Matrix ownVects_=ownVects(t.getFirst());
            for(Vect l: ownVects_.lines) {
                mat_.addLineRef(l);
            }
        }
        return mat_;
    }

    public Trigonal diagTrig() {
        EqList<RootPol> ownValues_=polCaract().racines();
        EqList<PairEq<Rate,PairNumber<Integer,Integer>>> ownValuesSpaces_;
        ownValuesSpaces_ = new EqList<PairEq<Rate,PairNumber<Integer,Integer>>>();
        int sum_=0;
        int nbLines_=lines.size();
        Matrix id_ = new Matrix();
        Vect line_ = new Vect();
        Rate tmp_ = Rate.zero();
        line_.add(Rate.one());
        for(int i=1;i<nbLines_;i++) {
            line_.add(Rate.zero());
        }
        id_.addLineRef(line_);
        for(int i=1;i<nbLines_;i++) {
            tmp_=line_.get(i-1);
            line_.set(i-1, line_.get(i));
            line_.set(i, tmp_);
            id_.addLineRef(line_);
        }
        for(RootPol c:ownValues_) {
            sum_+=c.getDegree();
            PairEq<Rate,PairNumber<Integer,Integer> > t_;
            t_ = new PairEq<Rate,PairNumber<Integer,Integer> >();
            t_.setSecond(new PairNumber<Integer,Integer>());
            t_.setFirst(c.getValue());
            t_.getSecond().setFirst(c.getDegree());
            t_.getSecond().setSecond((int)(nbLines_-minusMatrix(id_.multMatrix(c.getValue())).quickRank()));
            ownValuesSpaces_.add(t_);
        }
//        Pair<CustList<PairEq<Rate,PairNumber<Integer,Integer>>>,Diagonal> infos_;
//        infos_ = new Pair<CustList<PairEq<Rate,PairNumber<Integer,Integer>>>,Diagonal>();
//        infos_.setFirst(ownValuesSpaces_);
        if(sum_<lines.size()) {
//            infos_.setSecond(Diagonal.UN_TRIGO);
            return new Trigonal(ownValuesSpaces_, Diagonal.UN_TRIGO);
        }
        boolean diag_=true;
        for(PairEq<Rate,PairNumber<Integer,Integer> > t: ownValuesSpaces_) {
            if (t.getSecond().getFirst()!=t.getSecond().getSecond()) {
                diag_ = false;
                break;
            }
        }
        if(diag_) {
//            infos_.setSecond(Diagonal.DIAGO);
            return new Trigonal(ownValuesSpaces_, Diagonal.DIAGO);
        }
        //else {
//            infos_.setSecond(Diagonal.TRIGO);
        //}
        return new Trigonal(ownValuesSpaces_, Diagonal.TRIGO);
//        return infos_;
    }

    public Matrix ownVects(Rate _value) {
        Matrix id_ = new Matrix();
        Vect line_ = new Vect();
        Rate tmp_ = Rate.zero();
        line_.add(Rate.one());
        int nbLines_=nbLines();
        for(int i=1;i<nbLines_;i++) {
            line_.add(Rate.zero());
        }
        id_.addLineRef(line_);
        for(int i=1;i<nbLines_;i++) {
            tmp_=line_.get(i-1);
            line_.set(i-1,line_.get(i));
            line_.set(i, tmp_);
            id_.addLineRef(line_);
        }
        Matrix mat_=minusMatrix(id_.multMatrix(_value));
        long rang_=mat_.quickRank();
        if(rang_ == 0L) {
            return id_;
        }
        Matrix matFree_ = new Matrix();
        for(int i=0;i<nbLines_;i++) {
            Matrix subMat_=new Matrix(matFree_);
            subMat_.addLineRef(mat_.lines.get(i));
//            extract_mat=extract_mat+mat.lignes[i];
            if(subMat_.quickRank()==subMat_.nbLines()) {
                matFree_=subMat_;
            }
        }
        Matrix ownVects_ = new Matrix();
        Matrix inv_ = new Matrix();
//        int dims=rang_;
        for(int i=0;i<rang_;i++) {
            Vect v_ = new Vect();
            for(int j=0;j<rang_;j++) {
                v_.add(matFree_.cell(i,j));
            }
            inv_.addLineRef(v_);
//            matrice_inv=matrice_inv+vect;
        }
        Matrix nearlyInv_=inv_.inv();
        for(int i=(int) rang_;i<nbLines_;i++) {
            Matrix secondMember_ = new Matrix();
            for(int j=0;j<rang_;j++) {
                secondMember_.addLineRef(new Vect());
                secondMember_.lines.last().add(Rate.zero());
//                second_membre=second_membre+Vecteur();
//                second_membre.lignes.last()<<Taux();
                secondMember_.lines.get(j).set(0, matFree_.cell(j,i).opposNb());
//                second_membre(j,0)=-matrice_sans_vect_lies.cell(j,i);
            }
            Matrix sol_=nearlyInv_.multMatrix(secondMember_);
            int nbLinesTwo_=sol_.nbLines();
            Vect solCopy_ = new Vect();
            for(int j=0;j<nbLinesTwo_;j++) {
                solCopy_.add(sol_.cell(j,0));
            }
            for(int j=(int) rang_;j<i;j++) {
                solCopy_.add(Rate.zero());
            }
            solCopy_.add(Rate.one());
            for(int j=i+1;j<nbLines_;j++) {
                solCopy_.add(Rate.zero());
            }
            ownVects_.addLineRef(solCopy_);
        }
        return ownVects_;
    }

    public Polynom polCaract() {
        Polynom pol_ = new Polynom();
        EqList<PairEq<Rate,Rate>> antImgs_;
        antImgs_ = new EqList<PairEq<Rate,Rate>>();
        int nbLines_=lines.size();
        Matrix id_ = new Matrix();
        Vect line_ = new Vect();
        Rate tmp_ = Rate.zero();
        line_.add(Rate.one());
        for(int i=1;i<nbLines_;i++) {
            line_.add(Rate.zero());
        }
        id_.addLineRef(line_);
        for(int i=1;i<nbLines_;i++) {
            tmp_=line_.get(i-1);
            line_.set(i-1, line_.get(i));
            line_.set(i, tmp_);
            id_.addLineRef(line_);
        }
        for(int i=0;i<=nbLines_;i++) {
            Matrix locMat_ = minusMatrix(id_.multMatrix(new Rate(i)));
            antImgs_.add(new PairEq<Rate,Rate>(new Rate(i),locMat_.det()));
        }
        pol_=Polynom.interpolation(antImgs_);
        return pol_;
    }


    public Rate detInv() {
        int nbCols_=lines.first().size();
        int nbLines_=lines.size();
        if(nbCols_!=nbLines_) {
//            return transpose().multMatrix(this).detSquare();
            return Rate.zero();
            // (transpose()*(*this)).det();
        }
        return detSquare();
    }

    public Rate det() {
        int nbCols_=lines.first().size();
        int nbLines_=lines.size();
        if(nbCols_!=nbLines_) {
//            return transpose().multMatrix(this).detSquare();
            if(nbCols_<nbLines_) {
                Rate det_ = transposeRef().multMatrix(this).detSquare();
                return Rate.powNb(det_, new Rate(1, 2));
            }
            Rate det_ = multMatrix(transposeRef()).detSquare();
            return Rate.powNb(det_, new Rate(1, 2));
            // (transpose()*(*this)).det();
        }
        return detSquare();
    }

    public Rate detSquare() {
//        int nbCols_=lines.first().size();
//        int nbLines_=lines.size();
        Rate deter_ = Rate.one();
        Matrix copy_= new Matrix(this);
        int nbLines_=copy_.lines.size();
        for(int i=0;i<nbLines_;i++) {
            if(copy_.cell(i,i).isZero()) {
                for(int j=i+1;j<nbLines_;j++) {
                    if(!copy_.cell(j,i).isZero()) {
                        deter_.changeSignum();
//                        deter=-deter;
                        for(int k=i;k<nbLines_;k++) {
                            Rate tmp_=copy_.cell(i,k);
                            copy_.lines.get(i).set(k, copy_.cell(j,k));
                            copy_.lines.get(j).set(k, tmp_);
//                            copie(i,k)=copie(j,k);
//                            copie(j,k)=tmp;
                        }
                        //permuter les lignes i et j
                        break;
                    }
                }
            }
            if(copy_.cell(i,i).isZero()) {
                return Rate.zero();
            }
            for(int j=i+1;j<nbLines_;j++) {
                if(!copy_.cell(j,i).isZero()) {
                    Rate coeffPiv_=Rate.divide(copy_.cell(j,i), copy_.cell(i,i)).opposNb();
                    // -copy_.cell(j,i)/copy_.cell(i,i);
                    for(int k=i+1;k<nbLines_;k++) {
//                        copie(j,k)=copie(j,k)+coeff_pivot*copie(i,k);
                        copy_.cell(j, k).addNb(Rate.multiply(coeffPiv_, copy_.cell(i,k)));
                    }
                }
                //calculer LG = ligne j par coeff_pivot
                //ajouter la ligne LG a la ligne i
            }
            deter_.multiplyBy(copy_.cell(i,i));
//            deter=deter*copy_.cell(i,i);
        }
        return deter_;
    }

    public LgInt detSquareIntPart() {
        return detSquare().intPart();
    }

    public long quickRank() {
        Matrix copy_;
        int nbCols_=lines.first().size();
        int nbLines_=lines.size();
//        QPair<int,int> dims=dimensions();
        if(nbLines_<nbCols_) {
//            copy_ = transpose();
            copy_ = transposeRef();
            int tmp_ = nbCols_;
            nbCols_ = nbLines_;
            nbLines_ = tmp_;
        } else {
            copy_ = new Matrix(this);
        }
//        QPair<int,int> dims2=copie.dimensions();
        long rk_= copy_.nbCols();
//        Entier _1(1);
        for(int i=0;i<nbCols_;i++) {
//            int j;
            if(copy_.cell(i,i).isZero()) {
                for(int j=0;j<i;j++) {
                    if(!copy_.cell(j,i).isZero()) {
                        for(int k=i;k<nbCols_;k++) {
                            Rate tmp_=copy_.cell(j,k);
                            copy_.lines.get(j).set(k, copy_.cell(i,k));
                            copy_.lines.get(i).set(k, tmp_);
//                            copie.cell(j,k)=copie.cell(i,k);
//                            copie.cell(i,k)=tmp;
                        }
                        break;
                    }
                }
            }
            if(copy_.cell(i,i).isZero()) {
                for(int j=i+1;j<nbLines_;j++) {
                    if(!copy_.cell(j,i).isZero()) {
                        for(int k=i;k<nbCols_;k++) {
                            Rate tmp_=copy_.cell(j,k);
                            copy_.lines.get(j).set(k, copy_.cell(i,k));
                            copy_.lines.get(i).set(k, tmp_);
//                            Taux tmp=copie(j,k);
//                            copie(j,k)=copie(i,k);
//                            copie(i,k)=tmp;
                        }
                        break;
                    }
                }
            }
            if(!copy_.cell(i,i).isZero()) {
                for(int j=0;j<i;j++) {
                    if(!copy_.cell(j,i).isZero()) {
                        Rate coeff_=Rate.divide(copy_.cell(i,i),copy_.cell(j,i));
                        for(int k=i;k<nbCols_;k++) {
//                            copie(j,k)=coeff*copie(j,k)-copie(i,k);
                            copy_.lines.get(j).set(k, Rate.minus(Rate.multiply(coeff_, copy_.cell(j,k)), copy_.cell(i,k)));
                        }
                    }
                }
                for(int j=i+1;j<nbLines_;j++) {
                    if(!copy_.cell(j,i).isZero()) {
                        Rate r_=Rate.divide(copy_.cell(i,i),copy_.cell(j,i));
                        for(int k=i;k<nbCols_;k++) {
//                            copie(j,k)=coeff*copie(j,k)-copie(i,k);
                            copy_.lines.get(j).set(k, Rate.minus(Rate.multiply(r_, copy_.cell(j,k)), copy_.cell(i,k)));
                        }
                    }
                }
                for(int k=i;k<nbCols_;k++) {
                    copy_.lines.get(i).set(k,Rate.zero());
//                    copie(i,k)=Taux();
                }
            } else {
                rk_--;
            }
        }
        return rk_;
    }

    public long rank() {
        Matrix inv_ = inv();
        int nbCols_=inv_.lines.first().size();
        Rate tr_ = Rate.zero();
        int nbLines_=inv_.lines.size();
        for(int j=0;j<nbCols_;j++) {
            for(int k=0;k<nbLines_;k++) {
                tr_.addNb(Rate.multiply(cell(j, k), inv_.cell(k, j)));
            }
        }
        return tr_.intPart().remainByBase();
    }

    public Matrix inv() {
        Matrix inv_ = new Matrix();
        Rate nbOne_ = Rate.one();
        Vect nulCol_ = new Vect();
        int nbCol_=lines.first().size();
        for(int i=0;i<nbCol_;i++) {
            nulCol_.add(Rate.zero());
        }
        Matrix lineMat_ = new Matrix();
        if(lines.first().isZero()) {
            inv_.addQuickFirstColumn(nulCol_);
        } else {
            lineMat_.addLineRef(lines.first());
            Matrix col_ = lineMat_.transposeRef().multMatrix(Rate.divide(nbOne_, lines.first().square()));
            Vect vectCol_ = new Vect();
            int colSize_ = col_.lines.size();
            for(int i=0;i<colSize_;i++) {
                vectCol_.addNb(col_.cell(i,0));
            }
            inv_.addQuickFirstColumn(vectCol_);
        }
        Matrix b_ = new Matrix();
        Matrix c_ = new Matrix();
        Matrix d_ = new Matrix();
        Matrix e_ = new Matrix();
        Matrix f_ = new Matrix();
        int nbLines_ = lines.size();
        for(int i=1;i<nbLines_;i++) {
            c_.lines= new EqList<Vect>();
            c_.addLineRef(lines.get(i));
            b_.addLineRef(lines.get(i-1));
//            d_.affect(c_.multMatrix(inv_));
            d_ = c_.multMatrix(inv_);
//            f_.affect(c_.minusMatrix(d_.multMatrix(b_)));
            f_ = c_.minusMatrix(d_.multMatrix(b_));
            Rate norme_=f_.lines.first().square();
            if(norme_.isZero()) {
                Rate scal_ = Rate.divide(nbOne_, Rate.plus(nbOne_, d_.lines.first().square()));
//                e_.affect(inv_.multMatrix(d_.transpose()).multMatrix(scal_));
                e_ = inv_.multMatrix(d_.transposeRef()).multMatrix(scal_);
            } else {
                Rate scal_ = Rate.divide(nbOne_, norme_);
//                e_.affect(f_.transpose().multMatrix(scal_));
                e_ = f_.transposeRef().multMatrix(scal_);
            }
//            inv_.affect(inv_.minusMatrix(e_.multMatrix(d_)));
            inv_ = inv_.minusMatrix(e_.multMatrix(d_));
            Vect col_ = new Vect();
            int nbCols_=e_.lines.size();
            for(int j=0;j<nbCols_;j++) {
                col_.add(e_.cell(j,0));
            }
            inv_.addColumnRef(col_);
        }
        return inv_;
    }

    public Matrix transpose() {
        Matrix m_ = new Matrix();
        int nbLines_ = lines.size();
        int nbCols_;
        if (lines.isEmpty()) {
            nbCols_ = 0;
        } else {
            nbCols_ = lines.first().size();
        }
        for (int i = 0; i < nbCols_; i++) {
            Vect v_ = new Vect();
            for (int j = 0; j < nbLines_; j++) {
                v_.addNb(cell(j,i));
            }
            m_.lines.add(v_);
        }
        return m_;
    }

    public Matrix transposeRef() {
        Matrix m_ = new Matrix();
        int nbLines_ = lines.size();
        int nbCols_;
        if (lines.isEmpty()) {
            nbCols_ = 0;
        } else {
            nbCols_ = lines.first().size();
        }
        for (int i = 0; i < nbCols_; i++) {
            Vect v_ = new Vect();
            for (int j = 0; j < nbLines_; j++) {
                v_.add(cell(j,i));
            }
            m_.lines.add(v_);
        }
        return m_;
    }

    public Matrix multMatrix(Rate _scal) {
        Matrix m_ = new Matrix();
        int nbLines_ = lines.size();
        for (int i = 0; i < nbLines_; i++) {
            int nbCols_ = lines.get(i).size();
            Vect v_ = new Vect();
            for (int j = 0; j < nbCols_; j++) {
                v_.add(Rate.multiply(cell(i,j), _scal));
            }
            m_.lines.add(v_);
        }
        return m_;
    }

    public Matrix multMatrix(Matrix _m) {
        Matrix m_ = new Matrix();
        int nbLines_ = lines.size();
        int nbLinesSecond_ = _m.lines.size();
        int nbCols_ = _m.lines.first().size();
        for (int i = 0; i < nbLines_; i++) {
            Vect v_ = new Vect();
            for (int j = 0; j < nbCols_; j++) {
                Rate sum_ = Rate.zero();
                for (int k = 0; k < nbLinesSecond_; k++) {
                    sum_.addNb(Rate.multiply(cell(i,k), _m.cell(k,j)));
                }
                v_.add(sum_);
            }
            m_.lines.add(v_);
        }
        return m_;
    }

    public Matrix minusMatrix(Matrix _m) {
        return addMatrix(_m.minusMatrix());
    }

    public Matrix minusMatrix() {
        Matrix m_ = new Matrix();
        int nbLines_ = lines.size();
        for (int i = 0; i < nbLines_; i++) {
            int nbCols_ = lines.get(i).size();
            Vect v_ = new Vect();
            for (int j = 0; j < nbCols_; j++) {
                v_.add(cell(i,j).opposNb());
            }
            m_.lines.add(v_);
        }
        return m_;
    }

    public Matrix addMatrix(Matrix _m) {
        Matrix m_ = new Matrix();
        int nbLines_ = lines.size();
        for (int i = 0; i < nbLines_; i++) {
            int nbCols_ = lines.get(i).size();
            Vect v_ = new Vect();
            for (int j = 0; j < nbCols_; j++) {
                v_.add(Rate.plus(cell(i,j), _m.cell(i,j)));
            }
            m_.lines.add(v_);
        }
        return m_;
    }

    public void addLine(Vect _v) {
        addLineRef(new Vect(_v));
    }

    public void addLineRef(Vect _v) {
        lines.add(_v);
    }

    public void addColumn(Vect _v) {
        int nbLines_ = _v.size();
        for (int i = 0; i < nbLines_; i++) {
            lines.get(i).addNb(_v.get(i));
        }
    }

    public void addColumnRef(Vect _v) {
        int nbLines_ = _v.size();
        for (int i = 0; i < nbLines_; i++) {
            lines.get(i).add(_v.get(i));
        }
    }

    public void addFirstColumn(Vect _v) {
        if (!lines.isEmpty()) {
            return;
        }
        addQuickFirstColumn(_v);
    }

    public void addQuickFirstColumn(Vect _v) {
        int nbLines_ = _v.size();
        for (int i = 0; i < nbLines_; i++) {
            Vect line_ = new Vect();
            line_.addNb(_v.get(i));
            lines.add(line_);
        }
    }

    public Rate trace() {
        Rate trace_ = Rate.zero();
        int nbLines_ = lines.size();
        int nbCols_;
        if (lines.isEmpty()) {
            nbCols_ = 0;
        } else {
            nbCols_ = lines.first().size();
        }
        int min_ = Math.min(nbLines_, nbCols_);
        for (int i = 0; i < min_; i++) {
            trace_.addNb(cell(i,i));
        }
        return trace_;
    }

    public static boolean eq(Matrix _tx1,Matrix _tx2) {
        if (_tx1 == null) {
            return _tx2 == null;
        }
        return _tx1.isEqualTo(_tx2);
    }

    public boolean isEqualTo(Matrix _o) {
        if (nbLines() != _o.nbLines()) {
            return false;
        }
        if (nbCols() != _o.nbCols()) {
            return false;
        }
        int nbLines_ = nbLines();
        for (int i = 0; i < nbLines_; i++) {
            if (!lines.get(i).isEqualTo(_o.lines.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean eq(Matrix _o) {
        if (nbLines() != _o.nbLines()) {
            return false;
        }
        if (nbCols() != _o.nbCols()) {
            return false;
        }
        int nbLines_ = nbLines();
        for (int i = 0; i < nbLines_; i++) {
            if (!lines.get(i).isEqualTo(_o.lines.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    @FromAndToString
    public String toString() {
        return lines.join(SEPARATOR);
    }
}
