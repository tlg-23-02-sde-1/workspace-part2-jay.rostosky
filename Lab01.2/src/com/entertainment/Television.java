package com.entertainment;

import java.util.Objects;

/*
 * Natural order is defined by 'brand' (String) and volume (int) when tied on brand.
 *
 * To be "consistent with equals," our sort key(s) must align with what was chosen
 * for equals() and hashCode().
 */
public class Television implements Comparable<Television> {
    // fields or instance variables
    private String brand;
    private int volume;

    // Television HAS-A Tuner, delegates all channel-related functionality to Tuner
    private Tuner tuner = new Tuner();  // instantiated internally, not exposed

    // constructors
    public Television() {
    }

    public Television(String brand, int volume) {
        setBrand(brand);
        setVolume(volume);
    }

    // business or "action" methods
    public int getCurrentChannel() {
        return tuner.getChannel();      // delegate to contained Tuner object
    }

    public void changeChannel(int channel) {
        tuner.setChannel(channel);      // delegate to contained Tuner object
    }

    // accessor methods
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    /*
    @Override
    public boolean equals(Object obj) {
        // if I am the same physical object as obj
        if (this == obj) return true;

        // if obj is null  OR  I and obj are not the same EXACT type
        if (obj == null || this.getClass() != obj.getClass()) return false;

        // downcast and do the checks
        Television that = (Television) obj;
        return this.getVolume() == that.getVolume() &&
               Objects.equals(this.getBrand(), that.getBrand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrand(), getVolume());
    }
    */

    @Override
    public int hashCode() {
        // this is a poorly written hash function, because it easily results in "hash collisions"
        // a "hash collision" is when DIFFERENT objects (by equals())
        // have the SAME hashcode (just by coincidence or dumb luck)
        // return getBrand().length() + getVolume();

        // we can use java.util.Objects to give us a "scientifically correct" hash function,
        // i.e., one that minimizes the probability of hash collisions
        return Objects.hash(getBrand(), getVolume());
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        // 'this' (me) and 'obj' refer to the same physical object in memory!
        if (this == obj) {
            result = true;
        }
        // 'obj' is not-null and my Class object is the same as its Class object
        else if (obj != null && (this.getClass() == obj.getClass())) {
            Television other = (Television) obj;
            result = Objects.equals(this.getBrand(), other.getBrand()) &&   // null-safe
                     this.getVolume() == other.getVolume();  // primitives can't be null
        }
        return result;
    }

    /*
     * Natural order is defined by 'brand' (String) and volume (int) when tied on brand.
     *
     * To be "consistent with equals," our sort key(s) must align with what was chosen
     * for equals() and hashCode().
     */
    @Override
    public int compareTo(Television other) {
        int result = this.getBrand().compareTo(other.getBrand());

        if (result == 0) {  // tied on brand, so break the tie by volume
            result = Integer.compare(this.getVolume(), other.getVolume());
        }

        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [brand=" + getBrand() + ", volume=" + getVolume() +
                ", currentChannel=" + getCurrentChannel() + "]";
    }
}