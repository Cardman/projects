package code.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import java.awt.*;

public final class TreeGui extends CustComponent {
    private JTree tree;
    public TreeGui() {
        tree = new JTree();
    }
    public TreeGui(TreeNode _t) {
        tree = new JTree(_t);
    }

    TreeModel getModel() {
        return tree.getModel();
    }

    public boolean isRootVisible() {
        return tree.isRootVisible();
    }

    public void setRootVisible(boolean rootVisible) {
        tree.setRootVisible(rootVisible);
    }

    public void setDragEnabled(boolean b) {
        tree.setDragEnabled(b);
    }

    public boolean getDragEnabled() {
        return tree.getDragEnabled();
    }

    public int getRowCount() {
        return tree.getRowCount();
    }

    public void setSelectionPath(TreePath path) {
        tree.setSelectionPath(path);
    }

    public Object getLastSelectedPathComponent() {
        return tree.getLastSelectedPathComponent();
    }

    public TreePath getSelectionPath() {
        return tree.getSelectionPath();
    }

    public boolean isPathSelected(TreePath path) {
        return tree.isPathSelected(path);
    }

    public TreePath getPathForRow(int row) {
        return tree.getPathForRow(row);
    }

    public int getRowForPath(TreePath path) {
        return tree.getRowForPath(path);
    }

    TreeSelectionModel getSelectionModel() {
        return tree.getSelectionModel();
    }

    public void clearSelection() {
        tree.clearSelection();
    }

    public boolean isSelectionEmpty() {
        return tree.isSelectionEmpty();
    }

    public void addTreeSelectionListener(TreeSelectionListener tsl) {
        tree.addTreeSelectionListener(tsl);
    }

    public void setInheritsPopupMenu(boolean value) {
        tree.setInheritsPopupMenu(value);
    }

    public boolean getInheritsPopupMenu() {
        return tree.getInheritsPopupMenu();
    }

    public boolean contains(int x, int y) {
        return tree.contains(x, y);
    }

    public Border getBorder() {
        return tree.getBorder();
    }

    public float getAlignmentY() {
        return tree.getAlignmentY();
    }

    public float getAlignmentX() {
        return tree.getAlignmentX();
    }

    public void setEnabled(boolean enabled) {
        tree.setEnabled(enabled);
    }

    public void setForeground(Color fg) {
        tree.setForeground(fg);
    }

    public void setBackground(Color bg) {
        tree.setBackground(bg);
    }

    public String getToolTipText() {
        return tree.getToolTipText();
    }

    public void scrollRectToVisible(Rectangle aRect) {
        tree.scrollRectToVisible(aRect);
    }

    public Rectangle getBounds(Rectangle rv) {
        return tree.getBounds(rv);
    }

    public Dimension getSize(Dimension rv) {
        return tree.getSize(rv);
    }

    public Point getLocation(Point rv) {
        return tree.getLocation(rv);
    }

    public int getX() {
        return tree.getX();
    }

    public int getY() {
        return tree.getY();
    }

    public void setOpaque(boolean isOpaque) {
        tree.setOpaque(isOpaque);
    }

    @Override
    public JComponent getComponent() {
        return tree;
    }
}
