package cards.belote;

import cards.belote.comparators.DeclareHandBeloteComparator;
import cards.belote.enumerations.DeclaresBelote;
import code.util.CustList;
import code.util.EnumList;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

final class GameBeloteDeclaring {
    private final GameBeloteTrickInfo doneTrickInfo;
    private final GameBeloteTeamsRelation teamsRelation;
    private final HandBelote curHand;
    private final CustList<DeclareHandBelote> declares;
    GameBeloteDeclaring(GameBeloteTrickInfo _doneTrickInfo,GameBeloteTeamsRelation _teamsRelation,
                        HandBelote _curHand,CustList<DeclareHandBelote> _declares) {
        doneTrickInfo = _doneTrickInfo;
        teamsRelation = _teamsRelation;
        curHand = _curHand;
        declares = _declares;
    }
    DeclareHandBelote strategieAnnonces() {
        byte next_ = doneTrickInfo.getProgressingTrick().getNextPlayer(teamsRelation.getNombreDeJoueurs());
        EnumList<DeclaresBelote> annoncesAutorisees_ = new EnumList<DeclaresBelote>();
        for(DeclaresBelote a: teamsRelation.getRules().getAllowedDeclares().getKeys()) {
            if(teamsRelation.getRules().getAllowedDeclares().getVal(a) != BoolVal.TRUE) {
                continue;
            }
            annoncesAutorisees_.add(a);
        }
        DeclareHandBelote annonce_ = curHand.annonce(annoncesAutorisees_, doneTrickInfo.getBid());
        annonce_.setPlayer(next_);
        return annonce_;
    }
    void annulerAnnonces() {
        CustList<DeclareHandBelote> annoncesLoc_ = new CustList<DeclareHandBelote>(declares);
        DeclareHandBeloteComparator comparateur_ =
                new DeclareHandBeloteComparator(doneTrickInfo.getBid());
        Bytes takerTeam_ = teamsRelation.partenaires(teamsRelation.getTaker());
        takerTeam_.add(teamsRelation.getTaker());
        CustList<DeclareHandBelote> declarationsTakerTeam_ = filterSort(annoncesLoc_, comparateur_, takerTeam_);
        Bytes takerFoesTeam_ = teamsRelation.adversaires(teamsRelation.getTaker());
        CustList<DeclareHandBelote> declarationsTakerFoesTeam_ = filterSort(annoncesLoc_, comparateur_, takerFoesTeam_);
        //annuler les annonces de l'equipe les plus faibles a la fin du premier tour
        int diff_ = cmp(comparateur_, declarationsTakerTeam_, declarationsTakerFoesTeam_);
        if (diff_ > 0) {
            cancelDeclaring(takerTeam_);
        } else if (diff_ < 0) {
            cancelDeclaring(takerFoesTeam_);
        } else {
            cancelDeclaring(takerTeam_);
            cancelDeclaring(takerFoesTeam_);
        }
    }
    private int cmp(DeclareHandBeloteComparator _comparateur,CustList<DeclareHandBelote> _declarationsTakerTeam,CustList<DeclareHandBelote> _declarationsTakerFoesTeam) {
        int min_ = Math.min(_declarationsTakerFoesTeam.size(), _declarationsTakerTeam.size());
        for (int i = IndexConstants.FIRST_INDEX; i<min_; i++) {
            int res_ = _comparateur.compare(_declarationsTakerTeam.get(i), _declarationsTakerFoesTeam.get(i));
            if (res_ != 0) {
                return res_;
            }
        }
        return _declarationsTakerFoesTeam.size() - _declarationsTakerTeam.size();
    }

    private void cancelDeclaring(Bytes _team) {
        for (byte p: _team) {
            declares.get(p).setDeclare(DeclaresBelote.UNDEFINED);
        }
    }

    private static CustList<DeclareHandBelote> filterSort(CustList<DeclareHandBelote> _annoncesLoc, DeclareHandBeloteComparator _comparateur, Bytes _team) {
        CustList<DeclareHandBelote> declarationsTeam_ = new CustList<DeclareHandBelote>();
        for (byte p: _team) {
            DeclareHandBelote annonceMainBelote_ = _annoncesLoc.get(p);
            if (annonceMainBelote_.getHand().estVide()) {
                continue;
            }
            declarationsTeam_.add(new DeclareHandBelote(annonceMainBelote_));
        }
        declarationsTeam_.sortElts(_comparateur);
        return declarationsTeam_;
    }

    CustList<DeclareHandBelote> getDeclares() {
        return declares;
    }
}
