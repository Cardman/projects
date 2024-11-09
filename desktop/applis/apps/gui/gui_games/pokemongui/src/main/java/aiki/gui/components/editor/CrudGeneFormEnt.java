package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormEnt<T> extends CrudGeneFormSub<String, T> {
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
        GeneComponentModel<EditedCrudPair<String,T>> key_ = factoryCommonParam.build(getFrame(), _core, getCrudGeneFormSubContent());
//        setGeneKey(geneComponentModelSelectKey.getSelectUniq());
        getCrudGeneFormSubContent().addSubRoot(new SubscribedTranslationMessages(messages_,factoryCommonParam));
        initForm(messages_, key_, factoryCommonParam.all(facadeGame_));
        getFrame().setContentPane(getGroup());
        getFrame().setVisible(true);
        getFrame().pack();
    }

    @Override
    protected void afterModif(int _index, EditedCrudPair<String, T> _value) {
        String key_ = _value.getKey();
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
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
            factoryCommonParam.completeQuickMembers(facadeGame_,key_,_value.getValue());
            factoryCommonParam.removeOpenSub(getCrudGeneFormSubContent());
            afterModif();
            return;
        }
        factoryCommonParam.all(facadeGame_).set(key_, _value.getValue());
        factoryCommonParam.removeOpenSub(getCrudGeneFormSubContent());
        afterModif();
    }

    @Override
    public void cancel() {
        factoryCommonParam.removeOpenSub(getCrudGeneFormSubContent());
        cancelBase();
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(factoryCommonParam.all());
        return all_;
    }

}
