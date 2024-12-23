package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class CrudGeneFormEntImg extends CrudGeneFormListSub<EditedCrudPair<String, ImageArrayBaseSixtyFour>> implements AbsCrudGeneFormTrCstOpen {
    private final SubscribedTranslationMessagesFactoryCommonInt<ImageArrayBaseSixtyFour> factoryCommonParam;

    public CrudGeneFormEntImg(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, SubscribedTranslationMessagesFactoryCommonInt<ImageArrayBaseSixtyFour> _factoMess) {
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
        GeneComponentModel<EditedCrudPair<String, ImageArrayBaseSixtyFour>> key_ = factoryCommonParam.build(getFrame(), _core, getCrudGeneFormSubContent());
//        setGeneKey(geneComponentModelSelectKey.getSelectUniq());
        getCrudGeneFormSubContent().addSubRoot(new SubscribedTranslationMessages<String>(messages_,factoryCommonParam, new StringMap<String>()));
        Comparing<EditedCrudPair<String, ImageArrayBaseSixtyFour>> cmp_ = new ComparatorTrWrapperPairs<String, ImageArrayBaseSixtyFour>().wrap(messages_);
        initForm(new DisplayKeyOnly<String, ImageArrayBaseSixtyFour>(messages_),key_, cmp_,new ValidateElementPairIdImg());
        setupValues(new MapToEntriesListUtil<String, ImageArrayBaseSixtyFour>().build(factoryCommonParam.all(facadeGame_)));
        getFrame().setContentPane(getGroup());
        getFrame().setVisible(true);
        getFrame().pack();
    }

    @Override
    protected void afterModif(int _index, EditedCrudPair<String, ImageArrayBaseSixtyFour> _value) {
        String key_ = _value.getKey();
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        if (_index > -1) {
            factoryCommonParam.delete(facadeGame_,key_);
            getList().remove(_index);
            factoryCommonParam.removeOpenSub(getCrudGeneFormSubContent());
            afterModif();
            return;
        }
        if (getSelectedIndex() < 0) {
            factoryCommonParam.all(facadeGame_).addEntry(key_,_value.getValue());
            factoryCommonParam.removeOpenSub(getCrudGeneFormSubContent());
            afterModif();
            return;
        }
        factoryCommonParam.all(facadeGame_).set(key_, _value.getValue());
        getCrudGeneFormSubContent().getSubscription().updateRenamingId("","",new StringList());
        getCrudGeneFormSubContent().getSubscription().update();
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
