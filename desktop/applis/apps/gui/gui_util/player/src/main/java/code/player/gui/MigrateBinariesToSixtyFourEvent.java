package code.player.gui;

import code.gui.events.AbsActionListener;

public final class MigrateBinariesToSixtyFourEvent implements AbsActionListener {
    private final WindowPlayer player;

    public MigrateBinariesToSixtyFourEvent(WindowPlayer _p) {
        this.player = _p;
    }

    @Override
    public void action() {
        player.migrate();
    }
}
