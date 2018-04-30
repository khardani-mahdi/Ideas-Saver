import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Font;

public class OneIdeaExplorerFrame extends JFrame {

	private static final long serialVersionUID = -8062415339025274922L;
	private JPanel contentPane;
	private JLabel ideaLabel;
	private JLabel dateLabel;
	private JTextPane otherLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OneIdeaExplorerFrame frame = new OneIdeaExplorerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OneIdeaExplorerFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 481, 301);
		contentPane.add(scrollPane);
		
		otherLabel = new JTextPane();
		otherLabel.setEditable(false);
		scrollPane.setViewportView(otherLabel);
		ideaLabel = new JLabel("");
		ideaLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ideaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ideaLabel.setBounds(32, 29, 445, 29);
		contentPane.add(ideaLabel);
		dateLabel = new JLabel("");
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setBounds(32, 80, 445, 14);
		contentPane.add(dateLabel);
		JButton btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IdeasFrame ideasFrame=new IdeasFrame();
				ideasFrame.setLocationRelativeTo(null);
				ideasFrame.setVisible(true);
				dispose();
			}
		});
		btnClose.setBounds(402, 432, 89, 23);
		contentPane.add(btnClose);
	}
	
	public void fillLabels(Idea idea) {
		ideaLabel.setText(idea.getTitle());
		dateLabel.setText(idea.getDate());
		String otherValue=idea.getOther();
		String[] lines=otherValue.split("~");
		otherValue="";
		for(int i=0; i<lines.length; i++) {
			otherValue+=lines[i]+"\n";
		}
		otherLabel.setText(otherValue);	
	}
}
