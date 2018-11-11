package cards.president;
import java.util.Iterator;

import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.enumerations.CardPresident;
import code.maths.montecarlo.AbMonteCarlo;
import code.util.CustList;
import code.util.EnumList;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.annot.RwXml;
import code.util.consts.Constants;
import code.util.ints.Equallable;

@RwXml
public final class HandPresident implements Iterable<CardPresident>, Equallable<HandPresident> {

    private static final String SEPARATOR = " - ";
    private EnumList<CardPresident> cards=new EnumList<CardPresident>();

    public HandPresident() {
    }

    public HandPresident(HandPresident _hand) {
        cards.addAllElts(_hand.cards);
    }

    public static HandPresident stack(int _nbStacks) {
        HandPresident liste_ = new HandPresident();
        for (int i = CustList.FIRST_INDEX; i < _nbStacks; i++) {
            liste_.ajouterCartes(HandPresident.pileBase());
        }
        return liste_;
    }

    public static HandPresident pileBase() {
        HandPresident liste_ = new HandPresident();
        for(CardPresident carte_: CardPresident.values()) {
            if(!carte_.isPlayable()) {
                continue;
            }
            liste_.ajouter(carte_);
        }
        return liste_;
    }

    public boolean validStack(int _nbStacks) {
        for (CardPresident c: pileBase()) {
            int nbUses_ = CustList.SIZE_EMPTY;
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

    public EqList<HandPresident> getCardsByLengthSortedByStrength(boolean _reverse, int _len) {
        EqList<HandPresident> l_ = new EqList<HandPresident>();
        for (HandPresident h: getCardsByStrength(_reverse).values()) {
            if (h.total() != _len) {
                continue;
            }
            l_.add(h);
        }
        return l_;
    }

    public NatTreeMap<Byte,HandPresident> getCardsByStrength(boolean _reverse) {
        NatTreeMap<Byte,HandPresident> m_ = new NatTreeMap<Byte,HandPresident>();
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
    /**Ajoute une carte a la position donnee
    @param _t la carte a ajouter
    @param _a la position ou placer la carte*/
    public void ajouter(CardPresident _t,int _a) {
        cards.add(_a,_t);
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
        for (int i = CustList.FIRST_INDEX;i<taille_;i++) {
            ajouter(jouer(0));
        }
    }
    /**<ol>
    <li>Choisit un nombre aleatoire entre 0 et le nombre total de carte dans la main moins une carte
    d'ou total() x Math.random() puis Math.floor(total() x Math.random())</li>
    <li>puis retire la carte choisie de la main courante et la retourne</li>
    </ol>
    @return la carte aleatoire choisie*/
    CardPresident tirerUneCarteAleatoire() {
//        return jouer((int)Math.floor(total()*MonteCarlo.randomDouble()));
        return jouer(AbMonteCarlo.randomInt(total()));
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
            Numbers<Integer> indexesHand_ = cards.indexesOfObj(c);
            Numbers<Integer> indexesOtherHand_ = _hand.cards.indexesOfObj(c);
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

    public void supprimerUneCarte(CardPresident _c) {
        int s_ = cards.size();
        for (int i = CustList.FIRST_INDEX; i < s_; i++) {
            if (cards.get(i) == _c) {
                cards.remove(i);
                break;
            }
        }
    }

    public void supprimerCartes(HandPresident _hand) {
        int s_ = cards.size();
        for (CardPresident c: _hand) {
            for (int i = CustList.FIRST_INDEX; i < s_; i++) {
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

    public static boolean equalsSet(HandPresident _handOne, HandPresident _handTwo) {
        return CardPresident.equalsCards(_handOne.cards, _handTwo.cards);
//        return CustList.equalsSet(_handOne.cards, _handTwo.cards, true);
    }

    @Override
    public boolean eq(HandPresident _o) {
        if(_o.total()!=total()) {
            return false;
        }
        boolean id_=true;
        int nbCards_ = total();
        for (int i = CustList.FIRST_INDEX; i < nbCards_; i++) {
            if (!CardPresident.eq(_o.carte(i), carte(i))) {
                id_ = false;
            }
        }
        return id_;
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

    public String toString(String _lg) {
        StringList retString_= new StringList();
        for (CardPresident c: cards) {
            retString_.add(c.toString(_lg));
        }
        return retString_.join(SEPARATOR);
    }

    public CharSequence display() {
        StringList retString_= new StringList();
        for (CardPresident c: cards) {
            retString_.add(c.toString(Constants.getDefaultLanguage()));
        }
        return retString_.join(SEPARATOR);
    }
}
