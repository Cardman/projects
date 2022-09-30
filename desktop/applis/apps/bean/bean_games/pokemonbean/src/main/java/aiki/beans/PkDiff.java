package aiki.beans;

import aiki.beans.game.AikiBeansGameStd;
import aiki.beans.game.DifficultyBean;
import code.formathtml.Configuration;

public final class PkDiff extends PokemonStandards {
    @Override
    public void buildAddon() {
        AikiBeansGameStd.buildDifficultyBean(this);
    }

    @Override
    public void initBeans(Configuration _conf, String _language) {
        getBeansStruct().setValue(0,bean(new DifficultyBean(), AikiBeansGameStd.TYPE_DIFFICULTY_BEAN,_language));

    }
}
