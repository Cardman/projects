package aiki.beans.endround;
import aiki.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;

public class EffectEndRoundGlobalBean extends EffectEndRoundBean {
    private StringList immuneTypes;
    private Rate damageEndRound;
    private Rate healingEndRound;
    private Rate healingEndRoundGround;
    private boolean puttingKo;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        MoveData move_ = data_.getMove(getElement().getElement());
        EffectGlobal effect_ = null;
        for (Effect e: move_.getEffects()) {
            if (e instanceof EffectGlobal) {
                effect_ = (EffectGlobal) e;
                break;
            }
        }
        if (effect_ == null) {
            return;
        }
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringList immuneTypes_;
        immuneTypes_ = new StringList();
        for (String t: effect_.getImmuneTypes()) {
            immuneTypes_.add(translatedTypes_.getVal(t));
        }
        immuneTypes_.sort();
        immuneTypes = immuneTypes_;
        damageEndRound = effect_.getDamageEndRound();
        healingEndRound = effect_.getHealingEndRound();
        healingEndRoundGround = effect_.getHealingEndRoundGround();
        puttingKo = effect_.getPuttingKo();
    }

    public Rate getDamageEndRound() {
        return damageEndRound;
    }

    public Rate getHealingEndRoundGround() {
        return healingEndRoundGround;
    }

    public Rate getHealingEndRound() {
        return healingEndRound;
    }

    public boolean getPuttingKo() {
        return puttingKo;
    }

    public StringList getImmuneTypes() {
        return immuneTypes;
    }
}