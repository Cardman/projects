package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import code.gui.initialize.AbstractProgramInfos;

public final class UpdateStatEvMessage implements SubscribedTranslation{
    private final Statistic stat;
    private final FormStatBaseEv form;

    public UpdateStatEvMessage(Statistic _s, FormStatBaseEv _f) {
        this.stat = _s;
        this.form = _f;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _current) {
        form.getGroup().setTitledBorder(_facade.getData().getTranslatedStatistics().getVal(_api.getLanguage()).getVal(stat));
    }
}
