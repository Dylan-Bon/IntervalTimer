public class Timer extends Thread {
    private final int INTERVAL;
    private boolean playing;

    public Timer (int interval, boolean seconds) {
        int multiplier;
        if (seconds)
            multiplier = 1000;
        else
            multiplier = 1;
        this.INTERVAL = interval * multiplier;
    }

    public void run() {
        playing = true;
        while (playing) {
            Sound sound = new Sound();
            sound.start();
            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopPlayback() {
        playing = false;
    }
}
