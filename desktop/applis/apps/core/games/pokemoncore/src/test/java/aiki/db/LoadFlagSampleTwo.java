package aiki.db;

public final class LoadFlagSampleTwo implements LoadFlag {

    private PerCentImpl perCent;

    public LoadFlagSampleTwo(PerCentImpl _perCent) {
        perCent = _perCent;
    }

    @Override
    public void set(boolean _b) {

    }

    @Override
    public boolean get() {
        return perCent.getPercent() != 70;
    }
}
