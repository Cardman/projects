package aiki.game.fight;

import aiki.fight.moves.effects.EffectCounterAttack;

final class EffectCounterAttackCheckerDirectMove implements AbsEffectCounterAttackChecker {
    @Override
    public boolean skip(EffectCounterAttack _effect) {
        return _effect.getSufferingDamageDirectMove().isZero();
    }
}
