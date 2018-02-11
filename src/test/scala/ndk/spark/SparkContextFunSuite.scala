package ndk.spark

import org.apache.spark.SharedSparkContext
import org.scalatest.FunSuite

class SparkContextFunSuite extends FunSuite with SharedSparkContext {
  test("standard shared spark context should work with an FunSuite") {
    assert(sc.parallelize(1 to 100).reduce(_ + _) === 5050)
  }
}
