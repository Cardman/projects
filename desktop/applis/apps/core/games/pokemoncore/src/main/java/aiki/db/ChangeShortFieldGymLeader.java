package aiki.db;

import aiki.map.characters.GymLeader;

public final class ChangeShortFieldGymLeader implements ChangeShortField {
    private final GymLeader gymLeader;

    public ChangeShortFieldGymLeader(GymLeader _g) {
        this.gymLeader = _g;
    }

    @Override
    public int value() {
        return gymLeader.getTm();
    }

    @Override
    public void value(int _v) {
        gymLeader.setTm(_v);
    }
}
