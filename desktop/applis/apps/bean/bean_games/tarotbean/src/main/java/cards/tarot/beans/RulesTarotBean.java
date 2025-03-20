package cards.tarot.beans;
import cards.tarot.RulesTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.scripts.pages.cards.MessagesTarotPage;
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
    private boolean allowPlayCalledSuit = true;

    public void build() {
        beforeDisplaying();
        header(MessagesTarotPage.M_BEAT_CARDS);
        getBuilder().formatMessageDir(cartesBattues);
        header(MessagesTarotPage.M_DEALING_PL);
        getBuilder().formatMessageDir(repartition);
        header(MessagesTarotPage.M_MODE);
        getBuilder().formatMessageDir(mode);
        header(MessagesTarotPage.M_DISCARD);
        if (discardAfterCall) {
            getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_YES);
        } else {
            getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_NO);
        }
        header(MessagesTarotPage.M_PLAY_CALLED);
        if (allowPlayCalledSuit) {
            getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_YES);
        } else {
            getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_NO);
        }
        header(MessagesTarotPage.M_BIDS);
        for (String b: contrats) {
            getBuilder().initLine();
            getBuilder().paintMetaLabelDisk();
            getBuilder().formatMessageDir(b);
            getBuilder().feedParents();
        }
        header(MessagesTarotPage.M_DECLS);
        getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"", MessagesTarotPage.M_HANDS);
        getBuilder().initGrid();
        getBuilder().colCount(2);
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"", MessagesTarotPage.M_HAND));
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"", MessagesTarotPage.M_NB));
        for (EntryCust<String,Integer> e: poigneesAutorisees.entryList()) {
            getBuilder().formatMessageDirCts(e.getKey());
            getBuilder().formatMessageDirCts(Long.toString(e.getValue()));
        }
        getBuilder().feedParents();
        header(MessagesTarotPage.M_MIS);
        for (String b: miseres) {
            getBuilder().initLine();
            getBuilder().paintMetaLabelDisk();
            getBuilder().formatMessageDir(b);
            getBuilder().feedParents();
        }
        if (miseres.isEmpty()) {
            getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"", MessagesTarotPage.M_NOTHING);
        }
        header(MessagesTarotPage.M_ENDING);
        getBuilder().formatMessageDir(finPartieTarot);
    }
    private void header(String _key) {
        getBuilder().setHeader(1);
        getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"", _key);
        getBuilder().setHeader(0);
    }
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
        repartition = toString(rules_.getDealing(), rules_.getCommon().getSpecific());
        StringMap<Integer> str_ = new StringMap<Integer>();
        for (EntryCust<Handfuls,Integer> e: rules_.getAllowedHandfuls().entryList()) {
            Handfuls h_ = e.getKey();
            str_.addEntry(toString(h_, rules_.getCommon().getSpecific()), e.getValue());
        }
        poigneesAutorisees = str_;
        finPartieTarot = toString(rules_.getEndDealTarot(), rules_.getCommon().getSpecific());
        discardAfterCall = rules_.getDiscardAfterCall();
        allowPlayCalledSuit = rules_.isAllowPlayCalledSuit();
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

    public boolean isAllowPlayCalledSuit() {
        return allowPlayCalledSuit;
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
