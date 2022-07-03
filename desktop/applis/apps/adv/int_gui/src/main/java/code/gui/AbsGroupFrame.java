package code.gui;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.AbstractFileCoreStream;
import code.stream.core.TechStreams;
import code.threads.AbstractThreadFactory;
import code.util.StringMap;

public interface AbsGroupFrame extends ChangeableTitle {
    void quit();
    AbsCommonFrame getCommonFrame();
    String getApplicationName();
    void basicDispose();
    void exit();
    void init(AbstractProgramInfos _list);
    void setByFirst(AbsGroupFrame _first);
    void nativeExit();
    boolean isOpened();
    StringMap<String> getMessages();
    void setMessages(StringMap<String> _messages);
    void changeLanguage(String _language);
    AbstractGenerator getGenerator();
    void revalidateFrame();
    AbstractImageFactory getImageFactory();
    AbsCompoFactory getCompoFactory();
    AbstractThreadFactory getThreadFactory();
    AbstractInterceptor getInterceptor();
    TechStreams getStreams();
    AbstractFileCoreStream getFileCoreStream();
    AbstractNameValidating getValidator();
//    FileOpenDialog getFileOpenDialog();
//    FileSaveDialog getFileSaveDialog();
//    FolderOpenDialog getFolderOpenDialog();
//    ConfirmDialog getConfirmDialog();
//    LanguageDialog getLanguageDialog();
}
