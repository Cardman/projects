package cards.gui.panels;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import cards.consts.Status;
import cards.gui.labels.CellPlayer;
import code.gui.Panel;
import code.util.NumberMap;
import code.util.StringList;

public class MiniCarpet extends Panel {

    private boolean horaire;

    private NumberMap<Integer,CellPlayer> cellsPlayers = new NumberMap<Integer,CellPlayer>();

    public MiniCarpet(int _nombreDeJoueurs,boolean _horaire,StringList _pseudos) {
        horaire=_horaire;
        if(_nombreDeJoueurs==4) {
            setLayout(new GridLayout(0,3));
            for(int i=0;i<9;i++) {
                Panel surPanneau_=new Panel();
                surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                CellPlayer cell_ = new CellPlayer();
                cell_.setPreferredSize(new Dimension(20,10));
                if(i%2==1) {
                    Panel panneau_=new Panel();
                    panneau_.setLayout(new BorderLayout());
                    if(i==1) {
                        cell_.setTextPlayer(_pseudos.get(2));
                        cellsPlayers.put(2, cell_);
                    } else if(i==3) {
                        if(horaire) {
                            cell_.setTextPlayer(_pseudos.get(1));
                            cellsPlayers.put(1, cell_);
                        } else {
                            cell_.setTextPlayer(_pseudos.get(3));
                            cellsPlayers.put(3, cell_);
                        }
                    } else if(i==5) {
                        if(horaire) {
                            cell_.setTextPlayer(_pseudos.get(3));
                            cellsPlayers.put(3, cell_);
                        } else {
                            cell_.setTextPlayer(_pseudos.get(1));
                            cellsPlayers.put(1, cell_);
                        }
                    } else {
                        cell_.setTextPlayer(_pseudos.get(0));
                        cellsPlayers.put(0, cell_);
                    }
                    surPanneau_.add(cell_);
                } else {
                    surPanneau_.add(cell_);
                }
                add(cell_);
            }
        } else if(_nombreDeJoueurs==6) {
            setLayout(new GridLayout(0,4));
            for(int i=0;i<12;i++) {
                Panel surPanneau_=new Panel();
                Panel panneau_=new Panel();
                panneau_.setLayout(new BorderLayout());
                CellPlayer cell_ = new CellPlayer();
                cell_.setPreferredSize(new Dimension(20,5));
                if(i==1) {
                    if(horaire) {
                        cell_.setTextPlayer(_pseudos.get(2));
                        cellsPlayers.put(2, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(4));
                        cellsPlayers.put(4, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==7) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
                    if(horaire) {
                        cell_.setTextPlayer(_pseudos.get(4));
                        cellsPlayers.put(4, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(2));
                        cellsPlayers.put(2, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==4) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
                    if(horaire) {
                        cell_.setTextPlayer(_pseudos.get(1));
                        cellsPlayers.put(1, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(5));
                        cellsPlayers.put(5, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==10) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
                    if(horaire) {
                        cell_.setTextPlayer(_pseudos.get(5));
                        cellsPlayers.put(5, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(1));
                        cellsPlayers.put(1, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==2) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                    cell_.setTextPlayer(_pseudos.get(3));
                    cellsPlayers.put(3, cell_);
                    surPanneau_.add(cell_);
                } else if(i==9) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                    cell_.setTextPlayer(_pseudos.get(0));
                    cellsPlayers.put(0, cell_);
                    surPanneau_.add(cell_);
                } else {
                    surPanneau_.add(cell_);
                }
                add(cell_);
            }
        } else if(_nombreDeJoueurs==3) {
            setLayout(new GridLayout(0,3));
            for(int i=0;i<9;i++) {
                Panel surPanneau_=new Panel();
                surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                Panel panneau_=new Panel();
                panneau_.setLayout(new BorderLayout());
                CellPlayer cell_ = new CellPlayer();
                cell_.setPreferredSize(new Dimension(20,5));
                if(i==0) {
                    if(horaire) {
                        cell_.setTextPlayer(_pseudos.get(1));
                        cellsPlayers.put(1, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(2));
                        cellsPlayers.put(2, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==2) {
                    if(horaire) {
                        cell_.setTextPlayer(_pseudos.get(2));
                        cellsPlayers.put(2, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(1));
                        cellsPlayers.put(1, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==7) {
                    cell_.setTextPlayer(_pseudos.get(0));
                    cellsPlayers.put(0, cell_);
                    surPanneau_.add(cell_);
                } else if(i==4) {
                    surPanneau_.add(cell_);
                }
                add(cell_);
            }
        } else {
            setLayout(new GridLayout(0,3));
            for(int i=0;i<9;i++) {
                Panel surPanneau_=new Panel();
                Panel panneau_=new Panel();
                panneau_.setLayout(new BorderLayout());
                CellPlayer cell_ = new CellPlayer();
                cell_.setPreferredSize(new Dimension(20,5));
                if(i==0) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
                    if(horaire) {
                        cell_.setTextPlayer(_pseudos.get(2));
                        cellsPlayers.put(2, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(3));
                        cellsPlayers.put(3, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==2) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
                    if(horaire) {
                        cell_.setTextPlayer(_pseudos.get(3));
                        cellsPlayers.put(3, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(2));
                        cellsPlayers.put(2, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==3) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
                    if(horaire) {
                        cell_.setTextPlayer(_pseudos.get(1));
                        cellsPlayers.put(1, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(4));
                        cellsPlayers.put(4, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==5) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
                    if(horaire) {
                        cell_.setTextPlayer(_pseudos.get(4));
                        cellsPlayers.put(4, cell_);
                    } else {
                        cell_.setTextPlayer(_pseudos.get(1));
                        cellsPlayers.put(1, cell_);
                    }
                    surPanneau_.add(cell_);
                } else if(i==7) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                    cell_.setTextPlayer(_pseudos.get(0));
                    cellsPlayers.put(0, cell_);
                    surPanneau_.add(cell_);
                } else if(i==4) {
                    surPanneau_.add(cell_);
                }
                add(cell_);
            }
        }
        for (CellPlayer c: cellsPlayers.values()) {
            c.setStatus(Status.DEFENDER);
            c.repaint();
        }
    }

    public void setStatus(Status _st, int _player) {
        cellsPlayers.getVal(_player).setStatus(_st);
        cellsPlayers.getVal(_player).repaint();
    }
}
