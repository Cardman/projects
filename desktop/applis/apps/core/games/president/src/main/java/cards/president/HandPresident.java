package cards.president;
import java.util.Iterator;

import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.enumerations.CardPresident;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloUtil;
import code.util.CustList;
import code.util.EnumList;
import code.util.*;
import code.util.Ints;
import code.util.core.IndexConstants;


public final class HandPresident implements Iterable<CardPresident> {

    private EnumList<CardPresident> cards=new EnumList<CardPresident>();

    public HandPresident() {
    }

    public HandPresident(HandPresident _hand) {
        cards.addAllElts(_hand.cards);
    }

    public static HandPresident stack(int _nbStacks) {
        HandPresident liste_ = new HandPresident();
        for (int i = IndexConstants.FIRST_INDEX; i < _nbStacks; i++) {
            liste_.ajouterCartes(HandPresident.pileBase());
        }
        return liste_;
    }

    public static HandPresident pileBase() {
        HandPresident liste_ = new HandPresident();
        liste_.ajouter(CardPresident.HEART_2);
        liste_.ajouter(CardPresident.HEART_1);
        liste_.ajouter(CardPresident.HEART_KING);
        liste_.ajouter(CardPresident.HEART_QUEEN);
        liste_.ajouter(CardPresident.HEART_JACK);
        liste_.ajouter(CardPresident.HEART_10);
        liste_.ajouter(CardPresident.HEART_9);
        liste_.ajouter(CardPresident.HEART_8);
        liste_.ajouter(CardPresident.HEART_7);
        liste_.ajouter(CardPresident.HEART_6);
        liste_.ajouter(CardPresident.HEART_5);
        liste_.ajouter(CardPresident.HEART_4);
        liste_.ajouter(CardPresident.HEART_3);
        liste_.ajouter(CardPresident.SPADE_2);
        liste_.ajouter(CardPresident.SPADE_1);
        liste_.ajouter(CardPresident.SPADE_KING);
        liste_.ajouter(CardPresident.SPADE_QUEEN);
        liste_.ajouter(CardPresident.SPADE_JACK);
        liste_.ajouter(CardPresident.SPADE_10);
        liste_.ajouter(CardPresident.SPADE_9);
        liste_.ajouter(CardPresident.SPADE_8);
        liste_.ajouter(CardPresident.SPADE_7);
        liste_.ajouter(CardPresident.SPADE_6);
        liste_.ajouter(CardPresident.SPADE_5);
        liste_.ajouter(CardPresident.SPADE_4);
        liste_.ajouter(CardPresident.SPADE_3);
        liste_.ajouter(CardPresident.DIAMOND_2);
        liste_.ajouter(CardPresident.DIAMOND_1);
        liste_.ajouter(CardPresident.DIAMOND_KING);
        liste_.ajouter(CardPresident.DIAMOND_QUEEN);
        liste_.ajouter(CardPresident.DIAMOND_JACK);
        liste_.ajouter(CardPresident.DIAMOND_10);
        liste_.ajouter(CardPresident.DIAMOND_9);
        liste_.ajouter(CardPresident.DIAMOND_8);
        liste_.ajouter(CardPresident.DIAMOND_7);
        liste_.ajouter(CardPresident.DIAMOND_6);
        liste_.ajouter(CardPresident.DIAMOND_5);
        liste_.ajouter(CardPresident.DIAMOND_4);
        liste_.ajouter(CardPresident.DIAMOND_3);
        liste_.ajouter(CardPresident.CLUB_2);
        liste_.ajouter(CardPresident.CLUB_1);
        liste_.ajouter(CardPresident.CLUB_KING);
        liste_.ajouter(CardPresident.CLUB_QUEEN);
        liste_.ajouter(CardPresident.CLUB_JACK);
        liste_.ajouter(CardPresident.CLUB_10);
        liste_.ajouter(CardPresident.CLUB_9);
        liste_.ajouter(CardPresident.CLUB_8);
        liste_.ajouter(CardPresident.CLUB_7);
        liste_.ajouter(CardPresident.CLUB_6);
        liste_.ajouter(CardPresident.CLUB_5);
        liste_.ajouter(CardPresident.CLUB_4);
        liste_.ajouter(CardPresident.CLUB_3);
        return liste_;
    }

    public boolean validStack(int _nbStacks) {
        for (CardPresident c: pileBase()) {
            int nbUses_ = IndexConstants.SIZE_EMPTY;
            for (CardPresident e: cards) {
                if (c == e) {
                    nbUses_++;
                }
            }
            if (nbUses_ != _nbStacks) {
                return false;
            }
        }
        return true;
    }

    public void sortCardsBegin() {
        sortCards(false);
    }

    public void sortCards(boolean _reverse) {
        sortCards(true, _reverse);
    }

    public void sortCards(boolean _decroissant, boolean _reverse) {
        cards.sortElts(new GameStrengthCardPresidentComparator(_reverse, _decroissant));
    }

    public CustList<HandPresident> getCardsByLengthSortedByStrength(boolean _reverse, int _len) {
        CustList<HandPresident> l_ = new CustList<HandPresident>();
        for (HandPresident h: getCardsByStrength(_reverse).values()) {
            if (h.total() != _len) {
                continue;
            }
            l_.add(h);
        }
        return l_;
    }

    public ByteTreeMap<HandPresident> getCardsByStrength(boolean _reverse) {
        ByteTreeMap<HandPresident> m_ = new ByteTreeMap<HandPresident>();
        for (CardPresident c: pileBase()) {
            byte str_ = c.strength(_reverse);
            m_.put(str_, getCardsByStrength(str_, _reverse));
        }
        return m_;
    }

    public HandPresident getCardsByStrength(int _strength, boolean _reverse) {
        HandPresident h_ = new HandPresident();
        for (CardPresident c: cards) {
            if (c.strength(_reverse) == _strength) {
                h_.ajouter(c);
            }
        }
        return h_;
    }

    /**Ajoute une carte a la fin de la main
    et precise le jeu
    @param _t la carte a ajouter*/
    public void ajouter(CardPresident _t) {
        cards.add(_t);
    }

    /**Ajoute les cartes d'une main dans la main courante
    a la fin de celle-ci les cartes ajoutees conservent le
    meme ordre qu'a l'etat initial
    @param _cartes la main a ajouter*/
    public void ajouterCartes(HandPresident _cartes) {
        for(CardPresident carte_:_cartes) {
            cards.add(carte_);
        }
    }
    /**On prend la premiere carte de la main puis on la place a la fin
    dans le but de deplacer la moitie d'une main apres l'autre*/
    public void couper() {
        int taille_=total()/2;
        for (int i = IndexConstants.FIRST_INDEX; i<taille_; i++) {
            ajouter(jouer(0));
        }
    }
    /**<ol>
    <li>Choisit un nombre aleatoire entre 0 et le nombre total de carte dans la main moins une carte
    d'ou total() x Math.random() puis Math.floor(total() x Math.random())</li>
    <li>puis retire la carte choisie de la main courante et la retourne</li>
    </ol>
    @return la carte aleatoire choisie*/
    CardPresident tirerUneCarteAleatoire(AbstractGenerator _gene) {
//        return jouer((int)Math.floor(total()*MonteCarlo.randomDouble()));
        return jouer((int) MonteCarloUtil.randomLong(total(),_gene));
        //0<=total()*Math.random()<total()
        //Donc 0<=Math.floor(total()*Math.random())<Math.floor(total())=total()
    }

    /**Joue une carte a la position a
    @return la carte a jouer*/
    public CardPresident jouer(int _a) {
        CardPresident c_ = cards.get(_a);
        cards.remove(_a);
        return c_;
    }

    /**Renvoie le nombre total de cartes dans la main*/
    public int total() {
        return cards.size();
    }
    /**Renvoie la carte a la position i*/
    public CardPresident carte(int _i) {
        return cards.get(_i);
    }
    public CardPresident premiereCarte() {
        return cards.first();
    }
    public CardPresident derniereCarte() {
        return cards.last();
    }

    public boolean containsCards(HandPresident _hand) {
        for (CardPresident c: _hand) {
            Ints indexesHand_ = cards.indexesOfObj(c);
            Ints indexesOtherHand_ = _hand.cards.indexesOfObj(c);
            if (indexesHand_.size() < indexesOtherHand_.size()) {
                return false;
            }
        }
        return true;
    }

    public void supprimerCartes() {
        cards.clear();
    }

    public void supprimerCarte(int _index) {
        cards.remove(_index);
    }

    public void supprimerCartes(HandPresident _hand) {
        int s_ = cards.size();
        for (CardPresident c: _hand) {
            for (int i = IndexConstants.FIRST_INDEX; i < s_; i++) {
                if (cards.get(i) == c) {
                    cards.remove(i);
                    break;
                }
            }
        }
    }

    public boolean estVide() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<CardPresident> iterator() {
        return cards.iterator();
    }

    public EnumList<CardPresident> getCards() {
        return cards;
    }

    public void setCards(EnumList<CardPresident> _cards) {
        cards = _cards;
    }


}
