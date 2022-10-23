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
        getBeansStruct().setValue(0, beanDiff(_language));

    }

    public PokemonBeanStruct beanDiff(String _language) {
        return bean(new DifficultyBean(), _language);
    }
}
