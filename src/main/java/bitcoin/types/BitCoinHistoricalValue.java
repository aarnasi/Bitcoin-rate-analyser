package bitcoin.types;

import java.util.Map;

/**
 * Data class to capture Bitcoin historical rates from Coindesk source.
 */
public final class BitCoinHistoricalValue {
    Map<String, Double> bpi;
    String disclaimer;
    Map<String, String> time;

    public Map<String, Double> getBpi() {
          return bpi;
     }

     public void setBpi(Map<String, Double> bpi) {
          this.bpi = bpi;
     }

     public String getDisclaimer() {
          return disclaimer;
     }

     public void setDisclaimer(String disclaimer) {
          this.disclaimer = disclaimer;
     }

     public Map<String, String> getTime() {
          return time;
     }

     public void setTime(Map<String, String> time) {
          this.time = time;
     }
}
