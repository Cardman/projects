package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.montecarlo.MonteCarloString;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
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
            throw new DataException();
        }
        if (!_data.getStatus().containsAllAsKeys(localFailStatus.getKeys())) {
            throw new DataException();
        }
        if (!_data.getStatus().containsAllAsKeys(deletedStatus)) {
            throw new DataException();
        }
        if (koUserHealSubst) {
            if (statusFromUser) {
                throw new DataException();
            }
            if (!deletedStatus.isEmpty()) {
                throw new DataException();
            }
            if (!lawStatus.events().isEmpty()) {
                throw new DataException();
            }
            if (getTargetChoice() != TargetChoice.LANCEUR) {
                throw new DataException();
            }
            return;
        }
        if (!deletedStatus.isEmpty()) {
            StringList copy_ = new StringList(deletedStatus);
            copy_.removeDuplicates();
            int size_ = copy_.size();
            copy_.addAllElts(lawStatus.events());
            copy_.removeDuplicates();
            if (size_ + lawStatus.events().size() != copy_.size()) {
                if (!lawStatus.events().isEmpty()) {
                    throw new DataException();
                }
            }
            if (statusFromUser) {
                throw new DataException();
            }
            return;
        }
        if (statusFromUser) {
            if (!lawStatus.events().isEmpty()) {
                throw new DataException();
            }
            if (getTargetChoice() == TargetChoice.LANCEUR) {
                throw new DataException();
            }
            return;
        }
        if (lawStatus.events().isEmpty()) {
            throw new DataException();
        }
        StringList events_ = new StringList(lawStatus.events());
        events_.removeObj(DataBase.EMPTY_STRING);
        if (!_data.getStatus().containsAllAsKeys(events_)) {
            throw new DataException();
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
