package cards.facade;
import cards.belote.CheckerGameBeloteWithRules;
import cards.belote.GameBelote;
import cards.belote.RulesBelote;
import cards.facade.exceptions.FileRulesException;
import cards.president.CheckerGamePresidentWithRules;
import cards.president.GamePresident;
import cards.president.RulesPresident;
import cards.tarot.CheckerGameTarotWithRules;
import cards.tarot.GameTarot;
import cards.tarot.RulesTarot;
import code.datacheck.ObjectComponents;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.Numbers;

public final class Games {

    private static final String NO_CARD_GAME = "this is not a card game";

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
            StreamTextFile.saveObject(_nomFichier, game_);
        }
        if(enCoursDePartieTarot()){
            GameTarot game_ = partieTarot();
            StreamTextFile.saveObject(_nomFichier, game_);
        }
        if(enCoursDePartiePresident()){
            GamePresident game_ = partiePresident();
            StreamTextFile.saveObject(_nomFichier, game_);
        }
    }
    /**Load a game card from a XML file
    @throws RuntimeException */
    public void chargerPartie(String _fichier) {
        Object par_ = StreamTextFile.loadObject(_fichier);
        ObjectComponents.checkObjectNotNull(par_);
        if (par_ instanceof GameBelote) {
            CheckerGameBeloteWithRules.check((GameBelote) par_);
            jouerBelote((GameBelote)par_);
        } else if (par_ instanceof GameTarot) {
            CheckerGameTarotWithRules.check((GameTarot) par_);
            jouerTarot((GameTarot)par_);
        } else if (par_ instanceof GamePresident) {
            CheckerGamePresidentWithRules.check((GamePresident) par_);
            jouerPresident((GamePresident)par_);
        } else {
            throw new FileRulesException(NO_CARD_GAME);
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
}
