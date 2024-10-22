package aiki.db;

public final class ChangeStringFieldLevelWildItem extends ChangeStringFieldLevelWild {
    @Override
    protected ChangeStringFieldVisit buildChange() {
        return new ChangeStringFieldVisitItem();
    }
}
