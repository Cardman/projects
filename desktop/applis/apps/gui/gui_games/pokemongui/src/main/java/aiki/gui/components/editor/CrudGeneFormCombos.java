package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormCombos extends CrudGeneFormListSub<EditedCrudPair<StringList, EffectCombo>> {

    private GeneComponentModelSubscribeStringList key;
    private GeneComponentModelSubscribeEffectCombo value;

    public CrudGeneFormCombos(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact,_facade,_sub, _fr, null);
    }
    public void initForm(AbstractProgramInfos _core) {
        initForm();
        getCrudGeneFormSubContent().clearSub();
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        SubscribedTranslationList subscription_ = getCrudGeneFormSubContent().getSubscription();
        SubscribedTranslationMessagesFactoryMv factoryMv_ = subscription_.getFactoryMv();
        key = new GeneComponentModelSubscribeStringList(_core, facadeGame_, subscription_, getFrame(), factoryMv_);
        value = new GeneComponentModelSubscribeEffectCombo(_core, facadeGame_, subscription_, getFrame());
        initForm(new DisplayKeyOnlyInteger<EditedCrudPair<StringList, EffectCombo>>(), new GeneComponentModelSimplePair<StringList, EffectCombo>(_core, new GeneComponentModelSubscribeFactoryDirect<StringList>(key), new GeneComponentModelSubscribeFactoryDirect<EffectCombo>(value)));
        CustList<EditedCrudPair<StringList, EffectCombo>> ls_ = new CustList<EditedCrudPair<StringList, EffectCombo>>();
        for (ListEffectCombo e:  facadeGame_.getData().getCombos().getEffects()) {
            ls_.add(new EditedCrudPair<StringList, EffectCombo>(e.getList(),e.getCombo()));
        }
        setupValues(ls_);
        getFrame().setContentPane(getGroup());
        getFrame().setVisible(true);
        getFrame().pack();
    }

    @Override
    protected void afterModif(int _index, EditedCrudPair<StringList, EffectCombo> _value) {
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        if (_index > -1) {
            facadeGame_.getData().getCombos().getEffects().remove(_index);
            getList().remove(_index);
            afterModif();
            return;
        }
        if (getSelectedIndex() < 0) {
            facadeGame_.getData().getCombos().getEffects().add(new ListEffectCombo(_value.getKey(), _value.getValue()));
            afterModif();
            return;
        }
        facadeGame_.getData().getCombos().getEffects().set(getSelectedIndex(), new ListEffectCombo(_value.getKey(), _value.getValue()));
        afterModif();
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        return new IdList<SubscribedTranslation>();
    }

    public GeneComponentModelSubscribeStringList getKey() {
        return key;
    }

    public GeneComponentModelSubscribeEffectCombo getValue() {
        return value;
    }

}
