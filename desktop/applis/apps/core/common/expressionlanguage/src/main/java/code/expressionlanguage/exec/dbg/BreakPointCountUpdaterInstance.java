package code.expressionlanguage.exec.dbg;

public final class BreakPointCountUpdaterInstance implements BreakPointCountUpdater {

    @Override
    public void update(BreakPoint _bp, int _newValue) {
        _bp.getResultInstance().getCountModulo().set(_newValue);
    }

}
