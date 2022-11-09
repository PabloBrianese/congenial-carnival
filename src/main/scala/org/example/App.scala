package org.example
import org.apache.spark.sql.SparkSession

/**
 * @author ${user.name}
 */
object SparkExample extends App {

  val spark = SparkSession.builder()
    .master("local[1]")
    .appName("SparkExample")
    .getOrCreate()
  
  println("First SparkContext")
  println("APP Name :" + spark.sparkContext.appName)
  println("Deploy Mode :" + spark.sparkContext.deployMode)
  println("Master :" + spark.sparkContext.master)
}
