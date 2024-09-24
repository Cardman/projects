package cards.gui.containers;

import cards.solitaire.*;
import cards.solitaire.sml.*;
import code.util.*;

public final class DefFirstDealSolitaire implements IntFirstDealSolitaire {

    @Override
    public AbsDealSolitaire deal(ContainerSolitaire _container, SolitaireType _type) {
        AbsDealSolitaire deal_ = DocumentReaderSolitaireUtil.init(_type);
        deal_.setHandsBegin(new CustList<HandSolitaire>());
        deal_.setActions(new CustList<ActionSolitaire>());
        deal_.deal(_container.getOwner().getGenerator());
        return deal_;
    }
}
