package cards.network.common.before;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.annot.RwXml;
import cards.belote.RulesBelote;
import cards.president.RulesPresident;
import cards.tarot.RulesTarot;

@RwXml
public final class PlayersNamePresent {

    private NumberMap<Integer,String> pseudos;

    private NatTreeMap<Integer, Byte> placesPlayers;

    private NumberMap<Integer,Boolean> readyPlayers;

    private int nbPlayers;

    private boolean first;

    private RulesTarot rulesTarot;

    private RulesPresident rulesPresident;

    private RulesBelote rulesBelote;

    public NumberMap<Integer,String> getPseudos() {
        return pseudos;
    }

    public void setPseudos(NumberMap<Integer,String> _pseudos) {
        pseudos = _pseudos;
    }

    public NatTreeMap<Integer, Byte> getPlacesPlayers() {
        return placesPlayers;
    }

    public void setPlacesPlayers(NatTreeMap<Integer, Byte> _placesPlayers) {
        placesPlayers = _placesPlayers;
    }

    public NumberMap<Integer,Boolean> getReadyPlayers() {
        return readyPlayers;
    }

    public void setReadyPlayers(NumberMap<Integer,Boolean> _readyPlayers) {
        readyPlayers = _readyPlayers;
    }

    public int getNbPlayers() {
        return nbPlayers;
    }

    public void setNbPlayers(int _nbPlayers) {
        nbPlayers = _nbPlayers;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean _first) {
        first = _first;
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

    public RulesBelote getRulesBelote() {
        return rulesBelote;
    }

    public void setRulesBelote(RulesBelote _rulesBelote) {
        rulesBelote = _rulesBelote;
    }
}
