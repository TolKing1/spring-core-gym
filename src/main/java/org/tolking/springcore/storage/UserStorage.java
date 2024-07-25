package org.tolking.springcore.storage;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.tolking.springcore.util.EntityMapperUtils.getUsername;

public class UserStorage {
    private final List<Map<String, String>> userRecords;
    private final Map<String, Integer> userCount = new HashMap<>();

    public UserStorage(ConcurrentHashMap<Long,Map<String, String>> anotherMap) {
        userRecords = Collections.synchronizedList(new ArrayList<>(anotherMap.values()));
    }

    public int getSerialNumber(Map<String, String> record){
        String username = getUsername(record);

        userRecords.add(record);
        userCount.put(username, userCount.getOrDefault(username,-1) + 1);

        return userCount.get(username);
    }


}
