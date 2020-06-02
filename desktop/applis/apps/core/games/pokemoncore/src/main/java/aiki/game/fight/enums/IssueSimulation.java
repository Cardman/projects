package aiki.game.fight.enums;

public enum IssueSimulation {
    RULES_LEARN(true),
    RULES_SWITCH(true),
    RULES_MOVES(true),
    KO_PLAYER, SENDING_ISSUE, RANDOM, CANNOT_USE,
    NOT_KO_FOE, TOO_HARD_SIMULATION, NOTHING;
    private final boolean rules;
    IssueSimulation() {
        rules = false;
    }
    IssueSimulation(boolean _rules) {
        rules = _rules;
    }
    public boolean isRules() {
        return rules;
    }
}
