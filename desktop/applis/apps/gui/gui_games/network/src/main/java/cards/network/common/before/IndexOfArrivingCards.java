package cards.network.common.before;
import cards.belote.*;
import cards.president.*;
import cards.tarot.*;
import code.util.*;
import code.util.core.*;


public final class IndexOfArrivingCards extends PlayerActionBeforeGameCards {

    private IntTreeMap< Integer> placesPlayers;

    private IntMap<BoolVal> readyPlayers;

    private int nbPlayers;

    private RulesTarot rulesTarot;

    private RulesPresident rulesPresident;

    private RulesBelote rulesBelote;


    public int getNbPlayers() {
        return nbPlayers;
    }

    public void setNbPlayers(int _n) {
        this.nbPlayers = _n;
    }

    public IntTreeMap< Integer> getPlacesPlayers() {
        return placesPlayers;
    }

    public void setPlacesPlayers(IntTreeMap< Integer> _placesPlayers) {
        placesPlayers = _placesPlayers;
    }

    public IntMap<BoolVal> getReadyPlayers() {
        return readyPlayers;
    }

    public void setReadyPlayers(IntMap<BoolVal> _readyPlayers) {
        readyPlayers = _readyPlayers;
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
