package code.maths.litteraladv;

import code.maths.IdBezoutNb;

public class MaBezoutNbStruct implements MaStruct {
    private final IdBezoutNb idBezout;

    public MaBezoutNbStruct(IdBezoutNb _idBezout) {
        this.idBezout = _idBezout;
    }

    public IdBezoutNb getIdBezout() {
        return idBezout;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqBezout(this, _other);
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
