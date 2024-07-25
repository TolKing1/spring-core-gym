package org.tolking.springcore.storage;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.tolking.springcore.util.EntityMapperUtils.getUsername;

public class UserStorage {
    private final Map<String, Integer> userCount = new HashMap<>();

    public UserStorage(ConcurrentHashMap<Long,Map<String, String>> anotherMap) {
        for (var record : anotherMap.values()){
            String username = getUsername(record);

            putCounter(username);
        }
    }

    public int getSerialNumber(Map<String, String> record){
        String username = getUsername(record);

        putCounter(username);

        return userCount.get(username);
    }

    private void putCounter(String username) {
        userCount.put(username, userCount.getOrDefault(username,-1) + 1);
    }


}
