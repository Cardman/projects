package code.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.Border;

public class Panel extends CustComponent {

    private JPanel panel;

    public Panel() {
        this(new JPanel());
    }

    public Panel(LayoutManager _panel) {
        this(new JPanel(_panel));
    }

    public Panel(JPanel _panel) {
        panel = _panel;
    }

    public int getComponentCount() {
        return panel.getComponentCount();
    }

    public CustComponent getComponent(int _n) {
        return getChildren().get(_n);
    }

    public Component[] getComponents() {
        return panel.getComponents();
    }

    public Component add(CustComponent _comp) {
        _comp.setParent(this);
        getChildren().add(_comp);
        return panel.add(_comp.getComponent());
    }
    
    public Component add(String _name, CustComponent _comp) {
        _comp.setParent(this);
        getChildren().add(_comp);
        return panel.add(_name, _comp.getComponent());
    }
    
    public Component add(CustComponent _comp, int _index) {
        _comp.setParent(this);
        getChildren().add(_index,_comp);
        return panel.add(_comp.getComponent(), _index);
    }
    
    public void add(CustComponent _comp, Object _constraints) {
        _comp.setParent(this);
        getChildren().add(_comp);
        panel.add(_comp.getComponent(), _constraints);
    }

    public void add(CustComponent _comp, Object _constraints, int _index) {
        _comp.setParent(this);
        getChildren().add(_index,_comp);
        panel.add(_comp.getComponent(), _constraints, _index);
    }

    public Component add(JComponent _comp) {
        return add(new PseudoComponent(_comp));
    }

    public Component add(String _name, JComponent _comp) {
        return add(_name, new PseudoComponent(_comp));
    }

    public Component add(JComponent _comp, int _index) {
        return add(new PseudoComponent(_comp), _index);
    }

    public void add(JComponent _comp, Object _constraints) {
        add(new PseudoComponent(_comp), _constraints);
    }

    public void add(JComponent _comp, Object _constraints, int _index) {
        add(new PseudoComponent(_comp), _constraints, _index);
    }

    public boolean isDisplayable() {
        return panel.isDisplayable();
    }

    public boolean isVisible() {
        return panel.isVisible();
    }

    public void remove(int _index) {
        getChildren().get(_index).setParent(null);
        getChildren().remove(_index);
        panel.remove(_index);
    }

    public boolean isEnabled() {
        return panel.isEnabled();
    }

    public void removeAll() {
        for (CustComponent c: getChildren()) {
            c.setParent(null);
        }
        getChildren().clear();
        panel.removeAll();
    }

    public void requestFocus() {
        panel.requestFocus();
    }

    public LayoutManager getLayout() {
        return panel.getLayout();
    }

    public boolean requestFocus(boolean _temporary) {
        return panel.requestFocus(_temporary);
    }

    public void invalidate() {
        panel.invalidate();
    }

    public boolean requestFocusInWindow() {
        return panel.requestFocusInWindow();
    }

    public void validate() {
        panel.validate();
    }

    public Color getForeground() {
        return panel.getForeground();
    }

    public FontMetrics getFontMetrics(Font _font) {
        return panel.getFontMetrics(_font);
    }

    public void setPreferredSize(Dimension _preferredSize) {
        panel.setPreferredSize(_preferredSize);
    }

    public Font getFont() {
        return panel.getFont();
    }

    public Dimension getPreferredSize() {
        return panel.getPreferredSize();
    }

    public void setMaximumSize(Dimension _maximumSize) {
        panel.setMaximumSize(_maximumSize);
    }

    public Dimension getMaximumSize() {
        return panel.getMaximumSize();
    }

    public void setMinimumSize(Dimension _minimumSize) {
        panel.setMinimumSize(_minimumSize);
    }

    public Dimension getMinimumSize() {
        return panel.getMinimumSize();
    }

    public void setBorder(Border _border) {
        panel.setBorder(_border);
    }

    public Point getLocation() {
        return panel.getLocation();
    }

    public Point getLocationOnScreen() {
        return panel.getLocationOnScreen();
    }

    public void setLocation(int _x, int _y) {
        panel.setLocation(_x, _y);
    }

    public void setLocation(Point _p) {
        panel.setLocation(_p);
    }

    public Dimension getSize() {
        return panel.getSize();
    }

    public void setSize(int _width, int _height) {
        panel.setSize(_width, _height);
    }

    public void setSize(Dimension _d) {
        panel.setSize(_d);
    }

    public void setBounds(int _x, int _y, int _width, int _height) {
        panel.setBounds(_x, _y, _width, _height);
    }

    public void setBounds(Rectangle _r) {
        panel.setBounds(_r);
    }

    public void setVisible(boolean _aFlag) {
        panel.setVisible(_aFlag);
    }

    public void setEnabled(boolean _enabled) {
        panel.setEnabled(_enabled);
    }

    public void setForeground(Color _fg) {
        panel.setForeground(_fg);
    }

    public void setBackground(Color _bg) {
        panel.setBackground(_bg);
    }

    public void setFont(Font _font) {
        panel.setFont(_font);
    }

    public void setCursor(Cursor _cursor) {
        panel.setCursor(_cursor);
    }

    public Cursor getCursor() {
        return panel.getCursor();
    }

    public void repaint(long _tm) {
        panel.repaint(_tm);
    }

    public void repaint(int _x, int _y, int _width, int _height) {
        panel.repaint(_x, _y, _width, _height);
    }

    public void scrollRectToVisible(Rectangle _aRect) {
        panel.scrollRectToVisible(_aRect);
    }

    public void setAutoscrolls(boolean _autoscrolls) {
        panel.setAutoscrolls(_autoscrolls);
    }

    public Dimension getSize(Dimension _rv) {
        return panel.getSize(_rv);
    }

    public Point getLocation(Point _rv) {
        return panel.getLocation(_rv);
    }

    public int getX() {
        return panel.getX();
    }

    public int getY() {
        return panel.getY();
    }

    @Override
    public int getWidth() {
        return panel.getWidth();
    }

    @Override
    public int getHeight() {
        return panel.getHeight();
    }

    public boolean isOpaque() {
        return panel.isOpaque();
    }

    public void setOpaque(boolean _isOpaque) {
        panel.setOpaque(_isOpaque);
    }

    public void repaint(long _tm, int _x, int _y, int _width, int _height) {
        panel.repaint(_tm, _x, _y, _width, _height);
    }

    public void repaint(Rectangle _r) {
        panel.repaint(_r);
    }

    public void revalidate() {
        panel.revalidate();
    }

    public boolean isValidateRoot() {
        return panel.isValidateRoot();
    }

    public JRootPane getRootPane() {
        return panel.getRootPane();
    }

    public void addKeyListener(KeyListener _l) {
        panel.addKeyListener(_l);
    }

    public void addMouseListener(MouseListener _l) {
        panel.addMouseListener(_l);
    }

    @Override
    public JComponent getComponent() {
        return panel;
    }

    public void setLayout(LayoutManager _borderLayout) {
        panel.setLayout(_borderLayout);
    }

    public Color getBackground() {
        return panel.getBackground();
    }
}
