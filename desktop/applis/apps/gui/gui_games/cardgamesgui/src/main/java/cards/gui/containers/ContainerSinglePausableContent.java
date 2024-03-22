package cards.gui.containers;

import cards.gui.containers.events.*;
import cards.main.*;
import code.gui.*;
import code.scripts.messages.cards.MessagesGuiCards;

public final class ContainerSinglePausableContent<T> extends ContainerSinContent {
    private AbsButton mainCardGame;
    private AbsButton endDealGame;
    private AbsButton nextTrick;
    public void addButtonEndDeal(ContainerSinglePausable<T> _container, String _texte, boolean _apte) {
        AbsPanel panneau_=_container.getPanneauBoutonsJeu();
        AbsButton bouton_=_container.getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new CardsNonModalEvent(_container),new EndDealEvent<T>(_container));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
        endDealGame = bouton_;
    }
    public void addMainCardGame(ContainerSinglePausable<T> _container, boolean _apte) {
        AbsPanel panneau_=_container.getPanneauBoutonsJeu();
        AbsButton bouton_=_container.getOwner().getCompoFactory().newPlainButton(_container.file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME));
        bouton_.addActionListener(new CardsNonModalEvent(_container),new FirstTrickEvent<T>(_container));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
        mainCardGame = bouton_;
    }
    public void addButtonNextTrick(ContainerSinglePausable<T> _container, boolean _apte) {
        AbsPanel panneau_=_container.getPanneauBoutonsJeu();
        AbsButton bouton_=_container.getOwner().getCompoFactory().newPlainButton(_container.file().getVal(MessagesGuiCards.MAIN_NEXT_TRICK));
        bouton_.addActionListener(new CardsNonModalEvent(_container),new NextTrickEvent<T>(_container));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
        nextTrick = bouton_;
    }

    public AbsButton getMainCardGame() {
        return mainCardGame;
    }

    public AbsButton getNextTrick() {
        return nextTrick;
    }

    public AbsButton getEndDealGame() {
        return endDealGame;
    }
}
