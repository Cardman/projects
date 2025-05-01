package aiki.gui.components.editor;

import aiki.fight.items.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelFossil {
    private GeneComponentModelEltEnumSub<String> pokemon;
    private GeneComponentModelLong level;
    private AbsPanel fossilForm;
    AbsPanel form(GeneComponentModelItem _parent) {
        AbsCompoFactory compoFactory_ = _parent.getCompoFactory().getCompoFactory();
        fossilForm = compoFactory_.newLineBox();
        pokemon = ConverterCommonMapUtil.buildPkFull(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList());
        fossilForm.add(line(_parent,MessagesDataItemsFossil.M_P_21_PK,pokemon.geneEnum()));
        level=new GeneComponentModelLong(_parent.getCompoFactory());
        fossilForm.add(line(_parent,MessagesDataItemsFossil.M_P_21_LEVEL,level.geneLong()));
        fossilForm.setVisible(false);
        return fossilForm;
    }
    private AbsCustComponent line(GeneComponentModelItem _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.IT_FOSSIL, _key,_input);
    }
    void display(String _eff) {
        fossilForm.setVisible(StringUtil.quickEq(_eff, Item.FOSSIL));
    }
    void buildEntity(Fossil _item) {
        _item.setPokemon(pokemon.tryRet());
        _item.setLevel(level.valueLong());
    }
    void feedForm(Fossil _item) {
        pokemon.setupValue(_item.getPokemon());
        level.valueLong(_item.getLevel());
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(pokemon.getSubs());
        return ids_;
    }

    public GeneComponentModelLong getLevel() {
        return level;
    }
}
