package code.maths.litteraladv;

import code.maths.geo.CustLine;

public final class MaCustLineStruct implements MaStruct {
    private final CustLine line;

    public MaCustLineStruct(CustLine _line) {
        this.line = _line;
    }

    public CustLine getLine() {
        return line;
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
                +"-)";
    }
}
