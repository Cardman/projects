package aiki.db;

public final class LoadFlagSampleOne implements LoadFlag {

    private PerCentImpl perCent;

    public LoadFlagSampleOne(PerCentImpl _perCent) {
        perCent = _perCent;
    }

    @Override
    public void set(boolean _b) {

    }

    @Override
    public boolean get() {
        return perCent.getPercent() != 60;
    }
}
