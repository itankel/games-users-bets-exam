package com.ifat.bdd.final_exam.services

import java.sql.Timestamp

import com.ifat.bdd.final_exam.model.mapping.{EnrichedBetEvents, SuspiciousActivity}
import org.apache.spark.sql.{Dataset, Encoders, SparkSession}
import org.springframework.stereotype.Component

@Component
class DetectiveService(@transient sparkSession: SparkSession) extends Serializable{
  import sparkSession.implicits._

  private val NUM_HOURS = 5
  private val WIN_BET_RATION = 0.1
  private val USER_ID_COL = "userId"
  private val EVENT_COUNTRY = "eventCountry"


  def checkActivityFromDifferentLocation(enrichedBetEventsDataSet: Dataset[EnrichedBetEvents], startOn: Timestamp, endOn: Timestamp)= {
    val betweenTimesEnrichedDS = enrichedBetEventsDataSet.filter(_.inBetween(startOn, endOn)).persist()
    println("checkSuspiciousActivity between given times")
    betweenTimesEnrichedDS.show(false)
    println(" checkActivityFromCountries ")


    //for debug
    betweenTimesEnrichedDS
      .dropDuplicates(USER_ID_COL, EVENT_COUNTRY)
      .groupBy(USER_ID_COL).count()
      .filter('count > 1).show(false)

    val suspiciousActivityEncoder = Encoders.product[SuspiciousActivity]

    val suspiciousUsers = betweenTimesEnrichedDS
      .dropDuplicates(USER_ID_COL, EVENT_COUNTRY)
      .groupBy(USER_ID_COL).count()
      .filter('count > 1)
      .as[SuspiciousActivity](suspiciousActivityEncoder)
      .collect()

    println(" duplicate country array returned " + suspiciousUsers.mkString("Array(", ", ", ")"))
    suspiciousUsers
  }

  def checkSuspiciousActivity(enrichedBetEventsDataSet: Dataset[EnrichedBetEvents], startOn: Timestamp, endOn: Timestamp) = {
    val betweenTimesEnrichedDS = enrichedBetEventsDataSet.filter(_.inBetween(startOn, endOn)).persist()
    println("checkSuspiciousActivity between given times")
    betweenTimesEnrichedDS.show(false)

    val enrichedBetEventsEncoder = Encoders.product[EnrichedBetEvents]

    val suspiciousWinRateCheatingActivity=checkWinRateCheatingActivity(betweenTimesEnrichedDS)

    val suspiciousTimeCheatingActivity=checkContinuesTimeActivity(betweenTimesEnrichedDS)
    suspiciousTimeCheatingActivity.unionAll(suspiciousWinRateCheatingActivity)
    suspiciousTimeCheatingActivity.as[EnrichedBetEvents](enrichedBetEventsEncoder).collectAsList()

  }


  private def checkWinRateCheatingActivity(enrichedBetEventsDataSet: Dataset[EnrichedBetEvents])= {
    println(" checkCheatingActivity count==>" + enrichedBetEventsDataSet.filter(_.betRation(WIN_BET_RATION)).count())
    enrichedBetEventsDataSet.filter(_.betRation(WIN_BET_RATION)).show(false)
    val suspiciousEvent = enrichedBetEventsDataSet.filter(_.betRation(WIN_BET_RATION))//.collect()
    suspiciousEvent
  }

  private def checkContinuesTimeActivity(enrichedBetEventsDataSet: Dataset[EnrichedBetEvents])={
    println(" checkContinuesTimeActivity count==> " + enrichedBetEventsDataSet.filter(_.isMoreThan(5)).count())
    enrichedBetEventsDataSet.filter(_.isMoreThan(NUM_HOURS)).show(false)
    val suspiciousEvent = enrichedBetEventsDataSet.filter(_.isMoreThan(NUM_HOURS))//.collect()
    suspiciousEvent
  }

}
