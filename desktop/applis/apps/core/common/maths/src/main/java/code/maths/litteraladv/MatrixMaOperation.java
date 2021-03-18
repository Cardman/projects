package code.maths.litteraladv;

import code.maths.litteralcom.StrTypes;
import code.maths.matrix.Matrix;
import code.maths.matrix.Vect;
import code.util.CustList;
import code.util.StringMap;

public final class MatrixMaOperation extends MethodMaOperation {
    protected MatrixMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        CustList<MaStruct> val_ = tryGetAll(this);
        if (val_.isEmpty()) {
            _error.setOffset(getIndexExp());
            return;
        }
        int count_ = val_.size();
        if (val_.first() instanceof MaRateStruct) {
            procNbs(_error, val_, count_);
            return;
        }
        if (val_.first() instanceof MaVectStruct) {
            procVects(_error, val_, count_);
            return;
        }
        _error.setOffset(getIndexExp()+StrTypes.offset(getOperats().getParts(),0));
    }

    private void procVects(MaError _error, CustList<MaStruct> _val, int _count) {
        CustList<Vect> vects_ = new CustList<Vect>();
        Vect ref_ = ((MaVectStruct) _val.first()).getVect();
        vects_.add(ref_);
        int nbElts_ = ref_.size();
        for (int i = 1; i < _count; i++) {
            if (!(_val.get(i) instanceof MaVectStruct)) {
                _error.setOffset(getIndexExp()+StrTypes.offset(getOperats().getParts(),i));
                return;
            }
            Vect vect_ = ((MaVectStruct) _val.get(i)).getVect();
            if (vect_.size() != nbElts_) {
                _error.setOffset(getIndexExp()+StrTypes.offset(getOperats().getParts(),i));
                return;
            }
            vects_.add(vect_);
        }
        setStruct(new MaMatrixStruct(new Matrix(vects_)));
    }

    private void procNbs(MaError _error, CustList<MaStruct> _val, int _count) {
        CustList<Vect> vects_ = new CustList<Vect>();
        Vect dir_ = new Vect();
        dir_.add(((MaRateStruct) _val.first()).getRate());
        vects_.add(dir_);
        for (int i = 1; i < _count; i++) {
            if (!(_val.get(i) instanceof MaRateStruct)) {
                _error.setOffset(getIndexExp()+StrTypes.offset(getOperats().getParts(),i));
                return;
            }
            Vect dirTwo_ = new Vect();
            dirTwo_.add(((MaRateStruct) _val.get(i)).getRate());
            vects_.add(dirTwo_);
        }
        setStruct(new MaMatrixStruct( new Matrix(vects_)));
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        vs_.remove(0);
        getChs().addAllEntries(vs_);
    }
}
