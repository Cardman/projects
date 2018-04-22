package code.gui;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

public class ScrollPane extends CustComponent {

    private JScrollPane component;

    public ScrollPane(CustComponent _center) {
        component = new JScrollPane(_center.getComponent());
        _center.setParent(this);
        getChildren().add(_center);
    }

    public ScrollPane() {
        component = new JScrollPane();
    }

    public ScrollPane(JComponent _commentsErrors) {
        this(new PseudoComponent(_commentsErrors));
    }

    @Override
    public JComponent getComponent() {
        return component;
    }

    public void setPreferredSize(Dimension _dimension) {
        component.setPreferredSize(_dimension);
    }

    public void setVisible(boolean _b) {
        component.setVisible(_b);
    }

    public boolean isVisible() {
        return component.isVisible();
    }

}
