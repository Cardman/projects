package aiki.fight;
import aiki.fight.enums.EndTurnType;
import aiki.fight.moves.effects.enums.RelationType;
import code.serialize.CheckedData;

@CheckedData
public class EndRoundMainElements {

    private short numberIncrement;

    private boolean incrementNumberOfRounds;

    private EndTurnType endRoundType;

    private String element;

    private RelationType relation;

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
