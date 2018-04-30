import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 9116715672913829154L;
	private JPanel contentPane;
	private JTextField ideaTextField;
	private JTextField dateTextField;
	private DataManager dataManager;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		dataManager=new DataManager();
		setVisible(true);
		setName("mainFrame");
		setResizable(false);
		setTitle("Save your ideas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 416);
		contentPane = new JPanel();
		dateTextField = new JTextField();
		dateTextField.setToolTipText("When did it come to your mind!");
		dateTextField.setFont(new Font("Arial", Font.PLAIN, 16));
		dateTextField.setColumns(20);
		dateTextField.setBounds(136, 102, 173, 31);
		contentPane.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
				dateTextField.setText(String.valueOf(Date.valueOf(LocalDate.now())));
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel ideaLabel = new JLabel("Idea *");
		ideaLabel.setFont(new Font("Arial", Font.BOLD, 23));
		ideaLabel.setBounds(194, 14, 70, 21);
		contentPane.add(ideaLabel);
		ideaTextField = new JTextField();
		ideaTextField.setToolTipText("Main idea/Idea's title");
		ideaTextField.setFont(new Font("Arial", Font.PLAIN, 16));
		ideaTextField.setBounds(137, 42, 173, 20);
		contentPane.add(ideaTextField);
		ideaTextField.setColumns(20);
		JLabel dateLabel = new JLabel("Date *");
		dateLabel.setFont(new Font("Arial", Font.BOLD, 23));
		dateLabel.setBounds(193, 70, 70, 21);
		contentPane.add(dateLabel);
		contentPane.add(dateTextField);
		JLabel otherLabel = new JLabel("Description/Keywords :");
		otherLabel.setFont(new Font("Arial", Font.BOLD, 23));
		otherLabel.setBounds(110, 144, 281, 21);
		contentPane.add(otherLabel);
		JTextArea otherTextArea = new JTextArea();
		otherTextArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String v=otherTextArea.getText();
					v+='~';
					otherTextArea.setText(v);
				}
				
			}
		});
		otherTextArea.setFont(new Font("Arial Black", Font.ITALIC, 24));
		otherTextArea.setBounds(63, 185, 368, 128);
		JScrollPane scrollPane = new JScrollPane(otherTextArea);
		scrollPane.setBounds(63, 170, 368, 160);
		contentPane.add(scrollPane);
		JButton validateButton = new JButton("Validate");
		validateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Idea idea=null;
				if(ideaTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Please, verify your idea!");
					ideaTextField.grabFocus();
				}
				else
				{
					if(dateTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Please, verify the date!");
						dateTextField.grabFocus();
					}
					else {	
							idea=new Idea(ideaTextField.getText(), dateTextField.getText(), otherTextArea.getText());
							dataManager.insert(idea);
							dateTextField.setText("");
							ideaTextField.setText("");
							otherTextArea.setText("");
							JOptionPane.showMessageDialog(null, "Success!");
					}
				}
			}
		});
		validateButton.setBounds(63, 339, 82, 23);
		contentPane.add(validateButton);
		JButton clearButton = new JButton("Clear");
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dateTextField.setText("");
				ideaTextField.setText("");
				otherTextArea.setText("");
			}
		});
		clearButton.setBounds(276, 339, 75, 23);
		contentPane.add(clearButton);
		JButton closeButton = new JButton("Close");
		closeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		closeButton.setBounds(361, 339, 70, 23);
		contentPane.add(closeButton);
		JButton btnView = new JButton("View Ideas");
		btnView.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				IdeasFrame ideaFrame = new IdeasFrame();
				ideaFrame.setLocationRelativeTo(null);
				ideaFrame.show();
				dispose();
			}
		});
		btnView.setBounds(156, 339, 108, 23);
		contentPane.add(btnView);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{ideaTextField, dateTextField}));
	}
}