package aiki.gui.components.editor;

import aiki.fight.util.*;

public final class StatisticPokemonTechnicalCopier implements AbsTechnicalCopier<StatisticPokemon> {
    @Override
    public StatisticPokemon copy(StatisticPokemon _e) {
        return new StatisticPokemon(_e.getStatistic(), _e.getPokemon());
    }
}
