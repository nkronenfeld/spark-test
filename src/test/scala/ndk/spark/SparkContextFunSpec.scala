package ndk.spark

import org.apache.spark.SharedSparkContext
import org.scalatest.FunSpec

class SparkContextFunSpec extends FunSpec with SharedSparkContext {
  describe("standard shared spark context") {
    it("should work in a FunSpec") {
      assert(sc.parallelize(1 to 100).reduce(_ + _) === 5050)
    }
  }
}
