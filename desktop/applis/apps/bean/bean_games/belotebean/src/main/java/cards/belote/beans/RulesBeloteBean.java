package cards.belote.beans;
import cards.belote.RulesBelote;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DeclaresBelote;
import code.scripts.pages.cards.MessagesBelotePage;
import code.util.StringList;

public final class RulesBeloteBean extends BeloteBean {

    private String cartesBattues;

    private boolean dealAll;

    private StringList annoncesAutorisees;

    private boolean sousCoupeAdv;

    private String gestionCoupePartenaire;

    private StringList encheresAutorisees;

    private String repartition;

    private boolean comptePointsClassique=true;

    public void build() {
        beforeDisplaying();
        header(MessagesBelotePage.M_BEAT_CARDS);
        getBuilder().formatMessageDir(cartesBattues);
        header(MessagesBelotePage.M_DEAL);
        if (dealAll) {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN,"",MessagesBelotePage.M_YES);
        } else {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN,"",MessagesBelotePage.M_NO);
        }
        header(MessagesBelotePage.M_DECL);
        for (String b: encheresAutorisees) {
            getBuilder().initLine();
            getBuilder().paintMetaLabelDisk();
            getBuilder().formatMessageDir(b);
            getBuilder().feedParents();
        }
        header(MessagesBelotePage.M_UNDER);
        if (sousCoupeAdv) {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN,"",MessagesBelotePage.M_YES);
        } else {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN,"",MessagesBelotePage.M_NO);
        }
        header(MessagesBelotePage.M_BIDS);
        for (String b: annoncesAutorisees) {
            getBuilder().initLine();
            getBuilder().paintMetaLabelDisk();
            getBuilder().formatMessageDir(b);
            getBuilder().feedParents();
        }
        header(MessagesBelotePage.M_PARTNER);
        getBuilder().formatMessageDir(gestionCoupePartenaire);
        header(MessagesBelotePage.M_DEALING);
        getBuilder().formatMessageDir(repartition);
        header(MessagesBelotePage.M_END);
        if (comptePointsClassique) {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN,"",MessagesBelotePage.M_END_DEF);
        } else {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN,"",MessagesBelotePage.M_END_ELSE);
        }
    }

    private void header(String _key) {
        getBuilder().setHeader(1);
        getBuilder().formatMessage(MessagesBelotePage.APP_BEAN,"", _key);
        getBuilder().setHeader(0);
    }
    @Override
    public void beforeDisplaying() {
        RulesBelote rules_ = db();
        dealAll = rules_.withBidPointsForAllPlayers();
        cartesBattues=toString(rules_.getCommon().getMixedCards(), rules_.getCommon().getGeneral());
        annoncesAutorisees=new StringList();
        for (DeclaresBelote m: rules_.getListeAnnoncesAutorisees()) {
            annoncesAutorisees.add(toString(m, rules_.getCommon().getSpecific()));
        }
        sousCoupeAdv = rules_.getSousCoupeAdv();
        gestionCoupePartenaire=toString(rules_.getGestionCoupePartenaire(), rules_.getCommon().getSpecific());
        encheresAutorisees=new StringList();
        for (BidBelote m: rules_.getListeEncheresAutorisees()) {
            encheresAutorisees.add(toString(m, rules_.getCommon().getSpecific()));
        }
        repartition = toString(rules_.getDealing(), rules_.getCommon().getSpecific());
        comptePointsClassique = rules_.getComptePointsClassique();
    }

    public String getCartesBattues() {
        return cartesBattues;
    }

    public boolean isDealAll() {
        return dealAll;
    }

    public StringList getAnnoncesAutorisees() {
        return annoncesAutorisees;
    }

    public boolean isSousCoupeAdv() {
        return sousCoupeAdv;
    }

    public String getGestionCoupePartenaire() {
        return gestionCoupePartenaire;
    }

    public StringList getEncheresAutorisees() {
        return encheresAutorisees;
    }

    public String getRepartition() {
        return repartition;
    }

    public boolean isComptePointsClassique() {
        return comptePointsClassique;
    }
}
