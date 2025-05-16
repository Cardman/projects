package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.characters.*;
import aiki.map.characters.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelSeller {
    private Seller edited = Instances.newSeller();
    private GeneComponentModelElt<String> kind;
    private GeneComponentModelImgSelect miniFileName;
    private GeneComponentModelLsStrSub<String,StringList> items;
    private GeneComponentModelLsStrSub<Integer,Ints> tm;
    AbsPanel effectForm(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, FormLevelGrid _grid) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        kind = new GeneComponentModelElt<String>(_core, MessagesPkEditor.getMessagesEditorSelectTileKindSellTypeTr(MessagesPkEditor.getAppliTr(_core.currentLg())).getMapping(), new EmptyDefValue());
        selected_.add(SubscribedTranslationList.line(_core,MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(_core.currentLg())),MessagesEditorSelect.SOLD_IT,kind.geneEnum()));
        kind.setupValue(MessagesEditorSelect.SELL_TYPE_ITEM);
        miniFileName = new GeneComponentModelImgSelect(_core,_fac,_fact.getImgRetrieverMiniSub());
        selected_.add(SubscribedTranslationList.line(_core,MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_MINI,miniFileName.gene()));
        items = ConverterCommonMapUtil.buildItemList(_core,_fac,_fact);
        selected_.add(SubscribedTranslationList.line(_core,MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_ITEMS,items.geneEnum()));
        tm = ConverterCommonMapUtil.buildTmList(_core,_fac,_fact);
        selected_.add(SubscribedTranslationList.line(_core,MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_TMS,tm.geneEnum()));
        FormDataMap.baseSelectImage(miniFileName);
        IdList<SubscribedTranslation> group_= new IdList<SubscribedTranslation>();
        group_.addAllElts(miniFileName.subs());
        group_.addAllElts(items.getSubs());
        group_.addAllElts(tm.getSubs());
        _grid.subs(group_);
        return selected_;
    }
    Seller buildEntity() {
        edited.setImageMiniFileName(miniFileName.getName().tryRet());
        edited.setItems(items.tryRet());
        edited.setTm(tm.tryRet());
        edited.setSell(SellType.getSellTypeByName(kind.tryRet()));
        return edited;
    }
    void feedForm(Seller _edited) {
        edited = new SellerPersonMapper().map(_edited);
        miniFileName.updateValue(edited.getImageMiniFileName());
        items.setupValue(edited.getItems());
        tm.setupValue(edited.getTm());
        kind.setupValue(edited.getSell().getSel());
    }

    public GeneComponentModelImgSelect getMiniFileName() {
        return miniFileName;
    }

}
