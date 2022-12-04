package cards.gui.events;

import cards.gui.containers.ContainerMultiTarot;
import cards.network.tarot.actions.PlayingCardTarot;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import code.gui.AbsMouseLocation;

public class ListenerCardTarotMultiGame extends AbstractListenerCardTarot {
    private ContainerMultiTarot container;
    public ListenerCardTarotMultiGame(ContainerMultiTarot _container, CardTarot _pcarte) {
        super(_container, _pcarte);
        container = _container;
    }

    @Override
    protected boolean playCardExited(AbsMouseLocation _event) {
        return _event.getYcoord() < 0;
    }
    @Override
    protected boolean canListen() {
        return container.isCanPlay();
    }
    @Override
    protected void verifierRegles() {
        container.setCanPlay(false);
        PlayingCardTarot pl_ = new PlayingCardTarot();
        pl_.setPlace(container.getIndexInGame());
        pl_.setPlayedCard(getCarteVerif());
//        Map<Miseres, Boolean> selectedMiseres_;
//        selectedMiseres_ = new Map<>(container.getSelectedMiseres());
//        pl_.setMiseres(selectedMiseres_.getKeys(true));
        pl_.setMiseres(container.getAllowedMiseres());
        pl_.setChoosenHandful(container.getChoosenHandful());
        String lg_ = container.getOwner().getLanguageKey();
        pl_.setLocale(lg_);
        if (container.getChoosenHandful() != Handfuls.NO) {
            pl_.setExcludedTrumps(container.getCurrentExcludedTrumps());
            pl_.setHandful(container.getCurrentIncludedTrumps());
        } else {
            pl_.setExcludedTrumps(new HandTarot());
            pl_.setHandful(new HandTarot());
        }
        container.window().sendObject(pl_);
    }
}
