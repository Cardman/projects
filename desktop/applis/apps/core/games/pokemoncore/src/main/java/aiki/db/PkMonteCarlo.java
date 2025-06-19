package aiki.db;

import code.maths.*;
import code.maths.montecarlo.*;
import code.util.*;

public final class PkMonteCarlo<E> {
    private final DataBase dataBase;
    private final AbMonteCarlo<E> law;
    private final PkMonteCarloEvts evts;

    public PkMonteCarlo(DataBase _d, AbMonteCarlo<E> _l) {
        this(_d,_l,new PkMonteCarloEvts(new CustList<LgInt>()));
    }

    public PkMonteCarlo(DataBase _d, AbMonteCarlo<E> _l, PkMonteCarloEvts _e) {
        this.dataBase = _d;
        this.law = _l;
        this.evts = _e;
    }

    public E editNumber() {
        LgInt e_ = evts.tryIncr();
        if (e_ != null) {
            return law.editNumberSeed(e_);
        }
        return law.editNumber(dataBase.getMaxRd(), dataBase.getGenerator());
    }
}
