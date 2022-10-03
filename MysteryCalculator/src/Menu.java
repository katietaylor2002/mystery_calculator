import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;

public class Menu {
    protected JFrame frame;
    protected JFrame newFrame;
    public JLabel scoreLabel;

    public Menu() {
        //creates and opens the menu page
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Calculator");
            frame.add(new MenuPane());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public class MenuPane extends JPanel implements ActionListener {

        public MenuPane() {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            //adds title and label to frame
            add(new JLabel("<html><h1><strong><i>Mystery Calculator!</i></strong></h1><hr></br>Select maximum value:</html>"),
                    gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            //creates and adds buttons with text containing the max value on the cards for user to choose
            JButton b1 = new JButton("7");
            JButton b2 = new JButton("15");
            JButton b3 = new JButton("31");
            JButton b4 = new JButton("63");

            JPanel buttons = new JPanel(new GridBagLayout());
            buttons.add(b1, gbc);
            buttons.add(b2, gbc);
            buttons.add(b3, gbc);
            buttons.add(b4, gbc);

            //adds listeners for the buttons being clicked
            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
            b4.addActionListener(this);

            gbc.weighty = 1;
            add(buttons, gbc);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            String action = ae.getActionCommand();
            //listens for when a selection button is pressed
            if (action.equals("7") || action.equals("15") || action.equals("31") || action.equals("63")) {
                //disposes of menu frame and creates a new frame for the game
                frame.dispose();
                newFrame = new JFrame("Calculator");
                newFrame.add(new GamePane(Integer.parseInt(action)));
                newFrame.pack();
                newFrame.setLocationRelativeTo(null);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setVisible(true);
            }
        }
    }

    public class GamePane extends JPanel implements ActionListener{
        private final ArrayList<Integer> cards_with_x = new ArrayList<>();
        private final JButton [] myButtons;
        private final Game MyGame;

        public GamePane(int maxNum) {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            //adds a title label for the game frame
            scoreLabel = new JLabel(
                    "<html><h1><strong><i>Your Cards:</i></strong></h1><hr></br>Select the cards that feature your number:");
            add(scoreLabel, gbc);

            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            //creates and adds a submit button once the user has selected all the cards their number is on
            JButton button = new JButton("Submit");
            button.addActionListener(this);
            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.add(button, BorderLayout.LINE_END);
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
            add(mainPanel);

            //passes number selected on the menu frame to the game class to create a game
            MyGame = new Game(maxNum);
            JPanel buttons = new JPanel(new GridBagLayout());
            //creates and adds a button for all the cards needed
            myButtons = new JButton[MyGame.get_card_num() + 1];
            for (int i = 1; i <= MyGame.get_card_num(); i++){
                JButton btn = new JButton(Integer.toString(i));
                myButtons[i] = btn;
                JLabel lbl = new JLabel(MyGame.get_numbers(i-1));
                btn.addActionListener(this);
                buttons.add(btn, gbc);
                buttons.add(lbl, gbc);
            }
            add(buttons, gbc);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            String action = ae.getActionCommand();
            //listens to see if the submit button has been pressed
            if (action.equals("Submit")) {
                MyGame.get_cards_with_x(cards_with_x);
                //calculates the user's number using game class
                int score = MyGame.get_game_score();
                //displays the user's number
                scoreLabel.setText("<html><h1><strong>Your number is: " + score + "</html>");
            } else {
                //adds the selected card to the list of cards that contain the user's number
                int value = Integer.parseInt(action);
                cards_with_x.add(value);
                myButtons[value].setEnabled(false);
            }
        }
    }
}
