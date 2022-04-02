package code.formathtml.util;

import code.util.core.StringUtil;

public final class DefNodeContainer extends NodeContainer {

    private String idClass;

    @Override
    public boolean match(FieldUpdates _f) {
        return StringUtil.quickEq(idClass, ((DefFieldUpdates)_f).getId());
    }

    @Override
    public void setIdClass(FieldUpdates _idClass) {
        this.idClass = ((DefFieldUpdates)_idClass).getId();
    }

}
