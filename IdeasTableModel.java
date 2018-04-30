import java.util.*;
import javax.swing.table.*;

public class IdeasTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 978907865653792358L;
	public static final int TITLE_COLUMN = 0;
	public static final int DATE_COLUMN = 1;
	public static final int OTHER_COLUMN = 2;
	private Vector<Idea> ideas = new Vector<Idea>();

	public void addIdea(Idea movie) {
		ideas.addElement(movie);
		int index = ideas.size() - 1;
		fireTableRowsInserted(index, index);
	}

	public int getRowCount() {
		return ideas.size();
	}

	public int getColumnCount() {
		return 3;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex != 0; // title is not editable
	}

	public Class<String> getColumnClass(int columnIndex) {
			return String.class;
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case TITLE_COLUMN:
			return "Title";
		case DATE_COLUMN:
			return "Date";
		case OTHER_COLUMN:
			return "Other";
		default:
			return null;
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Idea idea = (Idea) ideas.elementAt(rowIndex);
		switch (columnIndex) {
		case TITLE_COLUMN:
			return idea.getTitle();
		case DATE_COLUMN:
			return idea.getDate();
		case OTHER_COLUMN:
			return idea.getOther();
		default:
			return null;
		}
	}

	public Idea getIdeaAt(int rowIndex) {
		return ideas.elementAt(rowIndex);
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Idea idea = (Idea) ideas.elementAt(rowIndex);
		try {
			switch (columnIndex) {
			case TITLE_COLUMN:
				idea.setTitle((String) aValue);
				break;
			case DATE_COLUMN:
				idea.setDate((String)aValue);
				break;
			case OTHER_COLUMN:
				idea.setOther((String)aValue);
				break;
			}
			fireTableCellUpdated(rowIndex, columnIndex);
		} catch (ClassCastException cce) {
			cce.printStackTrace();
		}
	}
}