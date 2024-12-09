package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class CrudGeneFormEntImgType extends CrudGeneFormListSub<EditedCrudPair<String, String>> implements AbsCrudGeneFormTrCstOpen {

    private GeneComponentModelImgType keyImg;

    public CrudGeneFormEntImgType(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
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
        SubscribedTranslationMessagesFactoryTy factoryTy_ = getCrudGeneFormSubContent().getSubscription().getFactoryTy();
        AbsMap<String, String> messages_ = factoryTy_.getContainer().buildMessages(_core,getCrudGeneFormSubContent().getFacadeGame());
//        geneComponentModelSelectKey = ConverterCommonMapUtil.buildPk(getFactory(), getCrudGeneFormSubContent().getFacadeGame(),subscription());
        keyImg = new GeneComponentModelImgType(getFrame(), getFactory(), facadeGame_, subscription());
//        setGeneKey(geneComponentModelSelectKey.getSelectUniq());
        getCrudGeneFormSubContent().addSubRoot(new SubscribedTranslationMessages<String>(messages_, factoryTy_, new StringMap<String>()));
        Comparing<EditedCrudPair<String, String>> cmp_ = new ComparatorTrWrapperPairs<String, String>().wrap(messages_);
        initForm(new DisplayKeyOnly<String, String>(messages_), keyImg, cmp_,new ValidateElementPairId<String>());
        setupValues(new MapToEntriesListUtil<String, String>().build(facadeGame_.getData().getTypesColors()));
        getFrame().setContentPane(getGroup());
        getFrame().setVisible(true);
        getFrame().pack();
    }

    @Override
    protected void afterModif(int _index, EditedCrudPair<String, String> _value) {
        String key_ = _value.getKey();
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        if (_index > -1) {
            facadeGame_.getData().getTypesColors().removeKey(key_);
            getList().remove(_index);
            getCrudGeneFormSubContent().removeOpenSub();
            afterModif();
            return;
        }
        if (getSelectedIndex() < 0) {
            facadeGame_.getData().getTypesColors().addEntry(key_,_value.getValue());
            getCrudGeneFormSubContent().removeOpenSub();
            afterModif();
            return;
        }
        facadeGame_.getData().getTypesColors().set(key_, _value.getValue());
        getCrudGeneFormSubContent().removeOpenSub();
        afterModif();
    }

    @Override
    public void cancel() {
        getCrudGeneFormSubContent().removeOpenSub();
        cancelBase();
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(keyImg.getGeneComponentModelSelectKey().getSubs());
        return all_;
    }

}
