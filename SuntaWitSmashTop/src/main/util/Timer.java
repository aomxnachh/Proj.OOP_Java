package main.util;

public class Timer {
    private double duration;
    private double currentTime;
    private boolean isRunning;

    public Timer(double duration) {
        this.duration = duration;
        reset();
    }

    public void update(double deltaTime) {
        if (isRunning) {
            currentTime = Math.max(0, currentTime - deltaTime);
            if (currentTime <= 0) {
                isRunning = false;
            }
        }
    }

    public void reset() {
        currentTime = duration;
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }

    public boolean isFinished() {
        return !isRunning && currentTime <= 0;
    }

    public double getCurrentTime() {
        return currentTime;
    }
}