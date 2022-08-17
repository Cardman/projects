package cards.belote.beans;
import cards.belote.RulesBelote;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DeclaresBelote;
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

    @Override
    public void beforeDisplaying() {
        RulesBelote rules_ = db();
        dealAll = rules_.dealAll();
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
        repartition = toString(rules_.getRepartition(), rules_.getCommon().getSpecific());
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
