package aiki.game.fight;

import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectTeam;
import code.maths.Rate;

interface AbsEffectTeamMultStatRetriever {
    Rate retrieve(EffectTeam _effect, Statistic _statistic);
}
