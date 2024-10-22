package aiki.db;

public final class ChangeStringFieldLevelWildAbility extends ChangeStringFieldLevelWild {
    @Override
    protected ChangeStringFieldVisit buildChange() {
        return new ChangeStringFieldVisitAbility();
    }
}
