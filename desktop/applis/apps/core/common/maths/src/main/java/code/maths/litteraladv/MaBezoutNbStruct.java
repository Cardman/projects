package code.maths.litteraladv;

import code.maths.IdBezoutNb;
import code.maths.Rate;
import code.util.CustList;

public final class MaBezoutNbStruct extends MaListNbStruct {
    private final IdBezoutNb idBezout;

    public MaBezoutNbStruct(IdBezoutNb _idBezout) {
        this.idBezout = _idBezout;
    }

    public IdBezoutNb getIdBezout() {
        return idBezout;
    }

    @Override
    public CustList<Rate> getNumberList() {
        return new CustList<Rate>(new Rate(idBezout.getFirst()),
                new Rate(idBezout.getSecond()),
                new Rate(idBezout.getPgcd()),
                new Rate(idBezout.getPpcm()));
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqBezout(this, _other);
    }

    @Override
    public String displayRsult() {
        return "("+idBezout.getFirst().toNumberString()+","+idBezout.getSecond().toNumberString()+","
                +idBezout.getPgcd().toNumberString()+","+idBezout.getPpcm().toNumberString()+")";
    }

    static boolean eqBezout(MaBezoutNbStruct _this, MaStruct _other) {
        if (!(_other instanceof MaBezoutNbStruct)) {
            return false;
        }
        IdBezoutNb oth_ = ((MaBezoutNbStruct) _other).idBezout;
        return _this.idBezout.getFirst().eq(oth_.getFirst())
                && _this.idBezout.getSecond().eq(oth_.getSecond())
                && _this.idBezout.getPgcd().eq(oth_.getPgcd())
                && _this.idBezout.getPpcm().eq(oth_.getPpcm());
    }
}
