package aiki.gui.components.editor;

import aiki.fight.items.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;

public final class GeneComponentModelItem implements GeneComponentModel<Item> {
    private final AbstractProgramInfos compoFactory;
    private final GeneComponentModelInt price;
    private Item element;

    public GeneComponentModelItem(AbstractProgramInfos _core) {
        this.compoFactory = _core;
        price = new GeneComponentModelInt(_core);
    }
    @Override
    public AbsCustComponent gene() {
        element = Instances.newBall();
        AbsCompoFactory compoFactory_ = compoFactory.getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(price.geneInt());
        sc_.setViewportView(form_);
        return sc_;
    }

    @Override
    public Item value() {
        Item ent_ = Instances.newBall();
        ent_.setPrice(price.valueInt());
        return ent_;
    }

    @Override
    public Item value(Item _v) {
        price.valueInt(_v.getPrice());
        return element;
    }

    public GeneComponentModelInt getPrice() {
        return price;
    }
}
