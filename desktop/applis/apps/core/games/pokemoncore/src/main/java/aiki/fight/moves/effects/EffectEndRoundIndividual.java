package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.status.StatusType;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.util.CustList;
import code.util.StringMap;


public final class EffectEndRoundIndividual extends EffectEndRound {

    private Rate deleteAllStatus;
    private Rate recoilDamage;
    private Rate healHp;
    private StringMap<Rate> healHpByOwnerTypes;
    private StringMap<Rate> multDamageStatus;
    private String userStatusEndRound;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkTarget(TargetChoice.LANCEUR,getTargetChoice(),_data);
        CustList<String> mult_ = multDamageStatus.getKeys();
        DataInfoChecker.checkStringListContains(DataInfoChecker.filterStatusExclude(_data,StatusType.RELATION_UNIQUE).getKeys(), mult_,_data);
        CustList<String> keys_ = healHpByOwnerTypes.getKeys();
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getTypes(),keys_,_data);
        DataInfoChecker.checkPositiveOrZero(deleteAllStatus,_data);
        DataInfoChecker.checkPositiveOrZero(healHp,_data);
        DataInfoChecker.checkPositiveOrZero(recoilDamage,_data);
        if (!healHp.isZero()) {
            DataInfoChecker.checkEmptyStringList(keys_,_data);
        }
        if (!userStatusEndRound.isEmpty()) {
            DataInfoChecker.checkZero(deleteAllStatus,_data);
            DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),userStatusEndRound,_data);
            DataInfoChecker.checkEmptyStringList(mult_,_data);
        }
        if (!userStatusEndRound.isEmpty()) {
            return;
        }
        if (!deleteAllStatus.isZero()) {
            return;
        }
        if (!recoilDamage.isZero()) {
            return;
        }
        if (!healHp.isZero()) {
            return;
        }
        if (!healHpByOwnerTypes.isEmpty()) {
            return;
        }
        DataInfoChecker.checkEmptyNotStringList(mult_,_data);

    }

    @Override
    public RelationType getRelation() {
        return RelationType.INDIVIDUEL;
    }

    public Rate getDeleteAllStatus() {
        return deleteAllStatus;
    }

    public void setDeleteAllStatus(Rate _deleteAllStatus) {
        deleteAllStatus = _deleteAllStatus;
    }

    public Rate getRecoilDamage() {
        return recoilDamage;
    }

    public void setRecoilDamage(Rate _recoilDamage) {
        recoilDamage = _recoilDamage;
    }

    public Rate getHealHp() {
        return healHp;
    }

    public void setHealHp(Rate _healHp) {
        healHp = _healHp;
    }

    public StringMap<Rate> getHealHpByOwnerTypes() {
        return healHpByOwnerTypes;
    }

    public void setHealHpByOwnerTypes(StringMap<Rate> _healHpByOwnerTypes) {
        healHpByOwnerTypes = _healHpByOwnerTypes;
    }

    public StringMap<Rate> getMultDamageStatus() {
        return multDamageStatus;
    }

    public void setMultDamageStatus(StringMap<Rate> _multDamageStatus) {
        multDamageStatus = _multDamageStatus;
    }

    public String getUserStatusEndRound() {
        return userStatusEndRound;
    }

    public void setUserStatusEndRound(String _userStatusEndRound) {
        userStatusEndRound = _userStatusEndRound;
    }
}
