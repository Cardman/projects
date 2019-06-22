package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.status.StatusType;
import code.maths.Rate;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
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
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            _data.setError(true);
            return;

        }
        for (EntryCust<String, Rate> e : multDamageStatus.entryList()) {
            if (!_data.getStatus().contains(e.getKey())) {
                _data.setError(true);
                return;

            }
            if (_data.getStatus(e.getKey()).getStatusType() == StatusType.RELATION_UNIQUE) {
                _data.setError(true);
                return;

            }
            e.getValue().isZero();
        }
        CustList<String> keys_ = healHpByOwnerTypes.getKeys();
        StringList.removeObj(keys_, DataBase.EMPTY_STRING);
        if (!_data.getTypes().containsAllObj(keys_)) {
            _data.setError(true);
            return;

        }
        if (!deleteAllStatus.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (!healHp.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (!recoilDamage.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (!healHp.isZero()) {
            if (!healHpByOwnerTypes.isEmpty()) {
                _data.setError(true);
                return;

            }
        }
        if (!userStatusEndRound.isEmpty()) {
            if (!deleteAllStatus.isZero()) {
                _data.setError(true);
                return;

            }
            if (!_data.getStatus().contains(userStatusEndRound)) {
                _data.setError(true);
                return;

            }
            if (!multDamageStatus.isEmpty()) {
                _data.setError(true);
                return;

            }
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
        if (!multDamageStatus.isEmpty()) {
            return;
        }
        _data.setError(true);

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
