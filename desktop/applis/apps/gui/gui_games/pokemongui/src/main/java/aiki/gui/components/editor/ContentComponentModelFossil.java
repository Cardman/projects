package aiki.gui.components.editor;

import aiki.fight.items.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelFossil {
    private GeneComponentModelEltEnumSub<String> pokemon;
    private GeneComponentModelInt level;
    private AbsPanel fossilForm;
    AbsPanel form(GeneComponentModelItem _parent) {
        AbsCompoFactory compoFactory_ = _parent.getCompoFactory().getCompoFactory();
        fossilForm = compoFactory_.newLineBox();
        pokemon = ConverterCommonMapUtil.buildPkFull(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList());
        fossilForm.add(pokemon.geneEnum());
        level=new GeneComponentModelInt(_parent.getCompoFactory());
        fossilForm.add(level.geneInt());
        fossilForm.setVisible(false);
        return fossilForm;
    }
    void display(String _eff) {
        fossilForm.setVisible(StringUtil.quickEq(_eff, Item.FOSSIL));
    }
    void buildEntity(Fossil _item) {
        _item.setPokemon(pokemon.tryRet());
        _item.setLevel((short) level.valueInt());
    }
    void feedForm(Fossil _item) {
        pokemon.setupValue(_item.getPokemon());
        level.valueInt(_item.getLevel());
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(pokemon.getSubs());
        return ids_;
    }

    public GeneComponentModelInt getLevel() {
        return level;
    }
}
