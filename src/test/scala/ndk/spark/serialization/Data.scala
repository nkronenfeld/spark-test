package ndk.spark.serialization

import org.apache.spark.rdd.RDD


// Simple data class that is NOT serializable
class UnserializableData (val a: Int, val b: String)
// Simple data class that IS serializable
class SerializableData (val a: Int, val b: String) extends Serializable
// Simple processor class that is not serializable, but that processes data that is serializable
class UnserializableProcessor {
  val multiplier = 4
  def runUnserializableMapping (data: RDD[SerializableData]): RDD[SerializableData] = {
    data.map(d => new SerializableData(d.a * multiplier, d.b))
  }
}