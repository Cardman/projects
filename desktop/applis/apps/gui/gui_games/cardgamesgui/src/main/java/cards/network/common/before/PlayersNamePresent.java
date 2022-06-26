package cards.network.common.before;

import cards.belote.RulesBelote;
import cards.president.RulesPresident;
import cards.tarot.RulesTarot;
import code.util.IntMap;
import code.util.IntTreeMap;
import code.util.core.BoolVal;


public final class PlayersNamePresent {

    private IntMap<String> pseudos;

    private IntTreeMap< Byte> placesPlayers;

    private IntMap<BoolVal> readyPlayers;

    private int nbPlayers;

    private boolean first;

    private RulesTarot rulesTarot;

    private RulesPresident rulesPresident;

    private RulesBelote rulesBelote;

    public IntMap<String> getPseudos() {
        return pseudos;
    }

    public void setPseudos(IntMap<String> _pseudos) {
        pseudos = _pseudos;
    }

    public IntTreeMap< Byte> getPlacesPlayers() {
        return placesPlayers;
    }

    public void setPlacesPlayers(IntTreeMap< Byte> _placesPlayers) {
        placesPlayers = _placesPlayers;
    }

    public IntMap<BoolVal> getReadyPlayers() {
        return readyPlayers;
    }

    public void setReadyPlayers(IntMap<BoolVal> _readyPlayers) {
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
