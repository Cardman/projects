package code.maths.litteraladv;

import code.maths.matrix.RateImage;

public final class MaRateImageStruct implements MaStruct {
    private final RateImage rateImage;

    public MaRateImageStruct(RateImage _rateImage) {
        this.rateImage = _rateImage;
    }

    public RateImage getRateImage() {
        return rateImage;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaRateImageStruct)) {
            return false;
        }
        return eq(rateImage,((MaRateImageStruct)_other).rateImage);
    }

    static boolean eq(RateImage _rateThis,
                      RateImage _rateOther) {
        return _rateThis.getRate().eq(_rateOther.getRate())
                && _rateThis.getValue().eq(_rateOther.getValue());
    }
    @Override
    public String displayRsult() {
        return exportStr(rateImage);
    }

    static String exportStr(RateImage _rateThis) {
        return _rateThis.getRate().toNumberString() + "=>" + _rateThis.getValue().toNumberString();
    }
}
