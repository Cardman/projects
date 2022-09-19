package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.util.TypesDuo;
import aiki.fight.util.TypesDuos;
import aiki.util.DataInfoChecker;
import code.util.CustList;
import code.util.StringList;


public final class EffectUnprotectFromTypes extends Effect {

    private CustList<TypesDuo> types;

    private StringList disableImmuAgainstTypes;
    private StringList disableImmuFromMoves;
    private StringList attackTargetWithTypes;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(), TypesDuos.getTypesFrom(types), _data);
        DataInfoChecker.checkStringListContains(_data.getTypes(), disableImmuAgainstTypes, _data);
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(), disableImmuFromMoves, _data);
        DataInfoChecker.checkStringListContains(_data.getTypes(), attackTargetWithTypes, _data);
    }

    public CustList<TypesDuo> getTypes() {
        return types;
    }

    public void setTypes(CustList<TypesDuo> _types) {
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
