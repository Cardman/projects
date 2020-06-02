package aiki.db;

public final class LoadFlagSampleFive implements LoadFlag {

    private PerCentImpl perCent;

    public LoadFlagSampleFive(PerCentImpl _perCent) {
        perCent = _perCent;
    }

    @Override
    public void set(boolean _b) {

    }

    @Override
    public boolean get() {
        return perCent.getPercent() != 95;
    }
}
