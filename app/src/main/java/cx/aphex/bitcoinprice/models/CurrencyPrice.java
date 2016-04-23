package cx.aphex.bitcoinprice.models;

/**
 * Created by aphex on 4/23/16.
 */

//@Generated("org.jsonschema2pojo")
public class CurrencyPrice {
    private double ask;
    private double bid;
    private double last;
    private String timestamp;
    private double volumeBtc;
    private double volumePercent;

    /**
     * @return The ask
     */
    public double getAsk() {
        return ask;
    }

    /**
     * @param ask The ask
     */
    public void setAsk(double ask) {
        this.ask = ask;
    }

    /**
     * @return The bid
     */
    public double getBid() {
        return bid;
    }

    /**
     * @param bid The bid
     */
    public void setBid(double bid) {
        this.bid = bid;
    }

    /**
     * @return The last
     */
    public double getLast() {
        return last;
    }

    /**
     * @param last The last
     */
    public void setLast(double last) {
        this.last = last;
    }

    /**
     * @return The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp The timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return The volumeBtc
     */
    public double getVolumeBtc() {
        return volumeBtc;
    }

    /**
     * @param volumeBtc The volume_btc
     */
    public void setVolumeBtc(double volumeBtc) {
        this.volumeBtc = volumeBtc;
    }

    /**
     * @return The volumePercent
     */
    public double getVolumePercent() {
        return volumePercent;
    }

    /**
     * @param volumePercent The volume_percent
     */
    public void setVolumePercent(double volumePercent) {
        this.volumePercent = volumePercent;
    }
}
