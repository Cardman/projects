package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class CrudGeneFormEntImgFree extends CrudGeneFormListSub<EditedCrudPair<String, ImageArrayBaseSixtyFour>> implements AbsCrudGeneFormTrCstOpen {
    private final ImgRetriever factoryCommonParam;
    private final ValidateElementPairIdImgFree validateElementPairIdImgFree = new ValidateElementPairIdImgFree();

    public CrudGeneFormEntImgFree(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, ImgRetriever _factoMess) {
        super(_fact,_facade,_sub, _fr, null);
        factoryCommonParam = _factoMess;
    }

    @Override
    public void initFormAll() {
        initForm();
        getCrudGeneFormSubContent().clearSub();
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        GeneComponentModel<EditedCrudPair<String, ImageArrayBaseSixtyFour>> key_ = new GeneComponentModelImgFree(getFactory());
        initForm(new DisplayKeyOnlyDirect<ImageArrayBaseSixtyFour>(),key_, new ComparingStringKey<ImageArrayBaseSixtyFour>(),validateElementPairIdImgFree);
        setupValues(new MapToEntriesListUtil<String, ImageArrayBaseSixtyFour>().build(factoryCommonParam.all(facadeGame_)));
        scroll();
    }

    @Override
    public void afterModif(int _index, EditedCrudPair<String, ImageArrayBaseSixtyFour> _value) {
        String old_ = validateElementPairIdImgFree.getOldValue();
        String key_ = _value.getKey();
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        if (_index > -1) {
            factoryCommonParam.all(facadeGame_).removeKey(key_);
            getList().remove(_index);
            update(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
            getCrudGeneFormSubContent().removeOpenSub();
            afterModif();
            return;
        }
        if (old_ == null) {
            factoryCommonParam.all(facadeGame_).addEntry(key_,_value.getValue());
            update(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
            getCrudGeneFormSubContent().removeOpenSub();
            afterModif();
            return;
        }
        if (StringUtil.quickEq(old_, key_)) {
            factoryCommonParam.all(facadeGame_).set(key_, _value.getValue());
            update(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
            getCrudGeneFormSubContent().removeOpenSub();
            afterModif();
            return;
        }
        factoryCommonParam.all(facadeGame_).removeKey(old_);
        factoryCommonParam.all(facadeGame_).addEntry(key_, _value.getValue());
        update(old_,key_);
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
        return new IdList<SubscribedTranslation>();
    }

    private void update(String _oldId, String _newId) {
        getCrudGeneFormSubContent().getSubscription().updateRenamingFileName(_oldId,_newId,factoryCommonParam);
        getCrudGeneFormSubContent().getSubscription().update();
    }
}
