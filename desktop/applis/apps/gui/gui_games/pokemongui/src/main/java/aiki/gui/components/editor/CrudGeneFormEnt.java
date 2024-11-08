package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormEnt<T> extends CrudGeneFormSub<String, T> {
    private GeneComponentModelEltStrSub geneComponentModelSelectKey;
    private final SubscribedTranslationMessagesFactoryCommonParam<T> factoryCommonParam;
//    private GeneComponentModelPokemonData geneComponentModelPokemonData;

    public CrudGeneFormEnt(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, SubscribedTranslationMessagesFactoryCommonParam<T> _factoMess) {
        super(_fact,_facade,_sub, _fr);
        factoryCommonParam = _factoMess;
    }
    public void initForm(AbstractProgramInfos _core) {
        initForm();
        getCrudGeneFormSubContent().clearSub();
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        StringMap<String> messages_ = factoryCommonParam.buildMessages(_core,getCrudGeneFormSubContent().getFacadeGame());
//        geneComponentModelSelectKey = ConverterCommonMapUtil.buildPk(getFactory(), getCrudGeneFormSubContent().getFacadeGame(),subscription());
        GeneComponentModel<T> key_ = factoryCommonParam.build(getFrame(), _core, getCrudGeneFormSubContent());
//        setGeneKey(geneComponentModelSelectKey.getSelectUniq());
        getCrudGeneFormSubContent().addSubRoot(new SubscribedTranslationMessages(messages_,factoryCommonParam));
        initForm(messages_, getGeneKey(), key_, factoryCommonParam.all(facadeGame_));
        getFrame().setContentPane(getGroup());
        getFrame().setVisible(true);
        getFrame().pack();
    }

    @Override
    protected void afterModif(int _index, String _key, T _value) {
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        if (_index > -1) {
            int old_ = factoryCommonParam.all(facadeGame_).size();
            StringMap<StringMap<String>> trs_ = factoryCommonParam.buildMessages(facadeGame_);
            StringMap<StringMap<String>> bk_ = ConverterCommonMapUtil.backUp(trs_);
            factoryCommonParam.delete(facadeGame_,_key);
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
            factoryCommonParam.completeQuickMembers(facadeGame_,_key,_value);
            factoryCommonParam.removeOpenSub(getCrudGeneFormSubContent());
            afterModif();
            return;
        }
        factoryCommonParam.all(facadeGame_).set(_key, _value);
        factoryCommonParam.removeOpenSub(getCrudGeneFormSubContent());
        afterModif();
    }

    @Override
    public void cancel() {
        factoryCommonParam.removeOpenSub(getCrudGeneFormSubContent());
        cancelBase();
    }

    @Override
    protected void build() {
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        geneComponentModelSelectKey = ConverterCommonMapUtil.merge(getFactory(), facadeGame_, factoryCommonParam, factoryCommonParam.all(facadeGame_).getKeys());
        setGeneKey(geneComponentModelSelectKey.getSelectUniq());
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(geneComponentModelSelectKey.getSubs());
        all_.addAllElts(factoryCommonParam.all());
        return all_;
    }

}
