package aiki.beans;

public final class BeanDisplayInt implements BeanDisplay<Integer> {
    @Override
    public int display(CommonBean _rend, Integer _info, int _index) {
        _rend.formatMessageDirCts(Long.toString(_info));
        return 1;
    }

}
