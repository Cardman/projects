package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.PossibleTrickWinner;
import cards.consts.Role;
import cards.consts.Suit;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class GameBeloteProgTrick {
    private final GameBeloteTeamsRelation teamsRelation;
    private final GameBeloteTrickInfo info;

    private final Role currentStatus;
    private final HandBelote playableCards;
    private final BidBeloteSuit bid;
    private final byte taker;
    private final BeloteInfoPliEnCours beloteInfoPliEnCours;
    private final IdMap<Suit, HandBelote> repartitionJouables;
    private final Suit couleurDemandee;
    private final IdMap<Suit, HandBelote> repartitionCartesJouees;
    private final IdMap<Suit, CustList<HandBelote>> repartitionJouablesToutes = new IdMap<Suit, CustList<HandBelote>>();
    private final CustList<HandBelote> suitesJouables;
    private final CardBelote carteForte;
    private final Bytes joueursNonJoue;
    private final IdMap<Suit, CustList<HandBelote>> cartesPossibles;
    private final IdMap<Suit, CustList<HandBelote>> cartesCertaines;
    private final Bytes joueursNonConfiance;
    private final Bytes adversaireNonJoue;
    private final Suit couleurAtout;
    private final CustList<HandBelote> cartesRelativementMaitre;
    private final CustList<HandBelote> cartesRelativementMaitreAtout;
    private final CustList<HandBelote> suitesJouablesAtout;
    private final CustList<TrickBelote> plisFaits;
    private final int offset;
    private final IdMap<Suit, HandBelote> cartesMaitresses;
    private final IdList<Suit> strictCouleursMaitresses;
    private final boolean maitreJeu;
    private final IdList<Suit> couleursNonAtouts;
    private final IdList<Suit> couleursNonVidesJouable;
    private final IdList<Suit> couleursAutreQueDemandeeEtAtout;
    private final IdList<Suit> couleursAvecPointsJouable;
    private final IdList<Suit> couleursFiguresJouable;
    private final IdList<Suit> couleursSansPointJouable;

    public GameBeloteProgTrick(GameBeloteTrickInfo _done, GameBeloteTeamsRelation _teamsRelation, HandBelote _currentHand) {
        teamsRelation = _teamsRelation;
        info = _done;
        bid = _done.getBid();
        taker = _teamsRelation.getTaker();
        GameBeloteCommonPlaying common_ = new GameBeloteCommonPlaying(_done, _teamsRelation);
        byte nbPlayers_ = _teamsRelation.getNombreDeJoueurs();
        TrickBelote trBelote_ = _done.getProgressingTrick();
        byte nextPlayer_ = trBelote_.getNextPlayer(nbPlayers_);
        IdMap<Suit, HandBelote> repartition_ = _currentHand.couleurs(bid);
        playableCards = common_.playableCards(repartition_);
        repartitionJouables = playableCards.couleurs(bid);
        currentStatus = _teamsRelation.statutDe(nextPlayer_);
        beloteInfoPliEnCours = common_.initInformations(_currentHand);
        repartitionCartesJouees = beloteInfoPliEnCours.getRepartitionCartesJouees();
        TrickBelote progressingTrick_ = _done.getProgressingTrick();
        couleurDemandee = progressingTrick_.couleurDemandee();
        couleurAtout = beloteInfoPliEnCours.getCouleurAtout();
        for (EntryCust<Suit, HandBelote> e: repartitionJouables.entryList()) {
            repartitionJouablesToutes.addEntry(e.getKey(),e.getValue().eclater(repartitionCartesJouees, bid));
        }
        suitesJouables = repartitionJouablesToutes.getVal(couleurDemandee);
        suitesJouablesAtout = repartitionJouablesToutes.getVal(couleurAtout);
        carteForte = progressingTrick_.carteDuJoueur( beloteInfoPliEnCours.getRamasseurVirtuel(), nbPlayers_);
        joueursNonJoue = beloteInfoPliEnCours.getJoueursNonJoue();
        cartesPossibles = beloteInfoPliEnCours.getCartesPossibles();
        cartesCertaines = beloteInfoPliEnCours.getCartesCertaines();
        joueursNonConfiance = beloteInfoPliEnCours.getJoueursNonConfiance();
        adversaireNonJoue = GameBeloteTeamsRelation.intersectionJoueurs(joueursNonConfiance, joueursNonJoue);
        cartesRelativementMaitre = cartesRelativementMaitre(couleurDemandee);
        if (couleurAtout != Suit.UNDEFINED) {
            cartesRelativementMaitreAtout = cartesRelativementMaitre(couleurAtout);
        } else {
            cartesRelativementMaitreAtout = new CustList<HandBelote>();
        }
        plisFaits = beloteInfoPliEnCours.getPlisFaits();
        offset = RulesBelote.offset(_teamsRelation.getRules());
        cartesMaitresses = beloteInfoPliEnCours.getCartesMaitresses();
        strictCouleursMaitresses = beloteInfoPliEnCours.getStrictCouleursMaitresses();
        maitreJeu = beloteInfoPliEnCours.isMaitreJeu();
        couleursNonAtouts = common_.couleursNonAtouts();
        couleursNonVidesJouable = GameBeloteCommon.couleursNonAtoutNonVides(repartitionJouables, couleursNonAtouts);
        IdList<Suit> couleursAutreQueDemandeeEtAtout_ = new IdList<Suit>(couleursNonAtouts);
        couleursAutreQueDemandeeEtAtout_.removeObj(couleurDemandee);
        couleursAutreQueDemandeeEtAtout = couleursAutreQueDemandeeEtAtout_;
        couleursAvecPointsJouable = GameBeloteCommon.couleursAvecPoints(repartitionJouables, bid, couleursNonVidesJouable);
        couleursFiguresJouable = GameBeloteCommon.couleursNonAtoutAyantNbCartesSupEg(repartitionJouables, couleursAvecPointsJouable, 2);
        couleursSansPointJouable = GameBeloteCommon.couleursSansPoint(repartitionJouables, bid, couleursNonVidesJouable);
    }

    CardBelote enCours() {
        if(playableCards.total()==1) {
            return playableCards.premiereCarte();
        }
        if(bid.getCouleurDominante()) {
            return domSuit();
        }
        if(bid.ordreCouleur()) {
            if(!repartitionJouables.getVal(couleurDemandee).estVide()) {
                return fournirCouleurOrdinaireSansAtout();
            }
            return defausseCouleurOrdinaireSansAtout();
        }
        //jeu tout atout
        if(!mainCouleurDemandeeJouable().estVide()) {
            return fournirAtoutToutAtout();
        }
        return defausseAtoutToutAtout();
    }

    private CardBelote domSuit() {
        Suit couleurAtout_= bid.getSuit();
        if (couleurDemandee != couleurAtout_) {
            if(!repartitionJouables.getVal(couleurDemandee).estVide()) {
                return fournirCouleurOrdinaireCouleurDominante();
            }
            if(!repartitionJouables.getVal(couleurAtout_).estVide()) {
                return coupeCouleurDominante();
            }
            return defausseCouleurOrdinaireCouleurDominante();
        }
        //entame atout
        if(!repartitionJouables.getVal(couleurAtout_).estVide()) {
            return fournirAtoutCouleurDominante();
        }
        return defausseAtoutCouleurDominante();
    }

    CardBelote fournirCouleurOrdinaireCouleurDominante() {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(beloteInfoPliEnCours);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            return followSuitFoe();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return followNormalSuitPartner();
        }
        //Si le joueur ne coupe pas et ne se defause pas sur la couleur demandee
        CardBelote card_ = followNormalSuit();
        if (card_ != CardBelote.WHITE) {
            return card_;
        }
        /*Maintenant le joueur peut prendre la GameBeloteCommon.main avec une figure a la couleur demandee*/
        if(currentStatus == Role.TAKER) {
            return followDominantAsTaker();
        }
        /*Appele*/
        if(currentStatus == Role.CALLED_PLAYER) {
            return followDominantAsCalledPlayer();
        }
        return followDominantAsDefender();
    }

    CardBelote followDominantAsDefender() {
        /*Defenseur*/
        if(!cartesRelativementMaitre.isEmpty()) {
            return cartesRelativementMaitre.last().premiereCarte();
        }
        boolean pasAtoutAdvNonJoue_ = GameBeloteTrickHypothesis.pasAtoutJoueurs(adversaireNonJoue, cartesPossibles, couleurAtout);
        //si aucun adv non joue n'a de l'atout alors les points peuvent etre joues
        if(pasAtoutAdvNonJoue_) {
            return sauveQuiPeutFigure();
        }
        return mainCouleurDemandeeJouable().derniereCarte();
    }

    CardBelote followDominantAsCalledPlayer() {
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee, plisFaits.mid(offset));
        if (!cartesRelativementMaitre.isEmpty()) {
            return cartesRelativementMaitre.last().premiereCarte();
        }
        if(tours_.isEmpty()) {
            return notStarted();
        }
        return playAtNextRound();
    }

    private CardBelote notStarted() {
        if (joueursNonJoue.containsObj(taker) && GameBeloteBeginTrick.playedLeading(bid, taker, couleurDemandee, repartitionCartesJouees, cartesCertaines)) {
            return suitesJouables.first().premiereCarte();
        }
        return playWhenLastDiscard();
    }

    CardBelote followDominantAsTaker() {
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee, plisFaits.mid(offset));
        if(tours_.isEmpty()) {
            return playWhenLastDiscard();
        }
        return playAtNextRound();

    }

    CardBelote playAtNextRound() {
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee, plisFaits.mid(offset));
        Bytes joueursSusceptiblesDeCouper_=new Bytes();
        TrickBelote dernierPli_;
        Bytes dernieresDefausses_;
        dernierPli_ = tours_.last();
        dernieresDefausses_ = dernierPli_.joueursDefausses(couleurAtout);
        for (byte joueur_ : joueursNonJoue) {
            if (GameBeloteCommonPlaying.peutCouper(couleurDemandee, joueur_, cartesPossibles, couleurAtout)) {
                joueursSusceptiblesDeCouper_.add(joueur_);
            }
        }
        if (!joueursSusceptiblesDeCouper_.isEmpty()) {
            return playWhenPossibleTrump();
        }
        /*Si la coupe semble improbable*/
        if (!dernieresDefausses_.isEmpty()) {
            return playWhenLastDiscard();
        }
        /*Le pli d'avant n'est pas defausse ou c'est au moins le troisieme tour*/
        if (!cartesRelativementMaitre.isEmpty()) {
            return cartesRelativementMaitre.last().premiereCarte();
        }
        return mainCouleurDemandeeJouable().derniereCarte();
    }

    CardBelote playWhenPossibleTrump() {
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        byte next_ = info.getProgressingTrick().getNextPlayer(teamsRelation.getNombreDeJoueurs());
        Bytes joueursSusceptiblesDeCouper_=GameBeloteCommonPlaying.joueursSusceptiblesCoupe(cartesPossibles,
                couleurDemandee, bid.getSuit(),joueursNonConfiance);
        byte maxTwo_;
        for (byte joueur_ : joueursNonConfiance) {
            if (joueursSusceptiblesDeCouper_.containsObj(joueur_)) {
                return mainCouleurDemandeeJouable().derniereCarte();
            }
        }
        if (maitreJeu) {
            maxTwo_ = 0;
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (joueur_ != next_) {
                    maxTwo_ = (byte) NumberUtil.max(GameBeloteCommon.hand(cartesPossibles, couleurDemandee, joueur_).total(), maxTwo_);
                }
            }
            if (suitesJouables.get(0).total() > maxTwo_) {
                return mainCouleurDemandeeJouable().premiereCarte();
            }
            return mainCouleurDemandeeJouable().derniereCarte();
        }
        return jeuFigureHauteDePlusFaibleSuite();
    }

    CardBelote playWhenLastDiscard() {
        /*Le joueur n'a aucune cartes maitresses*/
        return defFollow();
    }
//
//    CardBelote playWhenLastDiscard() {
//        /*Le joueur n'a aucune cartes maitresses*/
//        if(!cartesRelativementMaitre.isEmpty()) {
//            return cartesRelativementMaitre.last().premiereCarte();
//        }
//        return GameBeloteCommon.hand(repartitionJouables,couleurDemandee).derniereCarte();
//    }

    CardBelote followNormalSuit() {
        //Si le joueur ne coupe pas et ne se defause pas sur la couleur demandee
        CardBelote bestCard_ = mainCouleurDemandeeJouable().premiereCarte();
        if(bestCard_.strength(couleurDemandee,bid)
                <carteForte.strength(couleurDemandee,bid)) {
            CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee, plisFaits.mid(offset));
            /*Si le joueur ne peut pas prendre la GameBeloteCommon.main*/
            if(tours_.isEmpty()) {
                /*Si cette couleur est entamee pour la premiere fois*/
                return followPossiblePtsFirstRound();
            }
            return followSuitFoe();
        }
        /*Maintenant on sait que le joueur peut prendre la main*/
        if(bestCard_.points(bid)<1) {
            //si aucun adv non joue ne possede de figure
            return bestCard_;
        }
        return CardBelote.WHITE;
    }

    boolean leadingPartner() {
        boolean partenaireMaitre_ = true;
        for(byte j: adversaireNonJoue) {
            HandBelote couleurPossible_ = GameBeloteCommon.hand(cartesPossibles, couleurDemandee,j);
            if (couleurPossible_.estVide() || carteForte.strength(couleurDemandee, bid) <= couleurPossible_.premiereCarte().strength(couleurDemandee, bid)) {
                partenaireMaitre_ = false;
                break;
            }
        }
        return partenaireMaitre_;
    }

    CardBelote followPossiblePtsFirstRound() {
        boolean partenaireMaitre_ = leadingPartner();
        if(partenaireMaitre_) {
            return mainCouleurDemandeeJouable().premiereCarte();
        }
        return mainCouleurDemandeeJouable().derniereCarte();
    }

    CardBelote followNormalSuitPartner() {
        HandBelote playable_ = mainCouleurDemandeeJouable();
        for(byte joueur_:joueursNonConfiance) {
            HandBelote possible_ = GameBeloteCommon.hand(cartesPossibles, couleurDemandee, joueur_);
            if (!possible_.estVide() && possible_.premiereCarte().strength(couleurDemandee, bid)
                    > playable_.premiereCarte().strength(couleurDemandee, bid)) {
                return playable_.premiereCarte();
            }
        }
        if(playable_.nombreCartesPoints(bid)>1) {
            return cartePlusPetitePoints(suitesJouables,bid);
        }
        return playable_.derniereCarte();
    }

    private boolean canLeadTrick() {
        return !cartesRelativementMaitreAtout.isEmpty() && maitreJeu;
    }

    CardBelote coupeCouleurDominante() {
        if(mainCouleurAtoutJouable().total()==playableCards.total()) {
            return coupeObligatoireCouleurDominante();
        }
        return coupeFacultativeCouleurDominante();
    }
    CardBelote coupeObligatoireCouleurDominante() {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(beloteInfoPliEnCours);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*Si le joueur est oblige de couper la couleur demandee*/
            return mainCouleurAtoutJouable().derniereCarte();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            if(suitesJouablesAtout.size()==1) {
                return mainCouleurAtoutJouable().premiereCarte();
            }
            return suitesJouablesAtout.last().premiereCarte();
        }
        /*Coupe obligatoire (ou surcoupe ou sous-coupe)*/
        return coupe();
    }
    CardBelote coupeFacultativeCouleurDominante() {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(beloteInfoPliEnCours);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*Si le joueur se defausse*/
            return discardFoe();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return defausseCouleurDemandeeSurPartenaireCouleurDominante(
                    cartesMaitresses
            );
        }
        return optTrump();
    }

    CardBelote optTrump() {
//        CustList<HandBelote> cartesRelMaitres_=cartesRelativementMaitre(suitesJouablesAtout,
//                couleurAtout);
        /*Coupe possible non obligatoire, car partenaire.contains(ramasseurVirtuel)*/
        if(canLeadTrick()) {
            return cartesRelativementMaitreAtout.last().premiereCarte();
        }
        if(!leadingPartner()) {
            return coupe();
        }
        return defausseCouleurDemandeeSurPartenaireCouleurDominante(
                cartesMaitresses);
    }

    CardBelote fournirAtoutCouleurDominante() {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(beloteInfoPliEnCours);
        //entame atout
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            return followSuitFoe();

        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return followTrumpPartner();
        }
        //peut ramasser
        return playDefaultTrump();
    }

    CardBelote followSuitFoe() {
        return mainCouleurDemandeeJouable().derniereCarte();
    }

    CardBelote followTrumpPartner() {
        HandBelote playable_ = mainCouleurDemandeeJouable();
        if(suitesJouables.size()==1) {
            return playable_.premiereCarte();
        }
        for(byte joueur_:joueursNonConfiance) {
            HandBelote possible_ = GameBeloteCommon.hand(cartesPossibles, couleurDemandee, joueur_);
            if (!possible_.estVide() && possible_.premiereCarte().strength(couleurDemandee, bid)
                    > playable_.premiereCarte().strength(couleurDemandee, bid)) {
                //Il existe un adversaire pouvant surcouper le pli avec un atout relativement maitre sur le joueur courant
                return playable_.premiereCarte();
            }
        }
        return playable_.derniereCarte();
    }

    CardBelote defausseCouleurOrdinaireCouleurDominante() {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(beloteInfoPliEnCours);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            return discardFoe();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return defausseCouleurDemandeeSurPartenaireCouleurDominante(cartesMaitresses);
        }
        return discardFoe();
    }
    CardBelote defausseAtoutCouleurDominante() {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(beloteInfoPliEnCours);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*La couleur demandee est atout*/
            /*Maintenant le joueur se defausse sur demande d'atout*/

            return discardFoe();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            /*Maintenant le joueur se defausse*/
            return defausseAtoutSurPartenaireCouleurDominante(cartesMaitresses);
        }

        return discardFoe();
    }
    CardBelote fournirCouleurOrdinaireSansAtout() {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliSansAtout(beloteInfoPliEnCours);

        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            return followSuitFoe();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return followNormalSuitPartner();
        }
        CardBelote card_ = followNormalSuitNoTrump();
        if (card_ != CardBelote.WHITE) {
            return card_;
        }
        /*Maintenant le joueur peut prendre la GameBeloteCommon.main avec une figure a la couleur demandee*/
        if(currentStatus == Role.TAKER) {
            return followNoTrumpAsTaker();
        }
        /*Appele*/
        if(currentStatus == Role.CALLED_PLAYER) {
            return followNoTrumpAsCalledPlayer();
        }
        return followNoTrumpAsDefender();
    }

    CardBelote followNoTrumpAsDefender() {
        /*Defenseur*/
        if(!cartesRelativementMaitre.isEmpty()) {
            return cartesRelativementMaitre.last().premiereCarte();
        }
        //si aucun adv non joue n'a de l'atout alors les points peuvent etre joues
        return sauveQuiPeutFigure();
    }

    CardBelote followNoTrumpAsCalledPlayer() {
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee, plisFaits.mid(offset));
        if (!cartesRelativementMaitre.isEmpty()) {
            return cartesRelativementMaitre.last().premiereCarte();
        }
        if(tours_.isEmpty()) {
            return notStarted();
        }
        return mainCouleurDemandeeJouable().derniereCarte();
    }

    CardBelote followNoTrumpAsTaker() {
        return playWhenLastDiscard();
    }

    CardBelote followNormalSuitNoTrump() {

        //Si le joueur ne se defause pas sur la couleur demandee
        CardBelote bestCard_ = mainCouleurDemandeeJouable().premiereCarte();
        if(bestCard_.strength(couleurDemandee,bid)<
                carteForte.strength(couleurDemandee,bid)) {
            /*Si le joueur ne peut pas prendre la GameBeloteCommon.main*/
            CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee, plisFaits.mid(offset));
            /*Le joueur possede au moins une figure*/
            if(tours_.isEmpty()) {
                /*Si cette couleur est entamee pour la premiere fois*/
                return followPossiblePtsFirstRound();
            }
            /*Maintenant on aborde au moins le deuxieme tour*/
            /*Si le dernier pli n'est pas coupe a cette couleur*/
            return mainCouleurDemandeeJouable().derniereCarte();
        }
        /*Maintenant on sait que le joueur peut prendre la GameBeloteCommon.main*/
        if(bestCard_.points(bid)<1) {
            //si aucun adv non joue ne possede de figure
            return bestCard_;
        }
        return CardBelote.WHITE;
    }
    CardBelote defausseCouleurOrdinaireSansAtout() {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliSansAtout(beloteInfoPliEnCours);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*Maintenant le joueur se defausse*/
            return discardFoe();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return defausseCouleurDemandeeSurPartenaireCouleursEgales(cartesMaitresses);
        }
        return discardFoe();
    }
    CardBelote fournirAtoutToutAtout() {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(beloteInfoPliEnCours);
        //jeu tout atout
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            return followSuitFoe();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return followTrumpPartner();

        }

        return playDefaultTrump();

    }

    CardBelote playDefaultTrump() {
        return defFollow();
    }

    private CardBelote defFollow() {
        if(!cartesRelativementMaitre.isEmpty()) {
            return cartesRelativementMaitre.last().premiereCarte();
        }
        return mainCouleurDemandeeJouable().derniereCarte();
    }

    CardBelote defausseAtoutToutAtout() {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(beloteInfoPliEnCours);
        //jeu tout atout
        //
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*Si le joueur se defausse*/
            return discardFoe();

        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return defausseAtoutSurPartenaireCouleursEgales(cartesMaitresses);
        }
        return discardFoe();

    }
    BeloteInfoPliEnCours initInformations() {
        return beloteInfoPliEnCours;
    }

    CardBelote coupe() {
//        CustList<HandBelote> cartesRelMaitres_ = cartesRelativementMaitre(suitesJouablesAtout, couleurAtout);
        if(!cartesRelativementMaitreAtout.isEmpty()) {
            return cartesRelativementMaitreAtout.last().premiereCarte();
        }
        return mainCouleurAtoutJouable().premiereCarte();
    }

    private CardBelote sauveQuiPeutFigure() {
        return sauveQuiPeutFigure(
                cartesRelativementMaitre);
    }

    CardBelote sauveQuiPeutFigure(
            CustList<HandBelote> _cartesRelMaitres) {
        //si aucun adversaire ne possede une carte a point dans la couleur demandee, alors les points peuvent etre sauves
        boolean aucuneCartePointsAdvNonJoue_ = true;
        for(byte j: adversaireNonJoue) {
            HandBelote possible_ = GameBeloteCommon.hand(cartesPossibles, couleurDemandee, j);
            if (!possible_.estVide() && possible_.premiereCarte().points(bid) >= 1) {
                aucuneCartePointsAdvNonJoue_ = false;
                break;
            }
        }
        if (!_cartesRelMaitres.isEmpty()) {
            if (_cartesRelMaitres.size() == 1) {
                return suitesJouables.first().premiereCarte();
            }
            //cartesRelMaitres.size() > 1 && cartesRelMaitres.get(1).premiereCarte().estUneFigure()
            return _cartesRelMaitres.last().premiereCarte();
        }
        if(aucuneCartePointsAdvNonJoue_) {
            return jeuFigureHauteDePlusFaibleSuite();
        }
        return suitesJouables.last().derniereCarte();
    }

    CustList<HandBelote> cartesRelativementMaitre(
            Suit _couleurJoueur) {
        byte maxValeur_=0;
        Suit dom_ = bid.getSuit();
        if(_couleurJoueur==dom_&&couleurDemandee!=dom_) {
            for(byte joueur_:joueursNonJoue) {
                HandBelote possible_ = GameBeloteCommon.hand(cartesPossibles, _couleurJoueur, joueur_);
                if(!possible_.estVide()&&GameBeloteCommon.hand(cartesCertaines,couleurDemandee,joueur_).estVide()) {
                    maxValeur_=(byte)NumberUtil.max(possible_.premiereCarte().strength(couleurDemandee,bid),maxValeur_);
                }
            }
        } else {
            for(byte joueur_:joueursNonJoue) {
                HandBelote possible_ = GameBeloteCommon.hand(cartesPossibles, _couleurJoueur, joueur_);
                if(!possible_.estVide()) {
                    maxValeur_=(byte)NumberUtil.max(possible_.premiereCarte().strength(couleurDemandee,bid),maxValeur_);
                }
            }
        }
        maxValeur_=(byte)NumberUtil.max(maxValeur_,carteForte.strength(couleurDemandee,bid));
        return filterSeq(_couleurJoueur, maxValeur_);
    }

    private CustList<HandBelote> filterSeq(Suit _couleurJoueur, byte _maxValeur) {
        CustList<HandBelote> suitesBis_=new CustList<HandBelote>();
        for(HandBelote suite_: repartitionJouablesToutes.getVal(_couleurJoueur)) {
            if(suite_.premiereCarte().strength(couleurDemandee,bid)> _maxValeur) {
                suitesBis_.add(suite_);
            } else {
                break;
            }
        }
        return suitesBis_;
    }

    private CardBelote jeuFigureHauteDePlusFaibleSuite() {
        if(suitesJouables.size()== IndexConstants.ONE_ELEMENT) {
            return suitesJouables.first().premiereCarte();
        }
        return cartePlusPetitePoints(suitesJouables,bid);
    }
    static CardBelote cartePlusPetitePoints(CustList<HandBelote> _suites, BidBeloteSuit _contrat) {
        CustList<HandBelote> suitesPoints_ = new CustList<HandBelote>();
        CustList<HandBelote> suitesZeroPoints_ = new CustList<HandBelote>();
        for(HandBelote suite_:_suites) {
            HandBelote points_ = new HandBelote();
            HandBelote zeroPoints_ = new HandBelote();
            for(CardBelote c: suite_) {
                if(c.points(_contrat) > 0) {
                    points_.ajouter(c);
                } else {
                    zeroPoints_.ajouter(c);
                }
            }
            if(!points_.estVide()) {
                suitesPoints_.add(points_);
            }
            if(!zeroPoints_.estVide()) {
                suitesZeroPoints_.add(zeroPoints_);
            }
        }
        if(!suitesZeroPoints_.isEmpty()) {
            return suitesZeroPoints_.last().premiereCarte();
        }
        return suitesPoints_.last().premiereCarte();
    }

    CardBelote defausseAtoutSurPartenaireCouleurDominante(
            IdMap<Suit, HandBelote> _cartesMaitresses) {
        if(strictCouleursMaitresses.size()== couleursNonAtouts.size()) {
            return jeuDefausseMaitreJouable();
        }
        /*Il peut exister une couleur pour se defausser non strictement maitresse*/
        IdList<Suit> couleursBis_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses, couleursNonVidesJouable);
        if(!couleursBis_.isEmpty()) {
            return jeuPetiteDefausseMaitre(couleursBis_);
        }
        return discardPartner();
    }

    CardBelote defausseAtoutSurPartenaireCouleursEgales(
            IdMap<Suit, HandBelote> _cartesMaitresses) {
        if(strictCouleursMaitresses.size()==GameBeloteCommon.couleurs().size() - 1) {
            return jeuDefausseMaitreJouable();
        }
        /*Il peut exister une couleur pour se defausser non strictement maitresse*/
        IdList<Suit> couleurs_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses, couleursNonVidesJouable);
        if(!couleurs_.isEmpty()) {
            return jeuPetiteDefausseMaitre(couleurs_);
        }
        if(!couleursAvecPointsJouable.isEmpty()) {
            return sauverFiguresDefausse(couleursAvecPointsJouable);
        }
        return jouerPetiteCarteDefausse(couleursNonVidesJouable);

    }

    CardBelote discardFoe() {
        if(!couleursSansPointJouable.isEmpty()) {
            return jouerPetiteCarteDefausse(couleursSansPointJouable);
        }
        return jeuPetiteCarteCouleurFigure();
    }

    CardBelote defausseCouleurDemandeeSurPartenaireCouleurDominante(
            IdMap<Suit, HandBelote> _cartesMaitresses) {
        if(strictCouleursMaitresses.containsAllObj(couleursAutreQueDemandeeEtAtout)) {
            return jeuDefausseMaitreJouable();
        }
        /*Il peut exister une couleur non strictement maitresse pour se defausser*/
        IdList<Suit> couleursBis_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses, couleursNonVidesJouable);
        if(!couleursBis_.isEmpty()) {
            return jeuPetiteDefausseMaitre(couleursBis_);
        }
        return discardPartner();
    }

    CardBelote defausseCouleurDemandeeSurPartenaireCouleursEgales(
            IdMap<Suit, HandBelote> _cartesMaitresses) {
        if(strictCouleursMaitresses.size()==GameBeloteCommon.couleurs().size() - 1) {
            return jeuDefausseMaitreJouable();
        }
        /*Il peut exister une couleur non strictement maitresse pour se defausser*/
        IdList<Suit> couleursBis_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses, couleursNonVidesJouable);
        if(!couleursBis_.isEmpty()) {
            return jeuPetiteDefausseMaitre(couleursBis_);
        }
        return discardPartner();
    }
    CardBelote jeuDefausseMaitreJouable() {
        if(!couleursFiguresJouable.isEmpty()) {
            return jeuGrandeCarteDefausseMaitre(couleursFiguresJouable);
        }
        return jeuPetiteDefausseMaitre(couleursNonVidesJouable);
    }

    CardBelote discardPartner() {
        if(!couleursAvecPointsJouable.isEmpty()) {
            return sauverFiguresDefausse(couleursAvecPointsJouable);
        }
        return jouerPetiteCarteDefausse(couleursNonVidesJouable);
    }
    private CardBelote sauverFiguresDefausse(
            IdList<Suit> _couleursFigures) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursAvecLePlusGrandNbPoints(repartitionJouables, bid, _couleursFigures);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(repartitionJouables, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(repartitionCartesJouees, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLaPlusGrandeFigure(repartitionJouables, bid, couleurs_);
        return mainCouleurDemandeeJouable(couleurs_.first()).premiereCarte();
    }
    private CardBelote jeuPetiteCarteCouleurFigure() {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursAvecLaPlusPetiteCarteBasse(repartitionJouables, bid, couleursNonVidesJouable);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(repartitionJouables, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(repartitionJouables, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(repartitionCartesJouees, couleurs_);
        return mainCouleurDemandeeJouable(couleurs_.first()).derniereCarte();
    }
    private CardBelote jeuGrandeCarteDefausseMaitre(
            IdList<Suit> _couleursFigures) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(repartitionJouables, _couleursFigures);
        couleurs_ = GameBeloteCommon.couleursLesPlusHautes(repartitionJouables, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusGrandNbPoints(repartitionJouables, bid, couleurs_);
        return mainCouleurDemandeeJouable(couleurs_.first()).premiereCarte();
    }
    private CardBelote jouerPetiteCarteDefausse(
            IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(repartitionJouables, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(repartitionCartesJouees, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(repartitionJouables, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(repartitionJouables, bid, couleurs_);
        return mainCouleurDemandeeJouable(couleurs_.first()).derniereCarte();
    }
    private CardBelote jeuPetiteDefausseMaitre(
            IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(repartitionJouables, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(cartesMaitresses, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(repartitionJouables, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(repartitionJouables, bid, couleurs_);
        return mainCouleurDemandeeJouable(couleurs_.first()).derniereCarte();
    }

    private HandBelote mainCouleurDemandeeJouable() {
        return mainCouleurDemandeeJouable(couleurDemandee);
    }

    private HandBelote mainCouleurAtoutJouable() {
        return mainCouleurDemandeeJouable(couleurAtout);
    }

    private HandBelote mainCouleurDemandeeJouable(Suit _c) {
        return GameBeloteCommon.hand(repartitionJouables, _c);
    }

    Role getCurrentStatus() {
        return currentStatus;
    }
}
