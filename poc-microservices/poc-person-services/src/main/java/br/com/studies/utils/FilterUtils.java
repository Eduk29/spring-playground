package br.com.studies.utils;

import br.com.studies.enums.MessagesEnum;

public abstract class FilterUtils {
    public static String getModeSearch(String filterQuery) throws RuntimeException {
        if (filterQuery != null) {
            String searchMode = filterQuery.substring(1, filterQuery.indexOf("="));
;        	return searchMode;
        }
        throw new RuntimeException(MessagesEnum.INVALID_FILTER_PARAMETERS.getDescription());
    }

    public static String getParameterSearch(String filterQuery) throws RuntimeException {
        if (filterQuery != null) {
        	
            return filterQuery.substring(filterQuery.indexOf("=") + 1, filterQuery.length() - 1);
        }
        throw new RuntimeException(MessagesEnum.INVALID_FILTER_PARAMETERS.getDescription());
    }

    public static String removeDoubleQuotes(String stringWithDoubleQuotes) {
        return stringWithDoubleQuotes.replace("\"","");
    }
}
