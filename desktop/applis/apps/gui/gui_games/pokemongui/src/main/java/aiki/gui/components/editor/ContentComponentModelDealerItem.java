package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.characters.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelDealerItem {
    private DealerItem edited = Instances.newDealerItem();
    private GeneComponentModelImgSelect miniFileName;
    private GeneComponentModelLsStrSub<String,StringList> items;
    private GeneComponentModelLsStrSub<Integer,Ints> technicalMoves;
    AbsPanel effectForm(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, FormLevelGrid _grid) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        miniFileName = new GeneComponentModelImgSelect(_core,_fac,_fact.getImgRetrieverMiniSub());
        selected_.add(miniFileName.gene());
        items = ConverterCommonMapUtil.buildItemList(_core,_fac,_fact);
        selected_.add(items.geneEnum());
        technicalMoves = ConverterCommonMapUtil.buildTmList(_core,_fac,_fact);
        selected_.add(technicalMoves.geneEnum());
        FormDataMap.baseSelectImage(miniFileName);
        IdList<SubscribedTranslation> group_= new IdList<SubscribedTranslation>();
        group_.addAllElts(miniFileName.subs());
        group_.addAllElts(items.getSubs());
        group_.addAllElts(technicalMoves.getSubs());
        _grid.subs(group_);
        return selected_;
    }
    DealerItem buildEntity() {
        edited.setImageMiniFileName(miniFileName.getName().tryRet());
        edited.setItems(items.tryRet());
        edited.setTechnicalMoves(technicalMoves.tryRet());
        return edited;
    }
    void feedForm(DealerItem _edited) {
        edited = ConverterCommonMapUtil.copyDealerItem(_edited);
        miniFileName.updateValue(edited.getImageMiniFileName());
        items.setupValue(edited.getItems());
        technicalMoves.setupValue(edited.getTechnicalMoves());
    }

    public GeneComponentModelImgSelect getMiniFileName() {
        return miniFileName;
    }

}
