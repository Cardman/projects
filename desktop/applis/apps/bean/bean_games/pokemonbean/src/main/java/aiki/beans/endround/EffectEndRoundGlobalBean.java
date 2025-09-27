package aiki.beans.endround;
import aiki.beans.*;
import aiki.beans.TranslatedKey;
import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.instances.Instances;
import code.maths.Rate;
import code.scripts.pages.aiki.*;
import code.util.*;

public class EffectEndRoundGlobalBean extends EffectEndRoundBean {
    private CustList<TranslatedKey> immuneTypes;
    private CustList<TranslatedKey> immuDamageByDisappearingMoves;
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
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        StringList immuneTypes_;
//        immuneTypes_ = new StringList();
//        for (String t: effect_.getImmuneTypes()) {
//            immuneTypes_.add(translatedTypes_.getVal(t));
//        }
//        immuneTypes_.sort();
        immuneTypes = listTrStringsTy(effect_.getImmuneTypes(),getFacade());
        immuDamageByDisappearingMoves = listTrStringsMv(effect_.getImmuDamageByDisappearingMoves(),getFacade());
        damageEndRound = effect_.getDamageEndRound();
        healingEndRound = effect_.getHealingEndRound();
        healingEndRoundGround = effect_.getHealingEndRoundGround();
        puttingKo = effect_.getPuttingKo();
    }

    @Override
    public void buildSub() {
        super.buildSub();
        displayIntDef(damageEndRound, MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_DAMAGE_END_ROUND);
        displayIntDef(healingEndRoundGround,MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_HEALING_END_ROUND_GROUND);
        displayIntDef(healingEndRound,MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_HEALING_END_ROUND);
        displayBoolTrue(toInt(puttingKo),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_PUTTING_KO);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,immuneTypes,MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_IMMUNE_TYPES);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,immuDamageByDisappearingMoves,MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_IMMUNE_DISAPPEARED);
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

    public CustList<TranslatedKey> getImmuneTypes() {
        return immuneTypes;
    }

    public CustList<TranslatedKey> getImmuDamageByDisappearingMoves() {
        return immuDamageByDisappearingMoves;
    }
}