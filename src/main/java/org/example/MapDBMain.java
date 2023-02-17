package org.example;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

public class MapDBMain {

    public static void main(String[] args) {
        batchStore(10, "db10.db", "map10");
        batchStore(100, "db100.db", "map100");
        batchStore(1000, "db1000.db", "map1000");
        batchStore(10000, "db10000.db", "map10000");
        batchStore(100000, "db100000.db", "map100000");
        batchStore(1000000, "db1000000.db", "map1000000");

        searchPersonInstance(1, "db10.db", "map10");
        searchPersonInstance(10, "db100.db", "map100");
        searchPersonInstance(100, "db1000.db", "map1000");
        searchPersonInstance(1000, "db10000.db", "map10000");
        searchPersonInstance(10000, "db100000.db", "map100000");
        searchPersonInstance(100000, "db1000000.db", "map1000000");

        batchStoreForInitSize(10, "db10.db", "map10");
        batchStoreForInitSize(100, "db100.db", "map100");
        batchStoreForInitSize(1000, "db1000.db", "map1000");
        batchStoreForInitSize(10000, "db10000.db", "map10000");
        batchStoreForInitSize(100000, "db100000.db", "map100000");
        batchStoreForInitSize(1000000, "db1000000.db", "map1000000");

        batchStoreWithThread(10, "db10.db", "map10");
        batchStoreWithThread(100, "db100.db", "map100");
        batchStoreWithThread(1000, "db1000.db", "map1000");
        batchStoreWithThread(10000, "db10000.db", "map10000");
        batchStoreWithThread(100000, "db100000.db", "map100000");
        batchStoreWithThread(1000000, "db1000000.db", "map1000000");
    }

    static void batchStore(int size, String dbName, String mapName) {
        long startTime = System.nanoTime();
        DB db = DBMaker.fileDB(dbName).make();
        Map<String, Object> map = db.hashMap(mapName)
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        for (int i = 0; i < size; i++) {
            map.put(i + "", new Person("name" + i, i));
        }
        db.close();
        long elapsedNanos = System.nanoTime() - startTime;
        System.out.println("Database" + dbName + " map " + mapName + " elapsedNanos = " + elapsedNanos);
    }

    static void batchStoreForInitSize(int size, String dbName, String mapName) {
        long startTime = System.nanoTime();
        DB db = DBMaker.fileDB(dbName).fileMmapEnable().allocateStartSize(1024 * 1024 * 1024).allocateIncrement(1024 * 1024).make();
        Map<String, Object> map = db.hashMap(mapName)
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        for (int i = 0; i < size; i++) {
            map.put(i + "", new Person("name" + i, i));
        }
        db.close();
        long elapsedNanos = System.nanoTime() - startTime;
        System.out.println("Database" + dbName + " map " + mapName + " elapsedNanos = " + elapsedNanos);
    }

    static void batchStoreWithThread(int size, String dbName, String mapName) {
        long startTime = System.nanoTime();
        DB db = DBMaker.fileDB(dbName).fileMmapEnable().allocateStartSize(1024 * 1024 * 1024).allocateIncrement(1024 * 1024).make();
        Map<String, Object> map = db.hashMap(mapName)
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();
        final int poolSize = 100;
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        for (int i = 0; i < size; i++) {
            final AtomicInteger ai = new AtomicInteger(i);
            executor.submit(() -> {
                map.put(ai.get() + "", new Person("name" + ai.get(), ai.get()));
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        db.close();
        long elapsedNanos = System.nanoTime() - startTime;
        System.out.println("Database" + dbName + " map " + mapName + " elapsedNanos = " + elapsedNanos);
    }

    static void searchPersonInstance(int index, String dbName, String mapName) {
        long startTime = System.nanoTime();
        DB db = DBMaker.fileDB(dbName).make();
        Map<String, Object> map = db.hashMap(mapName)
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();
        Object instance = map.get(index + "");
        long elapsedNanos = System.nanoTime() - startTime;
        System.out.println("Database" + dbName + " map " + mapName + " elapsedNanos = " + elapsedNanos);

        System.out.println("Database" + dbName + " map " + mapName + " instance = " + instance);
    }
}

class Person implements java.io.Serializable {

    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }

        Person person = (Person) o;

        if (age != person.age) {
            return false;
        }
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}