package code.gui;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
public class MenuBar {

    private JMenuBar menuBar = new JMenuBar();

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public JMenu add(JMenu _c) {
        return menuBar.add(_c);
    }

    public JMenu getMenu(int _index) {
        return menuBar.getMenu(_index);
    }

    public int getMenuCount() {
        return menuBar.getMenuCount();
    }

}
