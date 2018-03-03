package cards.facade;
import code.stream.StreamTextFile;
import code.util.EnumList;
import code.util.annot.RwXml;
import cards.facade.enumerations.GameEnum;

@RwXml
public final class SoftParams {

    private static final int DELTA = 500;

    private EnumList<GameEnum> launching = new EnumList<GameEnum>();
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
    public EnumList<GameEnum> getLancement() {
        return launching;
    }
    public void setLancement(EnumList<GameEnum> _lancement) {
        launching = _lancement;
    }
    public int getDelaiAttenteContrats() {
        return delayWaitingBids;
    }
    public void setDelaiAttenteContrats(int _delaiAttenteContrats) {
        delayWaitingBids = _delaiAttenteContrats;
    }
    public int getDelaiAttenteCartes() {
        return delayWaitingCards;
    }
    public void setDelaiAttenteCartes(int _delaiAttenteCartes) {
        delayWaitingCards = _delaiAttenteCartes;
    }
    public int getDelaiAttentePlis() {
        return delayWaitingTricks;
    }
    public void setDelaiAttentePlis(int _delaiAttentePlis) {
        delayWaitingTricks = _delaiAttentePlis;
    }
    public boolean getAttentePlisClic() {
        return waitTrickClick;
    }
    public void setAttentePlisClic(boolean _attentePlisClic) {
        waitTrickClick = _attentePlisClic;
    }
    public boolean getJeuCarteClic() {
        return playCardClick;
    }
    public void setJeuCarteClic(boolean _jeuCarteClic) {
        playCardClick = _jeuCarteClic;
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
    public void sauvegarder(String _fichier){
        StreamTextFile.saveObject(_fichier, this);
    }
    public EnumList<GameEnum> getLaunching() {
        return launching;
    }
    public void setLaunching(EnumList<GameEnum> _launching) {
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
