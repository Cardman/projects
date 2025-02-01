package code.gui.document;

public final class BeanDisplayLong implements BeanDisplay<Long> {
    @Override
    public int display(AbsBeanRender _rend, Long _info, int _index) {
        _rend.formatMessageDirCts(Long.toString(_info));
        return 1;
    }

}
