package com.syck.transactiondiagnostic.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MetaData {
    private List<Entry> entries = new ArrayList<>();

    public MetaData(String key, Object value) {
        entries.add(new Entry(key, value));
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static public class Entry {
        String key;
        Object value;
        public static Entry of (String key, Object value) {
            return new Entry(key, value);
        }
    }

    public static MetaData of (String key, Object value) {
        return new MetaData(key, value);
    }
}
