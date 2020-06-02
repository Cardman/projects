package aiki.gui.threads;
import aiki.gui.components.walk.ScenePanel;

public final class PaintingScene implements Runnable {

    private ScenePanel scenePanel;

    private boolean enabled;

    public PaintingScene(ScenePanel _scenePanel, boolean _enabled) {
        scenePanel = _scenePanel;
        enabled = _enabled;
    }

    @Override
    public void run() {
        scenePanel.setPaintingScene(enabled);
    }
}
