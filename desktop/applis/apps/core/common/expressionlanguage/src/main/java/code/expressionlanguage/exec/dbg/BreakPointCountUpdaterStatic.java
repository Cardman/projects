package code.expressionlanguage.exec.dbg;

public final class BreakPointCountUpdaterStatic implements BreakPointCountUpdater {

    @Override
    public void update(BreakPoint _bp, int _newValue) {
        _bp.getResultStatic().getCountModulo().set(_newValue);
    }

}
