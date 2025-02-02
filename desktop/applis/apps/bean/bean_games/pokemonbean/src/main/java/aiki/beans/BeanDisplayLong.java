package aiki.beans;

public final class BeanDisplayLong implements BeanDisplay<Long> {
    @Override
    public int display(CommonBean _rend, Long _info, int _index) {
        _rend.formatMessageDirCts(Long.toString(_info));
        return 1;
    }

}
