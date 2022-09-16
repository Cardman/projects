package aiki.game.fight;

import aiki.fight.moves.effects.EffectCounterAttack;

final class EffectCounterAttackCheckerDropStat implements AbsEffectCounterAttackChecker {
    @Override
    public boolean skip(EffectCounterAttack _effect) {
        return _effect.getDroppedStatDirectMove().isEmpty();
    }
}
