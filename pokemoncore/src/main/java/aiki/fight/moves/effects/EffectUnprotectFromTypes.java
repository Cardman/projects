package aiki.fight.moves.effects;
import code.util.EqList;
import code.util.StringList;
import code.util.annot.RwXml;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.util.TypesDuo;

@RwXml
public class EffectUnprotectFromTypes extends Effect {

    private EqList<TypesDuo> types;

    private StringList disableImmuAgainstTypes;
    private StringList disableImmuFromMoves;
    private StringList attackTargetWithTypes;
    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (TypesDuo t: types) {
            if (!_data.getTypes().containsObj(t.getDamageType())) {
                throw new DataException();
            }
            if (!_data.getTypes().containsObj(t.getPokemonType())) {
                throw new DataException();
            }
        }
        if (!_data.getTypes().containsAllObj(disableImmuAgainstTypes)) {
            throw new DataException();
        }
        if (!_data.getMoves().containsAllAsKeys(disableImmuFromMoves)) {
            throw new DataException();
        }
        if (!_data.getTypes().containsAllObj(attackTargetWithTypes)) {
            throw new DataException();
        }
    }

    public EqList<TypesDuo> getTypes() {
        return types;
    }
    public void setTypes(EqList<TypesDuo> _types) {
        types = _types;
    }

    public StringList getDisableImmuAgainstTypes() {
        return disableImmuAgainstTypes;
    }
    public void setDisableImmuAgainstTypes(StringList _disableImmuAgainstTypes) {
        disableImmuAgainstTypes = _disableImmuAgainstTypes;
    }
    public StringList getDisableImmuFromMoves() {
        return disableImmuFromMoves;
    }
    public void setDisableImmuFromMoves(StringList _disableImmuFromMoves) {
        disableImmuFromMoves = _disableImmuFromMoves;
    }
    public StringList getAttackTargetWithTypes() {
        return attackTargetWithTypes;
    }
    public void setAttackTargetWithTypes(StringList _attackTargetWithTypes) {
        attackTargetWithTypes = _attackTargetWithTypes;
    }

}
