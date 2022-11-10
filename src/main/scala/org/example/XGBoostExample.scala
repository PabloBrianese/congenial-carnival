/**
 * @author ${user.name}
 */
package org.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DoubleType, IntegerType, StringType, StructField, StructType}
import org.apache.spark.ml.feature.StringIndexer

object XGBoostExample extends App {
  val spark = SparkSession.builder()
    .master(master = "local[1]")
    .appName(name = "XGBoostExample")
    .getOrCreate()
  val schema = new StructType(
    Array(
      StructField("index", IntegerType, nullable = false),
      StructField("sepal length (cm)", DoubleType, nullable = false),
      StructField("sepal width (cm)", DoubleType, nullable = false),
      StructField("petal length (cm)", DoubleType, nullable = false),
      StructField("petal width (cm)", DoubleType, nullable = false),
      StructField("species", StringType, nullable = false)
    )
  )
  val rawInput = spark.read
    .schema(schema = schema)
    .option("header", value = true)
    .option("delimiter", ",")
    .option("quotes", "")
    .csv(path = "data/iris.csv")

  val classIndexer = new StringIndexer()
    .setInputCol("species")
    .setOutputCol("classIndex")
    .fit(rawInput)
  val labelTransformed = classIndexer.transform(rawInput).drop(colName = "species")

  labelTransformed.printSchema()
  labelTransformed.show()
}
