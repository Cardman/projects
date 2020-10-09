package cards.belote.beans;
import cards.belote.BidBeloteSuit;
import cards.belote.ResultsBelote;
import cards.belote.enumerations.BonusBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import code.util.CustList;
import code.util.core.IndexConstants;

final class DetailsResultsBeloteBean extends BeloteBean {

    private CustList<SumDeclaringPlayer> declaring;

    @Override
    public void beforeDisplaying() {
        ResultsBelote res_ = getResults();
        setGame(res_.getGame());
        setNicknames(res_.getNicknames());
        setScores(res_.getScores());
        setUser(res_.getUser());
        setLoc(res_.getLoc());
        BidBeloteSuit bid_ = getGame().getContrat();
        declaring = new CustList<SumDeclaringPlayer>();
        if (bid_.jouerDonne()) {
            byte nombreJoueurs_ = getGame().getNombreDeJoueurs();
            for (byte p = IndexConstants.FIRST_INDEX; p<nombreJoueurs_; p++){
                SumDeclaringPlayer sumDeclaring_ = new SumDeclaringPlayer();
                sumDeclaring_.setNickname(getNicknames().get(p));
                sumDeclaring_.setStatut(toString(getGame().getTeamsRelation().statutDe(p),getLoc()));
                int sum_ = 0;
                CustList<DeclaringPlayerValue> listDeclaring_ = new CustList<DeclaringPlayerValue>();
                if (getGame().getAnnonce(p).getDeclare() != DeclaresBelote.UNDEFINED) {
                    DeclaringPlayerValue decl_ = new DeclaringPlayerValue();
                    decl_.setDeclaring(toString(getGame().getAnnonce(p).getDeclare(),getLoc()));
                    decl_.setValue(getGame().getAnnonce(p).getDeclare().getPoints());
                    sum_ += decl_.getValue();
                    listDeclaring_.add(decl_);
                }
                if (!getGame().getAnnoncesBeloteRebelote(p).estVide()) {
                    DeclaringPlayerValue decl_ = new DeclaringPlayerValue();
                    decl_.setDeclaring(toString(DeclaresBeloteRebelote.BELOTE_REBELOTE,getLoc()));
                    decl_.setValue(DeclaresBeloteRebelote.BELOTE_REBELOTE.getPoints());
                    sum_ += decl_.getValue();
                    listDeclaring_.add(decl_);
                }
                if (getGame().getDixDeDer(p)) {
                    DeclaringPlayerValue decl_ = new DeclaringPlayerValue();
                    decl_.setDeclaring(toString(BonusBelote.LAST_TRICK,getLoc()));
                    decl_.setValue(BonusBelote.LAST_TRICK.getPoints());
                    sum_ += decl_.getValue();
                    listDeclaring_.add(decl_);
                }
                sumDeclaring_.setDeclaring(listDeclaring_);
                sumDeclaring_.setSum(sum_);
                declaring.add(sumDeclaring_);
            }
        }
    }
    CustList<SumDeclaringPlayer> getDeclaring() {
        return declaring;
    }
}
