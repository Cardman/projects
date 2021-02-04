package code.gui;
import javax.swing.ListCellRenderer;
import code.util.CustList;
import javax.swing.*;
import java.awt.Component;

public class CustSelList<T> implements ListCellRenderer {

	private CustCellRender<T> render;
	private CustList<T> list = new CustList<T>();
	private PreparedLabel label = new PreparedLabel();

    public CustCellRender<T> getRender() {
        return render;
    }

    public void setRender(CustCellRender<T> _render) {
        this.render = _render;
    }


    public CustList<T> getList() {
        return list;
    }

    public void setList(CustList<T> _list) {
        this.list = _list;
    }

	public Component getListCellRendererComponent(
       JList _ls,           
       Object _value,           
       int _index,               
       boolean _isSelected,     
       boolean _cellHasFocus)   
     {
         render.setList(list);
         render.getListCellRendererComponent(label,_index,_isSelected,_cellHasFocus);
         render.paintComponent(label);
         return label.getComponent();
     }
	 public PreparedLabel getLabel(){
		return label;
	 }
}