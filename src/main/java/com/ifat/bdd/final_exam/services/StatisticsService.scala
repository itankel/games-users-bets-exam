package com.ifat.bdd.final_exam.services

import java.sql.Timestamp

import com.ifat.bdd.final_exam.model.mapping.{EnrichedBetEvents, StatisticsData}
import org.apache.spark.sql.functions.{avg, col, max, min, when}
import org.apache.spark.sql.types.DoubleType
import org.apache.spark.sql.{Dataset, Encoders, SparkSession}
import org.springframework.stereotype.Service

@Service
class StatisticsService(@transient sparkSession: SparkSession) {

  import sparkSession.implicits._

  private val AS_USD_COL = "AsUSD"
  private val EVENT_CURRENCY_CODE_COL = "eventCurrencyCode"
  private val EUR = "EUR"
  private val BET_COL = "Bet"
  private val EURO_TO_USD_RATE = 1.1
  private val WIN_COL = "Win"
  private val BET_COL_USD = BET_COL + AS_USD_COL
  private val WIN_COL_USD = WIN_COL + AS_USD_COL
  private val PROFIT_COL_USD = "Profit" + AS_USD_COL
  private val GAME_NAME_COL = "gameName"

  private val MIN = "min"
  private val MAX = "max"
  private val AVG = "avg"


  def getAllGamesStatistics(enrichedBetEventsDataSet: Dataset[EnrichedBetEvents], startOn: Timestamp, endOn: Timestamp):java.util.List[StatisticsData]={

    val betweenTimesEnrichedDS = enrichedBetEventsDataSet.filter(_.inBetween(startOn, endOn))
    println("all statistics between given times")
    val betStatisticsData=getGamesStatistics(betweenTimesEnrichedDS,startOn,endOn)
    val statisticsEncoder= Encoders.product[StatisticsData]
    betStatisticsData.as[StatisticsData](statisticsEncoder).collectAsList()

  }

  def getGamesStatisticsPerGroup(enrichedBetEventsDataSet: Dataset[EnrichedBetEvents], startOn: Timestamp, endOn: Timestamp,gameName: String):java.util.List[StatisticsData]={
    val betweenTimesEnrichedDS = enrichedBetEventsDataSet.filter(_.inBetween(startOn, endOn)).filter(_.gameName.equals(gameName))
    println("game statistics between given times")
    val betStatisticsData=getGamesStatistics(betweenTimesEnrichedDS,startOn,endOn)
    val statisticsEncoder= Encoders.product[StatisticsData]
    betStatisticsData.as[StatisticsData](statisticsEncoder).collectAsList()

  }

  private def getGamesStatistics(enrichedBetEventsDataSet: Dataset[EnrichedBetEvents], startOn: Timestamp, endOn: Timestamp) = {

    val calculatedDataWithUSD = enrichedBetEventsDataSet.withColumn(BET_COL_USD, when(col(EVENT_CURRENCY_CODE_COL).equalTo(EUR),
      (col(BET_COL).multiply(EURO_TO_USD_RATE))).otherwise(col(BET_COL)).cast(DoubleType))
      .withColumn(WIN_COL_USD, when(col(EVENT_CURRENCY_CODE_COL).equalTo(EUR),
        (col(WIN_COL).multiply(EURO_TO_USD_RATE))).otherwise(col(WIN_COL)).cast(DoubleType))
      .withColumn(PROFIT_COL_USD, col(WIN_COL_USD).minus(col(BET_COL_USD)).cast(DoubleType))

    //for debug
    calculatedDataWithUSD.show(false)

    val betStatisticsData = calculatedDataWithUSD.groupBy(GAME_NAME_COL).
      agg(min(BET_COL_USD).alias(MIN + BET_COL_USD), max(BET_COL_USD).alias(MAX + BET_COL_USD), avg(BET_COL_USD).alias(AVG + BET_COL_USD),
        min(WIN_COL_USD).alias(MIN + WIN_COL_USD), max(WIN_COL_USD).alias(MAX + WIN_COL_USD), avg(WIN_COL_USD).alias(AVG+WIN_COL_USD),
        min(PROFIT_COL_USD).alias(MIN + PROFIT_COL_USD), max(PROFIT_COL_USD).alias(MAX + PROFIT_COL_USD), avg(PROFIT_COL_USD).alias(AVG + PROFIT_COL_USD))

    //for debug
    betStatisticsData.show(false)

    betStatisticsData
  }

}
