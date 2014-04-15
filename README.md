# GraphChi-DB

GraphChi-DB is a scalable, embedded, single-computer online graph database that can also execute similar large-scale graph computation as [GraphChi](https://github.com/graphchi).
it has been developed by [Aapo Kyrola](http://www.cs.cmu.edu/~akyrola) as part of his Ph.D. thesis.

GraphChi-DB is written in Scala, with some Java code. Generally, you need to know Scala quite well to be able to use it.

** IMPORTANT: GraphChi-DB is early release, research code. It is buggy, it has awful API, and it is provided with no guarantees.
DO NOT USE IT FOR ANYTHING IMPORTANT.  **


## License

GraphChi-DB is licensed under the Apache License, Version 2.0
Each source code file has full license information.

## Requirements

* SSD
* 8 GB of RAM is recommended. Might work with less.
* Scala 2.9

 ## Discussion group

 Please join the discussion group (shared with GraphChi users).

 http://groups.google.com/group/graphchi-discuss


 ## Publication

You can read about GraphChi-DB's design, evaluation and motivation from pre-print [GraphChi-DB: Simple Design For a Scalable Graph Database System - on Just a PC](http://arxiv.org/abs/1403.0701).

# Documentation

Not provided. We suggest you look through the examples (see below), to get the basic idea.

# Examples

## To run

Best way to explore GraphChi-DB is to load the project into an IDE (such as Eclipse or IntelliJ IDEA), and use the Scala Console. This will allow you to interactively explore the data.
You can also include GraphChi-DB easily in your Scala project.

Following JVM parameters are recommended:
```
   -Xmx5G -ea
```

**Note: ** With less than 5 gigabyte of RAM, the database may crash (silently) in an out-of-memory exception. This is because its buffers overflow, and the database cannot yet manage its own memory usage.
However, do NOT add more memory to the JVM, because GraphChi-DB uses memory mapping of the operating system to manage the data. It is better to leave as much memory for the OS to use for memory mapping.




# Notes

## Online

An added edge is immediately visible to queries and subsequent computation. That is, you can query the database while inserting new data.
There is currently no batch mode to insert edges. Still, even in the online mode you can expect to be able to insert over 100K edges per second.

## ID-mapping

GraphChi-DB maps original vertex IDs to an internal ID space. Functions that ask for "origId" expect you to provide the original vertex ID (from the source data).
Typically you need to map IDs returned by the database into original IDs. See the example applications for examples. The GraphChiDB class has two functions to map between the ID spaces:

```java
DB.originalToInternalId(origId)
DB.internalToOriginalId(internalId)
```

This mapping is awkward, but crucial for the performance of the database. See the publication for more information.



## Durability

New edges are first added to in-memory buffers. When the buffers are full, they are flushed to disk. If the computer crashes when the edges are in buffers, they are lost!
GraphChi-DB has also a durable mode which logs all edges to a file before adding them to buffers (see the configuration file). However, there is currently no method to recover after a crash.

GraphChi-DB uses a shutdown hook to flush the buffered edges to disk. It is important not to force-kill the JVM! You can also manually call

```java
DB.flushAllBuffers()

```

Again, see the example applications...
