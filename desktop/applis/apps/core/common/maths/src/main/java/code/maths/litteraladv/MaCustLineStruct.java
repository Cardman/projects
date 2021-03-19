package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.geo.CustLine;
import code.maths.matrix.Matrix;
import code.maths.matrix.Vect;
import code.util.CustList;

public final class MaCustLineStruct implements MaAddonStruct {
    private final CustLine line;

    public MaCustLineStruct(CustLine _line) {
        this.line = _line;
    }

    public CustLine getLine() {
        return line;
    }

    @Override
    public boolean sameReferenceMath(MaStruct _other) {
        if (!(_other instanceof MaCustLineStruct)) {
            return false;
        }
        CustLine othLine_ = ((MaCustLineStruct) _other).line;
        Matrix mat_ = new Matrix();
        mat_.addLineRef(new Vect(new CustList<Rate>(this.line.getxRate(), this.line.getyRate(), this.line.getCst())));
        mat_.addLineRef(new Vect(new CustList<Rate>(othLine_.getxRate(), othLine_.getyRate(), othLine_.getCst())));
        return mat_.quickRank() <= 1L;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaCustLineStruct)) {
            return false;
        }
        MaCustLineStruct oth_ = (MaCustLineStruct) _other;
        return line.getxRate().eq(oth_.line.getxRate())
                &&line.getyRate().eq(oth_.line.getyRate())
                &&line.getCst().eq(oth_.line.getCst());
    }

    @Override
    public String displayRsult() {
        return "("+line.getxRate().toNumberString()+","
                +line.getyRate().toNumberString()+","
                +line.getCst().toNumberString()+","
                +MaOperationNode.LINE_THREE+")";
    }
}
