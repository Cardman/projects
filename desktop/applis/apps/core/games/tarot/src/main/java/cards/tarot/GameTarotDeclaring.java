package cards.tarot;

import cards.consts.Suit;
import cards.tarot.enumerations.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public final class GameTarotDeclaring {
    private final GameTarotTrickInfo doneTrickInfo;
    private final GameTarotTeamsRelation teamsRelation;
    private final HandTarot curHand;
    private final CustList<IdList<Handfuls>> declaresHandfuls;

    public GameTarotDeclaring(GameTarotTrickInfo _doneTrickInfo, GameTarotTeamsRelation _teamsRelation, HandTarot _curHand, CustList<IdList<Handfuls>> _declaresHandfuls) {
        doneTrickInfo = _doneTrickInfo;
        teamsRelation = _teamsRelation;
        curHand = _curHand;
        declaresHandfuls = _declaresHandfuls;
    }

    public IdList<Handfuls> strategieAnnoncesPoignees(HandTarot _calledCards) {

        IdList<Handfuls> va_ = new IdList<Handfuls>();
        byte next_ = doneTrickInfo.getProgressingTrick().getNextPlayer(teamsRelation.getNombreDeJoueurs());
        IdMap<Suit,HandTarot> repartition_ = curHand.couleurs();
        // peuvent etre annoncees par le joueur si toutes les annonces etaient
        // autorisees
        HandTarot atouts_ = GameTarotCommonPlaying.atoutsPoignee(repartition_);
        if (teamsRelation.getTaker() == next_ && curHand.contientCartes(_calledCards) && !GameTarotBid.estUnJeuDeChelemSur(repartition_, doneTrickInfo.cartesJoueesEnCours(next_).couleurs())) {
            return va_;
        }
        CustList<Handfuls> poigneesOrdonnees_ = teamsRelation.getRules().getPoigneesOrdonnees();
        IdList<Handfuls> poigneesAutorisees_ = new IdList<Handfuls>();
        for(Handfuls p: poigneesOrdonnees_) {
            if(!teamsRelation.getRules().poigneeAutorisee(p)) {
                continue;
            }
            poigneesAutorisees_.add(p);
        }
        if(poigneesAutorisees_.isEmpty()) {
            return va_;
        }
        IdMap<Handfuls,Integer> poigneesNbAtout_ = teamsRelation.getRules().getAllowedHandfuls();
        if (atouts_.total() < poigneesNbAtout_.getVal(poigneesAutorisees_.first())) {
            return va_;
        }
        int nbHandfuls_ = poigneesOrdonnees_.size();
        for(int i = IndexConstants.SECOND_INDEX; i<nbHandfuls_; i++) {
            Handfuls p_ = poigneesOrdonnees_.get(i);
            if(atouts_.total() < poigneesNbAtout_.getVal(p_)) {
                va_.add(poigneesAutorisees_.getPrev(i));
                return va_;
            }

        }
        va_.add(poigneesAutorisees_.last());
        return va_;
    }
    IdList<Handfuls> getAnnoncesPoigneesPossibles() {
        IdMap<Suit,HandTarot> repartition_ = curHand.couleurs();
        int nombreAtoutsEx_ = GameTarotCommon.atoutsAvecExcuse(repartition_);
        IdList<Handfuls> annoncesPossibles_ = new IdList<Handfuls>();
        for (EntryCust<Handfuls,Integer> e: teamsRelation.getRules().getAllowedHandfuls().entryList()) {
            if (nombreAtoutsEx_ < e.getValue()) {
                continue;
            }
            annoncesPossibles_.add(e.getKey());
        }
        return annoncesPossibles_;

    }

    public HandTarot strategiePoignee() {
        byte next_ = doneTrickInfo.getProgressingTrick().getNextPlayer(teamsRelation.getNombreDeJoueurs());
        IdMap<Suit,HandTarot> repartition_ = curHand.couleurs();
        HandTarot atouts_ = GameTarotCommonPlaying.atoutsPoignee(repartition_);
        HandTarot poignee_ = new HandTarot();
        for(Handfuls p: getAnnoncesPoignees(next_)) {
            int max_ = teamsRelation.getRules().getAllowedHandfuls().getVal(p);
            byte trumpIndex_ = IndexConstants.FIRST_INDEX;
            if(atouts_.total() == max_) {
                while (poignee_.total() < max_) {
                    poignee_.ajouter(atouts_.carte(trumpIndex_));
                    trumpIndex_++;
                }
            } else {
                //atouts_.total() > max_ because of strategieAnnoncesPoignees
                while (poignee_.total() < max_) {
                    CardTarot card_ = atouts_.carte(trumpIndex_);
                    if (card_.getId().getCouleur() == Suit.TRUMP) {
                        poignee_.ajouter(card_);
                    }
                    trumpIndex_++;
                }
            }
        }
        return poignee_;
    }
    public IdList<Miseres> getAnnoncesMiseresPossibles() {
        IdMap<Suit,HandTarot> repartition_ = curHand.couleurs();
        int nombreAtoutsEx_ = GameTarotCommon.atoutsAvecExcuse(repartition_);
        IdList<Miseres> annoncesPossibles_ = new IdList<Miseres>();
        if (nombreAtoutsEx_ == 0) {
            annoncesPossibles_.add(Miseres.TRUMP);
        }
        if (nombreAtoutsEx_ + curHand.nombreDeFigures() == curHand
                .total()) {
            annoncesPossibles_.add(Miseres.LOW_CARDS);
        }
        if (nombreAtoutsEx_ == curHand.total()) {
            annoncesPossibles_.add(Miseres.SUIT);
        }
        if (curHand.nombreDeFigures() == 0) {
            annoncesPossibles_.add(Miseres.CHARACTER);
        }
        if (curHand.nombreDeFigures() + curHand.nombreDeBouts() == 0) {
            annoncesPossibles_.add(Miseres.POINT);
        }
        return annoncesPossibles_;
    }

    public IdList<Miseres> strategieAnnoncesMiseres() {

        IdList<Miseres> vaa_ = teamsRelation.getRules().getMiseres();
        IdList<Miseres> vap_ = getAnnoncesMiseresPossibles();
        IdList<Miseres> vainter_ = new IdList<Miseres>();
        // Intersection entre
        // les annonces
        // auorisees par les
        // regles du jeu et
        // celles
        // peuvent etre annoncees par le joueur si toutes les annonces etaient
        // autorisees
        for (Miseres m: vaa_) {
            if (vap_.containsObj(m)) {
                vainter_.add(m);
            }
        }
        return vainter_;
    }
    IdList<Handfuls> getAnnoncesPoignees(byte _numero) {
        return declaresHandfuls.get(_numero);
    }
}
