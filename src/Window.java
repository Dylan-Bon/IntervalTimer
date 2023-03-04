import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JPanel contentPanel;
    private Sound sound;
    private Timer timer;
    private boolean playing;

    public Window() {
        contentPanel = new JPanel();
        init();
        pack();
    }

    private void init() {
        playing = false;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Interval Timer");
        GridBagConstraints gbc = new GridBagConstraints();
        contentPanel.setLayout(new GridBagLayout());

        JLabel lblTime = new JLabel("Time:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(lblTime, gbc);

        JTextField txtTime = new JTextField("");
        txtTime.setPreferredSize(new Dimension(100, 40));
        gbc.gridx = 1;
        contentPanel.add(txtTime, gbc);

        JComboBox<String> cmbTime = new JComboBox<>(new String[]{"ms", "s"});
        gbc.gridx = 2;
        contentPanel.add(cmbTime, gbc);

        JButton btnStart = new JButton("Start");

        btnStart.addActionListener(e -> {
            if (!playing) {
                String interval = txtTime.getText();
                if (validInterval(interval)) {
                    playing = true;
                    btnStart.setText("Stop");
                    int timeUnitIndex = cmbTime.getSelectedIndex();
                    play(Integer.parseInt(interval), timeUnitIndex);
                }
            } else {
                btnStart.setText("Start");
                //timer.stop();
                timer.stopPlayback();
                playing = false;
            }
        });

        btnStart.setPreferredSize(new Dimension(100, 40));
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPanel.add(btnStart, gbc);

        add(contentPanel);

    }

    private void play(int interval, int unitIndex) {
        timer = new Timer(interval, unitIndex == 1);
        timer.start();
    }

    private boolean validInterval(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}