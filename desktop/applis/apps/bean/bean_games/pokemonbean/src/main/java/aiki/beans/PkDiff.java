package aiki.beans;

import aiki.beans.game.AikiBeansGameStd;
import aiki.beans.game.DifficultyBean;
import code.bean.nat.analyze.NatConfigurationCore;

public final class PkDiff extends PokemonStandards {
    @Override
    public void buildAddon() {
        AikiBeansGameStd.buildDifficultyBean(this);
        AikiBeansGameStd.buildDifficultyCommonBean(this);
    }

    @Override
    public void initBeans(NatConfigurationCore _conf, String _language) {
        getBeansStruct().setValue(0, beanDiff(_language));
        getBeansStruct().setValue(1, beanDiffCommon(_language));

    }

    public PokemonBeanStruct beanDiff(String _language) {
        return bean(new DifficultyBean(), _language);
    }
}
