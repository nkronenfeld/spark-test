package ndk.spark

import org.apache.spark.SharedSparkContext
import org.scalatest.WordSpec

class SparkContextWordSpec extends WordSpec with SharedSparkContext {
  "a shared spark context" can {
    "work properly in a WordSpec" in {
      assert(sc.parallelize(1 to 100).reduce(_ + _) === 5050)
    }
  }
}
