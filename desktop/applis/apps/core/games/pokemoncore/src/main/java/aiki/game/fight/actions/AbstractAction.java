package aiki.game.fight.actions;


public abstract class AbstractAction {

    private final KindAction kindAction;

    protected AbstractAction(KindAction _kindAction) {
        kindAction = _kindAction;
    }

    public KindAction getKindAction() {
        return kindAction;
    }
}
