package cards.tarot.beans;
import cards.tarot.RulesTarot;
import cards.tarot.comparators.AllowedHandfulDefaultComparator;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

public final class RulesTarotBean extends TarotBean {

    private String cartesBattues;

    private StringList miseres;

    private StringList contrats;

    private String mode;

    private String repartition;

    private StringMap<Integer> poigneesAutorisees;

    private String finPartieTarot;

    private boolean discardAfterCall = true;

    @Override
    public void beforeDisplaying() {
        RulesTarot rules_ = (RulesTarot) getDataBase();
        String lg_ = getLanguage();
        cartesBattues=rules_.getCartesBattues().toString(lg_);
        miseres=new StringList();
        for (Miseres m: rules_.getMiseres()) {
            miseres.add(m.toString(lg_));
        }
        contrats=new StringList();
        for (BidTarot m: rules_.getContratsAutorises()) {
            contrats.add(m.toString(lg_));
        }
        mode = rules_.getMode().toString(lg_);
        repartition = rules_.getRepartition().toString(lg_);
        int nbCardsPerPlayer_ = rules_.getRepartition().getNombreCartesParJoueur();
        TreeMap<Handfuls,Integer> tr_;
        tr_ = new TreeMap<Handfuls,Integer>(new AllowedHandfulDefaultComparator(nbCardsPerPlayer_));
        for (EntryCust<Handfuls,Integer> e: rules_.getPoigneesAutorisees().entryList()) {
            tr_.put(e.getKey(), e.getValue());
        }
        StringMap<Integer> str_ = new StringMap<Integer>();
        for (EntryCust<Handfuls,Integer> e: tr_.entryList()) {
            Handfuls h_ = e.getKey();
            str_.addEntry(h_.toString(lg_), e.getValue());
        }
        poigneesAutorisees = str_;
        finPartieTarot = rules_.getFinPartieTarot().toString(lg_);
        discardAfterCall = rules_.getDiscardAfterCall();
    }

    String getCartesBattues() {
        return cartesBattues;
    }

    StringList getMiseres() {
        return miseres;
    }

    StringList getContrats() {
        return contrats;
    }

    String getMode() {
        return mode;
    }

    String getRepartition() {
        return repartition;
    }

    StringMap<Integer> getPoigneesAutorisees() {
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
