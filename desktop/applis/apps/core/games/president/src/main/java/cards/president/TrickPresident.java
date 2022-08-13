package cards.president;
import java.util.Iterator;

import code.util.CustList;
import code.util.*;
import code.util.Ints;
import code.util.core.IndexConstants;


public final class TrickPresident implements Iterable<HandPresident> {
    /**Entameur du pli*/
    private byte starter;
    /**cards est l'ensemble de cartes jouees pendant le pli a la belote ou au tarot*/
    private CustList<HandPresident> cards=new CustList<HandPresident>();

    private Bytes players = new Bytes();

    public TrickPresident() {}
    TrickPresident(byte _pentameur) {
        initPli(_pentameur);
    }

    private void initPli(byte _pentameur) {
        starter=_pentameur;
    }

    public byte getPlayer(int _index, byte _nbPlayers) {
        return (byte) ((starter + _index) % _nbPlayers);
    }

    public Ints getPlayedCardsIndexes(byte _player, byte _nbPlayers) {
        Ints l_ = new Ints();
        byte pl_ = starter;
        int len_ = cards.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (pl_ == _player) {
                l_.add(i);
            }
//            pl_ = (byte) ((starter + pl_) % _nbPlayers);
            pl_ = (byte) ((1 + pl_) % _nbPlayers);
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

    public CustList<HandPresident> getPlayedCards(byte _player, byte _nbPlayers) {
        CustList<HandPresident> l_ = new CustList<HandPresident>();
        byte pl_ = starter;
        for (HandPresident h: cards) {
            if (pl_ == _player) {
                l_.add(h);
            }
//            pl_ = (byte) ((starter + pl_) % _nbPlayers);
            pl_ = (byte) ((1 + pl_) % _nbPlayers);
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
    public byte getRamasseur(byte _nbPlayers) {
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
        return (byte) ((starter + index_) % _nbPlayers);
    }

    /**Retourne l'entameur du pli*/
    public byte getEntameur() {
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
    void ajouter(HandPresident _c, byte _pl) {
        cards.add(_c);
        players.add(_pl);
    }
    public Bytes getPlayers() {
        return players;
    }
    public HandPresident carte(int _i) {
        return cards.get(_i);
    }

    public byte getNombreDeCartesParJoueur() {
        if(cards.isEmpty()) {
            return 0;
        }
        return (byte) cards.first().total();
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
        starter = (byte) _i;
        players = new Bytes();
    }
    public CustList<HandPresident> getCards() {
        return cards;
    }
    public void setCards(CustList<HandPresident> _cards) {
        cards = _cards;
    }
}
