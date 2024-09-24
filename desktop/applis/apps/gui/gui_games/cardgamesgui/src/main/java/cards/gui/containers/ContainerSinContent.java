package cards.gui.containers;

import cards.gui.containers.events.*;
import cards.main.*;
import code.gui.*;

public class ContainerSinContent {
    private AbsButton replayButton;
    private AbsButton stopButton;
    public void addButtonStopPlaying(ContainerSin _cont, AbsPanel _panneau, String _texte) {
        AbsButton bouton_=_cont.getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new CardsNonModalEvent(_cont),new StopPlayingEvent(_cont.window()));
        _panneau.add(bouton_);
        stopButton = bouton_;
    }
    public void addButtonReplayDeal(ContainerSin _cont, AbsPanel _panneau, String _texte) {
        AbsButton bouton_=_cont.getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new CardsNonModalEvent(_cont),new ReplayEvent(_cont));
        _panneau.add(bouton_);
        replayButton = bouton_;
    }

    public AbsButton getStopButton() {
        return stopButton;
    }

    public AbsButton getReplayButton() {
        return replayButton;
    }
}
