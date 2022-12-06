package code.expressionlanguage.guicompos;

public interface GuiRunnable extends Runnable {
    boolean isVisible();
    GuiContextEl getContext();
}
