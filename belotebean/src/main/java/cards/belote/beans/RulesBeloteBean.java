package cards.belote.beans;
import cards.belote.RulesBelote;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DeclaresBelote;
import code.util.EnumList;
import code.util.consts.Constants;

final class RulesBeloteBean extends BeloteBean {

    private String cartesBattues;

    private boolean dealAll;

    private EnumList<DeclaresBelote> annoncesAutorisees = new EnumList<DeclaresBelote>();

    private boolean sousCoupeAdv;

    private String gestionCoupePartenaire;

    private EnumList<BidBelote> encheresAutorisees = new EnumList<BidBelote>();

    private String repartition;

    private boolean comptePointsClassique=true;

    @Override
    public void beforeDisplaying() {
        RulesBelote rules_ = (RulesBelote) getDataBase();
        dealAll = rules_.dealAll();
        cartesBattues=rules_.getCartesBattues().toString(Constants.getLanguage());
//        Map<DeclaresBelote, Boolean> allowedAnnouncing_ = new Map<>(rules_.getAnnoncesAutorisees());
//        annoncesAutorisees = allowedAnnouncing_.getKeys(true);
        annoncesAutorisees = rules_.getListeAnnoncesAutorisees();
        sousCoupeAdv = rules_.getSousCoupeAdv();
        gestionCoupePartenaire=rules_.getGestionCoupePartenaire().toString(Constants.getLanguage());
//        Map<BidBelote, Boolean> allowedBids_ = new Map<>(rules_.getEncheresAutorisees());
//        encheresAutorisees = allowedBids_.getKeys(true);
        encheresAutorisees = rules_.getListeEncheresAutorisees();
        repartition = rules_.getRepartition().toString(Constants.getLanguage());
        comptePointsClassique = rules_.getComptePointsClassique();
    }

    String getCartesBattues() {
        return cartesBattues;
    }

    boolean isDealAll() {
        return dealAll;
    }

    EnumList<DeclaresBelote> getAnnoncesAutorisees() {
        return annoncesAutorisees;
    }

    boolean isSousCoupeAdv() {
        return sousCoupeAdv;
    }

    String getGestionCoupePartenaire() {
        return gestionCoupePartenaire;
    }

    EnumList<BidBelote> getEncheresAutorisees() {
        return encheresAutorisees;
    }

    String getRepartition() {
        return repartition;
    }

    boolean isComptePointsClassique() {
        return comptePointsClassique;
    }
}
