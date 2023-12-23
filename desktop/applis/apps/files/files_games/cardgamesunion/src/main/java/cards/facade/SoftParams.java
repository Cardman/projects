package cards.facade;
import cards.facade.enumerations.GameEnum;
import cards.facade.sml.DocumentWriterCardsUnionUtil;
import code.stream.StreamTextFile;
import code.stream.core.TechStreams;
import code.util.IdList;


public final class SoftParams {

    private static final int DELTA = 500;

    private IdList<GameEnum> launching = new IdList<GameEnum>();
    private boolean saveHomeFolder = true;
    private int delayWaitingBids=DELTA;
    private int delayWaitingCards=DELTA;
    private int delayWaitingTricks=DELTA;
    private boolean waitTrickClick=true;
    private boolean playCardClick=true;
//    private String locale;
    public SoftParams(){
    }
    public SoftParams(SoftParams _parametres) {
        launching = _parametres.launching;
        delayWaitingBids = _parametres.delayWaitingBids;
        delayWaitingCards = _parametres.delayWaitingCards;
        delayWaitingTricks = _parametres.delayWaitingTricks;
        waitTrickClick = _parametres.waitTrickClick;
        playCardClick = _parametres.playCardClick;
        saveHomeFolder = _parametres.saveHomeFolder;
    }
    public void setDelays() {
        if (delayWaitingBids < 0) {
            delayWaitingBids=DELTA;
        }
        if (delayWaitingCards < 0) {
            delayWaitingCards=DELTA;
        }
        if (delayWaitingTricks < 0) {
            delayWaitingTricks=DELTA;
        }
    }
    public IdList<GameEnum> getLancement() {
        return getLaunching();
    }
    public void setLancement(IdList<GameEnum> _lancement) {
        setLaunching(_lancement);
    }
    public int getDelaiAttenteContrats() {
        return getDelayWaitingBids();
    }
    public void setDelaiAttenteContrats(int _delaiAttenteContrats) {
        setDelayWaitingBids(_delaiAttenteContrats);
    }
    public int getDelaiAttenteCartes() {
        return getDelayWaitingCards();
    }
    public void setDelaiAttenteCartes(int _delaiAttenteCartes) {
        setDelayWaitingCards(_delaiAttenteCartes);
    }
    public int getDelaiAttentePlis() {
        return getDelayWaitingTricks();
    }
    public void setDelaiAttentePlis(int _delaiAttentePlis) {
        setDelayWaitingTricks(_delaiAttentePlis);
    }
    public boolean getAttentePlisClic() {
        return isWaitTrickClick();
    }
    public void setAttentePlisClic(boolean _attentePlisClic) {
        setWaitTrickClick(_attentePlisClic);
    }
    public boolean getJeuCarteClic() {
        return isPlayCardClick();
    }
    public void setJeuCarteClic(boolean _jeuCarteClic) {
        setPlayCardClick(_jeuCarteClic);
    }
    public boolean isSaveHomeFolder() {
        return saveHomeFolder;
    }
    public void setSaveHomeFolder(boolean _saveHomeFolder) {
        saveHomeFolder = _saveHomeFolder;
    }
    //    public String getLocale() {
//        return locale;
//    }
//    public void setLocale(String _locale) {
//        locale = _locale;
//    }
    public void sauvegarder(String _fichier, TechStreams _tech){
        StreamTextFile.saveTextFile(_fichier, DocumentWriterCardsUnionUtil.setSoftParams(this),_tech);
    }
    public IdList<GameEnum> getLaunching() {
        return launching;
    }
    public void setLaunching(IdList<GameEnum> _launching) {
        launching = _launching;
    }
    public int getDelayWaitingBids() {
        return delayWaitingBids;
    }
    public void setDelayWaitingBids(int _delayWaitingBids) {
        delayWaitingBids = _delayWaitingBids;
    }
    public int getDelayWaitingCards() {
        return delayWaitingCards;
    }
    public void setDelayWaitingCards(int _delayWaitingCards) {
        delayWaitingCards = _delayWaitingCards;
    }
    public int getDelayWaitingTricks() {
        return delayWaitingTricks;
    }
    public void setDelayWaitingTricks(int _delayWaitingTricks) {
        delayWaitingTricks = _delayWaitingTricks;
    }
    public boolean isWaitTrickClick() {
        return waitTrickClick;
    }
    public void setWaitTrickClick(boolean _waitTrickClick) {
        waitTrickClick = _waitTrickClick;
    }
    public boolean isPlayCardClick() {
        return playCardClick;
    }
    public void setPlayCardClick(boolean _playCardClick) {
        playCardClick = _playCardClick;
    }
}
