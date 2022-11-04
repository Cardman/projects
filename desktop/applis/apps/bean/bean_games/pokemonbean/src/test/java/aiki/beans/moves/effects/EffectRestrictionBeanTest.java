package aiki.beans.moves.effects;

import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import org.junit.Test;

public final class EffectRestrictionBeanTest extends InitDbMoveEffectRestriction {
    @Test
    public void getForbidTargetUsingItem1() {
        assertFalse(callEffectRestrictionBeanForbidTargetUsingItemGet(dispMoveEffRestriction(false, MoveChoiceRestrictionType.NOTHING)));
    }
    @Test
    public void getForbidTargetUsingItem2() {
        assertTrue(callEffectRestrictionBeanForbidTargetUsingItemGet(dispMoveEffRestriction(true, MoveChoiceRestrictionType.NOTHING)));
    }
    @Test
    public void forbid1() {
        assertFalse(callEffectRestrictionBeanForbid(dispMoveEffRestriction(true, MoveChoiceRestrictionType.NOTHING)));
    }
    @Test
    public void forbid2() {
        assertTrue(callEffectRestrictionBeanForbid(dispMoveEffRestriction(true, MoveChoiceRestrictionType.FORCE)));
    }
    @Test
    public void forbidStatusMove1() {
        assertFalse(callEffectRestrictionBeanForbidStatusMove(dispMoveEffRestriction(true, MoveChoiceRestrictionType.NOTHING)));
    }
    @Test
    public void forbidStatusMove2() {
        assertTrue(callEffectRestrictionBeanForbidStatusMove(dispMoveEffRestriction(true, MoveChoiceRestrictionType.CATEGORIE_AUTRE)));
    }
    @Test
    public void forbidLastMove1() {
        assertFalse(callEffectRestrictionBeanForbidLastMove(dispMoveEffRestriction(true, MoveChoiceRestrictionType.NOTHING)));
    }
    @Test
    public void forbidLastMove2() {
        assertTrue(callEffectRestrictionBeanForbidLastMove(dispMoveEffRestriction(true, MoveChoiceRestrictionType.DER)));
    }
    @Test
    public void forbidUserMoves1() {
        assertFalse(callEffectRestrictionBeanForbidUserMoves(dispMoveEffRestriction(true, MoveChoiceRestrictionType.NOTHING)));
    }
    @Test
    public void forbidUserMoves2() {
        assertTrue(callEffectRestrictionBeanForbidUserMoves(dispMoveEffRestriction(true, MoveChoiceRestrictionType.LANCEUR_ATTAQUES)));
    }
    @Test
    public void forbidUseMove1() {
        assertFalse(callEffectRestrictionBeanForbidUseMove(dispMoveEffRestriction(true, MoveChoiceRestrictionType.NOTHING)));
    }
    @Test
    public void forbidUseMove2() {
        assertTrue(callEffectRestrictionBeanForbidUseMove(dispMoveEffRestriction(true, MoveChoiceRestrictionType.FORBIDDEN)));
    }
    @Test
    public void forceUseMove1() {
        assertFalse(callEffectRestrictionBeanForceUseMove(dispMoveEffRestriction(true, MoveChoiceRestrictionType.NOTHING)));
    }
    @Test
    public void forceUseMove2() {
        assertTrue(callEffectRestrictionBeanForceUseMove(dispMoveEffRestriction(true, MoveChoiceRestrictionType.FORCE)));
    }
}
