package aiki.fight;
import aiki.fight.enums.EndTurnType;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.enums.RelationType;

public class EndRoundMainElements {

    private final EffectEndRound eff;
    private short numberIncrement;

    private boolean incrementNumberOfRounds;

    private EndTurnType endRoundType;

    private String element;

    private RelationType relation;

    public EndRoundMainElements(EffectEndRound _endRound) {
        eff = _endRound;
    }

    public EffectEndRound getEff() {
        return eff;
    }

    public short getNumberIncrement() {
        return numberIncrement;
    }

    public void setNumberIncrement(short _numberIncrement) {
        numberIncrement = _numberIncrement;
    }

    public boolean isIncrementNumberOfRounds() {
        return incrementNumberOfRounds;
    }

    public void setIncrementNumberOfRounds(boolean _incrementNumberOfRounds) {
        incrementNumberOfRounds = _incrementNumberOfRounds;
    }

    public EndTurnType getEndRoundType() {
        return endRoundType;
    }

    public void setEndRoundType(EndTurnType _endRoundType) {
        endRoundType = _endRoundType;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String _element) {
        element = _element;
    }

    public RelationType getRelation() {
        return relation;
    }

    public void setRelation(RelationType _relation) {
        relation = _relation;
    }
}
