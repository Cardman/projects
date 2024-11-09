package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.moves.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelMoveData extends GeneComponentModelEntity<MoveData> {
    private final GeneComponentModelInt pp;
//    private MoveData element;


    public GeneComponentModelMoveData(AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_core, _facade, _sub);
        pp = new GeneComponentModelInt(_core);
    }
    @Override
    public AbsCustComponent gene(int _select) {
//        element = Instances.newDamagingMoveData();
        SubscribedTranslationMessagesFactoryMv factoryMv_ = getSubscribedTranslationList().getFactoryMv();
        buildKey(_select,factoryMv_,factoryMv_.all(getFacade()).getKeys());
        AbsCompoFactory compoFactory_ = getCompoFactory().getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(getGeneComponentModelSelectKey().geneEnum());
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(pp.geneInt());
        sc_.setViewportView(form_);
        page_.add(sc_);
        return page_;
    }

    @Override
    public EditedCrudPair<String,MoveData> value() {
        DamagingMoveData ent_ = Instances.newDamagingMoveData();
        ent_.setPp((short) pp.valueInt());
        return new EditedCrudPair<String, MoveData>(getGeneComponentModelSelectKey().tryRet(DataBase.EMPTY_STRING),ent_);
    }

    @Override
    public void value(EditedCrudPair<String,MoveData> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        MoveData move_ = _v.getValue();
        pp.valueInt(move_.getPp());
    }

    public GeneComponentModelInt getPp() {
        return pp;
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getGeneComponentModelSelectKey().getSubs());
        return ids_;
    }
}
