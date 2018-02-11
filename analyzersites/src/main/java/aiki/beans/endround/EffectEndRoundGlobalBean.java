package aiki.beans.endround;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;

public class EffectEndRoundGlobalBean extends EffectEndRoundBean {

    @Accessible
    private StringList immuneTypes;

    @Accessible
    private Rate damageEndRound;

    @Accessible
    private Rate healingEndRound;

    @Accessible
    private Rate healingEndRoundGround;

    @Accessible
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
}
