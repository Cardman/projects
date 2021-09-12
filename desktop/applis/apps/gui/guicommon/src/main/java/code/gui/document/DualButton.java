package code.gui.document;



import code.formathtml.render.MetaButton;
import code.gui.AbsCustComponent;
import code.gui.AbsPlainButton;
import code.gui.GuiConstants;

public final class DualButton extends DualInput {

    private final String value;
    private final AbsPlainButton label;

    public DualButton(DualContainer _container, MetaButton _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        label = _page.getCompoFactory().newPlainButton();
        updateGraphics(label,_component);
        label.setLineBorder(GuiConstants.BLACK, 1);
        label.setHandCursor();
        label.addMouseListener(new FormEvent(this, _page));
        value = _component.getValue();
    }

    @Override
    protected void postAdd() {
        paint();
    }

    @Override
    public AbsCustComponent getGraphic() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public void paint() {
//        BufferedImage img_ = LabelButtonUtil.paintButton(label, value, true);
//        label.setIcon(img_);
        label.setText(value);
    }
}
