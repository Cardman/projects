package code.maths.litteraladv;

import code.maths.Rate;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaRateListStruct implements MaStruct {
    private final CustList<Rate> rates;

    public MaRateListStruct(CustList<Rate> _rates) {
        rates = _rates;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqRates(this, _other);
    }

    @Override
    public String displayRsult() {
        StringList list_ = new StringList(new CollCapacity(rates.size()));
        for (Rate r: rates) {
            list_.add(r.toNumberString());
        }
        return "("+ StringUtil.join(list_,",")+")";
    }

    public static boolean eqRates(MaRateListStruct _this, MaStruct _other) {
        if (!(_other instanceof MaRateListStruct)) {
            return false;
        }
        return Rate.eq(_this.rates, ((MaRateListStruct) _other).rates);
    }

    public CustList<Rate> getRates() {
        return rates;
    }

}
