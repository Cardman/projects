package cards.gui.containers.events;

import cards.gui.containers.ContainerSolitaire;
import cards.solitaire.ActionSolitaire;
import code.gui.*;
import code.gui.events.*;

public final class RefreshSolitaireEvent implements AbsMouseListenerIntRel {
    private final ContainerSolitaire container;
    private final int group;
    private final int card;

    public RefreshSolitaireEvent(ContainerSolitaire _c, int _i, int _j) {
        this.container = _c;
        this.group = _i;
        this.card = _j;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (container.getGroup() == -1) {
            container.setGroup(group);
            container.setCard(card);
            container.placerIhmSolitaire();
            return;
        }
        if (!container.partieSolitaire().canBePlayed(container.getGroup(),container.getCard(),group)) {
            container.setGroup(-1);
            container.setCard(-1);
            container.placerIhmSolitaire();
            return;
        }
        ActionSolitaire ac_ = new ActionSolitaire();
        ac_.setFromIndex(container.getGroup());
        ac_.setCardIndex(container.getCard());
        ac_.setToIndex(group);
        container.setGroup(-1);
        container.setCard(-1);
        container.partieSolitaire().getActions().add(ac_);
        container.partieSolitaire().play(ac_.getFromIndex(),ac_.getCardIndex(),ac_.getToIndex());
        container.placerIhmSolitaire();
    }
}
