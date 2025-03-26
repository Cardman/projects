package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormCombos extends CrudGeneFormListSub<ListEffectCombo> implements AbsCrudGeneFormTrCstOpen {

    private GeneComponentModelSubscribeEffectCombo value;

    public CrudGeneFormCombos(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact,_facade,_sub, _fr, null);
    }

    @Override
    public void initFormAll() {
        initForm(getFactory());
    }

    public void initForm(AbstractProgramInfos _core) {
        initForm();
        getCrudGeneFormSubContent().clearSub();
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        SubscribedTranslationList subscription_ = getCrudGeneFormSubContent().getSubscription();
        value = new GeneComponentModelSubscribeEffectCombo(_core, facadeGame_, subscription_, getFrame());
        initForm(new DisplayKeyOnlyInteger<ListEffectCombo>(), value);
        setupValues(facadeGame_.getData().getCombos().getEffects());
        scroll();
    }

    @Override
    public void afterModif(int _index, ListEffectCombo _value) {
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        if (_index > -1) {
            facadeGame_.getData().getCombos().getEffects().remove(_index);
            getList().remove(_index);
            afterModif();
            return;
        }
        if (getSelectedIndex() < 0) {
            facadeGame_.getData().getCombos().getEffects().add(_value);
            afterModif();
            return;
        }
        facadeGame_.getData().getCombos().getEffects().set(getSelectedIndex(), _value);
        afterModif();
    }

    @Override
    public void cancel() {
        subscription().effectEndRoundCombo(null);
        cancelBase();
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        return value.all();
    }

    public GeneComponentModelSubscribeEffectCombo getValue() {
        return value;
    }

}
