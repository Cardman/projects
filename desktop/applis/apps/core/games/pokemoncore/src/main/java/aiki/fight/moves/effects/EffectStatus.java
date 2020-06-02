package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.montecarlo.MonteCarloString;
import code.util.StringList;
import code.util.StringMap;


public final class EffectStatus extends Effect {

    private MonteCarloString lawStatus;
    private StringList deletedStatus;
    private StringMap<String> localFailStatus;
    private boolean koUserHealSubst;
    private boolean statusFromUser;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!lawStatus.checkEvents()) {
            _data.setError(true);
        }
        if (!_data.getStatus().containsAllAsKeys(localFailStatus.getKeys())) {
            _data.setError(true);
        }
        if (!_data.getStatus().containsAllAsKeys(deletedStatus)) {
            _data.setError(true);
        }
        if (koUserHealSubst) {
            if (statusFromUser) {
                _data.setError(true);
            }
            if (!deletedStatus.isEmpty()) {
                _data.setError(true);
            }
            if (!lawStatus.events().isEmpty()) {
                _data.setError(true);
            }
            if (getTargetChoice() != TargetChoice.LANCEUR) {
                _data.setError(true);
            }
            return;
        }
        if (!deletedStatus.isEmpty()) {
            StringList copy_ = new StringList(deletedStatus);
            copy_.addAllElts(lawStatus.events());
            if (copy_.hasDuplicates()) {
                _data.setError(true);
            }
            if (statusFromUser) {
                _data.setError(true);
            }
            return;
        }
        if (statusFromUser) {
            if (!lawStatus.events().isEmpty()) {
                _data.setError(true);
            }
            if (getTargetChoice() == TargetChoice.LANCEUR) {
                _data.setError(true);
            }
            return;
        }
        if (lawStatus.events().isEmpty()) {
            _data.setError(true);
        }
        StringList events_ = new StringList(lawStatus.events());
        StringList.removeObj(events_, DataBase.EMPTY_STRING);
        if (!_data.getStatus().containsAllAsKeys(events_)) {
            _data.setError(true);
        }
    }

    public MonteCarloString getLawStatus() {
        return lawStatus;
    }

    public void setLawStatus(MonteCarloString _lawStatus) {
        lawStatus = _lawStatus;
    }

    public StringList getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(StringList _deletedStatus) {
        deletedStatus = _deletedStatus;
    }

    public StringMap<String> getLocalFailStatus() {
        return localFailStatus;
    }

    public void setLocalFailStatus(StringMap<String> _localFailStatus) {
        localFailStatus = _localFailStatus;
    }

    public boolean getKoUserHealSubst() {
        return koUserHealSubst;
    }

    public void setKoUserHealSubst(boolean _koUserHealSubst) {
        koUserHealSubst = _koUserHealSubst;
    }

    public boolean getStatusFromUser() {
        return statusFromUser;
    }

    public void setStatusFromUser(boolean _statusFromUser) {
        statusFromUser = _statusFromUser;
    }
}
