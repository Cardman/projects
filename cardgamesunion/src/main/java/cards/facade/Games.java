package cards.facade;
import cards.belote.GameBelote;
import cards.belote.RulesBelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.president.GamePresident;
import cards.president.RulesPresident;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.tarot.GameTarot;
import cards.tarot.RulesTarot;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.Numbers;

public final class Games {

    private CustList<GameBelote> partiesBelote = new CustList<GameBelote>();

    private CustList<GameTarot> partiesTarot = new CustList<GameTarot>();

    private CustList<GamePresident> partiesPresident = new CustList<GamePresident>();

    private RulesBelote rulesBelote;

    private RulesTarot rulesTarot;

    private RulesPresident rulesPresident;

    public boolean enCoursDePartie(){
        return enCoursDePartieBelote() || enCoursDePartieTarot() || enCoursDePartiePresident();
    }
    public boolean enCoursDePartieBelote(){
        return !partiesBelote.isEmpty();
    }

    public boolean enCoursDePartieTarot(){
        return !partiesTarot.isEmpty();
    }

    public boolean enCoursDePartiePresident(){
        return !partiesPresident.isEmpty();
    }

    public GameBelote partieBelote(){
        return partiesBelote.first();
    }

    public GameTarot partieTarot(){
        return partiesTarot.first();
    }

    public GamePresident partiePresident() {
        return partiesPresident.first();
    }

    public void finirParties(){
        partiesBelote.clear();
        partiesTarot.clear();
        partiesPresident.clear();
    }
    public void jouerBelote(GameBelote _partieBelote){
        finirParties();
        partiesBelote.add(_partieBelote);
    }
    public void jouerTarot(GameTarot _partieTarot){
        finirParties();
        partiesTarot.add(_partieTarot);
    }
    public void jouerPresident(GamePresident _partiePresident){
        finirParties();
        partiesPresident.add(_partiePresident);
    }

    public boolean isSameTeam(Numbers<Byte> _players) {
        if (enCoursDePartieBelote()) {
            return partieBelote().isSameTeam(_players);
        }
        if (enCoursDePartieTarot()) {
            return partieTarot().isSameTeam(_players);
        }
        if (enCoursDePartiePresident()) {
//            return false;
            return _players.size() == CustList.ONE_ELEMENT;
        }
        return true;
    }
    public void sauvegarderPartieEnCours(String _nomFichier){
        if(enCoursDePartieBelote()){
            GameBelote game_ = partieBelote();
            StreamTextFile.saveTextFile(_nomFichier, DocumentWriterBeloteUtil.setGameBelote(game_));
        }
        if(enCoursDePartieTarot()){
            GameTarot game_ = partieTarot();
            StreamTextFile.saveTextFile(_nomFichier, DocumentWriterTarotUtil.setGameTarot(game_));
        }
        if(enCoursDePartiePresident()){
            GamePresident game_ = partiePresident();
            StreamTextFile.saveTextFile(_nomFichier, DocumentWriterPresidentUtil.setGamePresident(game_));
        }
    }
    public RulesBelote getRulesBelote() {
        return rulesBelote;
    }
    public void setRulesBelote(RulesBelote _rulesBelote) {
        rulesBelote = _rulesBelote;
    }
    public RulesTarot getRulesTarot() {
        return rulesTarot;
    }
    public void setRulesTarot(RulesTarot _rulesTarot) {
        rulesTarot = _rulesTarot;
    }
    public RulesPresident getRulesPresident() {
        return rulesPresident;
    }
    public void setRulesPresident(RulesPresident _rulesPresident) {
        rulesPresident = _rulesPresident;
    }
    public void setPartiesBelote(CustList<GameBelote> _listGameBelote) {
        partiesBelote = _listGameBelote;
    }
    public void setPartiesTarot(CustList<GameTarot> _listGameTarot) {
        partiesTarot = _listGameTarot;
    }
    public void setPartiesPresident(CustList<GamePresident> _listGamePresident) {
        partiesPresident = _listGamePresident;
    }
    public CustList<GameBelote> getPartiesBelote() {
        return partiesBelote;
    }
    public CustList<GameTarot> getPartiesTarot() {
        return partiesTarot;
    }
    public CustList<GamePresident> getPartiesPresident() {
        return partiesPresident;
    }
}
