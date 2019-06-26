package cards.belote;

import cards.belote.comparators.DeclareHandBeloteComparator;
import cards.belote.enumerations.DeclaresBelote;
import code.util.CustList;
import code.util.EnumList;
import code.util.EqList;
import code.util.*;

final class GameBeloteDeclaring {
    private GameBeloteTrickInfo doneTrickInfo;
    private GameBeloteTeamsRelation teamsRelation;
    private HandBelote curHand;
    private EqList<DeclareHandBelote> declares;
    GameBeloteDeclaring(GameBeloteTrickInfo _doneTrickInfo,GameBeloteTeamsRelation _teamsRelation,
                        HandBelote _curHand,EqList<DeclareHandBelote> _declares) {
        doneTrickInfo = _doneTrickInfo;
        teamsRelation = _teamsRelation;
        curHand = _curHand;
        declares = _declares;
    }
    DeclareHandBelote strategieAnnonces() {
        byte next_ = doneTrickInfo.getProgressingTrick().getNextPlayer(teamsRelation.getNombreDeJoueurs());
        EnumList<DeclaresBelote> annoncesAutorisees_ = new EnumList<DeclaresBelote>();
        for(DeclaresBelote a: teamsRelation.getRules().getAnnoncesAutorisees().getKeys()) {
            if(!teamsRelation.getRules().getAnnoncesAutorisees().getVal(a)) {
                continue;
            }
            annoncesAutorisees_.add(a);
        }
        DeclareHandBelote annonce_ = curHand.annonce(annoncesAutorisees_, doneTrickInfo.getBid());
        annonce_.setJoueur(next_);
        return annonce_;
    }
    void annulerAnnonces() {
        EqList<DeclareHandBelote> annoncesLoc_ = new EqList<DeclareHandBelote>(declares);
        DeclareHandBeloteComparator comparateur_ =
                new DeclareHandBeloteComparator(doneTrickInfo.getBid());
        Bytes takerTeam_ = teamsRelation.partenaires(teamsRelation.getTaker());
        takerTeam_.add(teamsRelation.getTaker());
        EqList<DeclareHandBelote> declarationsTakerTeam_ = filterSort(annoncesLoc_, comparateur_, takerTeam_);
        Bytes takerFoesTeam_ = teamsRelation.adversaires(teamsRelation.getTaker());
        EqList<DeclareHandBelote> declarationsTakerFoesTeam_ = filterSort(annoncesLoc_, comparateur_, takerFoesTeam_);
        if (!declarationsTakerTeam_.isEmpty()) {
            if (declarationsTakerFoesTeam_.isEmpty()) {
                for (byte p: takerFoesTeam_) {
                    declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
                }
                return;
            }
            boolean equals_ = true;
            int min_ = Math.min(takerFoesTeam_.size(), takerTeam_.size());
            for (int i = CustList.FIRST_INDEX; i<min_; i++) {
                int res_ = comparateur_.compare(declarationsTakerTeam_.get(i), declarationsTakerFoesTeam_.get(i));
                if (res_ < 0) {
                    for (byte p: takerFoesTeam_) {
                        declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
                    }
                    equals_ = false;
                    break;
                }
                if (res_ > 0) {
                    for (byte p: takerTeam_) {
                        declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
                    }
                    equals_ = false;
                    break;
                }
            }
            if (!equals_) {
                return;
            }
            if (takerFoesTeam_.size() > takerTeam_.size()) {
                for (byte p: takerTeam_) {
                    declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
                }
            } else if (takerFoesTeam_.size() < takerTeam_.size()) {
                for (byte p: takerFoesTeam_) {
                    declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
                }
            } else {
                for (byte p: takerTeam_) {
                    declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
                }
                for (byte p: takerFoesTeam_) {
                    declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
                }
            }
            return;
        }
        if (!declarationsTakerFoesTeam_.isEmpty()) {
            for (byte p: takerTeam_) {
                declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
            }
        }
        //annuler les annonces de l'equipe les plus faibles a la fin du premier tour
    }

    private static EqList<DeclareHandBelote> filterSort(EqList<DeclareHandBelote> _annoncesLoc, DeclareHandBeloteComparator _comparateur, Bytes _team) {
        EqList<DeclareHandBelote> declarationsTeam_ = new EqList<DeclareHandBelote>();
        for (byte p: _team) {
            DeclareHandBelote annonceMainBelote_ = _annoncesLoc.get(p);
            if (annonceMainBelote_.getMain().estVide()) {
                continue;
            }
            declarationsTeam_.add(new DeclareHandBelote(annonceMainBelote_));
        }
        declarationsTeam_.sortElts(_comparateur);
        return declarationsTeam_;
    }
}
