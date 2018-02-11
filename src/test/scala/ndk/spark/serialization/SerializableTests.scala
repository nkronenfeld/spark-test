package ndk.spark.serialization

import org.scalatest.FunSpec
import org.apache.spark.{SharedSparkContext, SparkException}

// Tests to make sure that unserializable objects fail properly
class SerializableTests extends FunSpec with SharedSparkContext {
  describe("serialization issues") {
    it("should fail when trying to create an RDD of an unserializable type") {
      val data = sc.parallelize(Seq(
        new UnserializableData(1, "one"),
        new UnserializableData(2, "two"),
        new UnserializableData(3, "three")
      ))
      intercept[SparkException](data.count)
    }
    it("should fail when trying to run a job that requires unserialized local variables") {
      val data = sc.parallelize(Seq(
        new SerializableData(1, "one"),
        new SerializableData(2, "two"),
        new SerializableData(3, "three")
      ))
      data.count
      val processor = new UnserializableProcessor
      intercept[SparkException](processor.runUnserializableMapping(data).count)
    }
  }
}
