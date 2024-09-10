package aiki.beans.moves.effects;

import aiki.fight.moves.effects.enums.ExchangeType;
import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class EffectSwitchAbilitiesBeanTest extends InitDbMoveEffectSwitch {
    @Test
    public void giveToTarget1() {
        assertTrue(callEffectSwitchAbilitiesBeanGiveToTarget(dispMoveEffSwitchAbilities(NULL_REF,ExchangeType.GIVE_TO_TARGET)));
    }
    @Test
    public void giveToTarget2() {
        assertFalse(callEffectSwitchAbilitiesBeanGiveToTarget(dispMoveEffSwitchAbilities(NULL_REF,ExchangeType.NOTHING)));
    }
    @Test
    public void giveToUser1() {
        assertTrue(callEffectSwitchAbilitiesBeanGiveToUser(dispMoveEffSwitchAbilities(NULL_REF,ExchangeType.GIVE_TO_THROWER)));
    }
    @Test
    public void giveToUser2() {
        assertFalse(callEffectSwitchAbilitiesBeanGiveToUser(dispMoveEffSwitchAbilities(NULL_REF,ExchangeType.NOTHING)));
    }
    @Test
    public void giveConst1() {
        assertTrue(callEffectSwitchAbilitiesBeanGiveConst(dispMoveEffSwitchAbilities(NULL_REF,ExchangeType.GIVE_CONST)));
    }
    @Test
    public void giveConst2() {
        assertFalse(callEffectSwitchAbilitiesBeanGiveConst(dispMoveEffSwitchAbilities(NULL_REF,ExchangeType.NOTHING)));
    }
    @Test
    public void switchAbilities1() {
        assertTrue(callEffectSwitchAbilitiesBeanSwitchAbilities(dispMoveEffSwitchAbilities(NULL_REF,ExchangeType.EXCHANGE)));
    }
    @Test
    public void switchAbilities2() {
        assertFalse(callEffectSwitchAbilitiesBeanSwitchAbilities(dispMoveEffSwitchAbilities(NULL_REF,ExchangeType.NOTHING)));
    }
    @Test
    public void isDefAbility1() {
        assertTrue(callEffectSwitchAbilitiesBeanIsDefAbility(dispMoveEffSwitchAbilities(A_ABILITY,ExchangeType.NOTHING)));
    }
    @Test
    public void isDefAbility2() {
        assertFalse(callEffectSwitchAbilitiesBeanIsDefAbility(dispMoveEffSwitchAbilities(NULL_REF,ExchangeType.NOTHING)));
    }
    @Test
    public void getTrAbility() {
        assertEq(A_ABILITY_TR,callEffectSwitchAbilitiesBeanGetTrAbility(dispMoveEffSwitchAbilities(A_ABILITY,ExchangeType.NOTHING),0));
    }
    @Test
    public void clickAbility1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callEffectSwitchAbilitiesBeanClickAbility(dispMoveEffSwitchAbilities(A_ABILITY,ExchangeType.NOTHING),0));
    }
    @Test
    public void clickAbility2() {
        assertEq(A_ABILITY,callEffectSwitchAbilitiesBeanClickAbilityId(dispMoveEffSwitchAbilities(A_ABILITY,ExchangeType.NOTHING),0));
    }
}
