public class Timer extends Thread {

    private int multiplier;
    private int interval;
    private boolean playing;

    public Timer (int interval, boolean seconds) {

        int multiplier;

        if (seconds)
            multiplier = 1000;
        else
            multiplier = 1;

        this.interval = interval * multiplier;
    }

    public void run() {
        playing = true;
        while (playing) {
            Sound sound = new Sound();
            sound.start();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean stopPlayback() {
        playing = false;
        return true;
    }
}
