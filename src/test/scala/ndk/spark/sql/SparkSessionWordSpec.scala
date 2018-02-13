package ndk.spark.sql

import org.apache.spark.sql.test.SharedSparkSession
import org.scalatest.WordSpec

class SparkSessionWordSpec extends WordSpec with SharedSparkSession {
  import testImplicits._
  "a shared spark session" can {
    "work properly in a WordSpec" in {
      assert((1 to 100).toDS.reduce(_ + _) === 5050)
    }
  }
}
