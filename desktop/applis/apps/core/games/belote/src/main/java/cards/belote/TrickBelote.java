package cards.belote;
import java.util.Iterator;

import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.consts.SortedPlayers;
import cards.consts.Suit;
import code.util.*;
import code.util.core.IndexConstants;


public final class TrickBelote implements Iterable<CardBelote> {
    /**Entameur du pli*/
    private byte starter;
    /**m est l'ensemble de cartes jouees pendant le pli a la belote ou au tarot*/
    private HandBelote cards = new HandBelote();
    public TrickBelote() {}
    public TrickBelote(byte _pentameur) {
        initPli(_pentameur);
    }
    public TrickBelote(HandBelote _pm,byte _pentameur) {
        initPli(_pentameur);
        setCards(_pm);
    }
    void initPli(byte _pentameur) {
        setStarter(_pentameur);
    }

    public boolean foundLast(CustList<TrickBelote> _tricks) {
        boolean found_ = false;
        for (TrickBelote t: _tricks.right(1)) {
            if (HandBelote.equalsSet(t.getCartes(), getCartes())) {
                found_ = true;
                break;
            }
        }
        return found_;
    }

    /**Retourne l'entameur du pli*/
    public byte getEntameur() {
        return getStarter();
    }

    public HandBelote getCartes() {
        return getCards();
    }
    /**Indique le joueur qui doit ramasser le pli &agrave; la belote
    pour entamer l'eventuel suivant
    @param nombre_joueurs nombre de joueurs qui jouent a cette partie
    @param _contrat contrat de la partie
    @param couleur_atout la couleur d'atout si elle existe*/
    public byte getRamasseur(BidBeloteSuit _contrat) {
        return getRamasseurPliEnCours((byte) total(),_contrat);
    }
    public byte getRamasseurPliEnCours(byte _nombreJoueurs, BidBeloteSuit _contrat) {
        byte ramasseur_;
        byte max_=0;
        byte i=0;
        byte position_=0;
        Suit demande_=couleurDemandee();
        byte valForce_;
        for(CardBelote c:cards) {
            valForce_=c.strength(demande_,_contrat);
            if(valForce_>max_) {
                position_=i;
                max_=valForce_;
            }
            i++;
        }
        ramasseur_=position_;
        //Ramasseur est_ la_ position_ du_ ramasseur_ par_ rapport_ a l'entameur
        //On calcule_ la_ position_ de_ ramasseur_ par_ rapport_ a celle_ de_ l'utilisateur_
        return (byte) ((ramasseur_+getEntameur())%_nombreJoueurs);
        //On renvoie_ le_ ramasseur_ du_ pli_ courant_
    }

    public byte getNextPlayer(byte _nbPlayer) {
        return (byte) ((starter + total()) % _nbPlayer);
    }
    void ajouter(CardBelote _c) {
        cards.ajouter(_c);
    }
    void retirer(CardBelote _ct) {
        cards.jouer(_ct);
    }
    public CardBelote carte(int _i) {
        return cards.carte(_i);
    }
    public CardBelote premiereCarte() {
        return cards.premiereCarte();
    }
    byte joueurAyantJoue(CardBelote _c) {
        return joueurAyantJoue(_c,(byte) total());
    }
    public byte joueurAyantJoue(CardBelote _c,byte _nombreDeJoueurs) {
        if(!contient(_c)) {
            return IndexConstants.INDEX_NOT_FOUND_ELT;
        }
        byte position_=(byte)cards.position(_c);
        return (byte)((position_+getEntameur())%_nombreDeJoueurs);
    }

    Bytes joueursAyantJoueAvant(byte _pnumero, DealingBelote _d) {
        return _d.getId().joueursAyantJoueAvant(_pnumero,starter,total());
    }

    boolean aJoue(byte _joueur,byte _nombreDeJoueurs) {
        return new SortedPlayers(_nombreDeJoueurs).aJoue(_joueur,total(),getEntameur());
    }
    CardBelote carteDuJoueur(byte _joueur) {
        return carteDuJoueur(_joueur, (byte) total());
    }
    /**Retourne la carte du joueur de variable joueur en fonction du nombre de joueurs et du pli ayant la plus petite longueur*/
    public CardBelote carteDuJoueur(byte _joueur,byte _nombreDeJoueurs) {
        return carte(new SortedPlayers(_nombreDeJoueurs).index(_joueur,getEntameur(),total()));
    }
    /**Retourne la couleur demandee du pli*/
    public Suit couleurDemandee() {
        if(estVide()) {
            return Suit.UNDEFINED;
        }
        return premiereCarte().getId().getCouleur();
    }
    /**Retourne l'ensemble des joueurs qui coupent ce pli<br>
    <ol><li>si la couleur demandee est de l'atout alors on cherche l'ensemble des joueurs n'ayant pas joue de l'atout(Excuse incluse)</li>
    <li>sinon on cherche les joueurs ayant joue de l'atout sur une couleur</li></ol>
    Ces joueurs sont classes par ordre chronologique de jeu*/
    Bytes joueursCoupes(Suit _couleurAtout) {
        Bytes coupes_=new Bytes();
        for(CardBelote c: cards) {
            if(c.getId().getCouleur() ==_couleurAtout) {
                coupes_.add(joueurAyantJoue(c));
            }
        }
        return coupes_;
    }
    Bytes joueursDefausses(Suit _couleurAtout) {
        Bytes coupes_=new Bytes();
        Suit couleur_;
        couleur_=couleurDemandee();
        for(CardBelote c: cards) {
            if(c.getId().getCouleur() !=_couleurAtout&& c.getId().getCouleur() !=couleur_) {
                coupes_.add(joueurAyantJoue(c));
            }
        }
        return coupes_;
    }

    public CustList<HandBelote> completeCurrent(byte _nb, boolean _add) {
        CustList<HandBelote> ls_ = new CustList<HandBelote>();
        for (int i = 0; i < _nb; i++) {
            ls_.add(new HandBelote());
        }
        if (_add) {
            for (byte b: playersHavingPlayed(_nb)) {
                ls_.get(b).ajouter(carteDuJoueur(b, _nb));
            }
        }
        return ls_;
    }
    public Bytes playersHavingPlayed(byte _numberPlayers) {
        return new SortedPlayers(_numberPlayers).joueursAyantJoue(starter,total());
    }
    /**Retourne vrai si et seulement si la carte est jouee dans ce pli*/
    public boolean contient(CardBelote _c) {
        return cards.contient(_c);
    }
    /**Si tous les joueurs ne joue qu'une carte alors total() renvoie le nombre de cartes du pli
    sinon il renvoie le nombre total de cartes divise par le nombres de cartes jouees par joueur pour le pli*/
    public int total() {
        return cards.total();
    }
    public boolean estVide() {
        return cards.estVide();
    }
    @Override
    public Iterator<CardBelote> iterator() {
        return cards.iterator();
    }

    void setEntameur(int _i) {
        starter = (byte) _i;
    }
    public byte getStarter() {
        return starter;
    }
    public void setStarter(byte _starter) {
        starter = _starter;
    }
    public HandBelote getCards() {
        return cards;
    }
    public void setCards(HandBelote _cards) {
        cards = _cards;
    }
}
