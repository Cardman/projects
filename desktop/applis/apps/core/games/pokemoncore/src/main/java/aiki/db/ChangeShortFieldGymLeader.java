package aiki.db;

import aiki.map.characters.GymLeader;

public final class ChangeShortFieldGymLeader implements ChangeShortField {
    private final GymLeader gymLeader;

    public ChangeShortFieldGymLeader(GymLeader _g) {
        this.gymLeader = _g;
    }

    @Override
    public short value() {
        return gymLeader.getTm();
    }

    @Override
    public void value(short _v) {
        gymLeader.setTm(_v);
    }
}
