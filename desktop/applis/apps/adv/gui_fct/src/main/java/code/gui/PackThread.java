package code.gui;

/**Thread safe class*/
final class PackThread implements Runnable {

    private Packable frame;

    public PackThread(Packable _frame) {
        frame = _frame;
    }

    @Override
    public void run() {
        frame.pack();
    }
}
