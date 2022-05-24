package CommonClasses;

public interface TimeConverter {
	
    static String calculateTime(long time) {
        int CONVERT_TO_SEC = 1000;
        int CONVERT_TO_OTHERS = 60;

        int ms = (int) time;
        int sec = ms / CONVERT_TO_SEC;
        int min = sec / CONVERT_TO_OTHERS; 
        int hr = min / CONVERT_TO_OTHERS; 

        if (hr == 0) {
            if (min == 0) {
                if (sec == 0) {
                	if (ms == 0) {
                		return "0 sec ";
                	}
                    return ms + " ms";
                }   
                else {
                	if (ms % 1000 == 0) {
                		return sec + " sec ";
                	}
                    return sec + " sec " + ms % 1000 + " ms";
                }
            } 
            else {
            	if (ms % CONVERT_TO_SEC == 0) {
            		return min + " min " + sec % CONVERT_TO_OTHERS + " sec "; 
            	}
                return min + " min " + sec % CONVERT_TO_OTHERS + " sec " + ms % CONVERT_TO_SEC + " ms ";
            }
        } 
        else {
        	if (ms % CONVERT_TO_SEC == 0) {
        		return hr + " hour " + min % CONVERT_TO_OTHERS + " min " + sec % CONVERT_TO_OTHERS + " sec "; 
        	}
            return hr + " hour " + min % CONVERT_TO_OTHERS + " min " + sec % CONVERT_TO_OTHERS + " sec " + ms % CONVERT_TO_SEC + " ms";
        }
    }
}
