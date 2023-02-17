### MapDB
#### simple way
##### time spent in storage
* Databasedb10.db map map10 elapsedNanos = 1078453206 
* Databasedb100.db map map100 elapsedNanos = 275659723
* Databasedb1000.db map map1000 elapsedNanos = 733130504
* Databasedb10000.db map map10000 elapsedNanos = 3934030327
* Databasedb100000.db map map100000 elapsedNanos = 40747136057
* Databasedb1000000.db map map1000000 elapsedNanos = 473628725817

##### time spend in search
* Databasedb10.db map map10 e`lapsedNanos = 767811557
  * Databasedb10.db map map10 instance = Person{name='name1', age=1}
* Databasedb100.db map map100 elapsedNanos = 50518603
  * Databasedb100.db map map100 instance = Person{name='name10', age=10}
* Databasedb1000.db map map1000 elapsedNanos = 49556871
  * Databasedb1000.db map map1000 instance = Person{name='name100', age=100}
* Databasedb10000.db map map10000 elapsedNanos = 50006857
  * Databasedb10000.db map map10000 instance = Person{name='name1000', age=1000}
* Databasedb100000.db map map100000 elapsedNanos = 47894226
  * Databasedb100000.db map map100000 instance = Person{name='name10000', age=10000}
* Databasedb1000000.db map map1000000 elapsedNanos = 55479235
  * Databasedb1000000.db map map1000000 instance = Person{name='name100000', age=100000}

##### size of the db file

![](imge/mapdb.png)

##### size of mapdb jar
![](imge/mapdb-lib-size.png)

#### high performance (batchStoreForInitSize())
##### time spent in storage
* Databasedb10.db map map10 elapsedNanos = 1253454624
* Databasedb100.db map map100 elapsedNanos = 109900697
* Databasedb1000.db map map1000 elapsedNanos = 165791220
* Databasedb10000.db map map10000 elapsedNanos = 282409609
* Databasedb100000.db map map100000 elapsedNanos = 1290683473
* Databasedb1000000.db map map1000000 elapsedNanos = 8834792564
##### size of the db file
![](imge/performance-mapdb.png)
###  https://mapdb.org/book/performance/

### RocksDB
#### simple way
##### time spent in storage
* Databasedb10.bin elapsedNanos = 180960162
* Databasedb100.bin elapsedNanos = 3993881
* Databasedb1000.bin elapsedNanos = 25124662
* Databasedb10000.bin elapsedNanos = 329895281
* Databasedb100000.bin elapsedNanos = 1316623998
* Databasedb1000000.bin elapsedNanos = 9212562913

##### time spend in search
* Databasedb10.bin elapsedNanos = 16061516
  * Databasedb10.bin instance = Person{name='name1', age=1}
* Databasedb100.bin elapsedNanos = 246111
  * Databasedb100.bin instance = Person{name='name10', age=10}
* Databasedb1000.bin elapsedNanos = 257274
  * Databasedb1000.bin instance = Person{name='name100', age=100}
* Databasedb10000.bin elapsedNanos = 283932
  * Databasedb10000.bin instance = Person{name='name1000', age=1000}
* Databasedb100000.bin elapsedNanos = 228597
  * Databasedb100000.bin instance = Person{name='name10000', age=10000}
* Databasedb1000000.bin elapsedNanos = 250921
  * Databasedb1000000.bin instance = Person{name='name100000', age=100000}

##### size of the db file
###### db10
![](imge/db10.png)
###### db100
![](imge/db100.png)
###### db1000
![](imge/db1000.png)
###### db10000
![](imge/db10000.png)
###### db100000
![](imge/db100000.png)

##### size of rocks jar
![](imge/rocks-lib-size.png)

##### Multithreading
##### time spent in storage
Databaserocks/db10.bin elapsedNanos = 3996552
Databaserocks/db100.bin elapsedNanos = 19662116
Databaserocks/db1000.bin elapsedNanos = 16023367
Databaserocks/db10000.bin elapsedNanos = 19912314
Databaserocks/db100000.bin elapsedNanos = 94300606
Databaserocks/db1000000.bin elapsedNanos = 576378420

