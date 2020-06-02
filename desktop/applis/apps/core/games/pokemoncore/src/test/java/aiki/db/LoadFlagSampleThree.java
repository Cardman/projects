package aiki.db;

public final class LoadFlagSampleThree implements LoadFlag {

    private PerCentImpl perCent;

    public LoadFlagSampleThree(PerCentImpl _perCent) {
        perCent = _perCent;
    }

    @Override
    public void set(boolean _b) {

    }

    @Override
    public boolean get() {
        return perCent.getPercent() != 85;
    }
}
