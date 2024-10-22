package aiki.db;

import aiki.map.pokemon.*;
import code.util.*;

public final class ChangeStringFieldPkListTrainer extends ChangeStringFieldPkList {
    private final CustList<PkTrainer> trainer;

    public ChangeStringFieldPkListTrainer(ChangeStringFieldVisit _v, CustList<PkTrainer> _ls) {
        super(_v);
        trainer = _ls;
    }

    public int length(){
        return trainer.size();
    }

    @Override
    public Pokemon elt(int _i) {
        return trainer.get(_i);
    }
}
