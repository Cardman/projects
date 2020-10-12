package cards.belote.beans;
import cards.belote.RulesBelote;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DeclaresBelote;
import code.util.StringList;

final class RulesBeloteBean extends BeloteBean {

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
        RulesBelote rules_ = (RulesBelote) getDataBase();
        dealAll = rules_.dealAll();
        String lg_ = getLanguage();
        cartesBattues=toString(rules_.getCartesBattues(), rules_.getGeneral());
        annoncesAutorisees=new StringList();
        for (DeclaresBelote m: rules_.getListeAnnoncesAutorisees()) {
            annoncesAutorisees.add(toString(m, rules_.getSpecific()));
        }
        sousCoupeAdv = rules_.getSousCoupeAdv();
        gestionCoupePartenaire=toString(rules_.getGestionCoupePartenaire(), rules_.getSpecific());
        encheresAutorisees=new StringList();
        for (BidBelote m: rules_.getListeEncheresAutorisees()) {
            encheresAutorisees.add(toString(m, rules_.getSpecific()));
        }
        repartition = toString(rules_.getRepartition(), rules_.getSpecific());
        comptePointsClassique = rules_.getComptePointsClassique();
    }

    String getCartesBattues() {
        return cartesBattues;
    }

    boolean isDealAll() {
        return dealAll;
    }

    StringList getAnnoncesAutorisees() {
        return annoncesAutorisees;
    }

    boolean isSousCoupeAdv() {
        return sousCoupeAdv;
    }

    String getGestionCoupePartenaire() {
        return gestionCoupePartenaire;
    }

    StringList getEncheresAutorisees() {
        return encheresAutorisees;
    }

    String getRepartition() {
        return repartition;
    }

    boolean isComptePointsClassique() {
        return comptePointsClassique;
    }
}
