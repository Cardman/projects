package cards.president;
import java.util.Iterator;

import code.util.CustList;
import code.util.Ints;
import code.util.core.IndexConstants;


public final class TrickPresident implements Iterable<HandPresident> {
    /**Entameur du pli*/
    private int starter;
    /**cards est l'ensemble de cartes jouees pendant le pli a la belote ou au tarot*/
    private CustList<HandPresident> cards=new CustList<HandPresident>();

    public TrickPresident() {}
    TrickPresident(int _pentameur) {
        initPli(_pentameur);
    }

    private void initPli(int  _pentameur) {
        starter=_pentameur;
    }

    public int getPlayer(int _index, int _nbPlayers) {
        return (starter + _index) % _nbPlayers;
    }

    public Ints getPlayedCardsIndexes(int _player, int _nbPlayers) {
        Ints l_ = new Ints();
        int pl_ = starter;
        int len_ = cards.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (pl_ == _player) {
                l_.add(i);
            }
//            pl_ = (byte) ((starter + pl_) % _nbPlayers);
            pl_ = (1 + pl_) % _nbPlayers;
        }
        return l_;
    }

    public CustList<HandPresident> getFilledHandsBefore(int _index) {
        CustList<HandPresident> l_ = new CustList<HandPresident>();
        for (int i: getFilledHandsIndexesBefore(_index)) {
            l_.add(cards.get(i));
        }
        return l_;
    }

    public Ints getFilledHandsIndexesBefore(int _index) {
        Ints l_ = new Ints();
        int len_ = cards.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (i >= _index) {
                break;
            }
            HandPresident h_ = cards.get(i);
            if (!h_.estVide()) {
                l_.add(i);
            }
        }
        return l_;
    }

    public CustList<HandPresident> getPlayedCards(int _player, int _nbPlayers) {
        CustList<HandPresident> l_ = new CustList<HandPresident>();
        int pl_ = starter;
        for (HandPresident h: cards) {
            if (pl_ == _player) {
                l_.add(h);
            }
//            pl_ = (byte) ((starter + pl_) % _nbPlayers);
            pl_ = (1 + pl_) % _nbPlayers;
        }
        return l_;
    }

    public HandPresident getBestCards() {
        int index_ = cards.size() - 1;
        while (index_ >= IndexConstants.FIRST_INDEX) {
            if (!cards.get(index_).estVide()) {
                break;
            }
            index_--;
        }
        if (index_ < IndexConstants.FIRST_INDEX) {
            return new HandPresident();
        }
        return cards.get(index_);
    }

    /**Retourne le ramasseur du pli lorsque le pli est termine*/
    public int getRamasseur(int _nbPlayers) {
        int index_ = cards.size() - 1;
        while (index_ >= IndexConstants.FIRST_INDEX) {
            if (!cards.get(index_).estVide()) {
                break;
            }
            index_--;
        }
        if (index_ < IndexConstants.FIRST_INDEX) {
//            return CustList.INDEX_NOT_FOUND_ELT;
            index_ = total();
        }
        return (starter + index_) % _nbPlayers;
    }

    /**Retourne l'entameur du pli*/
    public int getEntameur() {
        return starter;
    }

    public HandPresident getCartes() {
        HandPresident h_ = new HandPresident();
        for (HandPresident h: cards) {
            h_.ajouterCartes(h);
        }
        return h_;
    }

    void ajouter() {
        cards.add(new HandPresident());
    }

    void ajouter(HandPresident _c) {
        cards.add(_c);
    }
    public HandPresident carte(int _i) {
        return cards.get(_i);
    }

    public int getNombreDeCartesParJoueur() {
        return HandPresident.nullToEmpty(firstOrNull(this)).total();
    }
    public static TrickPresident nullToEmpty(TrickPresident _t) {
        if (_t == null) {
            return new TrickPresident();
        }
        return _t;
    }
    private static HandPresident firstOrNull(TrickPresident _t) {
        if (_t.estVide()) {
            return null;
        }
        return _t.getCards().first();
    }

    /**Si tous les joueurs ne joue qu'une carte alors total() renvoie le nombre de cartes du pli
    sinon il renvoie le nombre total de cartes divise par le nombres de cartes jouees par joueur pour le pli*/
    public int total() {
        return cards.size();
    }
    public boolean estVide() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<HandPresident> iterator() {
        return cards.iterator();
    }

    void setEntameur(int _i) {
        starter = _i;
    }
    public CustList<HandPresident> getCards() {
        return cards;
    }
    public void setCards(CustList<HandPresident> _cards) {
        cards = _cards;
    }
}
