package cards.gui.containers;

import cards.gui.labels.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.gui.*;
import code.gui.events.*;
import code.util.*;

public interface ContainerPlayableTarot extends ContainerPlayableSlam,ContainerSingle<CardTarot>,ContainerSingleWithDiscard<CardTarot> {
    void prendreCartesChien();
    void refreshCurrentHand();
    void validateDog();
//    boolean isCanExcludeTrumps();
    HandTarot getCurrentIncludedTrumps();
    HandTarot getCurrentExcludedTrumps();
    Handfuls getChoosenHandful();
    AbsTextArea getInfoCurrentHandful();
    void displayTrumps();
    int required();
//
//    CardTarot getCarteSurvoleeTarot();
//
//    void setCarteSurvoleeTarot(CardTarot _c);

    AbsPanel getIncludedTrumpsForHandful();

    AbsPanel getExcludedTrumpsForHandful();

    void setCurrentIncludedTrumps(HandTarot _tr);

    AbsScrollPane getScrollDeclaringHandful();

    DisplayingTarot getDisplayingTarot();

    AbsSplitPane getDeclaringHandful();

    AbsMap<Handfuls, HandfulLabel> getHandfulsRadio();

    void setInfoCurrentHandful(AbsTextArea _t);

    void setChoosenHandful(Handfuls _h);

    CardTarot getCalledCard();
    void setCalledCard(CardTarot _c);
    BidTarot getContratUtilisateur();
    void setContratUtilisateur(BidTarot _contratUtilisateur);

    AbsPanel getPanelCallableCards();

    AbsCustComponent getScrollCallableCards();

    void border(GraphicCard<CardTarot> _g);

    void updateButtons();

    AbsActionListener bid(BidTarot _action);
    void pack();

    CustList<BidTarot> getBids();
}
