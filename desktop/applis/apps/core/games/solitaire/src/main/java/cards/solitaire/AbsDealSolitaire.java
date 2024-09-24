package cards.solitaire;

import code.maths.montecarlo.AbstractGenerator;
import code.util.CustList;
import code.util.StringMap;

public abstract class AbsDealSolitaire {
    public static final String NOT_PLAYABLE="0";
    public static final String BAD_HAND_COUNT="1";
    public static final String BAD_CARD_HAND_COUNT="2";
    public static final String BAD_CARD_UNIT_COUNT="3";
    public static final String CANNOT_BE_SELECTED="4";
    public static final String CANNOT_BE_PLAYED="5";
    public static final String DIFFERENT = "\u2260";
    private CustList<ActionSolitaire> actions;
    private CustList<HandSolitaire> hands;
    private CustList<HandSolitaire> handsBegin;
    private String error;

    static boolean mixed(CardSolitaire _selected, CardSolitaire _other) {
        return strengthDiff(_selected, _other) && _selected.getGroup() != _other.getGroup();
    }

    static boolean strengthDiff(CardSolitaire _selected, CardSolitaire _other) {
        return _selected.getForce() + 1 == _other.getForce();
    }

    public abstract void deal(AbstractGenerator _gene);
    public abstract boolean valid(StringMap<String> _messages);

    public abstract boolean canBeSelected(int _from, int _card);
    public abstract boolean canBePlayed(int _from, int _card, int _to);
    public abstract void play(int _from, int _card, int _to);
    public abstract int from();
    public abstract int count();
    public boolean finish() {
        for (HandSolitaire h: getHands().mid(from(),count())) {
            if (!h.estVide()) {
                return false;
            }
        }
        return true;
    }
    public abstract SolitaireType type();
    static void move(int _card, HandSolitaire _fromHand, HandSolitaire _destHand) {
        int fromCount_ = _fromHand.total();
        for (int i = _card; i < fromCount_; i++) {
            CardSolitaire played_ = _fromHand.jouer(_card);
            _destHand.ajouter(played_);
        }
    }

    public boolean okActions(int _stack, StringMap<String> _messages) {
        HandSolitaire all_ = new HandSolitaire();
        for (HandSolitaire h: getHandsBegin()) {
            all_.ajouterCartes(h);
        }
        if (!all_.validStack(_stack)) {
            setError(_messages.getVal(BAD_CARD_UNIT_COUNT)+_stack);
            return false;
        }
        fwd();
        for (ActionSolitaire a: getActions()) {
            if (!canBeSelected(a.getFromIndex(),a.getCardIndex())) {
                setError(_messages.getVal(CANNOT_BE_SELECTED));
                return false;
            }
            if (!canBePlayed(a.getFromIndex(),a.getCardIndex(),a.getToIndex())) {
                setError(_messages.getVal(CANNOT_BE_PLAYED));
                return false;
            }
            play(a.getFromIndex(),a.getCardIndex(),a.getToIndex());
        }
        setError("");
        return true;
    }
    public CustList<ActionSolitaire> getActions() {
        return actions;
    }

    public void setActions(CustList<ActionSolitaire> _a) {
        this.actions = _a;
    }

    public CustList<HandSolitaire> getHands() {
        return hands;
    }

    public void setHands(CustList<HandSolitaire> _h) {
        this.hands = _h;
    }

    public void fwd() {
        setHands(new CustList<HandSolitaire>());
        for (HandSolitaire h: getHandsBegin()) {
            hands.add(new HandSolitaire(h));
        }
    }

    public CustList<HandSolitaire> getHandsBegin() {
        return handsBegin;
    }

    public void setHandsBegin(CustList<HandSolitaire> _h) {
        this.handsBegin = _h;
    }

    public String getError() {
        return error;
    }

    public void setError(String _e) {
        this.error = _e;
    }
}
