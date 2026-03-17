package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {

    JTextField sizeField;
    JTextArea outputArea;
    JButton seriesButton, answerButton;

    int[] arr;

    public Project() {

        setTitle("Auto Series Finder");
        setSize(650,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10,10));

        JPanel top = new JPanel();
        JLabel label = new JLabel("Enter Array Size:");
        sizeField = new JTextField(5);

        seriesButton = new JButton("Generate Series");
        answerButton = new JButton("Show Answer");

        seriesButton.addActionListener(this);
        answerButton.addActionListener(this);

        top.add(label);
        top.add(sizeField);
        top.add(seriesButton);
        top.add(answerButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced",Font.PLAIN,14));

        JScrollPane scroll = new JScrollPane(outputArea);

        panel.add(top,BorderLayout.NORTH);
        panel.add(scroll,BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {
            int size = Integer.parseInt(sizeField.getText());

            if(size < 3){
                JOptionPane.showMessageDialog(this,"Enter size >= 3");
                return;
            }

            // 🔥 Button 1 → Generate Series
            if(e.getSource() == seriesButton){

                arr = new int[size];
                arr[0] = 14;
                int diff = 11;

                for(int i=1; i<size; i++){
                    arr[i] = arr[i-1] + diff;
                    diff = diff * 2;
                }

                StringBuilder sb = new StringBuilder();

                sb.append("Series: ");
                for(int n : arr){
                    sb.append(n).append(" ");
                }

                outputArea.setText(sb.toString());
            }

            // 🔥 Button 2 → Show Answer
            if(e.getSource() == answerButton){

                if(arr == null){
                    JOptionPane.showMessageDialog(this,"First generate the series!");
                    return;
                }

                int lastDiff = arr[arr.length-1] - arr[arr.length-2];
                int nextDiff = lastDiff * 2;
                int next = arr[arr.length-1] + nextDiff;

                StringBuilder sb = new StringBuilder();

                sb.append("Series: ");
                for(int n : arr){
                    sb.append(n).append(" ");
                }

                sb.append("\n\nPattern: Doubling Difference\n");
                sb.append("Next Number: ").append(next);

                outputArea.setText(sb.toString());
            }

        } catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Enter valid number");
        }
    }

    public static void main(String[] args) {
        new Project();
    }
}