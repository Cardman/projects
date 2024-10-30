package aiki.fight.util;

import aiki.util.*;
import code.maths.*;
import code.util.*;

public final class WeatherTypes extends CommonMap<WeatherType,Rate> {
    public WeatherTypes() {
    }
    public WeatherTypes(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Rate def() {
        return Rate.zero();
    }

    @Override
    protected boolean eq(WeatherType _k, WeatherType _e) {
        return _k.eq(_e);
    }

}
