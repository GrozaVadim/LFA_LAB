
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private JFrame frame;
    private JTextField regularExpression;
    private JTextArea testString;
    private JTextArea matchInformation;

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Create the application.
     */
    public Controller() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        /* create a new frame */
        setFrame(new JFrame("Regular Expression"));
        getFrame().getContentPane().setBackground(new Color(217, 130, 15));
        getFrame().setBounds(600, 250, 750, 400);
        getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getFrame().getContentPane().setLayout(null);

        /* create a label to display text */
        JLabel labelRegularExpression = new JLabel("Enter your regex");
        labelRegularExpression.setBounds(37, 50, 300, 36);
        getFrame().add(labelRegularExpression);

        regularExpression = new JTextField();
        regularExpression.setBounds(37, 85, 300, 30);
        getFrame().getContentPane().add(regularExpression);
        regularExpression.setColumns(10);

        /* create a label to display text */
        JLabel labelTestString = new JLabel("Test string");
        labelTestString.setBounds(37, 120, 300, 36);
        getFrame().add(labelTestString);

        testString = new JTextArea();
        testString.setBounds(37, 155, 300, 65);
        getFrame().getContentPane().add(testString);
        testString.setColumns(10);

        /* create a label to display text */
        JLabel labelMatchInfo = new JLabel("Match information");
        labelMatchInfo.setBounds(350, -7, 216, 148);
        getFrame().add(labelMatchInfo);

        matchInformation = new JTextArea();
        matchInformation.setEditable(false);
        JScrollPane scroll = new JScrollPane(matchInformation);
        matchInformation.setBounds(350, 85, 353, 200);
        scroll.setBounds(350, 85, 356, 206);
        getFrame().getContentPane().add(scroll);

        JButton btnSubmit = new JButton("Extract the pattern");
        btnSubmit.addActionListener(this::actionButtonSubmit);

        btnSubmit.setBounds(50, 260, 150, 29);
        getFrame().getContentPane().add(btnSubmit);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(this::actionButtonClear);

        btnClear.setBounds(200, 260, 106, 29);
        getFrame().getContentPane().add(btnClear);
    }

    private void actionButtonSubmit(ActionEvent arg0) {

        String regex = regularExpression.getText();
        String str = testString.getText();
        try {
            if (!regex.isEmpty() && !str.isEmpty()) {
                /* Defines a pattern */
                final Pattern pattern;

                pattern = Pattern.compile(regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
               
                final Matcher matcher = pattern.matcher(str);
                matchInformation.setText(null);

                if (!matcher.find()) {
                    matchInformation.setForeground(Color.RED);
                    matchInformation.append("\n\nYour regular expression does not match the subject string.\n\n");
                } else {
                    matchInformation.setForeground(Color.BLUE);
                        
                    matchInformation.append("\nMatch 1 " + matcher.start() + "-" + matcher.end() + " " + matcher.group(0) + "\n");
                    int value = 2;
                    while (matcher.find()) {
                        matchInformation.append("\nMatch  " + value + " " + matcher.start() + "-" + matcher.end() + " " + matcher.group(0) + "\n");
                        value = value + 1;
                    }
                    if (value-- == 1) {
                        matchInformation.append("\n " + value + " match ");
                    } else {
                        matchInformation.append("\n " + value + " matches ");
                    }
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Error: " + exception.getMessage());
        }
    }

    private void actionButtonClear(ActionEvent arg0) {
        matchInformation.setText(null);
        regularExpression.setText(null);
        testString.setText(null);
    }

}
