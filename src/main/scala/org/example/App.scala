/**
 * @author ${user.name}
 */
package org.example
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DoubleType, IntegerType, StringType, StructField, StructType}

object EXGBoostExample extends App {
  val spark = SparkSession.builder()
    .master(master = "local[1]")
    .appName(name = "XGBoostExample")
    .getOrCreate()
  val schema = new StructType(
    Array(
      StructField("index", IntegerType, false),
      StructField("sepal length (cm)", DoubleType, false),
      StructField("sepal width (cm)", DoubleType, false),
      StructField("petal length (cm)", DoubleType, false),
      StructField("petal width (cm)", DoubleType, false),
      StructField("species", StringType, false)
    )
  )
  val rawInput = spark.read
    .schema(schema = schema)
    .option("header", true)
    .option("delimiter", ",")
    .option("quotes", "")
    .csv(path = "data/iris.csv")

  rawInput.printSchema()
  rawInput.show()
}
