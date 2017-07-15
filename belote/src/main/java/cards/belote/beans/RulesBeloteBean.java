package cards.belote.beans;
import code.bean.Accessible;
import code.util.EnumList;
import code.util.consts.Constants;
import cards.belote.RulesBelote;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DeclaresBelote;

public final class RulesBeloteBean extends BeloteBean {

    @Accessible
    private String cartesBattues;

    @Accessible
    private boolean dealAll;

    @Accessible
    private EnumList<DeclaresBelote> annoncesAutorisees = new EnumList<DeclaresBelote>();

    @Accessible
    private boolean sousCoupeAdv;

    @Accessible
    private String gestionCoupePartenaire;

    @Accessible
    private EnumList<BidBelote> encheresAutorisees = new EnumList<BidBelote>();

    @Accessible
    private String repartition;

    @Accessible
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
}
