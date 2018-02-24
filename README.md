# Spark-Test

## Purpose

At the highest level, this repository collects and tests the testing capabilities of Spark.

More specifically, this project has the following goals:

* Collect Spark testing dependencies in a clean and consistent manner.
  Unfortunately, test-jar dependencies are not transitive in Maven, so there is no way to include a single portion of the Spark project to get the basic Spark test classes for use in testing one's own spark-based code.  This library collects those dependencies in a single place, so that writers of Spark-based libraries and applications have a single point-of-dependency for their own test classes.
* Testing the testing capabilities of spark, to make sure they are exported in a sane and usable manner, and that they test necessary aspects of the system (such as serializability constraints and the like)
* Documenting how to test libraries and applications that use spark.

The following are *not* (at least at present) goals of this project):

* Writing new code to test Spark, or to facilitate testing spark-based libraries
  I believe that code should be in Spark itself.  Really, I believe this project should be in Spark itself too.  But the point is that Spark test code should be part of Spark

## Writing test that require Spark

There are two basic classes in the Spark test jars (and exposed by this library) that are essential when testing Spark-based code

* `org.apache.spark.SharedSparkContext`
* `org.apache.spark.sql.test.SharedSparkSession`

Both classes work similarly - when they are mixed in to a test class, they expose a `SparkContext` or `SparkSession` for testing, that can be used within unit tests in that class.  There are, however, a small nmber of restrictions that must be observed in order for Spark-aware tests to work properly.

### Tests cannot be run in parallel

Tests should be run in a single thread.

TODO: Make sure this is still true

#### Maven
TODO: Figure out how this is specified in Maven

#### Gradle

In gradle, this is specified in the test task:

    test {
        maxParallelForks = 1
    }

###  The `SparkContext` or `SparkSession` is _only_ available within unit tests themselves

The `SparkContext` or `SparkSession`, while defined at the class level, is not really available at the class level.  It is created in `beforeAll` and destroyed in `afterAll`.  Most significantly, this means it cannot be used to create test data outside unit tests.

The significance of this restriction varies with the style of `ScalaTest` one uses.  With `FunSuite`, it really just means one can't define data at the class level - everything within a `test{...}` clause is fine.  With `FunSpec` or other specs, it means one can only define data inside an `it{...}` clause - `describe{...}` clauses, except in included `it{...}` clauses, are still outside the actual unit test.

This is a timing constraint, though, not a scope constraint.  So if one wants something like

    private val testData = sc.parallelize(1 to 100)

One can simply change it to

    private def testData = sc.parallelize(1 to 100)

and it will work just fine.
