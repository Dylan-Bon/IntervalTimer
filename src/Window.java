import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private final JPanel CONTENT_PANEL;
    private Timer timer;
    private boolean playing;

    public Window() {
        CONTENT_PANEL = new JPanel();
        init();
        pack();
    }

    private void init() {
        playing = false;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Interval Timer");
        GridBagConstraints gbc = new GridBagConstraints();
        CONTENT_PANEL.setLayout(new GridBagLayout());

        JLabel lblTime = new JLabel("Time:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        CONTENT_PANEL.add(lblTime, gbc);

        JTextField txtTime = new JTextField("");
        txtTime.setPreferredSize(new Dimension(100, 40));
        gbc.gridx = 1;
        CONTENT_PANEL.add(txtTime, gbc);

        JComboBox<String> cmbTime = new JComboBox<>(new String[]{"ms", "s"});
        gbc.gridx = 2;
        CONTENT_PANEL.add(cmbTime, gbc);

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
        CONTENT_PANEL.add(btnStart, gbc);
        add(CONTENT_PANEL);
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
        return Integer.parseInt(text) > 0;
    }
}