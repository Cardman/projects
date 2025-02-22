package aiki.beans;

import aiki.beans.abilities.*;

public final class BeanDisplayTypeDamageBoostKey implements BeanDisplay<TypeDamageBoostKey>{
    @Override
    public int display(CommonBean _rend, TypeDamageBoostKey _info, int _index) {
        _rend.formatMessageDirCts(_info.getType().getTranslation());
        _rend.formatMessageDirCts(_info.getBoost().toNumberString());
        return 2;
    }
}
