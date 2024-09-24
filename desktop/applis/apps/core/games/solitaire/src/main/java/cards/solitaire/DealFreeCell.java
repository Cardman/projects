package cards.solitaire;

import code.maths.montecarlo.*;
import code.util.*;

public final class DealFreeCell extends AbsDealSolitaire {
    public HandSolitaire hand(int _index) {
        if (_index <= 7) {
            return stackFrom().get(_index);
        }
        if (_index <= 11) {
            return stacksMove().get(_index - 8);
        }
        return stacksDest().get(_index - 12);
    }
    public CustList<HandSolitaire> stackFrom(){
        return getHands().mid(0,8);
    }
    public CustList<HandSolitaire> stacksMove(){
        return getHands().mid(8,4);
    }
    public CustList<HandSolitaire> stacksDest(){
        return getHands().mid(12,4);
    }
    @Override
    public void deal(AbstractGenerator _gene) {
        getHandsBegin().clear();
        for (int i = 0; i < 16; i++){
            getHandsBegin().add(new HandSolitaire());
        }
        HandSolitaire stack_ = HandSolitaire.stack(1);
        for (int i = 0; i <= 3; i++) {
            for (int j = 1; j <= 7; j++) {
                getHandsBegin().get(i).ajouter(stack_.tirerUneCarteAleatoire(_gene));
            }
        }
        for (int i = 4; i <= 7; i++) {
            for (int j = 1; j <= 6; j++) {
                getHandsBegin().get(i).ajouter(stack_.tirerUneCarteAleatoire(_gene));
            }
        }
        fwd();
    }

    @Override
    public boolean valid(StringMap<String> _messages) {
        for (HandSolitaire h: getHandsBegin()) {
            if (h.getCards().containsObj(CardSolitaire.WHITE)) {
                setError(_messages.getVal(NOT_PLAYABLE));
                return false;
            }
        }
        if (getHandsBegin().size() != 16) {
            setError(_messages.getVal(BAD_HAND_COUNT)+getHandsBegin().size()+ DIFFERENT +16);
            return false;
        }
        for (int i = 0; i <= 3; i++) {
            if (getHandsBegin().get(i).total() != 7) {
                setError(_messages.getVal(BAD_CARD_HAND_COUNT)+getHandsBegin().get(i).total()+ DIFFERENT +7);
                return false;
            }
        }
        for (int i = 4; i <= 7; i++) {
            if (getHandsBegin().get(i).total() != 6) {
                setError(_messages.getVal(BAD_CARD_HAND_COUNT)+getHandsBegin().get(i).total()+ DIFFERENT +6);
                return false;
            }
        }
        for (int i = 8; i <= 15; i++) {
            if (!getHandsBegin().get(i).estVide()) {
                setError(_messages.getVal(BAD_CARD_HAND_COUNT)+getHandsBegin().get(i).total()+ DIFFERENT +0);
                return false;
            }
        }
        return okActions(1,_messages);
    }

    @Override
    public boolean canBeSelected(int _from, int _card) {
        HandSolitaire fromHand_ = hand(_from);
        if (!fromHand_.getCards().isValidIndex(_card)) {
            return false;
        }
        if (_from <= 7) {
            return fromHand_.total() - 1 == _card;
        }
        if (_from <= 11) {
            return true;
        }
        return fromHand_.total() - 1 == _card;
    }

    @Override
    public boolean canBePlayed(int _from, int _card, int _to) {
        if (_from == _to) {
            return false;
        }
        HandSolitaire fromHand_ = hand(_from);
        if (_from <= 7) {
            return canBePlayed(fromHand_.getCards().get(_card), _to);
        }
        return canBePlayed(fromHand_.derniereCarte(), _to);
    }

    private boolean canBePlayed(CardSolitaire _selected, int _to) {
        HandSolitaire destHand_ = hand(_to);
        if (_to <= 7) {
            if (destHand_.estVide()) {
                return true;
            }
            return mixed(_selected, destHand_.derniereCarte());
        }
        if (_to <= 11) {
            return destHand_.estVide();
        }
        if (destHand_.estVide()) {
            return _selected.getForce() == 1;
        }
        return _selected.getForce() - 1 == destHand_.derniereCarte().getForce() && _selected.getId().getCouleur() == destHand_.derniereCarte().getId().getCouleur();
    }

    @Override
    public void play(int _from, int _card, int _to) {
        HandSolitaire fromHand_ = hand(_from);
        HandSolitaire destHand_ = hand(_to);
        move(_card, fromHand_, destHand_);
    }

    @Override
    public int from() {
        return 0;
    }

    @Override
    public int count() {
        return 12;
    }

    @Override
    public SolitaireType type() {
        return SolitaireType.FREECELL;
    }
}
