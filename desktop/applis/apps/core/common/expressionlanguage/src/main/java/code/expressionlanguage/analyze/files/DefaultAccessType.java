package code.expressionlanguage.analyze.files;

import code.expressionlanguage.common.AccessEnum;

public final class DefaultAccessType {
    private AccessEnum accInners = AccessEnum.PUBLIC;
    private AccessEnum accLocalTypes = AccessEnum.PUBLIC;
    private AccessEnum accMember = AccessEnum.PUBLIC;

    public AccessEnum getAccInners() {
        return accInners;
    }

    public void setAccInners(AccessEnum _accInners) {
        accInners = _accInners;
    }

    public AccessEnum getAccLocalTypes() {
        return accLocalTypes;
    }

    public void setAccLocalTypes(AccessEnum _accLocalTypes) {
        this.accLocalTypes = _accLocalTypes;
    }

    public AccessEnum getAccMember() {
        return accMember;
    }

    public void setAccMember(AccessEnum _accMember) {
        this.accMember = _accMember;
    }

}
