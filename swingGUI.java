import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class swingGUI {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				constructGUI();
			}
		});

	}

	public static void constructGUI() {
		JTextField firstNum = new JTextField();
		JTextField secNum = new JTextField();
		JLabel finalResult = new JLabel();

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame();

		frame.setTitle("Simple Calculator");
		frame.setLayout(new GridLayout(5, 2));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new JLabel("First Number:"));
		frame.add(firstNum);
		frame.add(new JLabel("Second Number:"));
		frame.add(secNum);
		frame.add(new JLabel());
		String[] selections = { "Add", "Subtract", "Multiply", "Divide" };
		JComboBox comboBox = new JComboBox(selections);
		String boxValue = (String) comboBox.getSelectedItem();
		comboBox.setSelectedIndex(0);
		frame.add(comboBox);
		frame.add(new JLabel());
		JButton button = new JButton("Calculate");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comboBox.getSelectedItem().equals("Add")) {
					double result = (Double.parseDouble(firstNum.getText())) + (Double.parseDouble(secNum.getText()));
					finalResult.setText(Double.toString(result));
				} else if (comboBox.getSelectedItem().equals("Subtract")) {
					double result = (Double.parseDouble(firstNum.getText())) - (Double.parseDouble(secNum.getText()));
					finalResult.setText(Double.toString(result));

				} else if (comboBox.getSelectedItem().equals("Multiply")) {
					double result = (Double.parseDouble(firstNum.getText())) * (Double.parseDouble(secNum.getText()));
					finalResult.setText(Double.toString(result));

				} else if (comboBox.getSelectedItem().equals("Divide")) {
					double result = (Double.parseDouble(firstNum.getText())) / (Double.parseDouble(secNum.getText()));
					finalResult.setText(Double.toString(result));

				}

			}
		});

		frame.getContentPane().add(button);
		frame.add(new JLabel("Result: "));
		frame.add(finalResult);

		frame.pack();
		frame.setVisible(true);
	}
}
