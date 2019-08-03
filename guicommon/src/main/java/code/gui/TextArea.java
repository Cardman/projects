package code.gui;

import javax.accessibility.AccessibleContext;
import javax.print.PrintService;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorListener;
import javax.swing.event.CaretListener;
import javax.swing.plaf.TextUI;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseEvent;
import java.awt.im.InputMethodRequests;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.beans.VetoableChangeListener;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.EventListener;
import java.util.Locale;
import java.util.Set;

public final class TextArea extends CustComponent {

    private JTextArea textArea;
    public TextArea() {
        textArea = new JTextArea();
    }
    public TextArea(int _r,int _c) {
        textArea = new JTextArea(_r,_c);
    }
    public TextArea(String _txt) {
        textArea = new JTextArea(_txt);
    }
    public TextArea(String _txt,int _r,int _c) {
        textArea = new JTextArea(_txt,_r,_c);
    }
    public String getUIClassID() {
        return textArea.getUIClassID();
    }

    public void setTabSize(int size) {
        textArea.setTabSize(size);
    }

    public int getTabSize() {
        return textArea.getTabSize();
    }

    public void setLineWrap(boolean wrap) {
        textArea.setLineWrap(wrap);
    }

    public boolean getLineWrap() {
        return textArea.getLineWrap();
    }

    public void setWrapStyleWord(boolean word) {
        textArea.setWrapStyleWord(word);
    }

    public boolean getWrapStyleWord() {
        return textArea.getWrapStyleWord();
    }

    public int getLineOfOffset(int offset) throws BadLocationException {
        return textArea.getLineOfOffset(offset);
    }

    public int getLineCount() {
        return textArea.getLineCount();
    }

    public int getLineStartOffset(int line) throws BadLocationException {
        return textArea.getLineStartOffset(line);
    }

    public int getLineEndOffset(int line) throws BadLocationException {
        return textArea.getLineEndOffset(line);
    }

    public void insert(String str, int pos) {
        textArea.insert(str, pos);
    }

    public void append(String str) {
        textArea.append(str);
    }

    public void replaceRange(String str, int start, int end) {
        textArea.replaceRange(str, start, end);
    }

    public int getRows() {
        return textArea.getRows();
    }

    public void setRows(int rows) {
        textArea.setRows(rows);
    }

    public int getColumns() {
        return textArea.getColumns();
    }

    public void setColumns(int columns) {
        textArea.setColumns(columns);
    }

    public Dimension getPreferredSize() {
        return textArea.getPreferredSize();
    }

    public void setFont(Font f) {
        textArea.setFont(f);
    }

    public boolean getScrollableTracksViewportWidth() {
        return textArea.getScrollableTracksViewportWidth();
    }

    public Dimension getPreferredScrollableViewportSize() {
        return textArea.getPreferredScrollableViewportSize();
    }

    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return textArea.getScrollableUnitIncrement(visibleRect, orientation, direction);
    }

    public AccessibleContext getAccessibleContext() {
        return textArea.getAccessibleContext();
    }

    public TextUI getUI() {
        return textArea.getUI();
    }

    public void setUI(TextUI ui) {
        textArea.setUI(ui);
    }

    public void updateUI() {
        textArea.updateUI();
    }

    public void addCaretListener(CaretListener listener) {
        textArea.addCaretListener(listener);
    }

    public void removeCaretListener(CaretListener listener) {
        textArea.removeCaretListener(listener);
    }

    public CaretListener[] getCaretListeners() {
        return textArea.getCaretListeners();
    }

    public void setDocument(Document doc) {
        textArea.setDocument(doc);
    }

    public Document getDocument() {
        return textArea.getDocument();
    }

    public void setComponentOrientation(ComponentOrientation o) {
        textArea.setComponentOrientation(o);
    }

    public Action[] getActions() {
        return textArea.getActions();
    }

    public void setMargin(Insets m) {
        textArea.setMargin(m);
    }

    public Insets getMargin() {
        return textArea.getMargin();
    }

    public void setNavigationFilter(NavigationFilter filter) {
        textArea.setNavigationFilter(filter);
    }

    public NavigationFilter getNavigationFilter() {
        return textArea.getNavigationFilter();
    }

    public void setCaret(Caret c) {
        textArea.setCaret(c);
    }

    public Highlighter getHighlighter() {
        return textArea.getHighlighter();
    }

    public void setHighlighter(Highlighter h) {
        textArea.setHighlighter(h);
    }

    public void setKeymap(Keymap map) {
        textArea.setKeymap(map);
    }

    public void setDragEnabled(boolean b) {
        textArea.setDragEnabled(b);
    }

    public boolean getDragEnabled() {
        return textArea.getDragEnabled();
    }

    public void setDropMode(DropMode dropMode) {
        textArea.setDropMode(dropMode);
    }

    public DropMode getDropMode() {
        return textArea.getDropMode();
    }

    public JTextComponent.DropLocation getDropLocation() {
        return textArea.getDropLocation();
    }

    public Keymap getKeymap() {
        return textArea.getKeymap();
    }

    public static Keymap addKeymap(String nm, Keymap parent) {
        return JTextComponent.addKeymap(nm, parent);
    }

    public static Keymap removeKeymap(String nm) {
        return JTextComponent.removeKeymap(nm);
    }

    public static Keymap getKeymap(String nm) {
        return JTextComponent.getKeymap(nm);
    }

    public static void loadKeymap(Keymap map, JTextComponent.KeyBinding[] bindings, Action[] actions) {
        JTextComponent.loadKeymap(map, bindings, actions);
    }

    public Color getCaretColor() {
        return textArea.getCaretColor();
    }

    public void setCaretColor(Color c) {
        textArea.setCaretColor(c);
    }

    public Color getSelectionColor() {
        return textArea.getSelectionColor();
    }

    public void setSelectionColor(Color c) {
        textArea.setSelectionColor(c);
    }

    public Color getSelectedTextColor() {
        return textArea.getSelectedTextColor();
    }

    public void setSelectedTextColor(Color c) {
        textArea.setSelectedTextColor(c);
    }

    public Color getDisabledTextColor() {
        return textArea.getDisabledTextColor();
    }

    public void setDisabledTextColor(Color c) {
        textArea.setDisabledTextColor(c);
    }

    public void replaceSelection(String content) {
        textArea.replaceSelection(content);
    }

    public String getText(int offs, int len) throws BadLocationException {
        return textArea.getText(offs, len);
    }

    public Rectangle modelToView(int pos) throws BadLocationException {
        return textArea.modelToView(pos);
    }

    public int viewToModel(Point pt) {
        return textArea.viewToModel(pt);
    }

    public void cut() {
        textArea.cut();
    }

    public void copy() {
        textArea.copy();
    }

    public void paste() {
        textArea.paste();
    }

    public void moveCaretPosition(int pos) {
        textArea.moveCaretPosition(pos);
    }

    public void setFocusAccelerator(char aKey) {
        textArea.setFocusAccelerator(aKey);
    }

    public char getFocusAccelerator() {
        return textArea.getFocusAccelerator();
    }

    public void read(Reader in, Object desc) throws IOException {
        textArea.read(in, desc);
    }

    public void write(Writer out) throws IOException {
        textArea.write(out);
    }

    public void removeNotify() {
        textArea.removeNotify();
    }

    public void setCaretPosition(int position) {
        textArea.setCaretPosition(position);
    }

    public void setText(String t) {
        textArea.setText(t);
    }

    public String getText() {
        return textArea.getText();
    }

    public String getSelectedText() {
        return textArea.getSelectedText();
    }

    public boolean isEditable() {
        return textArea.isEditable();
    }

    public void setEditable(boolean b) {
        textArea.setEditable(b);
    }

    public void setSelectionStart(int selectionStart) {
        textArea.setSelectionStart(selectionStart);
    }

    public void setSelectionEnd(int selectionEnd) {
        textArea.setSelectionEnd(selectionEnd);
    }

    public void select(int selectionStart, int selectionEnd) {
        textArea.select(selectionStart, selectionEnd);
    }

    public void selectAll() {
        textArea.selectAll();
    }

    public String getToolTipText(MouseEvent event) {
        return textArea.getToolTipText(event);
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return textArea.getScrollableBlockIncrement(visibleRect, orientation, direction);
    }

    public boolean getScrollableTracksViewportHeight() {
        return textArea.getScrollableTracksViewportHeight();
    }

    public InputMethodRequests getInputMethodRequests() {
        return textArea.getInputMethodRequests();
    }

    public void addInputMethodListener(InputMethodListener l) {
        textArea.addInputMethodListener(l);
    }

    public void setInheritsPopupMenu(boolean value) {
        textArea.setInheritsPopupMenu(value);
    }

    public boolean getInheritsPopupMenu() {
        return textArea.getInheritsPopupMenu();
    }

    public void setComponentPopupMenu(JPopupMenu popup) {
        textArea.setComponentPopupMenu(popup);
    }

    public JPopupMenu getComponentPopupMenu() {
        return textArea.getComponentPopupMenu();
    }

    public void update(Graphics g) {
        textArea.update(g);
    }

    public void paint(Graphics g) {
        textArea.paint(g);
    }

    public void printAll(Graphics g) {
        textArea.printAll(g);
    }

    public void print(Graphics g) {
        textArea.print(g);
    }

    public boolean isPaintingTile() {
        return textArea.isPaintingTile();
    }

    public boolean isPaintingForPrint() {
        return textArea.isPaintingForPrint();
    }

    public void setRequestFocusEnabled(boolean requestFocusEnabled) {
        textArea.setRequestFocusEnabled(requestFocusEnabled);
    }

    public boolean isRequestFocusEnabled() {
        return textArea.isRequestFocusEnabled();
    }

    public void requestFocus() {
        textArea.requestFocus();
    }

    public boolean requestFocus(boolean temporary) {
        return textArea.requestFocus(temporary);
    }

    public boolean requestFocusInWindow() {
        return textArea.requestFocusInWindow();
    }

    public void grabFocus() {
        textArea.grabFocus();
    }

    public void setVerifyInputWhenFocusTarget(boolean verifyInputWhenFocusTarget) {
        textArea.setVerifyInputWhenFocusTarget(verifyInputWhenFocusTarget);
    }

    public boolean getVerifyInputWhenFocusTarget() {
        return textArea.getVerifyInputWhenFocusTarget();
    }

    public FontMetrics getFontMetrics(Font font) {
        return textArea.getFontMetrics(font);
    }

    public void setPreferredSize(Dimension preferredSize) {
        textArea.setPreferredSize(preferredSize);
    }

    public void setMaximumSize(Dimension maximumSize) {
        textArea.setMaximumSize(maximumSize);
    }

    public void setMinimumSize(Dimension minimumSize) {
        textArea.setMinimumSize(minimumSize);
    }

    public boolean contains(int x, int y) {
        return textArea.contains(x, y);
    }

    public void setBorder(Border border) {
        textArea.setBorder(border);
    }

    public Border getBorder() {
        return textArea.getBorder();
    }

    public Insets getInsets() {
        return textArea.getInsets();
    }

    public Insets getInsets(Insets insets) {
        return textArea.getInsets(insets);
    }

    public float getAlignmentY() {
        return textArea.getAlignmentY();
    }

    public void setAlignmentY(float alignmentY) {
        textArea.setAlignmentY(alignmentY);
    }

    public float getAlignmentX() {
        return textArea.getAlignmentX();
    }

    public void setAlignmentX(float alignmentX) {
        textArea.setAlignmentX(alignmentX);
    }

    public void setInputVerifier(InputVerifier inputVerifier) {
        textArea.setInputVerifier(inputVerifier);
    }

    public InputVerifier getInputVerifier() {
        return textArea.getInputVerifier();
    }

    public Graphics getGraphics() {
        return textArea.getGraphics();
    }

    public void setDebugGraphicsOptions(int debugOptions) {
        textArea.setDebugGraphicsOptions(debugOptions);
    }

    public int getDebugGraphicsOptions() {
        return textArea.getDebugGraphicsOptions();
    }

    public void registerKeyboardAction(ActionListener anAction, String aCommand, KeyStroke aKeyStroke, int aCondition) {
        textArea.registerKeyboardAction(anAction, aCommand, aKeyStroke, aCondition);
    }

    public void registerKeyboardAction(ActionListener anAction, KeyStroke aKeyStroke, int aCondition) {
        textArea.registerKeyboardAction(anAction, aKeyStroke, aCondition);
    }

    public void unregisterKeyboardAction(KeyStroke aKeyStroke) {
        textArea.unregisterKeyboardAction(aKeyStroke);
    }

    public KeyStroke[] getRegisteredKeyStrokes() {
        return textArea.getRegisteredKeyStrokes();
    }

    public int getConditionForKeyStroke(KeyStroke aKeyStroke) {
        return textArea.getConditionForKeyStroke(aKeyStroke);
    }

    public ActionListener getActionForKeyStroke(KeyStroke aKeyStroke) {
        return textArea.getActionForKeyStroke(aKeyStroke);
    }

    public void resetKeyboardActions() {
        textArea.resetKeyboardActions();
    }

    public void setInputMap(int condition, InputMap map) {
        textArea.setInputMap(condition, map);
    }

    public InputMap getInputMap(int condition) {
        return textArea.getInputMap(condition);
    }

    public InputMap getInputMap() {
        return textArea.getInputMap();
    }

    public void setActionMap(ActionMap am) {
        textArea.setActionMap(am);
    }

    public ActionMap getActionMap() {
        return textArea.getActionMap();
    }

    public int getBaseline(int width, int height) {
        return textArea.getBaseline(width, height);
    }

    public Component.BaselineResizeBehavior getBaselineResizeBehavior() {
        return textArea.getBaselineResizeBehavior();
    }

    public void setVisible(boolean aFlag) {
        textArea.setVisible(aFlag);
    }

    public void setEnabled(boolean enabled) {
        textArea.setEnabled(enabled);
    }

    public void setForeground(Color fg) {
        textArea.setForeground(fg);
    }

    public void setBackground(Color bg) {
        textArea.setBackground(bg);
    }

    public static Locale getDefaultLocale() {
        return JComponent.getDefaultLocale();
    }

    public static void setDefaultLocale(Locale l) {
        JComponent.setDefaultLocale(l);
    }

    public void setToolTipText(String text) {
        textArea.setToolTipText(text);
    }

    public String getToolTipText() {
        return textArea.getToolTipText();
    }

    public Point getToolTipLocation(MouseEvent event) {
        return textArea.getToolTipLocation(event);
    }

    public Point getPopupLocation(MouseEvent event) {
        return textArea.getPopupLocation(event);
    }

    public JToolTip createToolTip() {
        return textArea.createToolTip();
    }

    public void scrollRectToVisible(Rectangle aRect) {
        textArea.scrollRectToVisible(aRect);
    }

    public void setAutoscrolls(boolean autoscrolls) {
        textArea.setAutoscrolls(autoscrolls);
    }

    public boolean getAutoscrolls() {
        return textArea.getAutoscrolls();
    }

    public void setTransferHandler(TransferHandler newHandler) {
        textArea.setTransferHandler(newHandler);
    }

    public TransferHandler getTransferHandler() {
        return textArea.getTransferHandler();
    }

    public Object getClientProperty(Object key) {
        return textArea.getClientProperty(key);
    }

    public void putClientProperty(Object key, Object value) {
        textArea.putClientProperty(key, value);
    }

    public void setFocusTraversalKeys(int id, Set<? extends AWTKeyStroke> keystrokes) {
        textArea.setFocusTraversalKeys(id, keystrokes);
    }

    public static boolean isLightweightComponent(Component c) {
        return JComponent.isLightweightComponent(c);
    }

    public Rectangle getBounds(Rectangle rv) {
        return textArea.getBounds(rv);
    }

    public Dimension getSize(Dimension rv) {
        return textArea.getSize(rv);
    }

    public Point getLocation(Point rv) {
        return textArea.getLocation(rv);
    }

    public int getX() {
        return textArea.getX();
    }

    public int getY() {
        return textArea.getY();
    }

    @Override
    public JComponent getComponent() {
        return textArea;
    }

    public int getWidth() {
        return textArea.getWidth();
    }

    public int getHeight() {
        return textArea.getHeight();
    }

    public boolean isOpaque() {
        return textArea.isOpaque();
    }

    public void setOpaque(boolean isOpaque) {
        textArea.setOpaque(isOpaque);
    }

    public void computeVisibleRect(Rectangle visibleRect) {
        textArea.computeVisibleRect(visibleRect);
    }

    public Rectangle getVisibleRect() {
        return textArea.getVisibleRect();
    }

    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
        textArea.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void firePropertyChange(String propertyName, int oldValue, int newValue) {
        textArea.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void firePropertyChange(String propertyName, char oldValue, char newValue) {
        textArea.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        textArea.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        textArea.removeVetoableChangeListener(listener);
    }

    public VetoableChangeListener[] getVetoableChangeListeners() {
        return textArea.getVetoableChangeListeners();
    }

    public Container getTopLevelAncestor() {
        return textArea.getTopLevelAncestor();
    }

    public void addAncestorListener(AncestorListener listener) {
        textArea.addAncestorListener(listener);
    }

    public void removeAncestorListener(AncestorListener listener) {
        textArea.removeAncestorListener(listener);
    }

    public AncestorListener[] getAncestorListeners() {
        return textArea.getAncestorListeners();
    }

    public void addNotify() {
        textArea.addNotify();
    }

    public void repaint(long tm, int x, int y, int width, int height) {
        textArea.repaint(tm, x, y, width, height);
    }

    public void repaint(Rectangle r) {
        textArea.repaint(r);
    }

    public void revalidate() {
        textArea.revalidate();
    }

    public boolean isValidateRoot() {
        return textArea.isValidateRoot();
    }

    public boolean isOptimizedDrawingEnabled() {
        return textArea.isOptimizedDrawingEnabled();
    }

    public void paintImmediately(int x, int y, int w, int h) {
        textArea.paintImmediately(x, y, w, h);
    }

    public void paintImmediately(Rectangle r) {
        textArea.paintImmediately(r);
    }

    public void setDoubleBuffered(boolean aFlag) {
        textArea.setDoubleBuffered(aFlag);
    }

    public boolean isDoubleBuffered() {
        return textArea.isDoubleBuffered();
    }

    public JRootPane getRootPane() {
        return textArea.getRootPane();
    }

    public int getCaretPosition() {
        return textArea.getCaretPosition();
    }
}
