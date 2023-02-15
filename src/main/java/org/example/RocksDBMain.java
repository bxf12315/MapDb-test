package org.example;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class RocksDBMain {

    public static void main(String[] args) {
//        batchStore(10, "db10.bin");
//        batchStore(100, "db100.bin");
//        batchStore(1000, "db1000.bin");
//        batchStore(10000, "db10000.bin");
//        batchStore(100000, "db100000.bin");
//        batchStore(1000000, "db1000000.bin");

        searchPersonInstance(1, "db10.bin");
        searchPersonInstance(10, "db100.bin");
        searchPersonInstance(100, "db1000.bin");
        searchPersonInstance(1000, "db10000.bin");
        searchPersonInstance(10000, "db100000.bin");
        searchPersonInstance(100000, "db1000000.bin");
    }

    static void batchStore(int size, String dbName) {
        RocksDB.loadLibrary();
        try (final Options options = new Options().setCreateIfMissing(true)) {
            try (final RocksDB db = RocksDB.open(options, dbName)) {
                long startTime = System.nanoTime();
                for (int i = 0; i < size; i++) {
                    writeValue(db, i + "", String.valueOf(new Person("name" + i, i)));
                }
                long elapsedNanos = System.nanoTime() - startTime;
                System.out.println("Database" + dbName + " elapsedNanos = " + elapsedNanos);
            } catch (Exception e) {

            }
        }
    }

    static void searchPersonInstance(int index, String dbName) {
        RocksDB.loadLibrary();
        try (final Options options = new Options().setCreateIfMissing(true)) {
            try (final RocksDB db = RocksDB.open(options, dbName)) {
                long startTime = System.nanoTime();
                String instance = readValue(db, index + "");
                long elapsedNanos = System.nanoTime() - startTime;
                System.out.println("Database" + dbName + " elapsedNanos = " + elapsedNanos);
                System.out.println("Database" + dbName + " instance = " + instance);
            } catch (Exception e) {

            }
        }
    }

    public static void writeValue(RocksDB db, String key, String value) throws RocksDBException {
        if (value != null) {
            db.put(key.getBytes(), value.getBytes());
        }
    }

    public static String readValue(RocksDB db, String key) throws RocksDBException {
        return new String(db.get(key.getBytes()));
    }
}
