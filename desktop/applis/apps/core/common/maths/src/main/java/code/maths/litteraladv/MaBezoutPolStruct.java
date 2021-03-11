package code.maths.litteraladv;

import code.maths.matrix.IdBezoutPol;

public final class MaBezoutPolStruct implements MaStruct {
    private final IdBezoutPol idBezout;

    public MaBezoutPolStruct(IdBezoutPol _idBezout) {
        this.idBezout = _idBezout;
    }

    public IdBezoutPol getIdBezout() {
        return idBezout;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqBezout(this, _other);
    }

    @Override
    public String displayRsult() {
        return "("+MaPolynomStruct.displayRsult(idBezout.getFirst())+","+MaPolynomStruct.displayRsult(idBezout.getSecond())+","
                +MaPolynomStruct.displayRsult(idBezout.getPgcd())+","+MaPolynomStruct.displayRsult(idBezout.getPpcm())+")";
    }

    static boolean eqBezout(MaBezoutPolStruct _this, MaStruct _other) {
        if (!(_other instanceof MaBezoutPolStruct)) {
            return false;
        }
        IdBezoutPol oth_ = ((MaBezoutPolStruct) _other).idBezout;
        return _this.idBezout.getFirst().eq(oth_.getFirst())
                && _this.idBezout.getSecond().eq(oth_.getSecond())
                && _this.idBezout.getPgcd().eq(oth_.getPgcd())
                && _this.idBezout.getPpcm().eq(oth_.getPpcm());
    }
}
