package au.com.octo

import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Dataset, Row, SparkSession}

object AndroidAppKata {

  def step1LoadDataset(sparkSession: SparkSession, csvPath: String): Dataset[Row] = {
    sparkSession
      .read
      .option("header", "true")
      .option("escape", "\"")
      .csv(csvPath)
  }

  def step2KeepOnlyCategoryEducation(dataset: Dataset[Row]): Dataset[Row] = {
    dataset
      .filter(col("Category") === "EDUCATION")
  }

  def step3MostReviewedApp(dataset: Dataset[Row]): Row = {
    dataset
      .select(col("App"), col("Reviews").cast("int"))
      .orderBy(desc("Reviews"))
      .head()
  }

  def step4NumberOfPositiveReviewsApp(apps: Dataset[Row], reviews: Dataset[Row]): Dataset[Row] = {
    reviews
      .filter(col("Sentiment") === "Positive")

      .select(col("App"), lit("1").as("value"))
      .groupBy(col("App"))
      .agg(sum(col("value")).as("nb_positives"))

      .join(apps, "App")
      .select("App", "Category", "Rating", "nb_positives")
      .orderBy(desc("nb_positives"))
      .dropDuplicates()
  }


}
