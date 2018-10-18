package au.com.octo

import au.com.octo.NYCPoliceKata._
import org.apache.spark.sql.SparkSession
import org.specs2.mutable.Specification
import org.specs2.specification.AfterAll

class NYCPoliceKataTest extends Specification with AfterAll {

  val csvFile = "src/test/resources/Parking_Violations_small.csv"

  implicit lazy val sparkSession: SparkSession =
    SparkSession
      .builder()
      .appName(getClass.getName)
      .master("local[*]")
      .getOrCreate()

  def afterAll: Unit = sparkSession.stop()

  "Top 10 Car Brand fined" should {
    "group number of fine by car brands at keep the top 10" in {
      val ds = top10carBrandsFined(sparkSession, csvFile)
      ds.count() must_== 10
      ds.take(10)(0).toString must_== "[TOYOT,143.0]"
      ds.take(10)(1).toString must_== "[FORD,126.0]"
    }
  }

}
