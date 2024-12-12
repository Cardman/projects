package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormEntImgHeros extends CrudGeneFormListSub<EditedCrudPair<ImageHeroKey, ImageArrayBaseSixtyFour>> implements AbsCrudGeneFormTrCstOpen {
    private final ValidateElementPairIdImgHeros validateElementPairIdImgFree = new ValidateElementPairIdImgHeros();
    private GeneComponentModelImgHeros geneComponentModelImgHeros;
    private final boolean withDirection;
    private final boolean frontImg;

    public CrudGeneFormEntImgHeros(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, boolean _withDir, boolean _front) {
        super(_fact,_facade,_sub, _fr, null);
        withDirection = _withDir;
        frontImg = _front;
    }

    @Override
    public void initFormAll() {
        initForm();
        getCrudGeneFormSubContent().clearSub();
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        geneComponentModelImgHeros = new GeneComponentModelImgHeros(getFactory(),facadeGame_,getCrudGeneFormSubContent().getSubscription(),withDirection);
        initForm(new DisplayKeyOnlyInteger<EditedCrudPair<ImageHeroKey, ImageArrayBaseSixtyFour>>(),geneComponentModelImgHeros, null,validateElementPairIdImgFree);
        setupValues(new MapToEntriesListUtil<ImageHeroKey, ImageArrayBaseSixtyFour>().build(info()));
        getFrame().setContentPane(getGroup());
        getFrame().setVisible(true);
        getFrame().pack();
    }

    @Override
    protected void afterModif(int _index, EditedCrudPair<ImageHeroKey, ImageArrayBaseSixtyFour> _value) {
        ImageHeroKey key_ = _value.getKey();
        if (_index > -1) {
            info().removeKey(key_);
            getList().remove(_index);
            getCrudGeneFormSubContent().removeOpenSub();
            afterModif();
            return;
        }
        if (getSelectedIndex() < 0) {
            info().addEntry(key_,_value.getValue());
            getCrudGeneFormSubContent().removeOpenSub();
            afterModif();
            return;
        }
        info().set(key_, _value.getValue());
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
        return geneComponentModelImgHeros.getEnvironment().getSubs();
    }

    private ImageHeroKeys info() {
        if (withDirection) {
            return getCrudGeneFormSubContent().getFacadeGame().getData().getOverWorldHeros();
        }
        if (frontImg) {
            return getCrudGeneFormSubContent().getFacadeGame().getData().getFrontHeros();
        }
        return getCrudGeneFormSubContent().getFacadeGame().getData().getBackHeros();
    }

    public GeneComponentModelImgHeros getGeneComponentModelImgHeros() {
        return geneComponentModelImgHeros;
    }
}
