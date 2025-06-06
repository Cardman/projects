package aiki.gui.components.editor;

import aiki.db.DataBase;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.Comparing;

public final class CrudGeneFormEnt<T> extends CrudGeneFormListSub<EditedCrudPair<String, T>> implements AbsCrudGeneFormTrCstOpen{
    private final SubscribedTranslationMessagesFactoryCommonParam<T> factoryCommonParam;
//    private GeneComponentModelPokemonData geneComponentModelPokemonData;

    public CrudGeneFormEnt(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, SubscribedTranslationMessagesFactoryCommonParam<T> _factoMess) {
        super(_fact,_facade,_sub, _fr, null);
        factoryCommonParam = _factoMess;
    }

    @Override
    public void initFormAll() {
        initForm(getFactory());
    }

    public void initForm(AbstractProgramInfos _core) {
        initForm();
        getCrudGeneFormSubContent().clearSub();
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        AbsMap<String, String> messages_ = factoryCommonParam.getContainer().buildMessages(_core,getCrudGeneFormSubContent().getFacadeGame());
//        geneComponentModelSelectKey = ConverterCommonMapUtil.buildPk(getFactory(), getCrudGeneFormSubContent().getFacadeGame(),subscription());
        GeneComponentModel<EditedCrudPair<String,T>> key_ = factoryCommonParam.build(getFrame(), _core, getCrudGeneFormSubContent());
//        setGeneKey(geneComponentModelSelectKey.getSelectUniq());
        getCrudGeneFormSubContent().addSubRoot(new SubscribedTranslationMessages<String>(messages_,factoryCommonParam, new StringMap<String>()));
        Comparing<EditedCrudPair<String, T>> cmp_ = new ComparatorTrWrapperPairs<String, T>().wrap(messages_);
        initForm(new DisplayKeyOnly<String, T>(messages_),key_, cmp_,new ValidateElementPair<String, T>(cmp_));
        setupValues(new MapToEntriesListUtil<String, T>().build(factoryCommonParam.all(facadeGame_)));
        scroll();
    }

    @Override
    public void afterModif(int _index, EditedCrudPair<String, T> _value) {
        String key_ = _value.getKey();
        if (key_.isEmpty()) {
            cancel();
            return;
        }
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        factoryCommonParam.all(facadeGame_).removeKey(DataBase.EMPTY_STRING);
        stopEffect();
        if (_index > -1) {
            int old_ = factoryCommonParam.all(facadeGame_).size();
            StringMap<StringMap<String>> trs_ = factoryCommonParam.buildMessages(facadeGame_);
            StringMap<StringMap<String>> bk_ = ConverterCommonMapUtil.backUp(trs_);
            factoryCommonParam.delete(facadeGame_,key_);
            if (old_ > factoryCommonParam.all(facadeGame_).size()) {
                trs_.clear();
                trs_.addAllEntries(bk_);
                getList().remove(_index);
                factoryCommonParam.removeOpenSub(getCrudGeneFormSubContent());
                afterModif();
            }
            return;
        }
        if (getSelectedIndex() < 0) {
            factoryCommonParam.all(facadeGame_).addEntry(key_,_value.getValue());
            factoryCommonParam.removeOpenSub(getCrudGeneFormSubContent());
            afterModif();
            return;
        }
        factoryCommonParam.all(facadeGame_).set(key_, _value.getValue());
        getCrudGeneFormSubContent().getSubscription().updateRenamingId(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING,new StringList());
        getCrudGeneFormSubContent().getSubscription().update();
        factoryCommonParam.removeOpenSub(getCrudGeneFormSubContent());
        afterModif();
    }

    @Override
    public void cancel() {
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        factoryCommonParam.all(facadeGame_).removeKey(DataBase.EMPTY_STRING);
        stopEffect();
        factoryCommonParam.removeOpenSub(getCrudGeneFormSubContent());
        cancelBase();
    }

    private void stopEffect() {
        if (factoryCommonParam instanceof SubscribedTranslationMessagesFactoryMv) {
            subscription().effect(null);
        }
        if (factoryCommonParam instanceof SubscribedTranslationMessagesFactoryAb) {
            subscription().effectEndRoundAbility(null);
            subscription().effectSendingAbility(null);
        }
        if (factoryCommonParam instanceof SubscribedTranslationMessagesFactoryIt) {
            subscription().effectEndRoundItem(null);
            subscription().effectSendingItem(null);
            subscription().itemForBattle(null);
        }
        if (factoryCommonParam instanceof SubscribedTranslationMessagesFactorySt) {
            subscription().effectEndRoundStatus(null);
        }
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(factoryCommonParam.all());
        return all_;
    }

}
