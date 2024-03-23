package cards.tarot;

import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.CustList;
import code.util.IdList;
import code.util.core.BoolVal;

public final class GameTarotContent {
    /** Ce sont les poignees annoncees par le(s) joueur(s) */
    private CustList<IdList<Handfuls>> declaresHandfuls = new CustList<IdList<Handfuls>>();
    /** Ce sont les miseres annoncees par le(s) joueur(s) */
    private CustList<IdList<Miseres>> declaresMiseres = new CustList<IdList<Miseres>>();
    /** Ce sont les petits au bout par le(s) joueur(s) */
    private CustList<BoolVal> smallBound = new CustList<BoolVal>();
    /** Poignees */
    private CustList<HandTarot> handfuls = new CustList<HandTarot>();
    public GameTarotContent(int _nb) {
        for (int i = 0; i < _nb; i++) {
            declaresMiseres.add(new IdList<Miseres>());
            handfuls.add(new HandTarot());
            declaresHandfuls.add(new IdList<Handfuls>());
            smallBound.add(BoolVal.FALSE);
        }
    }

    public CustList<IdList<Handfuls>> getDeclaresHandfuls() {
        return declaresHandfuls;
    }

    public void setDeclaresHandfuls(CustList<IdList<Handfuls>> _d) {
        this.declaresHandfuls = _d;
    }

    public CustList<BoolVal> getSmallBound() {
        return smallBound;
    }

    public void setSmallBound(CustList<BoolVal> _s) {
        this.smallBound = _s;
    }

    public CustList<HandTarot> getHandfuls() {
        return handfuls;
    }

    public void setHandfuls(CustList<HandTarot> _h) {
        this.handfuls = _h;
    }

    public CustList<IdList<Miseres>> getDeclaresMiseres() {
        return declaresMiseres;
    }

    public void setDeclaresMiseres(CustList<IdList<Miseres>> _d) {
        this.declaresMiseres = _d;
    }

}
