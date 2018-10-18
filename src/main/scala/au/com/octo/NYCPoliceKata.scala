package au.com.octo

import org.apache.spark.SparkConf
import org.apache.spark.sql.SaveMode.Overwrite
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Dataset, Row, SparkSession}

object NYCPoliceKata {

  val inputCsv = "s3://era-bigdata-demo/datasets/nyc/Parking_Violations_all.csv"
  val outputCsv = "s3://era-bigdata-demo/results/la_vie_est_belle.csv"

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession
      .builder()
      .config(new SparkConf())
      .getOrCreate()

    try {
      val ds = top10carBrandsFined(sparkSession, inputCsv)

      ds.show() // you will need to find this in the logs

      ds
        .write
        .option("header", "true")
        .option("escape", "\"")
        .mode(Overwrite)
        .csv(outputCsv)

    } finally {
      sparkSession.stop()
    }
  }

  def top10carBrandsFined(sparkSession: SparkSession, csvPath: String): Dataset[Row] = {
    sparkSession
      .read
      .option("header", "true")
      .option("escape", "\"")
      .csv(csvPath)

      .select(col("Vehicle Make"), lit("1").as("value"))
      .groupBy(col("Vehicle Make"))
      .agg(sum(col("value")).as("nb_fines"))
      .orderBy(desc("nb_fines"), asc("Vehicle Make"))
      .limit(10)
  }

}
