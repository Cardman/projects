package code.gui.initialize;

import code.gui.LoadLanguage;
import code.gui.SoftApplicationCore;
import code.gui.ThreadInvoker;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

public final class LoadLanguageUtil {
    private LoadLanguageUtil() {
    }
    public static void loadLaungage(SoftApplicationCore _soft, String _folder, String[] _args) {
        ThreadInvoker.invokeNow(_soft.getFrames().getThreadFactory(),new LoadLanguage(_soft, _folder, _args, null));
    }

    //Nul => Nul & !Nul => !Nul
    public static MutableTreeNode selected(TreePath _path) {
        if (_path == null) {
            return null;
        }
        return (MutableTreeNode)_path.getLastPathComponent();
    }
}
