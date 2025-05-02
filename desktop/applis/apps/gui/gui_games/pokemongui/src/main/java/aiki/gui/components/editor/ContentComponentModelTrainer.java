package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.characters.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelTrainer {
    private GeneComponentModelInt mult;
    private GeneComponentModelImgSelect maxiFileName;
    private GeneComponentModelImgSelect miniFileName;
    AbsPanel effectForm(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        mult = new GeneComponentModelInt(_core);
        selected_.add(SubscribedTranslationList.line(_core,MessagesPkBean.MAP,MessagesDataMapPokemonKey.M_P_34_MULTIPLICITY,mult.geneInt()));
        maxiFileName = new GeneComponentModelImgSelect(_core,_fac,_fact.getImgRetrieverMaxiSub());
        selected_.add(maxiFileName.gene());
        miniFileName = new GeneComponentModelImgSelect(_core,_fac,_fact.getImgRetrieverMiniSub());
        selected_.add(miniFileName.gene());
        FormDataMap.baseSelectImage(maxiFileName);
        FormDataMap.baseSelectImage(miniFileName);
        return selected_;
    }
    void buildEntity(Trainer _edited) {
        _edited.setMultiplicityFight(mult.valueInt());
        _edited.setImageMaxiFileName(maxiFileName.getName().tryRet());
        _edited.setImageMiniFileName(miniFileName.getName().tryRet());
    }
    void feedForm(Trainer _edited) {
        mult.valueInt(_edited.getMultiplicityFight());
        maxiFileName.updateValue(_edited.getImageMaxiFileName());
        miniFileName.updateValue(_edited.getImageMiniFileName());
    }

    public GeneComponentModelImgSelect getMiniFileName() {
        return miniFileName;
    }

    public GeneComponentModelImgSelect getMaxiFileName() {
        return maxiFileName;
    }
}
