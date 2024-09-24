package cards.solitaire;

import code.maths.montecarlo.AbstractGenerator;
import code.util.CustList;
import code.util.StringMap;

public final class DealClassic extends AbsDealSolitaire {

    public HandSolitaire hand(int _index) {
        if (_index == 0) {
            return stackFrom();
        }
        if (_index <= 7) {
            return stacksMove().get(_index - 1);
        }
        return stacksDest().get(_index - 8);
    }
    public HandSolitaire stackFrom(){
        return getHands().first();
    }
    public CustList<HandSolitaire> stacksMove(){
        return getHands().mid(1,7);
    }
    public CustList<HandSolitaire> stacksDest(){
        return getHands().mid(8,4);
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
        if (getHandsBegin().get(0).total() != 24) {
            setError(_messages.getVal(BAD_CARD_HAND_COUNT)+getHandsBegin().get(0).total()+ DIFFERENT +24);
            return false;
        }
        for (int i = 1; i <= 7; i++) {
            if (getHandsBegin().get(i).total() != i) {
                setError(_messages.getVal(BAD_CARD_HAND_COUNT)+getHandsBegin().get(i).total()+ DIFFERENT +i);
                return false;
            }
        }
        for (int i = 8; i <= 11; i++) {
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
        if (_from == 0) {
            return true;
        }
        if (_from <= 7) {
            int total_ = fromHand_.total();
            for (int i = _card; i < total_; i++) {
                if (i +1 < total_&&!mixed(fromHand_.carte(i+1),fromHand_.carte(i))) {
                    return false;
                }
            }
            return true;
        }
        return fromHand_.total() - 1 == _card;
    }

    @Override
    public boolean canBePlayed(int _from, int _card, int _to) {
        if (_from == _to) {
            return false;
        }
        if (_to == 0) {
            return false;
        }
        HandSolitaire fromHand_ = hand(_from);
        if (_from <= 7) {
            return canBePlayed(fromHand_.getCards().get(_card), _to);
        }
        if (_to > 7) {
            return false;
        }
        return canBePlayed(fromHand_.derniereCarte(), _to);
    }

    private boolean canBePlayed(CardSolitaire _selected, int _to) {
        HandSolitaire destHand_ = hand(_to);
        if (_to <= 7) {
            if (destHand_.estVide()) {
                return _selected.getForce() == 13;
            }
            return mixed(_selected, destHand_.derniereCarte());
        }
        if (destHand_.estVide()) {
            return _selected.getForce() == 1;
        }
        return _selected.getForce() - 1 == destHand_.derniereCarte().getForce() && _selected.getId().getCouleur() == destHand_.derniereCarte().getId().getCouleur();
    }

    @Override
    public void play(int _from, int _card, int _to) {
        if (_from == 0) {
            HandSolitaire fromHand_ = hand(_from);
            HandSolitaire destHand_ = hand(_to);
            CardSolitaire played_ = fromHand_.jouer(_card);
            destHand_.ajouter(played_);
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
        return 8;
    }

    @Override
    public void deal(AbstractGenerator _gene) {
        getHandsBegin().clear();
        for (int i = 0; i < 12; i++){
            getHandsBegin().add(new HandSolitaire());
        }
        HandSolitaire stack_ = HandSolitaire.stack(1);
        for (int i = 0; i < 24; i++) {
            getHandsBegin().get(0).ajouter(stack_.tirerUneCarteAleatoire(_gene));
        }
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= i; j++) {
                getHandsBegin().get(i).ajouter(stack_.tirerUneCarteAleatoire(_gene));
            }
        }
        fwd();
    }

    @Override
    public SolitaireType type() {
        return SolitaireType.CLASSIC;
    }
}
