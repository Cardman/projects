package aiki.db;

public final class ChangeStringFieldLevelWildName extends ChangeStringFieldLevelWild {
    @Override
    protected ChangeStringFieldVisit buildChange() {
        return new ChangeStringFieldVisitName();
    }
}
