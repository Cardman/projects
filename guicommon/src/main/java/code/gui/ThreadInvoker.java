package code.gui;
import javax.swing.SwingUtilities;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class ThreadInvoker extends Thread {

    private Runnable thread;

    private ThreadInvoker(Runnable _thread) {
        thread = _thread;
    }

    /**this method is thread safe because of calling SwingUtilities.invokeLater in an thread.
    Besides, this method execute immediately the thread after all "Event" threads.
    @param _thread the thread to invoke now for updating GUI, the "thread" parameter should not use thread sleep method*/
    public static void invokeNow(Runnable _thread) {
        ThreadInvoker thread_ = new ThreadInvoker(_thread);
        thread_.start();
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(thread);
    }
}
