package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;
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
        DataInfoChecker.checkEvents(lawStatus,_data);
        DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),localFailStatus.getKeys(),_data);
        DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),deletedStatus,_data);
        if (koUserHealSubst) {
            if (statusFromUser) {
                _data.setError(true);
            }
            DataInfoChecker.checkEmptyStringList(deletedStatus,_data);
            DataInfoChecker.checkEmptyStringList(lawStatus.events(),_data);
            DataInfoChecker.checkTarget(TargetChoice.LANCEUR,getTargetChoice(),_data);
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
            DataInfoChecker.checkEmptyStringList(lawStatus.events(),_data);
            DataInfoChecker.checkTargetNot(TargetChoice.LANCEUR,getTargetChoice(),_data);
            return;
        }
        DataInfoChecker.checkEmptyNotStringList(lawStatus.events(),_data);
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getStatus().getKeys(),lawStatus.events(),_data);
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
