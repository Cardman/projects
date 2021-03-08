package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.montecarlo.EventFreq;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaRepartitionStruct implements MaStruct {
    private final CustList<EventFreq<CustList<LgInt>>> events;

    public MaRepartitionStruct(CustList<EventFreq<CustList<LgInt>>> _idBezout) {
        this.events = _idBezout;
    }

    public CustList<EventFreq<CustList<LgInt>>> getEvents() {
        return events;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqDivs(this, _other);
    }

    @Override
    public String displayRsult() {
        StringList list_ = new StringList(new CollCapacity(events.size()));
        for (EventFreq<CustList<LgInt>> d: events) {
            CustList<LgInt> ev_ = d.getEvent();
            StringList part_ = new StringList(new CollCapacity(ev_.size()));
            for (LgInt e: ev_) {
                part_.add(e.toNumberString());
            }
            list_.add("("+StringUtil.join(part_,",")+","+d.getFreq().toNumberString()+")");
        }
        return "("+ StringUtil.join(list_,",")+")";
    }

    static boolean eqDivs(MaRepartitionStruct _this, MaStruct _other) {
        if (!(_other instanceof MaRepartitionStruct)) {
            return false;
        }
        CustList<EventFreq<CustList<LgInt>>> oth_ = ((MaRepartitionStruct) _other).events;
        CustList<EventFreq<CustList<LgInt>>> this_ = _this.events;
        int len_ = this_.size();
        if (oth_.size() != len_) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            CustList<LgInt> lsThis_ = this_.get(i).getEvent();
            CustList<LgInt> lsOther_ = oth_.get(i).getEvent();
            CustList<LgInt> allThis_ = new CustList<LgInt>(new CollCapacity(lsThis_.size()+1));
            CustList<LgInt> allOther_ = new CustList<LgInt>(new CollCapacity(lsOther_.size()+1));
            allThis_.addAllElts(lsThis_);
            allThis_.add(this_.get(i).getFreq());
            allOther_.addAllElts(lsOther_);
            allOther_.add(oth_.get(i).getFreq());
            if (!LgInt.eq(allThis_, allOther_)) {
                return false;
            }
        }
        return true;
    }
}
