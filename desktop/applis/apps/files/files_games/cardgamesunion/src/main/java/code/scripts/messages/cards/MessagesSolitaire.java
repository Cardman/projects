package code.scripts.messages.cards;

import cards.solitaire.*;
import code.sml.util.*;

public final class MessagesSolitaire {
    private MessagesSolitaire() {
    }
    public static TranslationsFile enCheck(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(AbsDealSolitaire.NOT_PLAYABLE,"Not playable card");
        e_.add(AbsDealSolitaire.BAD_HAND_COUNT,"Bad hand count:");
        e_.add(AbsDealSolitaire.BAD_CARD_HAND_COUNT,"The number of each card is the following (because of current stack):");
        e_.add(AbsDealSolitaire.BAD_CARD_UNIT_COUNT,"The number of each different card is the following (because of stack count):");
        e_.add(AbsDealSolitaire.CANNOT_BE_SELECTED,"This card could not be selected.");
        e_.add(AbsDealSolitaire.CANNOT_BE_PLAYED,"This card could not be played.");
        return e_;
    }
    public static TranslationsFile frCheck(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(AbsDealSolitaire.NOT_PLAYABLE,"Carte non jouable");
        f_.add(AbsDealSolitaire.BAD_HAND_COUNT,"Mauvais nombre de mains:");
        f_.add(AbsDealSolitaire.BAD_CARD_HAND_COUNT,"Le nombre de chaque carte est le suivant (à cause de la pile courante):");
        f_.add(AbsDealSolitaire.BAD_CARD_UNIT_COUNT,"Le nombre de chaque carte différente est le suivant (à cause du nombre de pile):");
        f_.add(AbsDealSolitaire.CANNOT_BE_SELECTED,"Cette carte ne peut pas être sélectionnée.");
        f_.add(AbsDealSolitaire.CANNOT_BE_PLAYED,"Cette carte ne peut pas être jouée.");
        return f_;
    }
}
