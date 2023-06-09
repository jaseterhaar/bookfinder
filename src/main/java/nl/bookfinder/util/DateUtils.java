package nl.bookfinder.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {
    public static String formatPublicationDate(String publicationDate) {
        if (publicationDate != null) {
        	if (publicationDate.length() >= 10) {
        		LocalDate date = LocalDate.parse(publicationDate.substring(0, 10));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("nl"));
                return date.format(formatter);
        	}
        	
        	return publicationDate;
        }
        
        return null;
    }

}