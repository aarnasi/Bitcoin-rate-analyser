package bitcoin.types;

import java.util.Map;

/**
 * Data class to capture Bitcoin current rate from Coindesk source.
 */
public final class BitCoinCurrentValue {
    Map<String,String> time;
    String disclaimer;

    Map<String,Map<String,String>> bpi;

    public Map<String, String> getTime() {
        return time;
    }

    public void setTime(Map<String, String> time) {
        this.time = time;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public Map<String, Map<String, String>> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, Map<String, String>> bpi) {
        this.bpi = bpi;
    }

}
