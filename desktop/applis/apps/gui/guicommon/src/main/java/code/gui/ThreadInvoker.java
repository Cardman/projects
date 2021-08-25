package code.gui;

import code.threads.AbstractThreadFactory;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class ThreadInvoker implements Runnable {

    private Runnable thread;

    private ThreadInvoker(Runnable _thread) {
        thread = _thread;
    }

    /**this method is thread safe because of calling CustComponent.invokeLater in an thread.
    Besides, this method execute immediately the thread after all "Event" threads.
    @param _thread the thread to invoke now for updating GUI, the "thread" parameter should not use thread sleep method*/
    public static void invokeNow(AbstractThreadFactory _facto, Runnable _thread) {
        ThreadInvoker thread_ = new ThreadInvoker(_thread);
        _facto.newStartedThread(thread_);
    }

    @Override
    public void run() {
        FrameUtil.invokeLater(thread);
    }
}
