package code.maths.litteraladv;

import code.maths.matrix.Matrix;
import code.util.CollCapacity;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaMatrixStruct implements MaStruct {
    private final Matrix matrix;

    public MaMatrixStruct(Matrix _matrix) {
        this.matrix = _matrix;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaMatrixStruct)) {
            return false;
        }
        return Matrix.eq(matrix,((MaMatrixStruct)_other).matrix);
    }

    public Matrix getMatrix() {
        return matrix;
    }

    @Override
    public String displayRsult() {
        int size_ = matrix.nbLines();
        StringList elts_ = new StringList(new CollCapacity(size_));
        for (int i = 0; i < size_; i++) {
            elts_.add(MaVectStruct.displayRsult(matrix.get(i)));
        }
        return "{"+StringUtil.join(elts_,";")+"}";
    }
}
