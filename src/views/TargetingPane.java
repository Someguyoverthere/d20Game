package views;

import java.awt.Component;
import java.awt.HeadlessException;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class TargetingPane extends JOptionPane {

	public static int singleTargetDialogue(Component parentComponent, Object message, String title, int optionType, int messageType,
			Icon icon, Object[] options, Object initialValue) throws HeadlessException {

		JOptionPane pane = new JOptionPane(message, messageType, optionType, icon, options, initialValue);

		JDialog dialog = pane.createDialog(parentComponent, title);
		dialog.show();

		if (pane.getValue() instanceof Integer)
			return ((Integer) pane.getValue()).intValue();
		return -1;
	}
}
