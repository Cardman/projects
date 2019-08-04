package code.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public final class TreeGui extends CustComponent {
    private JTree tree;
    public TreeGui() {
        tree = new JTree();
    }
    public TreeGui(TreeNode _t) {
        tree = new JTree(_t);
    }
    public TreeCellRenderer getCellRenderer() {
        return tree.getCellRenderer();
    }

    public void setCellRenderer(TreeCellRenderer x) {
        tree.setCellRenderer(x);
    }

    public void setEditable(boolean flag) {
        tree.setEditable(flag);
    }

    public boolean isEditable() {
        return tree.isEditable();
    }

    public void setCellEditor(TreeCellEditor cellEditor) {
        tree.setCellEditor(cellEditor);
    }

    public TreeCellEditor getCellEditor() {
        return tree.getCellEditor();
    }

    public TreeModel getModel() {
        return tree.getModel();
    }

    public void setModel(TreeModel newModel) {
        tree.setModel(newModel);
    }

    public boolean isRootVisible() {
        return tree.isRootVisible();
    }

    public void setRootVisible(boolean rootVisible) {
        tree.setRootVisible(rootVisible);
    }

    public void setShowsRootHandles(boolean newValue) {
        tree.setShowsRootHandles(newValue);
    }

    public boolean getShowsRootHandles() {
        return tree.getShowsRootHandles();
    }

    public void setRowHeight(int rowHeight) {
        tree.setRowHeight(rowHeight);
    }

    public int getRowHeight() {
        return tree.getRowHeight();
    }

    public boolean isFixedRowHeight() {
        return tree.isFixedRowHeight();
    }

    public void setLargeModel(boolean newValue) {
        tree.setLargeModel(newValue);
    }

    public boolean isLargeModel() {
        return tree.isLargeModel();
    }

    public void setInvokesStopCellEditing(boolean newValue) {
        tree.setInvokesStopCellEditing(newValue);
    }

    public boolean getInvokesStopCellEditing() {
        return tree.getInvokesStopCellEditing();
    }

    public void setScrollsOnExpand(boolean newValue) {
        tree.setScrollsOnExpand(newValue);
    }

    public boolean getScrollsOnExpand() {
        return tree.getScrollsOnExpand();
    }

    public void setToggleClickCount(int clickCount) {
        tree.setToggleClickCount(clickCount);
    }

    public int getToggleClickCount() {
        return tree.getToggleClickCount();
    }

    public void setExpandsSelectedPaths(boolean newValue) {
        tree.setExpandsSelectedPaths(newValue);
    }

    public boolean getExpandsSelectedPaths() {
        return tree.getExpandsSelectedPaths();
    }

    public void setDragEnabled(boolean b) {
        tree.setDragEnabled(b);
    }

    public boolean getDragEnabled() {
        return tree.getDragEnabled();
    }

    public void setDropMode(DropMode dropMode) {
        tree.setDropMode(dropMode);
    }

    public DropMode getDropMode() {
        return tree.getDropMode();
    }

    public boolean isPathEditable(TreePath path) {
        return tree.isPathEditable(path);
    }

    public String getToolTipText(MouseEvent event) {
        return tree.getToolTipText(event);
    }

    public String convertValueToText(Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        return tree.convertValueToText(value, selected, expanded, leaf, row, hasFocus);
    }

    public int getRowCount() {
        return tree.getRowCount();
    }

    public void setSelectionPath(TreePath path) {
        tree.setSelectionPath(path);
    }

    public void setSelectionPaths(TreePath[] paths) {
        tree.setSelectionPaths(paths);
    }

    public void setLeadSelectionPath(TreePath newPath) {
        tree.setLeadSelectionPath(newPath);
    }

    public void setAnchorSelectionPath(TreePath newPath) {
        tree.setAnchorSelectionPath(newPath);
    }

    public void setSelectionRow(int row) {
        tree.setSelectionRow(row);
    }

    public void setSelectionRows(int[] rows) {
        tree.setSelectionRows(rows);
    }

    public void addSelectionPath(TreePath path) {
        tree.addSelectionPath(path);
    }

    public void addSelectionPaths(TreePath[] paths) {
        tree.addSelectionPaths(paths);
    }

    public void addSelectionRow(int row) {
        tree.addSelectionRow(row);
    }

    public void addSelectionRows(int[] rows) {
        tree.addSelectionRows(rows);
    }

    public Object getLastSelectedPathComponent() {
        return tree.getLastSelectedPathComponent();
    }

    public TreePath getLeadSelectionPath() {
        return tree.getLeadSelectionPath();
    }

    public TreePath getAnchorSelectionPath() {
        return tree.getAnchorSelectionPath();
    }

    public TreePath getSelectionPath() {
        return tree.getSelectionPath();
    }

    public TreePath[] getSelectionPaths() {
        return tree.getSelectionPaths();
    }

    public int[] getSelectionRows() {
        return tree.getSelectionRows();
    }

    public int getSelectionCount() {
        return tree.getSelectionCount();
    }

    public int getMinSelectionRow() {
        return tree.getMinSelectionRow();
    }

    public int getMaxSelectionRow() {
        return tree.getMaxSelectionRow();
    }

    public int getLeadSelectionRow() {
        return tree.getLeadSelectionRow();
    }

    public boolean isPathSelected(TreePath path) {
        return tree.isPathSelected(path);
    }

    public boolean isRowSelected(int row) {
        return tree.isRowSelected(row);
    }

    public boolean hasBeenExpanded(TreePath path) {
        return tree.hasBeenExpanded(path);
    }

    public boolean isExpanded(TreePath path) {
        return tree.isExpanded(path);
    }

    public boolean isExpanded(int row) {
        return tree.isExpanded(row);
    }

    public boolean isCollapsed(TreePath path) {
        return tree.isCollapsed(path);
    }

    public boolean isCollapsed(int row) {
        return tree.isCollapsed(row);
    }

    public void makeVisible(TreePath path) {
        tree.makeVisible(path);
    }

    public boolean isVisible(TreePath path) {
        return tree.isVisible(path);
    }

    public Rectangle getPathBounds(TreePath path) {
        return tree.getPathBounds(path);
    }

    public Rectangle getRowBounds(int row) {
        return tree.getRowBounds(row);
    }

    public void scrollPathToVisible(TreePath path) {
        tree.scrollPathToVisible(path);
    }

    public void scrollRowToVisible(int row) {
        tree.scrollRowToVisible(row);
    }

    public TreePath getPathForRow(int row) {
        return tree.getPathForRow(row);
    }

    public int getRowForPath(TreePath path) {
        return tree.getRowForPath(path);
    }

    public void expandPath(TreePath path) {
        tree.expandPath(path);
    }

    public void expandRow(int row) {
        tree.expandRow(row);
    }

    public void collapsePath(TreePath path) {
        tree.collapsePath(path);
    }

    public void collapseRow(int row) {
        tree.collapseRow(row);
    }

    public TreePath getPathForLocation(int x, int y) {
        return tree.getPathForLocation(x, y);
    }

    public int getRowForLocation(int x, int y) {
        return tree.getRowForLocation(x, y);
    }

    public TreePath getClosestPathForLocation(int x, int y) {
        return tree.getClosestPathForLocation(x, y);
    }

    public int getClosestRowForLocation(int x, int y) {
        return tree.getClosestRowForLocation(x, y);
    }

    public boolean isEditing() {
        return tree.isEditing();
    }

    public boolean stopEditing() {
        return tree.stopEditing();
    }

    public void cancelEditing() {
        tree.cancelEditing();
    }

    public void startEditingAtPath(TreePath path) {
        tree.startEditingAtPath(path);
    }

    public TreePath getEditingPath() {
        return tree.getEditingPath();
    }

    public void setSelectionModel(TreeSelectionModel selectionModel) {
        tree.setSelectionModel(selectionModel);
    }

    public TreeSelectionModel getSelectionModel() {
        return tree.getSelectionModel();
    }

    public void setSelectionInterval(int index0, int index1) {
        tree.setSelectionInterval(index0, index1);
    }

    public void addSelectionInterval(int index0, int index1) {
        tree.addSelectionInterval(index0, index1);
    }

    public void removeSelectionInterval(int index0, int index1) {
        tree.removeSelectionInterval(index0, index1);
    }

    public void removeSelectionPath(TreePath path) {
        tree.removeSelectionPath(path);
    }

    public void removeSelectionPaths(TreePath[] paths) {
        tree.removeSelectionPaths(paths);
    }

    public void removeSelectionRow(int row) {
        tree.removeSelectionRow(row);
    }

    public void removeSelectionRows(int[] rows) {
        tree.removeSelectionRows(rows);
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

    public void treeDidChange() {
        tree.treeDidChange();
    }

    public void setVisibleRowCount(int newCount) {
        tree.setVisibleRowCount(newCount);
    }

    public int getVisibleRowCount() {
        return tree.getVisibleRowCount();
    }

    public Dimension getPreferredScrollableViewportSize() {
        return tree.getPreferredScrollableViewportSize();
    }

    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return tree.getScrollableUnitIncrement(visibleRect, orientation, direction);
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return tree.getScrollableBlockIncrement(visibleRect, orientation, direction);
    }

    public boolean getScrollableTracksViewportWidth() {
        return tree.getScrollableTracksViewportWidth();
    }

    public boolean getScrollableTracksViewportHeight() {
        return tree.getScrollableTracksViewportHeight();
    }

    public void setInheritsPopupMenu(boolean value) {
        tree.setInheritsPopupMenu(value);
    }

    public boolean getInheritsPopupMenu() {
        return tree.getInheritsPopupMenu();
    }

    public void setComponentPopupMenu(JPopupMenu popup) {
        tree.setComponentPopupMenu(popup);
    }

    public void setRequestFocusEnabled(boolean requestFocusEnabled) {
        tree.setRequestFocusEnabled(requestFocusEnabled);
    }

    public void requestFocus() {
        tree.requestFocus();
    }

    public boolean requestFocus(boolean temporary) {
        return tree.requestFocus(temporary);
    }

    public boolean requestFocusInWindow() {
        return tree.requestFocusInWindow();
    }

    public void grabFocus() {
        tree.grabFocus();
    }

    public void setVerifyInputWhenFocusTarget(boolean verifyInputWhenFocusTarget) {
        tree.setVerifyInputWhenFocusTarget(verifyInputWhenFocusTarget);
    }

    public boolean getVerifyInputWhenFocusTarget() {
        return tree.getVerifyInputWhenFocusTarget();
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

    public void registerKeyboardAction(ActionListener anAction, String aCommand, KeyStroke aKeyStroke, int aCondition) {
        tree.registerKeyboardAction(anAction, aCommand, aKeyStroke, aCondition);
    }

    public void registerKeyboardAction(ActionListener anAction, KeyStroke aKeyStroke, int aCondition) {
        tree.registerKeyboardAction(anAction, aKeyStroke, aCondition);
    }

    public KeyStroke[] getRegisteredKeyStrokes() {
        return tree.getRegisteredKeyStrokes();
    }

    public int getConditionForKeyStroke(KeyStroke aKeyStroke) {
        return tree.getConditionForKeyStroke(aKeyStroke);
    }

    public void resetKeyboardActions() {
        tree.resetKeyboardActions();
    }

    public int getBaseline(int width, int height) {
        return tree.getBaseline(width, height);
    }

    public void setVisible(boolean aFlag) {
        tree.setVisible(aFlag);
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

    public void setFont(Font font) {
        tree.setFont(font);
    }

    public String getToolTipText() {
        return tree.getToolTipText();
    }

    public Point getToolTipLocation(MouseEvent event) {
        return tree.getToolTipLocation(event);
    }

    public Point getPopupLocation(MouseEvent event) {
        return tree.getPopupLocation(event);
    }

    public void scrollRectToVisible(Rectangle aRect) {
        tree.scrollRectToVisible(aRect);
    }

    public void setAutoscrolls(boolean autoscrolls) {
        tree.setAutoscrolls(autoscrolls);
    }

    public boolean getAutoscrolls() {
        return tree.getAutoscrolls();
    }

    public Object getClientProperty(Object key) {
        return tree.getClientProperty(key);
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

    public void computeVisibleRect(Rectangle visibleRect) {
        tree.computeVisibleRect(visibleRect);
    }

    public Rectangle getVisibleRect() {
        return tree.getVisibleRect();
    }

    public void revalidate() {
        tree.revalidate();
    }

    @Override
    public JComponent getComponent() {
        return tree;
    }
}
