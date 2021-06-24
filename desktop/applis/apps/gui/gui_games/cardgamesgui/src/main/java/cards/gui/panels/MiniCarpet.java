package cards.gui.panels;
import java.awt.Dimension;

import cards.consts.Role;
import cards.gui.labels.CellPlayer;
import code.gui.Panel;
import code.gui.images.AbstractImageFactory;
import code.util.*;
import code.util.StringList;

public final class MiniCarpet {

    private boolean horaire;

    private final IntMap<CellPlayer> cellsPlayers = new IntMap<CellPlayer>();

    private Panel container;

    private MiniCarpet() {
    }

    public static MiniCarpet newCarpet(AbstractImageFactory _fact, int _nombreDeJoueurs, boolean _horaire, StringList _pseudos) {
        MiniCarpet m_ = new MiniCarpet();
        m_.horaire=_horaire;
        if(_nombreDeJoueurs==4) {
            m_.container = Panel.newGrid(0,3);
            for(int i=0;i<9;i++) {
                Panel surPanneau_= Panel.newLineBox();
                CellPlayer cell_ = new CellPlayer();
                cell_.setPreferredSize(new Dimension(20,10));
                if(i%2==1) {
                    if(i==1) {
                        cell_.setTextPlayer(_pseudos.get(2));
                        m_.cellsPlayers.put(2, cell_);
                    } else if(i==3) {
                        if(m_.horaire) {
                            cell_.setTextPlayer(_pseudos.get(1));
                            m_.cellsPlayers.put(1, cell_);
                        } else {
                            cell_.setTextPlayer(_pseudos.get(3));
                            m_.cellsPlayers.put(3, cell_);
                        }
                    } else if(i==5) {
                        if(m_.horaire) {
                            cell_.setTextPlayer(_pseudos.get(3));
                            m_.cellsPlayers.put(3, cell_);
                        } else {
                            cell_.setTextPlayer(_pseudos.get(1));
                            m_.cellsPlayers.put(1, cell_);
                        }
                    } else {
                        cell_.setTextPlayer(_pseudos.get(0));
                        m_.cellsPlayers.put(0, cell_);
                    }
                    surPanneau_.add(cell_);
                } else {
                    surPanneau_.add(cell_);
                }
                m_.container.add(cell_);
            }
        } else if(_nombreDeJoueurs==6) {
            m_.container = Panel.newGrid(0,4);
            for(int i=0;i<12;i++) {
                Panel surPanneau_=Panel.newLineBox();
                CellPlayer cell_ = new CellPlayer();
                cell_.setPreferredSize(new Dimension(20,10));
                if(i==1) {
                    if(m_.horaire) {
                        cell_.setTextPlayer(_pseudos.get(2));
                        m_.cellsPlayers.put(2, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(4));
                        m_.cellsPlayers.put(4, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==7) {
                    surPanneau_= Panel.newLineBox();
                    if(m_.horaire) {
                        cell_.setTextPlayer(_pseudos.get(4));
                        m_.cellsPlayers.put(4, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(2));
                        m_.cellsPlayers.put(2, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==4) {
                    surPanneau_= Panel.newLineBox();
                    if(m_.horaire) {
                        cell_.setTextPlayer(_pseudos.get(1));
                        m_.cellsPlayers.put(1, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(5));
                        m_.cellsPlayers.put(5, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==10) {
                    surPanneau_= Panel.newLineBox();
                    if(m_.horaire) {
                        cell_.setTextPlayer(_pseudos.get(5));
                        m_.cellsPlayers.put(5, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(1));
                        m_.cellsPlayers.put(1, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==2) {
                    surPanneau_= Panel.newLineBox();
                    cell_.setTextPlayer(_pseudos.get(3));
                    m_.cellsPlayers.put(3, cell_);
                    surPanneau_.add(cell_);
                } else if(i==9) {
                    surPanneau_= Panel.newLineBox();
                    cell_.setTextPlayer(_pseudos.get(0));
                    m_.cellsPlayers.put(0, cell_);
                    surPanneau_.add(cell_);
                } else {
                    surPanneau_.add(cell_);
                }
                m_.container.add(cell_);
            }
        } else if(_nombreDeJoueurs==3) {
            m_.container = Panel.newGrid(0,3);
            for(int i=0;i<9;i++) {
                Panel surPanneau_;
                surPanneau_= Panel.newLineBox();
                CellPlayer cell_ = new CellPlayer();
                cell_.setPreferredSize(new Dimension(20,10));
                if(i==0) {
                    if(m_.horaire) {
                        cell_.setTextPlayer(_pseudos.get(1));
                        m_.cellsPlayers.put(1, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(2));
                        m_.cellsPlayers.put(2, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==2) {
                    if(m_.horaire) {
                        cell_.setTextPlayer(_pseudos.get(2));
                        m_.cellsPlayers.put(2, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(1));
                        m_.cellsPlayers.put(1, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==7) {
                    cell_.setTextPlayer(_pseudos.get(0));
                    m_.cellsPlayers.put(0, cell_);
                    surPanneau_.add(cell_);
                } else if(i==4) {
                    surPanneau_.add(cell_);
                }
                m_.container.add(cell_);
            }
        } else {
            m_.container = Panel.newGrid(0,3);
            for(int i=0;i<9;i++) {
                Panel surPanneau_=Panel.newLineBox();
                CellPlayer cell_ = new CellPlayer();
                cell_.setPreferredSize(new Dimension(20,10));
                if(i==0) {
                    surPanneau_= Panel.newLineBox();
                    if(m_.horaire) {
                        cell_.setTextPlayer(_pseudos.get(2));
                        m_.cellsPlayers.put(2, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(3));
                        m_.cellsPlayers.put(3, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==2) {
                    surPanneau_= Panel.newLineBox();
                    if(m_.horaire) {
                        cell_.setTextPlayer(_pseudos.get(3));
                        m_.cellsPlayers.put(3, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(2));
                        m_.cellsPlayers.put(2, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==3) {
                    surPanneau_= Panel.newLineBox();
                    if(m_.horaire) {
                        cell_.setTextPlayer(_pseudos.get(1));
                        m_.cellsPlayers.put(1, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(4));
                        m_.cellsPlayers.put(4, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==5) {
                    surPanneau_= Panel.newLineBox();
                    if(m_.horaire) {
                        cell_.setTextPlayer(_pseudos.get(4));
                        m_.cellsPlayers.put(4, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(1));
                        m_.cellsPlayers.put(1, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==7) {
                    surPanneau_= Panel.newLineBox();
                    cell_.setTextPlayer(_pseudos.get(0));
                    m_.cellsPlayers.put(0, cell_);
                    surPanneau_.add(cell_);
                } else if(i==4) {
                    surPanneau_.add(cell_);
                }
                m_.container.add(cell_);
            }
        }
        for (CellPlayer c: m_.cellsPlayers.values()) {
            c.setStatus(Role.DEFENDER);
            c.repaintLabel(_fact);
        }
        return m_;
    }

    public void setStatus(AbstractImageFactory _fact,Role _st, int _player) {
        cellsPlayers.getVal(_player).setStatus(_st);
        cellsPlayers.getVal(_player).repaintLabel(_fact);
    }

    public Panel getContainer() {
        return container;
    }
}
