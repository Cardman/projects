package cards.tarot.beans;
import cards.tarot.RulesTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

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
        RulesTarot rules_ = db();
        cartesBattues=toString(rules_.getCommon().getMixedCards(), rules_.getCommon().getGeneral());
        miseres=new StringList();
        for (Miseres m: rules_.getMiseres()) {
            miseres.add(toString(m, rules_.getCommon().getSpecific()));
        }
        contrats=new StringList();
        for (BidTarot m: rules_.getContratsAutorises()) {
            contrats.add(toString(m, rules_.getCommon().getSpecific()));
        }
        mode = toString(rules_.getMode(), rules_.getCommon().getSpecific());
        repartition = toString(rules_.getRepartition(), rules_.getCommon().getSpecific());
        StringMap<Integer> str_ = new StringMap<Integer>();
        for (EntryCust<Handfuls,Integer> e: rules_.getPoigneesAutorisees().entryList()) {
            Handfuls h_ = e.getKey();
            str_.addEntry(toString(h_, rules_.getCommon().getSpecific()), e.getValue());
        }
        poigneesAutorisees = str_;
        finPartieTarot = toString(rules_.getEndDealTarot(), rules_.getCommon().getSpecific());
        discardAfterCall = rules_.getDiscardAfterCall();
    }

    public String getCartesBattues() {
        return cartesBattues;
    }

    public StringList getMiseres() {
        return miseres;
    }

    public StringList getContrats() {
        return contrats;
    }

    public String getMode() {
        return mode;
    }

    public String getRepartition() {
        return repartition;
    }

    public StringMap<Integer> getPoigneesAutorisees() {
        return poigneesAutorisees;
    }

    public String getFinPartieTarot() {
        return finPartieTarot;
    }

    public boolean isDiscardAfterCall() {
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
