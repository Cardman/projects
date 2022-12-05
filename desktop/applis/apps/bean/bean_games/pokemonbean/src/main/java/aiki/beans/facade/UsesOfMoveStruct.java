package aiki.beans.facade;

import aiki.game.UsesOfMove;
import code.bean.nat.NaNuSt;
import code.bean.nat.NaStSt;
import code.bean.nat.NatArrayStruct;
import code.bean.nat.PairStruct;
import code.util.AbsMap;
import code.util.EntryCust;

public final class UsesOfMoveStruct extends NaNuSt {
    private final UsesOfMove inst;
    public UsesOfMoveStruct(UsesOfMove _instance) {
        inst=(_instance);
    }

    public UsesOfMove getUsesOfMove() {
        return inst;
    }
    public static NatArrayStruct getUsesStr(AbsMap<String, UsesOfMove> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, UsesOfMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaStSt(e.getKey()),new UsesOfMoveStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
}
