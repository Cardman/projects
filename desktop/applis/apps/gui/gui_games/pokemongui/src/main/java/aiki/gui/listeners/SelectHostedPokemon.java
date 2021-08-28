package aiki.gui.listeners;

import aiki.gui.dialogs.ConsultHosts;
import aiki.util.Coords;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class SelectHostedPokemon implements AbsActionListener {

    private ConsultHosts consult;

    private boolean first;

    private Coords coords;

    public SelectHostedPokemon(ConsultHosts _consult,
            boolean _first, Coords _coords) {
        consult = _consult;
        first = _first;
        coords = _coords;
    }

    @Override
    public void action() {
        consult.seeHostedPokemon(first, coords);
    }
}
