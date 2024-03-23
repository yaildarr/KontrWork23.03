package org.example;

public class BroadcastsTime implements Comparable<BroadcastsTime>{
    private byte hour;
    private byte minutes;

    public BroadcastsTime(byte hour, byte minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    byte getHour(){
        return hour;
    }
    byte getMinutes(){
        return minutes;
    }
    boolean after(BroadcastsTime t){
        return this.compareTo(t) > 0;
    }
    boolean before(BroadcastsTime t){
        return this.compareTo(t) < 0;
    }
    boolean between(BroadcastsTime t1, BroadcastsTime t2){
        return true;
    }

    @Override
    public int compareTo(BroadcastsTime o) {
        if (this.hour != o.hour)
            return Byte.compare(this.hour, o.hour);
        else
            return Byte.compare(this.minutes, o.minutes);
    }
    public String toString() {
        return String.format("%02d:%02d", hour, minutes);
    }
}
