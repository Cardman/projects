package aiki.beans.facade;

import aiki.beans.ParamNatStruct;
import aiki.game.UsesOfMove;
import code.bean.nat.NatArrayStruct;
import code.bean.nat.PairStruct;
import code.expressionlanguage.structs.StringStruct;
import code.util.AbsMap;
import code.util.EntryCust;

public final class UsesOfMoveStruct extends ParamNatStruct<UsesOfMove> {
    public UsesOfMoveStruct(UsesOfMove _instance) {
        super(_instance);
    }

    public static NatArrayStruct getUsesStr(AbsMap<String, UsesOfMove> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, UsesOfMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new StringStruct(e.getKey()),new UsesOfMoveStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
}
