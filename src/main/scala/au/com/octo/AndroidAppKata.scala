package au.com.octo

import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Dataset, Row, SparkSession}

object AndroidAppKata {

  def step1LoadDataset(sparkSession: SparkSession, csvPath: String): Dataset[Row] = {
    // FIX-ME use the spark session to read the csv
    ???
  }

  def step2KeepOnlyCategoryEducation(dataset: Dataset[Row]): Dataset[Row] = {
    // FIX-ME filter the dataset to keep only the education EDUCATION
    ???
  }

  def step3MostReviewedApp(dataset: Dataset[Row]): Row = {
    // FIX-ME select good columns and order by number or reviews desc
    ???
  }

  def step4NumberOfPositiveReviewsApp(apps: Dataset[Row], reviews: Dataset[Row]): Dataset[Row] = {
    // FIX-ME count the number of Positive review by App, then join with the main dataset
    ???
  }

}
