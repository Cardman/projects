package aiki.beans;

public final class BeanDisplayString implements BeanDisplay<String> {

    @Override
    public int display(CommonBean _rend, String _info, int _index) {
        _rend.formatMessageDirCts(_info);
        return 1;
    }
}
