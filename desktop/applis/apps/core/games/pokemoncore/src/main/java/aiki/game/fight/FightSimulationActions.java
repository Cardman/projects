package aiki.game.fight;

import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.actions.ActionSwitch;
import code.util.ByteMap;
import code.util.CustList;

public final class FightSimulationActions {
    private final CustList<CustList<ByteMap<ChoiceOfEvolutionAndMoves>>> movesAbilities;

    private final CustList<CustList<CustList<ActionMove>>> actionsBeforeRound;

    private final CustList<CustList<CustList<ActionSwitch>>> actionsSubstitutingFront;

    private final CustList<CustList<CustList<ActionSwitch>>> actionsSubstitutingBack;

    public FightSimulationActions() {
        actionsBeforeRound = new CustList<CustList<CustList<ActionMove>>>();
        actionsSubstitutingBack = new CustList<CustList<CustList<ActionSwitch>>>();
        actionsSubstitutingFront = new CustList<CustList<CustList<ActionSwitch>>>();
        movesAbilities = new CustList<CustList<ByteMap<ChoiceOfEvolutionAndMoves>>>();
    }

    public CustList<ByteMap<ChoiceOfEvolutionAndMoves>> getMovesAbilities(int _index) {
        return movesAbilities.get(_index);
    }

    public CustList<CustList<ActionMove>> getActionsBeforeRound(int _index) {
        return actionsBeforeRound.get(_index);
    }

    public CustList<CustList<ActionSwitch>> getActionsSubstitutingBack(int _index) {
        return actionsSubstitutingBack.get(_index);
    }

    public CustList<CustList<ActionSwitch>> getActionsSubstitutingFront(int _index) {
        return actionsSubstitutingFront.get(_index);
    }

    public CustList<CustList<ByteMap<ChoiceOfEvolutionAndMoves>>> getMovesAbilities() {
        return movesAbilities;
    }

    public CustList<CustList<CustList<ActionMove>>> getActionsBeforeRound() {
        return actionsBeforeRound;
    }

    public CustList<CustList<CustList<ActionSwitch>>> getActionsSubstitutingBack() {
        return actionsSubstitutingBack;
    }

    public CustList<CustList<CustList<ActionSwitch>>> getActionsSubstitutingFront() {
        return actionsSubstitutingFront;
    }
}
