package code.gui.document;

public final class BeanDisplayBoolLong implements BeanDisplay<Long> {

    private final String valueTrue;
    private final String valueFalse;

    public BeanDisplayBoolLong(String _t, String _f) {
        this.valueTrue = _t;
        this.valueFalse = _f;
    }

    @Override
    public int display(AbsBeanRender _rend, Long _info, int _index) {
        if (_info > 0) {
            _rend.formatMessageDirCts(valueTrue);
        } else {
            _rend.formatMessageDirCts(valueFalse);
        }
        return 1;
    }
}
