package cards.solitaire;

import code.maths.montecarlo.*;
import code.util.*;

public final class DealSpider extends AbsDealSolitaire {

    public HandSolitaire hand(int _index) {
        if (_index == 0) {
            return stackFrom();
        }
        if (_index <= 10) {
            return stacksMove().get(_index - 1);
        }
        return getHands().last();
    }
    public HandSolitaire stackFrom(){
        return getHands().first();
    }
    public CustList<HandSolitaire> stacksMove(){
        return getHands().mid(1,10);
    }
    @Override
    public void deal(AbstractGenerator _gene) {
        getHandsBegin().clear();
        for (int i = 0; i < 12; i++){
            getHandsBegin().add(new HandSolitaire());
        }
        HandSolitaire stack_ = HandSolitaire.stack(2);
        for (int i = 0; i < 50; i++) {
            getHandsBegin().get(0).ajouter(stack_.tirerUneCarteAleatoire(_gene));
        }
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 6; j++) {
                getHandsBegin().get(i).ajouter(stack_.tirerUneCarteAleatoire(_gene));
            }
        }
        for (int i = 5; i <= 10; i++) {
            for (int j = 1; j <= 5; j++) {
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
        if (getHandsBegin().size() != 12) {
            setError(_messages.getVal(BAD_HAND_COUNT)+getHandsBegin().size()+ DIFFERENT +12);
            return false;
        }
        if (getHandsBegin().get(0).total() != 50) {
            setError(_messages.getVal(BAD_CARD_HAND_COUNT)+getHandsBegin().get(0).total()+ DIFFERENT +50);
            return false;
        }
        for (int i = 1; i <= 4; i++) {
            if (getHandsBegin().get(i).total() != 6) {
                setError(_messages.getVal(BAD_CARD_HAND_COUNT)+getHandsBegin().get(i).total()+ DIFFERENT +6);
                return false;
            }
        }
        for (int i = 5; i <= 10; i++) {
            if (getHandsBegin().get(i).total() != 5) {
                setError(_messages.getVal(BAD_CARD_HAND_COUNT)+getHandsBegin().get(i).total()+ DIFFERENT +5);
                return false;
            }
        }
        if (!getHandsBegin().last().estVide()) {
            setError(_messages.getVal(BAD_CARD_HAND_COUNT)+getHandsBegin().last().total()+ DIFFERENT +0);
            return false;
        }
        return okActions(2,_messages);
    }

    @Override
    public boolean canBeSelected(int _from, int _card) {
        HandSolitaire fromHand_ = hand(_from);
        if (!fromHand_.getCards().isValidIndex(_card)) {
            return false;
        }
        if (_from == 0) {
            return _card < 10;
        }
        if (_from <= 10) {
            int total_ = fromHand_.total();
            for (int i = _card; i < total_; i++) {
                if (i +1 < total_&&!strengthDiff(fromHand_.carte(i+1),fromHand_.carte(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canBePlayed(int _from, int _card, int _to) {
        if (_from == _to) {
            return false;
        }
        if (_to == 0) {
            return false;
        }
        if (_from == 0) {
            return _to < 11;
        }
        HandSolitaire fromHand_ = hand(_from);
        CardSolitaire selected_ = fromHand_.getCards().get(_card);
        if (_to == 11) {
            return selected_.getForce() == 13 && fromHand_.getCards().last().getForce() == 1;
        }
        HandSolitaire destHand_ = hand(_to);
        if (destHand_.estVide()) {
            return true;
        }
        return strengthDiff(selected_, destHand_.derniereCarte());
    }

    @Override
    public void play(int _from, int _card, int _to) {
        if (_from == 0) {
            HandSolitaire fromHand_ = hand(_from);
            for (int i = 1; i <= 10; i++) {
                HandSolitaire destHand_ = hand(i);
                destHand_.ajouter(fromHand_.jouer(0));
            }
            return;
        }
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
        return 11;
    }

    @Override
    public SolitaireType type() {
        return SolitaireType.SPIDER;
    }
}
