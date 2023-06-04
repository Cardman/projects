package code.expressionlanguage.exec.dbg;

public final class BreakPointBooleanUpdaterEnabled implements BreakPointBooleanUpdater {

    @Override
    public void update(BreakPoint _bp, boolean _newValue) {
        _bp.setEnabled(_newValue);
    }
}
