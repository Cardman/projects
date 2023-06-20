package code.expressionlanguage.exec.dbg;

public final class BreakPointCountUpdaterStd implements BreakPointCountUpdater {

    @Override
    public void update(BreakPoint _bp, int _newValue) {
        _bp.getResultStd().setCountModulo(_newValue);
    }

}
