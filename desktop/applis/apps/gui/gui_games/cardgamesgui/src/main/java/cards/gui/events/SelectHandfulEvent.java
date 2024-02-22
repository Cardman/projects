package cards.gui.events;

import cards.facade.Games;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerPlayableTarot;
import cards.gui.labels.AbsMetaLabelCard;
import cards.gui.labels.HandfulLabel;
import cards.tarot.enumerations.Handfuls;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.EntryCust;
import code.util.core.StringUtil;

public class SelectHandfulEvent implements AbsMouseListenerIntRel {

    private final ContainerPlayableTarot container;

    private final Handfuls handfuls;

    private final int requiredTrumps;

    public SelectHandfulEvent(ContainerPlayableTarot _container, Handfuls _suit, int _r) {
        container = _container;
        handfuls = _suit;
        requiredTrumps = _r;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        container.setChoosenHandful(handfuls);
        for (EntryCust<Handfuls, HandfulLabel> r: container.getHandfulsRadio().entryList()) {
            r.getValue().setSelected(handfuls);
        }
        for (EntryCust<Handfuls, HandfulLabel> r: container.getHandfulsRadio().entryList()) {
            AbsMetaLabelCard.paintCard(container.getWindow().getImageFactory(), r.getValue());
        }
        if (handfuls == Handfuls.NO) {
            container.getInfoCurrentHandful().setText(ContainerGame.EMPTY_STRING);
        } else {
            TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
            String mes_ = container.file().getVal(MessagesGuiCards.MAIN_REMOVE_TRUMPS_HANDFUL);
            int exces_ = container.getCurrentIncludedTrumps().total()-requiredTrumps;
            container.getInfoCurrentHandful().setText(StringUtil.simpleStringsFormat(mes_, Long.toString(exces_), Games.toString(handfuls,lg_)));
        }
        container.refreshCurrentHand();
        container.displayTrumps();
    }
}
