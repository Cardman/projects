package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.dialogs.ConsultHosts;
import aiki.util.Coords;

public class SelectHostedPokemon extends MouseAdapter {

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
    public void mouseReleased(MouseEvent _e) {
        consult.seeHostedPokemon(first, coords);
    }
}
