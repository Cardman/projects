package code.gui;


/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SetTextPane extends Thread {

    private SessionEditorPane session;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SetTextPane(SessionEditorPane _session) {
        session = _session;
    }

    @Override
    public void run() {
//        if (setText) {
////            session.setupText(true);
////            session.setTitle();
//            session.directScroll();
//        }
        session.directScroll();
    }
}
