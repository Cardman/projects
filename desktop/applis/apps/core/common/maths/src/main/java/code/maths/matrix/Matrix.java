package code.maths.matrix;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class Matrix implements Displayable {

    private static final String SEPARATOR = ";";
    private CustList<Vect> lines = new CustList<Vect>();

    public Matrix() {
    }

    public Matrix(Matrix _m) {
        affect(_m);
    }

    public Matrix(CustList<Vect> _m) {
        lines.addAllElts(_m);
    }
    public void affect(Matrix _m) {
        lines.clear();
        for (Vect l: _m.lines) {
            lines.add(new Vect(l));
        }
    }

    
    public static Matrix newMatrix(String _arg) {
        Matrix v_ = new Matrix();
        for (String s: StringUtil.splitStrings(_arg, SEPARATOR)) {
            v_.addLineRef(Vect.newVect(s));
        }
        return v_;
    }

    public int nbLines() {
        return lines.size();
    }

    public int nbCols() {
        if (lines.isEmpty()) {
            return IndexConstants.SIZE_EMPTY;
        }
        return lines.first().size();
    }

    public Rate cell(int _i, int _j) {
        return lines.get(_i).get(_j);
    }

    public Matrix passVects() {
        CustList<Vect> vects_ = modif().passVectsSquare();
        vects_ = clean(vects_);
        return new Matrix(vects_).transposeRef();
    }

    public static CustList<Vect> clean(CustList<Vect> _vects) {
        if (_vects.isEmpty()) {
            return new CustList<Vect>(new Vect(new CustList<Rate>(Rate.one())));
        }
        int len_ = _vects.first().size();
        int count_ = _vects.size();
        for (int i = 1; i < count_; i++) {
            if (_vects.get(i).size() != len_) {
                return new CustList<Vect>(new Vect(new CustList<Rate>(Rate.one())));
            }
        }
        return _vects;
    }
    public CustList<Vect> passVectsSquare() {
        CustList<RootPol> ownValues_=polCaract().racines();
        CustList<Vect> vects_ = new CustList<Vect>();
        Matrix id_ = buildId();
        for (RootPol r: ownValues_) {
            Matrix own_ = minusMatrix(id_.multMatrix(r.getValue()));
            vects_.addAllElts(own_.ker());
        }
        return vects_;
    }
    public Matrix matrixKer(){
        CustList<Vect> vects_ = ker();
        if (vects_.isEmpty()) {
            int nbLines_ = nbCols();
            return zeroMatrix(nbLines_).transposeRef();
        }
        return new Matrix(vects_).transposeRef();
    }
    public Matrix matrixIm(){
        CustList<Vect> vects_ = im();
        if (vects_.isEmpty()) {
            int nbLines_ = nbLines();
            return zeroMatrix(nbLines_).transposeRef();
        }
        return new Matrix(vects_).transposeRef();
    }

    private static Matrix zeroMatrix(int _count) {
        Vect v_ = new Vect();
        for (int j = 0; j < _count; j++) {
            v_.add(Rate.zero());
        }
        return new Matrix(new CustList<Vect>(v_));
    }

    public CustList<Vect> im() {
        int nbCols_ = nbCols();
        Matrix copy_ = new Matrix(this);
        int nbLines_ = nbLines();
        grouped(nbCols_,nbLines_,copy_);
        CustList<Vect> cols_ = new CustList<Vect>();
        for (int i = 0; i < nbCols_; i++) {
            if (!zeroPrev(copy_, nbLines_, i)) {
                addCol(copy_, 0, cols_, nbLines_, i);
            }
        }
        return cols_;
    }
    public CustList<Vect> ker() {
        int nbCols_ = nbCols();
        Matrix copy_ = new Matrix(this);
        int nbLines_ = nbLines();
        grouped(nbCols_,nbLines_,copy_);
        CustList<Vect> cols_ = new CustList<Vect>();
        int all_ = copy_.nbLines();
        for (int i = 0; i < nbCols_; i++) {
            if (zeroPrev(copy_, nbLines_, i)) {
                addCol(copy_, nbLines_, cols_, all_, i);
            }
        }
        return cols_;
    }

    private static void addCol(Matrix _copy, int _from, CustList<Vect> _cols, int _to, int _i) {
        Vect v_ = new Vect();
        for (int j = _from; j < _to; j++) {
            v_.add(_copy.lines.get(j).get(_i));
        }
        _cols.add(v_);
    }

    private static void grouped(int _nbCols, int _nbLines, Matrix _copy) {
        Matrix id_ = buildId(_nbCols);
        int nbLinesId_ = id_.nbLines();
        for (int i = 0; i < nbLinesId_; i++) {
            _copy.addLineRef(id_.lines.get(i));
        }
        for (int i = 0; i < _nbLines; i++) {
            int indexNotZero_ = indexNotZero(_nbCols, _copy, i);
            if (indexNotZero_ > -1){
                combine(_nbCols, _copy, i, indexNotZero_);
            }
        }
    }

    private static int indexNotZero(int _nbCols, Matrix _copy, int _i) {
        int indexNotZero_ = -1;
        for (int j = 0; j < _nbCols; j++) {
            boolean ok_ = zeroPrev(_copy, _i, j);
            if (ok_ && !_copy.lines.get(_i).get(j).isZero()) {
                indexNotZero_ = j;
                break;
            }
        }
        return indexNotZero_;
    }

    private static void combine(int _nbCols, Matrix _copy, int _i, int _indexNotZero) {
        Vect curLine_ = _copy.lines.get(_i);
        Rate co_ = curLine_.get(_indexNotZero);
        for (int j = 0; j < _indexNotZero; j++) {
            Rate r_ = Rate.divide(curLine_.get(j), co_).opposNb();
            _copy.combineCol(r_, _indexNotZero,j);
        }
        for (int j = _indexNotZero +1; j < _nbCols; j++) {
            Rate r_ = Rate.divide(curLine_.get(j), co_).opposNb();
            _copy.combineCol(r_, _indexNotZero,j);
        }
    }

    private static boolean zeroPrev(Matrix _copy, int _i, int _j) {
        boolean ok_ = true;
        for (int k = 0; k < _i; k++) {
            if (!_copy.lines.get(k).get(_j).isZero()) {
                ok_ = false;
                break;
            }
        }
        return ok_;
    }

    private void combineCol(Rate _rate,int _colOrigin,int _colDest) {
        int nbLines_ = nbLines();
        for (int i = 0; i < nbLines_; i++) {
            Vect curLine_ = lines.get(i);
            curLine_.set(_colDest,Rate.plus(curLine_.get(_colDest),Rate.multiply(_rate, curLine_.get(_colOrigin))));
        }
    }
//    public Matrix passMat() {
//        Matrix mat_ = new Matrix();
//        CustList<EigenValue> ownValues_=diagTrig().getRates();
//        for(EigenValue t: ownValues_) {
//            Matrix ownVects_=ownVects(t.getValue(),t.getDegree());
//            for(Vect l: ownVects_.lines) {
//                mat_.addLineRef(l);
//            }
//        }
//        return mat_;
//    }

    public Trigonal diagTrig() {
        CustList<RootPol> ownValues_=polCaract().racines();
        CustList<EigenValue> ownValuesSpaces_;
        ownValuesSpaces_ = new CustList<EigenValue>();
        int sum_=0;
        int nbLines_=lines.size();
//        Matrix id_ = new Matrix();
//        Vect line_ = new Vect();
//        line_.add(Rate.one());
//        feedZeros(nbLines_, 1, line_);
//        id_.addLineRef(line_);
//        for(int i=1;i<nbLines_;i++) {
//            Vect n_ = new Vect(line_);
//            n_.swapIndexes(i,i - 1);
//            id_.addLineRef(n_);
//            line_ = n_;
//        }
        Matrix id_ = buildId();
        for(RootPol c:ownValues_) {
            sum_+=c.getDegree();
            Matrix matrix_ = minusMatrix(id_.multMatrix(c.getValue()));
            int sp_ = (int) (nbLines_ - matrix_.quickRank());
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

//    public Matrix ownVects(Rate _value, int _space) {
//        Matrix id_ = new Matrix();
//        Vect line_ = new Vect();
//        line_.add(Rate.one());
//        int nbLines_=nbLines();
//        feedZeros(nbLines_, 1, line_);
//        id_.addLineRef(line_);
//        for(int i=1;i<nbLines_;i++) {
//            Vect n_ = new Vect(line_);
//            n_.swapIndexes(i,i - 1);
//            id_.addLineRef(n_);
//            line_ = n_;
//        }
//        Matrix mat_=minusMatrix(id_.multMatrix(_value));
//        Matrix res_ = mat_.power(_space);
//        long rang_=res_.quickRank();
//        if(rang_ == 0L) {
//            return id_;
//        }
//        return res_.quickRank2();
////        long rang_=res_.quickRank();
////        if(rang_ == 0L) {
////            return id_;
////        }
////        Matrix matFree_ = new Matrix();
////        for(int i=0;i<nbLines_;i++) {
////            Matrix subMat_=new Matrix(matFree_);
////            subMat_.addLineRef(res_.lines.get(i));
////            if(subMat_.quickRank()==subMat_.nbLines()) {
////                matFree_=subMat_;
////            }
////        }
////        Matrix ownVects_ = new Matrix();
////        Matrix inv_ = new Matrix();
////        for(int i=0;i<rang_;i++) {
////            Vect v_ = new Vect();
////            for(int j=0;j<rang_;j++) {
////                v_.add(matFree_.cell(i,j));
////            }
////            inv_.addLineRef(v_);
////        }
////        Matrix nearlyInv_=inv_.inv();
////        for(int i=(int) rang_;i<nbLines_;i++) {
////            Matrix secondMember_ = new Matrix();
////            for(int j=0;j<rang_;j++) {
////                Vect v_ = new Vect();
////                v_.add(matFree_.cell(j,i).opposNb());
////                secondMember_.addLineRef(v_);
////            }
////            Matrix sol_=nearlyInv_.multMatrix(secondMember_);
////            int nbLinesTwo_=sol_.nbLines();
////            Vect solCopy_ = new Vect();
////            for(int j=0;j<nbLinesTwo_;j++) {
////                solCopy_.add(sol_.cell(j,0));
////            }
////            feedZeros(i, nbLinesTwo_, solCopy_);
////            solCopy_.add(Rate.one());
////            feedZeros(nbLines_, i+1, solCopy_);
////            ownVects_.addLineRef(solCopy_);
////        }
////        return ownVects_;
//    }

    public Matrix power(LgInt _power) {
        Matrix res_ = buildId();
        LgInt it_ = LgInt.zero();
        while (LgInt.strLower(it_,_power)) {
            res_ = res_.multMatrix(this);
            it_.increment();
        }
        return res_;
    }

    Matrix buildId() {
        int nbLines_=nbLines();
        return buildId(nbLines_);
    }

    public static Matrix buildId(int _nbLines) {
        Matrix id_ = new Matrix();
        Vect line_ = new Vect();
        line_.add(Rate.one());
        feedZeros(_nbLines, 1, line_);
        id_.addLineRef(line_);
        for(int i = 1; i< _nbLines; i++) {
            Vect n_ = new Vect(line_);
            n_.swapIndexes(i,i - 1);
            id_.addLineRef(n_);
            line_ = n_;
        }
        return id_;
    }

    private static void feedZeros(long _i, long _start, Vect _solCopy) {
        for(long j = _start; j< _i; j++) {
            _solCopy.add(Rate.zero());
        }
    }

    public Polynom polCaract() {
        return modif().polCaractSquare();
    }

    public Matrix modif() {
        int nbCols_=lines.first().size();
        int nbLines_=lines.size();
        if(nbCols_!=nbLines_) {
            if(nbCols_<nbLines_) {
                return transposeRef().multMatrix(this);
            }
            return multMatrix(transposeRef());
        }
        return this;
    }

    private Polynom polCaractSquare() {
        CustList<RateImage> antImgs_;
        antImgs_ = new CustList<RateImage>();
        int nbLines_=lines.size();
        Matrix id_ = buildId();
        for(int i=0;i<=nbLines_;i++) {
            Rate antec_ = new Rate(i);
            Matrix locMat_ = minusMatrix(id_.multMatrix(antec_));
            antImgs_.add(new RateImage(antec_,locMat_.det()));
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
        return modif().detSquare();
    }

    public Rate detSquare() {
        Rate deter_ = Rate.one();
        Matrix copy_= new Matrix(this);
        int nbLines_=copy_.lines.size();
        for(int i=0;i<nbLines_;i++) {
            if(copy_.cell(i,i).isZero()) {
                swap(deter_, copy_, nbLines_, i);
            }
            sumVects(copy_, nbLines_, i);
            deter_.multiplyBy(copy_.cell(i,i));
        }
        return deter_;
    }

    private static void swap(Rate _deter, Matrix _copy, int _nbLines, int _after) {
        for(int j = _after +1; j< _nbLines; j++) {
            if(!_copy.cell(j, _after).isZero()) {
                _deter.changeSignum();
                for(int k = _after; k< _nbLines; k++) {
                    Rate tmp_= _copy.cell(_after,k);
                    _copy.lines.get(_after).set(k, _copy.cell(j,k));
                    _copy.lines.get(j).set(k, tmp_);
                }
                //permuter les lignes i et j
                break;
            }
        }
    }

    private static void sumVects(Matrix _copy, int _nbLines, int _after) {
        Rate piv_ = _copy.cell(_after, _after);
        if (piv_.isZero()) {
            return;
        }
        for(int j = _after +1; j< _nbLines; j++) {
            Rate rate_ = _copy.cell(j, _after);
            if(!rate_.isZero()) {
                Rate coeffPiv_=Rate.divide(rate_, piv_).opposNb();
                for(int k = _after +1; k< _nbLines; k++) {
                    _copy.cell(j, k).addNb(Rate.multiply(coeffPiv_, _copy.cell(_after,k)));
                }
            }
            //calculer LG = ligne j par coeff_pivot
            //ajouter la ligne LG a la ligne i
        }
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
//
//    public Matrix quickRank2() {
//        Matrix copy_;
//        int nbCols_=lines.first().size();
//        int nbLines_=lines.size();
//        if(nbLines_<nbCols_) {
//            copy_ = transposeRef();
//            int tmp_ = nbCols_;
//            nbCols_ = nbLines_;
//            nbLines_ = tmp_;
//        } else {
//            copy_ = new Matrix(this);
//        }
//        for(int i=0;i<nbCols_;i++) {
//            swapHelperTest(copy_, nbCols_, nbLines_, i, i + 1);
//            if(!copy_.cell(i,i).isZero()) {
//                rankHelper(copy_, nbCols_, nbLines_, i, i + 1);
//            }
//        }
//        Matrix out_ = new Matrix();
//        for (Vect v: copy_.lines) {
//            if (v.isZero()) {
//                continue;
//            }
//            out_.lines.add(v);
//        }
//        return out_;
//    }

    private static void swapHelperTest(Matrix _copy, int _nbCols, int _untilLines, int _secStart, int _start) {
        if (_copy.cell(_secStart, _secStart).isZero()) {
            swapHelper(_copy, _nbCols, _untilLines, _secStart, _start);
        }
    }

    private static void swapHelper(Matrix _copy, int _nbCols, int _untilLines, int _secStart, int _start) {
        for (int j = _start; j < _untilLines; j++) {
            if (!_copy.cell(j, _secStart).isZero()) {
                for (int k = _secStart; k < _nbCols; k++) {
                    Rate tmp_ = _copy.cell(j, k);
                    _copy.lines.get(j).set(k, _copy.cell(_secStart, k));
                    _copy.lines.get(_secStart).set(k, tmp_);
                }
                break;
            }
        }
    }

    private static void rankHelper(Matrix _copy, int _nbCols, int _untilLines, int _secStart, int _start) {
        for (int j = _start; j < _untilLines; j++) {
            Rate rate_ = _copy.cell(j, _secStart);
            if (!rate_.isZero()) {
                Rate r_ = Rate.divide(_copy.cell(_secStart, _secStart), rate_);
                for (int k = _secStart; k < _nbCols; k++) {
                    _copy.lines.get(j).set(k, Rate.minus(Rate.multiply(r_, _copy.cell(j, k)), _copy.cell(_secStart, k)));
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

    public boolean isSquare() {
        return nbCols() == nbLines();
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
            c_.lines= new CustList<Vect>();
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
        int nbCols_ = nbCols();
        for (int i = 0; i < nbCols_; i++) {
            addNb(m_, nbLines_, i);
        }
        return m_;
    }

    private void addNb(Matrix _m, int _nbLines, int _i) {
        Vect v_ = new Vect();
        for (int j = 0; j < _nbLines; j++) {
            v_.addNb(cell(j, _i));
        }
        _m.lines.add(v_);
    }


    public Matrix transposeRef() {
        Matrix m_ = new Matrix();
        int nbLines_ = lines.size();
        int nbCols_ = nbCols();
        for (int i = 0; i < nbCols_; i++) {
            addRef(m_, nbLines_, i);
        }
        return m_;
    }

    private void addRef(Matrix _m, int _nbLines, int _i) {
        Vect v_ = new Vect();
        for (int j = 0; j < _nbLines; j++) {
            v_.add(cell(j, _i));
        }
        _m.lines.add(v_);
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
        int nbLines_=lines.size();
        if (nbLines_ == 0) {
            return Rate.zero();
        }
        return modif().traceSquare();
    }

    public Rate traceSquare() {
        Rate trace_ = Rate.zero();
        int nbLines_ = lines.size();
        int nbCols_ = nbCols();
        int min_ = NumberUtil.min(nbLines_, nbCols_);
        for (int i = 0; i < min_; i++) {
            trace_.addNb(cell(i,i));
        }
        return trace_;
    }
    public Vect get(int _i) {
        return lines.get(_i);
    }
    public static boolean eq(Matrix _tx1,Matrix _tx2) {
        return _tx1.eq(_tx2);
    }

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
