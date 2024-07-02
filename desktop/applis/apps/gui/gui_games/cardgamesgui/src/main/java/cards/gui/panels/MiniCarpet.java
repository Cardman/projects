package cards.gui.panels;


import cards.consts.Role;
import cards.gui.labels.AbsMetaLabelCard;
import cards.gui.labels.CellPlayer;
import code.gui.AbsGridConstraints;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.*;
import code.util.StringList;

public final class MiniCarpet {

    private final IntMap<CellPlayer> cellsPlayers = new IntMap<CellPlayer>();
    private final CustList<CellPlayer> cellsPlayersAll = new CustList<CellPlayer>();

    private AbsPanel container;

    private MiniCarpet() {
    }

    public static MiniCarpet newCarpet(AbstractImageFactory _fact, int _nombreDeJoueurs, boolean _horaire, StringList _pseudos, AbsCompoFactory _compoFactory) {
        MiniCarpet m_ = new MiniCarpet();
        if(_nombreDeJoueurs==2) {
            m_.container = _compoFactory.newGrid();
            for (int i = 0; i < 9; i++) {
//                AbsPanel surPanneau_= _compoFactory.newLineBox();
                popup(_compoFactory,m_,Carpet.keyTwo(i),_pseudos,(i+1) % 3 == 0);
//                CellPlayer cell_ = new CellPlayer(_compoFactory);
//                cell_.setPreferredSize(new MetaDimension(20,10));
            }
        } else if(_nombreDeJoueurs==4) {
            m_.container = _compoFactory.newGrid();
            for (int i = 0; i < 9; i++) {
//                AbsPanel surPanneau_= _compoFactory.newLineBox();
                popup(_compoFactory,m_,Carpet.keyFour(_horaire,i),_pseudos,(i+1) % 3 == 0);
//                CellPlayer cell_ = new CellPlayer(_compoFactory);
//                cell_.setPreferredSize(new MetaDimension(20,10));
            }
//            for(int i=0;i<9;i++) {
//                AbsPanel surPanneau_= _compoFactory.newLineBox();
//                CellPlayer cell_ = new CellPlayer(_compoFactory);
//                cell_.setPreferredSize(new MetaDimension(20,10));
//                if(i%2==1) {
//                    if(i==1) {
//                        cell_.setTextPlayer(_pseudos.get(2));
//                        m_.cellsPlayers.put(2, cell_);
//                    } else if(i==3) {
//                        if(m_.horaire) {
//                            cell_.setTextPlayer(_pseudos.get(1));
//                            m_.cellsPlayers.put(1, cell_);
//                        } else {
//                            cell_.setTextPlayer(_pseudos.get(3));
//                            m_.cellsPlayers.put(3, cell_);
//                        }
//                    } else if(i==5) {
//                        if(m_.horaire) {
//                            cell_.setTextPlayer(_pseudos.get(3));
//                            m_.cellsPlayers.put(3, cell_);
//                        } else {
//                            cell_.setTextPlayer(_pseudos.get(1));
//                            m_.cellsPlayers.put(1, cell_);
//                        }
//                    } else {
//                        cell_.setTextPlayer(_pseudos.get(0));
//                        m_.cellsPlayers.put(0, cell_);
//                    }
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else {
//                    surPanneau_.add(cell_.getPaintableLabel());
//                }
//                m_.container.add(cell_.getPaintableLabel());
//            }
        } else if(_nombreDeJoueurs==6) {
            m_.container = _compoFactory.newGrid();
            for (int i = 0; i < 12; i++) {
//                AbsPanel surPanneau_= _compoFactory.newLineBox();
                popup(_compoFactory,m_,keySix(_horaire,i),_pseudos,(i+1) % 4 == 0);
//                CellPlayer cell_ = new CellPlayer(_compoFactory);
//                cell_.setPreferredSize(new MetaDimension(20,10));
            }
//            for(int i=0;i<12;i++) {
//                AbsPanel surPanneau_=_compoFactory.newLineBox();
//                CellPlayer cell_ = new CellPlayer(_compoFactory);
//                cell_.setPreferredSize(new MetaDimension(20,10));
//                if(i==1) {
//                    if(m_.horaire) {
//                        cell_.setTextPlayer(_pseudos.get(2));
//                        m_.cellsPlayers.put(2, cell_);
//                    } else {
//                        cell_.setTextPlayer(_pseudos.get(4));
//                        m_.cellsPlayers.put(4, cell_);
//                    }
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else if(i==7) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(m_.horaire) {
//                        cell_.setTextPlayer(_pseudos.get(4));
//                        m_.cellsPlayers.put(4, cell_);
//                    } else {
//                        cell_.setTextPlayer(_pseudos.get(2));
//                        m_.cellsPlayers.put(2, cell_);
//                    }
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else if(i==4) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(m_.horaire) {
//                        cell_.setTextPlayer(_pseudos.get(1));
//                        m_.cellsPlayers.put(1, cell_);
//                    } else {
//                        cell_.setTextPlayer(_pseudos.get(5));
//                        m_.cellsPlayers.put(5, cell_);
//                    }
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else if(i==10) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(m_.horaire) {
//                        cell_.setTextPlayer(_pseudos.get(5));
//                        m_.cellsPlayers.put(5, cell_);
//                    } else {
//                        cell_.setTextPlayer(_pseudos.get(1));
//                        m_.cellsPlayers.put(1, cell_);
//                    }
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else if(i==2) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    cell_.setTextPlayer(_pseudos.get(3));
//                    m_.cellsPlayers.put(3, cell_);
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else if(i==9) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    cell_.setTextPlayer(_pseudos.get(0));
//                    m_.cellsPlayers.put(0, cell_);
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else {
//                    surPanneau_.add(cell_.getPaintableLabel());
//                }
//                m_.container.add(cell_.getPaintableLabel());
//            }
        } else if(_nombreDeJoueurs==3) {
            m_.container = _compoFactory.newGrid();
            for (int i = 0; i < 9; i++) {
                popup(_compoFactory,m_,Carpet.keyThree(_horaire,i),_pseudos,(i+1) % 3 == 0);
            }
//            for(int i=0;i<9;i++) {
//                AbsPanel surPanneau_;
//                surPanneau_= _compoFactory.newLineBox();
//                CellPlayer cell_ = new CellPlayer(_compoFactory);
//                cell_.setPreferredSize(new MetaDimension(20,10));
//                if(i==0) {
//                    if(m_.horaire) {
//                        cell_.setTextPlayer(_pseudos.get(1));
//                        m_.cellsPlayers.put(1, cell_);
//                    } else {
//                        cell_.setTextPlayer(_pseudos.get(2));
//                        m_.cellsPlayers.put(2, cell_);
//                    }
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else if(i==2) {
//                    if(m_.horaire) {
//                        cell_.setTextPlayer(_pseudos.get(2));
//                        m_.cellsPlayers.put(2, cell_);
//                    } else {
//                        cell_.setTextPlayer(_pseudos.get(1));
//                        m_.cellsPlayers.put(1, cell_);
//                    }
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else if(i==7) {
//                    cell_.setTextPlayer(_pseudos.get(0));
//                    m_.cellsPlayers.put(0, cell_);
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else if(i==4) {
//                    surPanneau_.add(cell_.getPaintableLabel());
//                }
//                m_.container.add(cell_.getPaintableLabel());
//            }
        } else {
            m_.container = _compoFactory.newGrid();
            for (int i = 0; i < 9; i++) {
                popup(_compoFactory,m_,Carpet.keyFive(_horaire,i),_pseudos,(i+1) % 3 == 0);
            }
//            for(int i=0;i<9;i++) {
//                AbsPanel surPanneau_=_compoFactory.newLineBox();
//                CellPlayer cell_ = new CellPlayer(_compoFactory);
//                cell_.setPreferredSize(new MetaDimension(20,10));
//                if(i==0) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(m_.horaire) {
//                        cell_.setTextPlayer(_pseudos.get(2));
//                        m_.cellsPlayers.put(2, cell_);
//                    } else {
//                        cell_.setTextPlayer(_pseudos.get(3));
//                        m_.cellsPlayers.put(3, cell_);
//                    }
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else if(i==2) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(m_.horaire) {
//                        cell_.setTextPlayer(_pseudos.get(3));
//                        m_.cellsPlayers.put(3, cell_);
//                    } else {
//                        cell_.setTextPlayer(_pseudos.get(2));
//                        m_.cellsPlayers.put(2, cell_);
//                    }
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else if(i==3) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(m_.horaire) {
//                        cell_.setTextPlayer(_pseudos.get(1));
//                        m_.cellsPlayers.put(1, cell_);
//                    } else {
//                        cell_.setTextPlayer(_pseudos.get(4));
//                        m_.cellsPlayers.put(4, cell_);
//                    }
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else if(i==5) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(m_.horaire) {
//                        cell_.setTextPlayer(_pseudos.get(4));
//                        m_.cellsPlayers.put(4, cell_);
//                    } else {
//                        cell_.setTextPlayer(_pseudos.get(1));
//                        m_.cellsPlayers.put(1, cell_);
//                    }
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else if(i==7) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    cell_.setTextPlayer(_pseudos.get(0));
//                    m_.cellsPlayers.put(0, cell_);
//                    surPanneau_.add(cell_.getPaintableLabel());
//                } else if(i==4) {
//                    surPanneau_.add(cell_.getPaintableLabel());
//                }
//                m_.container.add(cell_.getPaintableLabel());
//            }
        }
        defStat(_fact, m_);
        return m_;
    }

    private static void defStat(AbstractImageFactory _fact, MiniCarpet _m) {
        for (CellPlayer c: _m.cellsPlayers.values()) {
            c.setStatus(Role.DEFENDER);
        }
        for (CellPlayer c: _m.cellsPlayersAll) {
            AbsMetaLabelCard.paintCard(_fact,c);
        }
    }

    private static void popup(AbsCompoFactory _compoFactory, MiniCarpet _c, int _k, StringList _pseudos, boolean _rem) {
        CellPlayer cell_ = new CellPlayer(_compoFactory);
        cell_.setPreferredSize(new MetaDimension(20,10));
        if (_k >= 0) {
            cell_.setTextPlayer(_pseudos.get(_k));
            _c.cellsPlayers.put(_k, cell_);
        }
        _c.cellsPlayersAll.add(cell_);
        AbsGridConstraints cts_ = _compoFactory.newGridCts();
        if (_rem) {
            cts_.gridwidth(GuiConstants.REMAINDER);
        }
        _c.container.add(cell_.getPaintableLabel(),cts_);
    }
    public static int keySix(boolean _horaire, int _i) {
        if (_i == 7) {
            if (_horaire) {
                return 4;
            }
            return 2;
        }
        if (_i == 6) {
            return -1;
        }
        return Carpet.keySix(_horaire, _i);
    }
    public void setStatus(AbstractImageFactory _fact,Role _st, int _player) {
        cellsPlayers.getVal(_player).setStatus(_st);
        AbsMetaLabelCard.paintCard(_fact,cellsPlayers.getVal(_player));
    }

    public AbsPanel getContainer() {
        return container;
    }
}
