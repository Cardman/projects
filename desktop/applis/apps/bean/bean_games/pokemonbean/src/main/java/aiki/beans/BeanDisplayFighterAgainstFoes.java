package aiki.beans;

import aiki.beans.fight.*;

public final class BeanDisplayFighterAgainstFoes implements BeanDisplayEltSec<FighterAgainstFoes> {
    @Override
    public int displayEltSec(CommonBean _rend, FighterAgainstFoes _info) {
        _rend.formatMessageDir(_info.getName());
        new BeanDisplayList<String>(new BeanDisplayString()).display(_rend,_info.getFoes().values());
        return 1;
    }
}
