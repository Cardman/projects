package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.status.StatusType;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public final class EffectEndRoundIndividual extends EffectEndRound {

    @CheckedData
    private Rate deleteAllStatus;
    @CheckedData
    private Rate recoilDamage;
    @CheckedData
    private Rate healHp;
    private StringMap<Rate> healHpByOwnerTypes;
    private StringMap<Rate> multDamageStatus;
    @CheckedData
    private String userStatusEndRound;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            throw new DataException();
        }
        for (EntryCust<String, Rate> e: multDamageStatus.entryList()) {
            if (!_data.getStatus().contains(e.getKey())) {
                throw new DataException();
            }
            if (_data.getStatus(e.getKey()).getStatusType() == StatusType.RELATION_UNIQUE) {
                throw new DataException();
            }
            e.getValue().isZero();
        }
        StringList keys_ = healHpByOwnerTypes.getKeys();
        keys_.removeObj(DataBase.EMPTY_STRING);
        if (!_data.getTypes().containsAllObj(keys_)) {
            throw new DataException();
        }
        for (EntryCust<String, Rate> e: healHpByOwnerTypes.entryList()) {
            e.getValue().isZero();
        }
        if (!deleteAllStatus.isZeroOrGt()) {
            throw new DataException();
        }
        if (!healHp.isZeroOrGt()) {
            throw new DataException();
        }
        if (!recoilDamage.isZeroOrGt()) {
            throw new DataException();
        }
        if (healHp.isZeroOrGt() && !healHp.isZero()) {
            if (!healHpByOwnerTypes.isEmpty()) {
                throw new DataException();
            }
        }
        if (!userStatusEndRound.isEmpty()) {
            if (!deleteAllStatus.isZero()) {
                throw new DataException();
            }
            if (!_data.getStatus().contains(userStatusEndRound)) {
                throw new DataException();
            }
            if (!multDamageStatus.isEmpty()) {
                throw new DataException();
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
        throw new DataException();
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
