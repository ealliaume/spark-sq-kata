package au.com.octo

import au.com.octo.AndroidAppKata._
import org.apache.spark.sql.SparkSession
import org.specs2.mutable.Specification
import org.specs2.specification.AfterAll

class AndroidAppKataTest extends Specification with AfterAll {

  val androidAppDatasetPath = "src/test/resources/googleplaystore.csv"
  val androidAppReviewsDatasetPath = "src/test/resources/googleplaystore_user_reviews.csv"

  implicit lazy val sparkSession: SparkSession =
    SparkSession
      .builder()
      .appName(getClass.getName)
      .master("local[*]")
      .getOrCreate()

  def afterAll: Unit = sparkSession.stop()

  "STEP1: Load the dataset" should {
    "count number of rows in the loaded dataset" in {
      step1LoadDataset(sparkSession, androidAppDatasetPath).count must_== 10841
    }
  }

  "STEP2: Get all movies from the EDUCATION Category" should {
    "count number of rows in the filtered dataset" in {
      val ds = step1LoadDataset(sparkSession, androidAppDatasetPath)
      step2KeepOnlyCategoryEducation(ds).count must_== 156
    }
  }

  "STEP3: Find the most Reviewed App" should {
    "get 1 row with the number with a App name and the number of Review" in {
      val ds = step1LoadDataset(sparkSession, androidAppDatasetPath)
      step3MostReviewedApp(ds).toString() must_== "[Facebook,78158306]"
    }
  }


  /*
    - use the "ratings" data set to compute the number of Positive reviews by app
    - join the result with the "App" dataset to get Category and Rating by App

    +--------------------+------------------+------+------------+
    |                 App|          Category|Rating|nb_positives|
    +--------------------+------------------+------+------------+
    |          Helix Jump|              GAME|   4.2|       209.0|   <-    209 = sum of positive sentiment
    |Duolingo: Learn L...|            FAMILY|   4.7|       200.0|
    |Duolingo: Learn L...|         EDUCATION|   4.7|       200.0|
    |Calorie Counter -...|HEALTH_AND_FITNESS|   4.0|       174.0|
    |Calorie Counter -...|HEALTH_AND_FITNESS|   4.6|       169.0|
    |          Bowmasters|              GAME|   4.7|       169.0|
    |10 Best Foods for...|HEALTH_AND_FITNESS|   4.0|       162.0|
      ....
   */
  "STEP4: big boss challenge - only for the coolest :)" should {
    "get 1 row with the number with a App name and the number of Review" in {
      val apps = step1LoadDataset(sparkSession, androidAppDatasetPath)
      val reviews = step1LoadDataset(sparkSession, androidAppReviewsDatasetPath)

      step4NumberOfPositiveReviewsApp(apps, reviews).head().toString() must_== "[Helix Jump,GAME,4.2,209.0]"
    }
  }

}
