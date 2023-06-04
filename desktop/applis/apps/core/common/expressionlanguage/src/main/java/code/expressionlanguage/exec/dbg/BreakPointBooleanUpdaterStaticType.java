package code.expressionlanguage.exec.dbg;

public final class BreakPointBooleanUpdaterStaticType implements BreakPointBooleanUpdater {

    @Override
    public void update(BreakPoint _bp, boolean _newValue) {
        _bp.setStaticType(_newValue);
    }
}
