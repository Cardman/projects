package aiki.beans.moves.effects;

import code.maths.Rate;
import org.junit.Test;

public final class EffectProtectionBeanTest extends InitDbMoveEffectChangeProtect {
    @Test
    public void getProtSingleAgainstKo() {
        assertEq(Rate.one(),callEffectProtectionBeanProtSingleAgainstKoGet(dispMoveEffProtection(true,true,true,true,true)));
    }
    @Test
    public void getProtSingle1() {
        assertTrue(callEffectProtectionBeanProtSingleGet(dispMoveEffProtection(true,true,true,true,true)));
    }
    @Test
    public void getProtSingle2() {
        assertFalse(callEffectProtectionBeanProtSingleGet(dispMoveEffProtection(false,true,true,true,true)));
    }
    @Test
    public void getProtTeamAgainstMultTargets1() {
        assertTrue(callEffectProtectionBeanProtTeamAgainstMultTargetsGet(dispMoveEffProtection(true,true,true,true,true)));
    }
    @Test
    public void getProtTeamAgainstMultTargets2() {
        assertFalse(callEffectProtectionBeanProtTeamAgainstMultTargetsGet(dispMoveEffProtection(true,true,true,true,false)));
    }
    @Test
    public void getProtTeamAgainstPrio1() {
        assertTrue(callEffectProtectionBeanProtTeamAgainstPrioGet(dispMoveEffProtection(true,true,true,true,true)));
    }
    @Test
    public void getProtTeamAgainstPrio2() {
        assertFalse(callEffectProtectionBeanProtTeamAgainstPrioGet(dispMoveEffProtection(true,false,true,true,true)));
    }
    @Test
    public void getProtTeamAgainstStatusMoves1() {
        assertTrue(callEffectProtectionBeanProtTeamAgainstStatusMovesGet(dispMoveEffProtection(true,true,true,true,true)));
    }
    @Test
    public void getProtTeamAgainstStatusMoves2() {
        assertFalse(callEffectProtectionBeanProtTeamAgainstStatusMovesGet(dispMoveEffProtection(true,true,false,true,true)));
    }
    @Test
    public void getProtTeamAgainstDamageMoves1() {
        assertTrue(callEffectProtectionBeanProtTeamAgainstDamageMovesGet(dispMoveEffProtection(true,true,true,true,true)));
    }
    @Test
    public void getProtTeamAgainstDamageMoves2() {
        assertFalse(callEffectProtectionBeanProtTeamAgainstDamageMovesGet(dispMoveEffProtection(true,true,true,false,true)));
    }
}
