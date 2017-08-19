package code.gui;
import javax.swing.JFrame;

import code.util.StringMap;
import code.util.consts.Constants;
import code.xml.util.ExtractFromFiles;

public abstract class CommonFrame extends JFrame {

    private String accessFile;

    public void revalidate() {
        synchronized (getTreeLock()) {
            invalidate();
            validate();
        }
    }
    
    protected StringMap<String> getMessages(String _messageFolder) {
        return ExtractFromFiles.getMessagesFromLocaleClass(_messageFolder, Constants.getLanguage(), accessFile);
    }

    protected void setAccessFile(String _accessFile) {
        accessFile = _accessFile;
    }

}
