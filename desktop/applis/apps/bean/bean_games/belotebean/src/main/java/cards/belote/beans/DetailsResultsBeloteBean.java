package cards.belote.beans;
import cards.belote.BidBeloteSuit;
import cards.belote.ResultsBelote;
import cards.belote.enumerations.BonusBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class DetailsResultsBeloteBean extends BeloteBean {

    private CustList<BeloteSumDeclaringPlayer> declaring;

    @Override
    public void beforeDisplaying() {
        ResultsBelote res_ = getResults();
        setGame(res_.getGame());
        setNicknames(res_.getRes().getNicknames());
        setHistory(res_.getRes().getHistory());
        setUser(res_.getRes().getUser());
        setLoc(res_.getRes().getLoc());
        BidBeloteSuit bid_ = getGame().getBid();
        declaring = new CustList<BeloteSumDeclaringPlayer>();
        if (bid_.jouerDonne()) {
            byte nombreJoueurs_ = getGame().getNombreDeJoueurs();
            for (byte p = IndexConstants.FIRST_INDEX; p<nombreJoueurs_; p++){
                BeloteSumDeclaringPlayer sumDeclaring_ = new BeloteSumDeclaringPlayer();
                sumDeclaring_.setNickname(getNicknames().get(p));
                sumDeclaring_.setStatut(toString(getGame().getTeamsRelation().statutDe(p), res_.getRes().getGeneral()));
                int sum_ = 0;
                CustList<DeclaringPlayerValue> listDeclaring_ = new CustList<DeclaringPlayerValue>();
                if (getGame().getAnnonce(p).getDeclare() != DeclaresBelote.UNDEFINED) {
                    DeclaringPlayerValue decl_ = new DeclaringPlayerValue();
                    decl_.setDeclaring(toString(getGame().getAnnonce(p).getDeclare(), res_.getRes().getSpecific()));
                    decl_.setValue(getGame().getAnnonce(p).getDeclare().getPoints());
                    sum_ += decl_.getValue();
                    listDeclaring_.add(decl_);
                }
                if (!getGame().getAnnoncesBeloteRebelote(p).estVide()) {
                    DeclaringPlayerValue decl_ = new DeclaringPlayerValue();
                    decl_.setDeclaring(toString(DeclaresBeloteRebelote.BELOTE_REBELOTE, res_.getRes().getSpecific()));
                    decl_.setValue(DeclaresBeloteRebelote.BELOTE_REBELOTE.getPoints());
                    sum_ += decl_.getValue();
                    listDeclaring_.add(decl_);
                }
                if (getGame().getDixDeDer(p)) {
                    DeclaringPlayerValue decl_ = new DeclaringPlayerValue();
                    decl_.setDeclaring(toString(BonusBelote.LAST_TRICK, res_.getRes().getSpecific()));
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
    public CustList<BeloteSumDeclaringPlayer> getDeclaring() {
        return declaring;
    }
}
