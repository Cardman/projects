package cards.tarot.beans;
import cards.tarot.RulesTarot;
import cards.tarot.comparators.AllowedHandfulDefaultComparator;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.TreeMap;

public final class RulesTarotBean extends TarotBean {

    private String cartesBattues;

    private EnumList<Miseres> miseres=new EnumList<Miseres>();

    private EnumList<BidTarot> contrats;

    private String mode;

    private String repartition;

    private TreeMap<Handfuls,Integer> poigneesAutorisees;

    private String finPartieTarot;

    private boolean discardAfterCall = true;

    @Override
    public void beforeDisplaying() {
        RulesTarot rules_ = (RulesTarot) getDataBase();
        cartesBattues=rules_.getCartesBattues().toString(getLanguage());
        miseres = rules_.getMiseres();
//        Map<BidTarot, Boolean> allowedBids_ = new Map<>(rules_.getContrats());
        contrats = rules_.getContratsAutorises();
        mode = rules_.getMode().toString(getLanguage());
        repartition = rules_.getRepartition().toString(getLanguage());
        int nbCardsPerPlayer_ = rules_.getRepartition().getNombreCartesParJoueur();
        TreeMap<Handfuls,Integer> tr_;
        tr_ = new TreeMap<Handfuls,Integer>(new AllowedHandfulDefaultComparator(nbCardsPerPlayer_));
        for (EntryCust<Handfuls,Integer> e: rules_.getPoigneesAutorisees().entryList()) {
            tr_.put(e.getKey(), e.getValue());
        }
        poigneesAutorisees = tr_;
        finPartieTarot = rules_.getFinPartieTarot().toString(getLanguage());
        discardAfterCall = rules_.getDiscardAfterCall();
    }

    String getCartesBattues() {
        return cartesBattues;
    }

    EnumList<Miseres> getMiseres() {
        return miseres;
    }

    EnumList<BidTarot> getContrats() {
        return contrats;
    }

    String getMode() {
        return mode;
    }

    String getRepartition() {
        return repartition;
    }

    TreeMap<Handfuls, Integer> getPoigneesAutorisees() {
        return poigneesAutorisees;
    }

    String getFinPartieTarot() {
        return finPartieTarot;
    }

    boolean isDiscardAfterCall() {
        return discardAfterCall;
    }

//
//    private List<Handfuls> handfuls() {
//        List<Handfuls> list_ = new List<>(poigneesAutorisees.getKeys());
//        RulesTarot rules_ = (RulesTarot) getDataBase();
//        int nbCardsPerPlayer_ = rules_.getRepartition().getNombreCartesParJoueur();
//        list_.sortElts(new AllowedHandfulDefaultComparator(nbCardsPerPlayer_));
//        return list_;
//    }
}
