package aiki.gui.threads;
import aiki.gui.components.walk.ScenePanel;

public final class PaintingScene implements Runnable {

    private final ScenePanel scenePanel;

    public PaintingScene(ScenePanel _scenePanel) {
        scenePanel = _scenePanel;
    }

    @Override
    public void run() {
        scenePanel.setPaintingScene(true);
    }
}
