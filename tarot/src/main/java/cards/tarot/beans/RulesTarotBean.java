package cards.tarot.beans;
import code.bean.Accessible;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.TreeMap;
import code.util.consts.Constants;
import cards.tarot.RulesTarot;
import cards.tarot.comparators.AllowedHandfulDefaultComparator;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;

public final class RulesTarotBean extends TarotBean {

    @Accessible
    private String cartesBattues;
    @Accessible
    private EnumList<Miseres> miseres=new EnumList<Miseres>();
    @Accessible
    private EnumList<BidTarot> contrats;
    @Accessible
    private String mode;
    @Accessible
    private String repartition;
    @Accessible
    private TreeMap<Handfuls,Integer> poigneesAutorisees;
    @Accessible
    private String finPartieTarot;
    @Accessible
    private boolean discardAfterCall = true;

    @Override
    public void beforeDisplaying() {
        RulesTarot rules_ = (RulesTarot) getDataBase();
        cartesBattues=rules_.getCartesBattues().toString(Constants.getLanguage());
        miseres = rules_.getMiseres();
//        Map<BidTarot, Boolean> allowedBids_ = new Map<>(rules_.getContrats());
        contrats = rules_.getContratsAutorises();
        mode = rules_.getMode().toString(Constants.getLanguage());
        repartition = rules_.getRepartition().toString(Constants.getLanguage());
        int nbCardsPerPlayer_ = rules_.getRepartition().getNombreCartesParJoueur();
        TreeMap<Handfuls,Integer> tr_;
        tr_ = new TreeMap<Handfuls,Integer>(new AllowedHandfulDefaultComparator(nbCardsPerPlayer_));
        for (EntryCust<Handfuls,Integer> e: rules_.getPoigneesAutorisees().entryList()) {
            tr_.put(e.getKey(), e.getValue());
        }
        poigneesAutorisees = tr_;
        finPartieTarot = rules_.getFinPartieTarot().toString(Constants.getLanguage());
        discardAfterCall = rules_.getDiscardAfterCall();
    }

//    @Accessible
//    private List<Handfuls> handfuls() {
//        List<Handfuls> list_ = new List<>(poigneesAutorisees.getKeys());
//        RulesTarot rules_ = (RulesTarot) getDataBase();
//        int nbCardsPerPlayer_ = rules_.getRepartition().getNombreCartesParJoueur();
//        list_.sortElts(new AllowedHandfulDefaultComparator(nbCardsPerPlayer_));
//        return list_;
//    }
}
