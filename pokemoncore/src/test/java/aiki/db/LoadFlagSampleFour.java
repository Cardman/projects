package aiki.db;

public final class LoadFlagSampleFour implements LoadFlag {

    private PerCentImpl perCent;

    public LoadFlagSampleFour(PerCentImpl _perCent) {
        perCent = _perCent;
    }

    @Override
    public void set(boolean _b) {

    }

    @Override
    public boolean get() {
        return perCent.getPercent() != 90;
    }
}
