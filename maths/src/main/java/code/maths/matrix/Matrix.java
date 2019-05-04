package code.maths.matrix;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class Matrix implements Equallable<Matrix>, Displayable {

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
        CustList<EigenValue> ownValues_=diagTrig().getRates();
        for(EigenValue t: ownValues_) {
            Matrix ownVects_=ownVects(t.getValue());
            for(Vect l: ownVects_.lines) {
                mat_.addLineRef(l);
            }
        }
        return mat_;
    }

    public Trigonal diagTrig() {
        CustList<RootPol> ownValues_=polCaract().racines();
        CustList<EigenValue> ownValuesSpaces_;
        ownValuesSpaces_ = new CustList<EigenValue>();
        int sum_=0;
        int nbLines_=lines.size();
        Matrix id_ = new Matrix();
        Vect line_ = new Vect();
        line_.add(Rate.one());
        feedZeros(nbLines_, 1, line_);
        id_.addLineRef(line_);
        for(int i=1;i<nbLines_;i++) {
            Vect n_ = new Vect(line_);
            n_.swapIndexes(i,i - 1);
            id_.addLineRef(n_);
            line_ = n_;
        }
        for(RootPol c:ownValues_) {
            sum_+=c.getDegree();
            int sp_ = (int) (nbLines_ - minusMatrix(id_.multMatrix(c.getValue())).quickRank());
            ownValuesSpaces_.add(new EigenValue(c.getValue(),c.getDegree(),sp_));
        }
        if(sum_<lines.size()) {
            return new Trigonal(ownValuesSpaces_, Diagonal.UN_TRIGO);
        }
        boolean diag_=true;
        for(EigenValue t: ownValuesSpaces_) {
            if (t.getDegree()!=t.getSpace()) {
                diag_ = false;
                break;
            }
        }
        if(diag_) {
            return new Trigonal(ownValuesSpaces_, Diagonal.DIAGO);
        }
        return new Trigonal(ownValuesSpaces_, Diagonal.TRIGO);
    }

    public Matrix ownVects(Rate _value) {
        Matrix id_ = new Matrix();
        Vect line_ = new Vect();
        line_.add(Rate.one());
        int nbLines_=nbLines();
        feedZeros(nbLines_, 1, line_);
        id_.addLineRef(line_);
        for(int i=1;i<nbLines_;i++) {
            Vect n_ = new Vect(line_);
            n_.swapIndexes(i,i - 1);
            id_.addLineRef(n_);
            line_ = n_;
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
            if(subMat_.quickRank()==subMat_.nbLines()) {
                matFree_=subMat_;
            }
        }
        Matrix ownVects_ = new Matrix();
        Matrix inv_ = new Matrix();
        for(int i=0;i<rang_;i++) {
            Vect v_ = new Vect();
            for(int j=0;j<rang_;j++) {
                v_.add(matFree_.cell(i,j));
            }
            inv_.addLineRef(v_);
        }
        Matrix nearlyInv_=inv_.inv();
        for(int i=(int) rang_;i<nbLines_;i++) {
            Matrix secondMember_ = new Matrix();
            for(int j=0;j<rang_;j++) {
                secondMember_.addLineRef(new Vect());
                secondMember_.lines.last().add(Rate.zero());
                secondMember_.lines.get(j).set(0, matFree_.cell(j,i).opposNb());
            }
            Matrix sol_=nearlyInv_.multMatrix(secondMember_);
            int nbLinesTwo_=sol_.nbLines();
            Vect solCopy_ = new Vect();
            for(int j=0;j<nbLinesTwo_;j++) {
                solCopy_.add(sol_.cell(j,0));
            }
            feedZeros(i, nbLinesTwo_, solCopy_);
            solCopy_.add(Rate.one());
            feedZeros(nbLines_, i+1, solCopy_);
            ownVects_.addLineRef(solCopy_);
        }
        return ownVects_;
    }

    private void feedZeros(int _i, int _nbLinesTwo, Vect _solCopy) {
        for(int j = _nbLinesTwo; j< _i; j++) {
            _solCopy.add(Rate.zero());
        }
    }

    public Polynom polCaract() {
        CustList<RateImage> antImgs_;
        antImgs_ = new CustList<RateImage>();
        int nbLines_=lines.size();
        Matrix id_ = new Matrix();
        Vect line_ = new Vect();
        line_.add(Rate.one());
        feedZeros(nbLines_, 1, line_);
        id_.addLineRef(line_);
        for(int i=1;i<nbLines_;i++) {
            Vect v_ = new Vect(line_);
            v_.swapIndexes(i, i-1);
            id_.addLineRef(v_);
            line_ = v_;
        }
        for(int i=0;i<=nbLines_;i++) {
            Matrix locMat_ = minusMatrix(id_.multMatrix(new Rate(i)));
            antImgs_.add(new RateImage(new Rate(i),locMat_.det()));
        }
        return Polynom.interpolation(antImgs_);
    }


    public Rate detInv() {
        int nbCols_=lines.first().size();
        int nbLines_=lines.size();
        if(nbCols_!=nbLines_) {
            return Rate.zero();
        }
        return detSquare();
    }

    public Rate det() {
        int nbCols_=lines.first().size();
        int nbLines_=lines.size();
        if(nbCols_!=nbLines_) {
            if(nbCols_<nbLines_) {
                Rate det_ = transposeRef().multMatrix(this).detSquare();
                return Rate.powNb(det_, new Rate(1, 2));
            }
            Rate det_ = multMatrix(transposeRef()).detSquare();
            return Rate.powNb(det_, new Rate(1, 2));
        }
        return detSquare();
    }

    public Rate detSquare() {
        Rate deter_ = Rate.one();
        Matrix copy_= new Matrix(this);
        int nbLines_=copy_.lines.size();
        for(int i=0;i<nbLines_;i++) {
            if(copy_.cell(i,i).isZero()) {
                for(int j=i+1;j<nbLines_;j++) {
                    if(!copy_.cell(j,i).isZero()) {
                        deter_.changeSignum();
                        for(int k=i;k<nbLines_;k++) {
                            Rate tmp_=copy_.cell(i,k);
                            copy_.lines.get(i).set(k, copy_.cell(j,k));
                            copy_.lines.get(j).set(k, tmp_);
                        }
                        //permuter les lignes i et j
                        break;
                    }
                }
            }
            for(int j=i+1;j<nbLines_;j++) {
                if(!copy_.cell(j,i).isZero()) {
                    Rate coeffPiv_=Rate.divide(copy_.cell(j,i), copy_.cell(i,i)).opposNb();
                    for(int k=i+1;k<nbLines_;k++) {
                        copy_.cell(j, k).addNb(Rate.multiply(coeffPiv_, copy_.cell(i,k)));
                    }
                }
                //calculer LG = ligne j par coeff_pivot
                //ajouter la ligne LG a la ligne i
            }
            deter_.multiplyBy(copy_.cell(i,i));
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
        if(nbLines_<nbCols_) {
            copy_ = transposeRef();
            int tmp_ = nbCols_;
            nbCols_ = nbLines_;
            nbLines_ = tmp_;
        } else {
            copy_ = new Matrix(this);
        }
        long rk_= copy_.nbCols();
        for(int i=0;i<nbCols_;i++) {
            swapHelperTest(copy_, nbCols_, i, i, 0);
            swapHelperTest(copy_, nbCols_, nbLines_, i, i + 1);
            if(!copy_.cell(i,i).isZero()) {
                rankHelper(copy_, nbCols_, i, i, 0);
                rankHelper(copy_, nbCols_, nbLines_, i, i + 1);
                for(int k=i;k<nbCols_;k++) {
                    copy_.lines.get(i).set(k,Rate.zero());
                }
            } else {
                rk_--;
            }
        }
        return rk_;
    }

    private void swapHelperTest(Matrix _copy, int _nbCols, int _nbLines, int _i, int i2) {
        if (_copy.cell(_i, _i).isZero()) {
            swapHelper(_copy, _nbCols, _nbLines, _i, i2);
        }
    }

    private void swapHelper(Matrix _copy, int _nbCols, int _nbLines, int _i, int _start) {
        for (int j = _start; j < _nbLines; j++) {
            if (!_copy.cell(j, _i).isZero()) {
                for (int k = _i; k < _nbCols; k++) {
                    Rate tmp_ = _copy.cell(j, k);
                    _copy.lines.get(j).set(k, _copy.cell(_i, k));
                    _copy.lines.get(_i).set(k, tmp_);
                }
                break;
            }
        }
    }

    private void rankHelper(Matrix _copy, int _nbCols, int _nbLines, int _i, int _start) {
        for (int j = _start; j < _nbLines; j++) {
            if (!_copy.cell(j, _i).isZero()) {
                Rate r_ = Rate.divide(_copy.cell(_i, _i), _copy.cell(j, _i));
                for (int k = _i; k < _nbCols; k++) {
                    _copy.lines.get(j).set(k, Rate.minus(Rate.multiply(r_, _copy.cell(j, k)), _copy.cell(_i, k)));
                }
            }
        }
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
        return tr_.intPart().ll();
    }

    public Matrix inv() {
        Matrix inv_ = new Matrix();
        Rate nbOne_ = Rate.one();
        Vect nulCol_ = new Vect();
        int nbCol_=lines.first().size();
        feedZeros(nbCol_, 0, nulCol_);
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
        int nbLines_ = lines.size();
        for(int i=1;i<nbLines_;i++) {
            c_.lines= new EqList<Vect>();
            c_.addLineRef(lines.get(i));
            b_.addLineRef(lines.get(i-1));
            Matrix d_ = c_.multMatrix(inv_);
            Matrix f_ = c_.minusMatrix(d_.multMatrix(b_));
            Rate norme_=f_.lines.first().square();
            Matrix e_;
            if(norme_.isZero()) {
                Rate scal_ = Rate.divide(nbOne_, Rate.plus(nbOne_, d_.lines.first().square()));
                e_ = inv_.multMatrix(d_.transposeRef()).multMatrix(scal_);
            } else {
                Rate scal_ = Rate.divide(nbOne_, norme_);
                e_ = f_.transposeRef().multMatrix(scal_);
            }
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

    public void addColumnRef(Vect _v) {
        int nbLines_ = _v.size();
        for (int i = 0; i < nbLines_; i++) {
            lines.get(i).add(_v.get(i));
        }
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
        return _tx1.eq(_tx2);
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
            if (!lines.get(i).eq(_o.lines.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    
    public String display() {
        if (lines.isEmpty()) {
            return "";
        }
        StringBuilder return_ = new StringBuilder(lines.first().display());
        int size_ = lines.size();
        for (int i=1;i<size_;i++) {
            return_.append(SEPARATOR);
            return_.append(lines.get(i).display());
        }
        return return_.toString();
    }
}
