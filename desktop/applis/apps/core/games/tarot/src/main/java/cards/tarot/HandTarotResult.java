package cards.tarot;

import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public final class HandTarotResult {
    private final HandTarot playable;
    private final ReasonPlayTarot reason;

    public HandTarotResult(HandTarot _p) {
        this(ReasonPlayTarot.NOTHING,_p);
    }

    public HandTarotResult(ReasonPlayTarot _r,HandTarot _p) {
        this.playable = _p;
        this.reason = _r;
    }
    static HandTarotResult cartesJouables(RulesTarot _nbPlayers, int _taker, IdMap<Suit, HandTarot> _repartitionMain, TrickTarot _progressingTrick, CustList<TrickTarot> _tricks, HandTarot _calledCards) {
        HandTarot atoutsJoues_ = _progressingTrick.getCartes().couleurs().getVal(Suit.TRUMP);
        Suit couleurDemandee_ = _progressingTrick.couleurDemandee();
        if (_progressingTrick.couleurDemandee() == Suit.UNDEFINED) {
            return starter(_nbPlayers,_taker, _repartitionMain, _tricks, _calledCards);
        }
        HandTarot cartesJouables_ = new HandTarot();
        cartesJouables_.ajouterCartes(_repartitionMain.getVal(CardTarot.EXCUSE.getId().getCouleur()));
        if (Suit.couleursOrdinaires().containsObj(couleurDemandee_)
                && !_repartitionMain.getVal(couleurDemandee_).estVide()) {
            cartesJouables_
                    .ajouterCartes(_repartitionMain.getVal(couleurDemandee_));
            return new HandTarotResult(ReasonPlayTarot.FOLLOW_SUIT,cartesJouables_);
        }
        if (_repartitionMain.getVal(Suit.TRUMP).estVide()) {
            for (Suit couleur_ : Suit.couleursOrdinaires()) {
                cartesJouables_.ajouterCartes(_repartitionMain.getVal(couleur_));
            }
            return new HandTarotResult(cartesJouables_);
        }
        if (atoutsJoues_.estVide()) {
            cartesJouables_.ajouterCartes(_repartitionMain.getVal(Suit.TRUMP));
            return new HandTarotResult(ReasonPlayTarot.TR_TRICK,cartesJouables_);
        }
        int nombreDeJoueurs_ = _nbPlayers.getDealing().getId().getNombreJoueurs();
        int ramasseurVirtuel_ = _progressingTrick.getRamasseur(nombreDeJoueurs_);
        CardTarot carteForte_ = _progressingTrick.carteDuJoueur(
                ramasseurVirtuel_, nombreDeJoueurs_);
        int valeurForte_ = carteForte_.strength(couleurDemandee_);
        if (_repartitionMain.getVal(Suit.TRUMP).premiereCarte().strength(couleurDemandee_) < valeurForte_) {
            cartesJouables_.ajouterCartes(_repartitionMain.getVal(Suit.TRUMP));
            return new HandTarotResult(ReasonPlayTarot.UNDER_TR_TRICK,cartesJouables_);
        }
        if (valeurForte_ < _repartitionMain.getVal(Suit.TRUMP).derniereCarte().strength(couleurDemandee_)) {
            cartesJouables_.ajouterCartes(_repartitionMain.getVal(Suit.TRUMP));
            return new HandTarotResult(ReasonPlayTarot.OVER_TR_TRICK,cartesJouables_);
        }
        int indiceCarte_ = IndexConstants.FIRST_INDEX;
        HandTarot trumps_ = _repartitionMain.getVal(Suit.TRUMP);
        while (trumps_.carte(indiceCarte_)
                .strength(couleurDemandee_) > valeurForte_) {
            cartesJouables_.ajouter(trumps_.carte(indiceCarte_));
            indiceCarte_++;
        }
        return new HandTarotResult(ReasonPlayTarot.FOLLOW_TR_GREATER,cartesJouables_);
    }

    private static HandTarotResult starter(RulesTarot _nbPlayers, int _taker, IdMap<Suit, HandTarot> _repartitionMain, CustList<TrickTarot> _tricks, HandTarot _calledCards) {
        if (!GameTarotCommonPlaying.premierTour(_taker, _tricks)) {
            HandTarot cartesJouables_ = new HandTarot();
            cartesJouables_.ajouterCartes(HandTarot.reunion(_repartitionMain));
            return new HandTarotResult(cartesJouables_);
        }
        HandTarot copy_ = new HandTarot();
        copy_.ajouterCartes(_calledCards);
        copy_.supprimerCartes(HandTarot.trumpsPlusExcuse());
        if (!copy_.estVide() && !_nbPlayers.isAllowPlayCalledSuit()) {
            HandTarot cartesJouables_ = new HandTarot();
            CardTarot cardTarot_ = copy_.premiereCarte();
            Suit couleur_ = cardTarot_.getId().getCouleur();
            for (Suit s : Suit.couleursOrdinaires()) {
                if (s == couleur_) {
                    continue;
                }
                cartesJouables_.ajouterCartes(_repartitionMain.getVal(s));
            }
            for (CardTarot c : _repartitionMain.getVal(couleur_)) {
                if (c == cardTarot_) {
                    cartesJouables_.ajouter(c);
                }
            }
            cartesJouables_.ajouterCartes(_repartitionMain.getVal(Suit.TRUMP));
            cartesJouables_.ajouterCartes(_repartitionMain.getVal(Suit.UNDEFINED));
            if (!cartesJouables_.estVide()) {
                return new HandTarotResult(ReasonPlayTarot.NO_CALLED_SUIT,cartesJouables_);
            }
        }
        HandTarot cartesJouables_ = new HandTarot();
        cartesJouables_.ajouterCartes(HandTarot.reunion(_repartitionMain));
        return new HandTarotResult(cartesJouables_);
    }

    public HandTarot getPlayable() {
        return playable;
    }

    public ReasonPlayTarot getReason() {
        return reason;
    }
}
