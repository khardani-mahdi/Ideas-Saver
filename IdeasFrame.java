import java.awt.EventQueue;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IdeasFrame extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1993875689460913737L;
	private Vector<Idea> dataVector;
	private DataManager dataManager;
	private JTable table;
	private JButton btnClose;
	private JButton btnDelete;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IdeasFrame frame = new IdeasFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public IdeasFrame() {
		setTitle("All your ideas");
		dataManager = new DataManager();
		dataVector=dataManager.getIdeas();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		IdeasTableModel customizedTableModel = new IdeasTableModel();
		Iterator<Idea> i = dataVector.iterator();
		while(i.hasNext()) {
			customizedTableModel.addIdea((Idea) i.next());
		}
		table = new JTable(customizedTableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 603, 382);
		contentPane.add(scrollPane);
		btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MainFrame mainWindow = new MainFrame();
				mainWindow.setLocationRelativeTo(null);
				mainWindow.setVisible(true);
				dispose();
			}
		});
		btnClose.setBounds(504, 393, 89, 23);
		contentPane.add(btnClose);
		btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow()!=-1) {
					dataManager.remove(customizedTableModel.getIdeaAt(table.getSelectedRow()));
				}
			}
		});
		btnDelete.setBounds(405, 393, 89, 23);
		contentPane.add(btnDelete);		
		JButton btnConsultIdea = new JButton("Consult Idea");
		btnConsultIdea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow()!= -1) {
				OneIdeaExplorerFrame oneIdeaExplorerFrame = new OneIdeaExplorerFrame();
				oneIdeaExplorerFrame.setLocationRelativeTo(null);
				oneIdeaExplorerFrame.setVisible(true);
				oneIdeaExplorerFrame.setTitle((String) customizedTableModel.getValueAt(table.getSelectedRow(),0));
				oneIdeaExplorerFrame.fillLabels(customizedTableModel.getIdeaAt(table.getSelectedRow()));
				dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You have to Select one row from the table!");
				}
			}
		});
		btnConsultIdea.setBounds(269, 393, 126, 23);
		contentPane.add(btnConsultIdea);
	}
}