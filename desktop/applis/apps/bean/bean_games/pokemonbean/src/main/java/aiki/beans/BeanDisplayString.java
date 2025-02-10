package aiki.beans;

public final class BeanDisplayString implements BeanDisplay<String>, BeanDisplayElt<String> {

    @Override
    public int display(CommonBean _rend, String _info, int _index) {
        _rend.formatMessageDirCts(_info);
        return 1;
    }

    @Override
    public int displayElt(CommonBean _rend, String _info) {
        _rend.formatMessageDir(_info);
        return 1;
    }
}
