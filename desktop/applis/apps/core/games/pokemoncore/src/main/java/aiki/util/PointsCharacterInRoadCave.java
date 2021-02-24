package aiki.util;

import aiki.instances.Instances;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import code.util.CollCapacity;

public final class PointsCharacterInRoadCave extends Points<CharacterInRoadCave> {
    public PointsCharacterInRoadCave() {
    }
    public PointsCharacterInRoadCave(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected CharacterInRoadCave def() {
        return Instances.newDealerItem();
    }
}
