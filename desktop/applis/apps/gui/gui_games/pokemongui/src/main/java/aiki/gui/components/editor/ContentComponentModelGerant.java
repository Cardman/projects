package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.characters.*;
import aiki.map.characters.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelGerant {
    private GerantPokemon edited = Instances.newGerantPokemon();
    private GeneComponentModelImgSelect miniFileName;
    private GeneComponentModelElt<String> kind;
    AbsPanel effectForm(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, FormLevelGrid _grid) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        kind = new GeneComponentModelElt<String>(_core, MessagesPkEditor.getMessagesEditorSelectTileKindGerantTypeTr(MessagesPkEditor.getAppliTr(_core.currentLg())).getMapping(), new EmptyDefValue());
        selected_.add(kind.geneEnum());
        kind.setupValue(MessagesEditorSelect.GERANCE_TYPE_HEAL);
        miniFileName = new GeneComponentModelImgSelect(_core,_fac,_fact.getImgRetrieverMiniSub());
        selected_.add(SubscribedTranslationList.line(_core,MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_MINI,miniFileName.gene()));
        FormDataMap.baseSelectImage(miniFileName);
        IdList<SubscribedTranslation> group_= new IdList<SubscribedTranslation>();
        group_.addAllElts(miniFileName.subs());
        _grid.subs(group_);
        return selected_;
    }
    GerantPokemon buildEntity() {
        edited.setImageMiniFileName(miniFileName.getName().tryRet());
        edited.setGerance(GeranceType.getGeranceTypeByName(kind.tryRet()));
        return edited;
    }
    void feedForm(GerantPokemon _edited) {
        edited = new GerantPersonMapper().map(_edited);
        miniFileName.updateValue(edited.getImageMiniFileName());
        kind.setupValue(edited.getGerance().getGer());
    }

    public GeneComponentModelImgSelect getMiniFileName() {
        return miniFileName;
    }

}
