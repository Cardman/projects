package code.gui.document;

public final class BeanDisplayString implements BeanDisplay<String> {

    @Override
    public int display(AbsBeanRender _rend, String _info, int _index) {
        _rend.formatMessageDirCts(_info);
        return 1;
    }
}
