package ndk.spark.sql

import org.apache.spark.sql.test.SharedSparkSession
import org.scalatest.FunSpec

class SparkSessionFunSpec extends FunSpec with SharedSparkSession {
  import testImplicits._
  describe("standard shared spark session") {
    it("should work in a FunSpec") {
      assert((1 to 100).toDS.reduce(_ + _) === 5050)
    }
  }
}
