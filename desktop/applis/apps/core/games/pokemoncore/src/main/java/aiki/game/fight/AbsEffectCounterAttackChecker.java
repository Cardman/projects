package aiki.game.fight;

import aiki.fight.moves.effects.EffectCounterAttack;

interface AbsEffectCounterAttackChecker {
    boolean skip(EffectCounterAttack _effect);
}
