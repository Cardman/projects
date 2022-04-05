package aiki.beans;

import aiki.beans.game.AikiBeansGameStd;

public final class PkDiff extends PokemonStandards {
    @Override
    public void buildAddon() {
        AikiBeansGameStd.buildDifficultyBean(this);
    }
}
