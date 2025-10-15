package com.railsoft.utils;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface SQLQueryLoader {

    public void loadingSQLQueries(Map<String, Optional<String>> sqlQueryProperties);

    public Optional<String> fetchSQLQuery(String quryKey);

    public Set<String> getSQLQueryKeys();

    
} 