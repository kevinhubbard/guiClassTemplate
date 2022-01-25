import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TfPanel extends JPanel {
	JTextField importTf = new JTextField(10);
	JTextField extendTf = new JTextField(10);
	JTextField implementTf = new JTextField(10);

	public TfPanel() {
		setBorder(BorderFactory.createEmptyBorder(22, 10, 0, 0));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(importTf);
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(extendTf);
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(implementTf);
		setBackground(Color.GRAY);
		importTf.setEnabled(false);
		extendTf.setEnabled(false);
		implementTf.setEnabled(false);
	}
}