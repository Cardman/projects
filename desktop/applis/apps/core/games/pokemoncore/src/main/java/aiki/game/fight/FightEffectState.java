package aiki.game.fight;

import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import code.maths.Rate;
import code.util.Ints;

class FightEffectState {
    private final boolean firstEffect;
    private final int index;
    private final Ints previousEffect;
    private String attaqueLanceur;
    private Effect effet;
    private Fighter creature;
    private Rate tauxMultPv;
    private MoveData fAttFinal;
    private boolean achieveTarget;

    FightEffectState(boolean _f, int _i, Ints _p) {
        this.firstEffect = _f;
        this.index = _i;
        this.previousEffect = _p;
    }

    boolean isFirstEffect() {
        return firstEffect;
    }

    int getIndex() {
        return index;
    }

    Ints getPreviousEffect() {
        return previousEffect;
    }

    Rate getTauxMultPv() {
        return tauxMultPv;
    }

    void setTauxMultPv(Rate _t) {
        this.tauxMultPv = _t;
    }

    Fighter getCreature() {
        return creature;
    }

    void setCreature(Fighter _c) {
        this.creature = _c;
    }

    MoveData getfAttFinal() {
        return fAttFinal;
    }

    void setfAttFinal(MoveData _f) {
        this.fAttFinal = _f;
    }

    Effect getEffet() {
        return effet;
    }

    void setEffet(Effect _e) {
        this.effet = _e;
    }

    String getAttaqueLanceur() {
        return attaqueLanceur;
    }

    void setAttaqueLanceur(String _a) {
        this.attaqueLanceur = _a;
    }

    boolean isAchieveTarget() {
        return achieveTarget;
    }

    void achieveTar() {
        this.achieveTarget = true;
    }
}
