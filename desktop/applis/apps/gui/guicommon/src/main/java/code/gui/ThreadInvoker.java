package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractThreadFactory;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class ThreadInvoker implements Runnable {

    private final Runnable thread;
    private final AbstractProgramInfos infos;

    private ThreadInvoker(Runnable _thread, AbstractProgramInfos _infos) {
        thread = _thread;
        infos = _infos;
    }

    /**this method is thread safe because of calling CustComponent.invokeLater in an thread.
    Besides, this method execute immediately the thread after all "Event" threads.
    @param _thread the thread to invoke now for updating GUI, the "thread" parameter should not use thread sleep method
     * @param _infos*/
    public static void invokeNow(AbstractThreadFactory _facto, Runnable _thread, AbstractProgramInfos _infos) {
        ThreadInvoker thread_ = new ThreadInvoker(_thread,_infos);
        _facto.newStartedThread(thread_);
    }

    @Override
    public void run() {
        FrameUtil.invokeLater(thread, infos);
    }
}
