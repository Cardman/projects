package aiki.game.fight;

import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectTeam;
import code.maths.Rate;

final class EffectTeamMultStatRetrieverFoeTeam implements AbsEffectTeamMultStatRetriever {
    @Override
    public Rate retrieve(EffectTeam _effect, Statistic _statistic) {
        if (_effect.getMultStatisticFoe().contains(_statistic)) {
            return _effect.getMultStatisticFoe().getVal(_statistic);
        }
        return Rate.one();
    }
}
