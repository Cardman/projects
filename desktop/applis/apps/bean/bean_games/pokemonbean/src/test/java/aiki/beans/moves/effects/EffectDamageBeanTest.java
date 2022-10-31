package aiki.beans.moves.effects;

import aiki.db.DataBase;
import aiki.game.fight.Fight;
import org.junit.Test;

public final class EffectDamageBeanTest extends InitDbMoveEffectDamage {

    @Test
    public void hasConstPower1() {
        assertFalse(callEffectDamageBeanHasConstPower(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam(DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR,true,true,1,true,true,true)),0)));
    }
    @Test
    public void hasConstPower2() {
        assertTrue(callEffectDamageBeanHasConstPower(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,true,true,true)),0)));
    }
}
