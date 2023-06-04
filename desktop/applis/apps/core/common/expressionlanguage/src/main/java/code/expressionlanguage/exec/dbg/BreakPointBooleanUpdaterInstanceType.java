package code.expressionlanguage.exec.dbg;

public final class BreakPointBooleanUpdaterInstanceType implements BreakPointBooleanUpdater {

    @Override
    public void update(BreakPoint _bp, boolean _newValue) {
        _bp.setInstanceType(_newValue);
    }
}
