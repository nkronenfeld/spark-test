package ndk.spark.sql

import org.apache.spark.sql.test.SharedSparkSession
import org.scalatest.FunSuite

class SparkSessionFunSuite extends FunSuite with SharedSparkSession{
  import testImplicits._
  test("standard shared spark session should work with an FunSuite") {
    assert((1 to 100).toDS.reduce(_ + _) === 5050)
  }
}
