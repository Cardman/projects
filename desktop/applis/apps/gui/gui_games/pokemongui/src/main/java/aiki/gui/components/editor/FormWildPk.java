package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class FormWildPk extends FormAbsPk {
    private WildPk wildPk;
    private AbsScrollPane view;
    public FormWildPk(AbstractProgramInfos _ed, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _f) {
        super(_ed, _facade, _sub, _f);
    }

    public void feedForm() {
        wildPk = Instances.newWildPk();
        feedFormQuick();
        view = getApi().getCompoFactory().newAbsScrollPane();
        getForm().add(view);
        getLevel().getTextLong().addChangeListener(new RefreshWildPkMoves(getFacadeGame(),this));
        getName().getSelectUniq().getSelect().addListener(new RefreshWildPkMoves(getFacadeGame(),this));
    }

    public void feedForm(WildPk _wp) {
        wildPk = ConverterCommonMapUtil.copyWildPk(_wp);
        feedFormQuick(wildPk);
        RefreshWildPkMoves.act(getApi(), getFacadeGame(),this);
    }

    public void feedSubs(IdList<SubscribedTranslation> _subs) {
        feedSubsQuick(_subs);
        _subs.add(new RefreshWildPkMoves(getFacadeGame(),this));
    }

    public WildPk buildEntity() {
        buildEntity(wildPk);
        return getWildPk();
    }

    public WildPk getWildPk() {
        return wildPk;
    }

    public AbsScrollPane getView() {
        return view;
    }

}
