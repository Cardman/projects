package cards.gui;

import cards.gui.containers.*;
import cards.solitaire.*;
import code.util.*;

public final class SolitaireSampleFirstDeal implements IntFirstDealSolitaire {

    public static DealClassic dealSample() {
        DealClassic d_ = new DealClassic();
        d_.setActions(new CustList<ActionSolitaire>());
        d_.setHands(new CustList<HandSolitaire>());
        d_.setHandsBegin(new CustList<HandSolitaire>());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.HEART_6);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.SPADE_6);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.DIAMOND_6);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.SPADE_5);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.DIAMOND_5);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.DIAMOND_4);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.HEART_KING);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.SPADE_KING);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.DIAMOND_KING);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.CLUB_KING);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.SPADE_QUEEN);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.DIAMOND_QUEEN);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.CLUB_QUEEN);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.HEART_QUEEN);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.DIAMOND_JACK);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.CLUB_JACK);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.HEART_JACK);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.SPADE_JACK);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.CLUB_10);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.HEART_10);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.SPADE_10);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.DIAMOND_10);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.HEART_9);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.SPADE_9);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.DIAMOND_9);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.SPADE_8);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.DIAMOND_8);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.SPADE_7);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_2);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_2);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_2);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_2);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_3);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_3);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_3);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_3);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_4);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_4);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_4);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_5);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_5);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_6);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_7);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_7);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_7);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_8);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_8);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_9);
        d_.fwd();
        return d_;
    }

    @Override
    public AbsDealSolitaire deal(ContainerSolitaire _container, SolitaireType _type) {
        return dealSample();
    }



}
