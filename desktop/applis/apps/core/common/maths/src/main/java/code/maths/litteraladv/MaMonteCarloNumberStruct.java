package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.montecarlo.EventFreq;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaMonteCarloNumberStruct implements MaStruct {
    private final MonteCarloNumber law;

    public MaMonteCarloNumberStruct(MonteCarloNumber _law) {
        this.law = _law;
    }

    public MonteCarloNumber getLaw() {
        return law;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaMonteCarloNumberStruct)) {
            return false;
        }
        MonteCarloNumber lawOth_ = ((MaMonteCarloNumberStruct) _other).law;
        return eq(law,lawOth_);
    }

    static boolean eq(MonteCarloNumber _this, MonteCarloNumber _other) {
        CustList<EventFreq<Rate>> thisEvts_ = _this.getEvents();
        CustList<EventFreq<Rate>> othEvts_ = _other.getEvents();
        int len_ = thisEvts_.size();
        if (len_ != othEvts_.size()) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            EventFreq<Rate> thisEvt_ = thisEvts_.get(i);
            EventFreq<Rate> othEvt_ = othEvts_.get(i);
            if (!MaEventFreqStruct.eq(thisEvt_,othEvt_)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String displayRsult() {
        CustList<EventFreq<Rate>> events_ = law.getEvents();
        StringList list_ = new StringList(new CollCapacity(events_.size()));
        for (EventFreq<Rate> e: events_) {
            list_.add(MaEventFreqStruct.exportStr(e));
        }
        return "("+ StringUtil.join(list_,",") +")";
    }
}
