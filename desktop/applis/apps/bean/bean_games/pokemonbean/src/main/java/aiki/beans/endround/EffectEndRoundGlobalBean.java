package aiki.beans.endround;
import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.instances.Instances;
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
        DataBase data_ = getDataBase();
        MoveData move_ = data_.getMove(getElement().getElement());
        EffectGlobal effect_ = global(move_);
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

    public static EffectGlobal global(MoveData _move) {
        for (Effect e: _move.getEffects()) {
            if (e instanceof EffectGlobal) {
                return (EffectGlobal) e;
            }
        }
        return Instances.newEffectGlobal();
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