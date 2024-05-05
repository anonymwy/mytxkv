My Transactional Key Value Store(MyTxKv)
======

# How To Compile and Run 

`./gradlew clean installDist && ./build/install/mytxkv/bin/mytxkv 
`

Now you have an interactive commandline interface and just type something!

# Design Issues

* For now the enum class _TransactionalKeyValueStoreType_ has only one instance _InMemory_. And this enum can be extended together with new implementations of _ITransactionalKeyValueStore_ to support data storage solutions other than write to memory, e.g. write to local disk, write to remote storage service.

* Code under _src/main/kotlin/com/wuyangnju/mytxkv/kv_ is reusable with different types of user interface other than commandline interface, e.g. an RPC service.

* We create new ICli/ITransactionalKeyValueStore instance for each user session since currently it only runs on a single machine. It will make more sense to request data access with userId, for the server-side to create new or reuse existing session.
