package com.ifat.bdd.final_exam.repo

import com.ifat.bdd.final_exam.model.loading.BetEvents
import org.apache.spark.sql.{Dataset, Encoders, SparkSession}
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component



@Component
class BetEventsRepository(sparkSession: SparkSession, @Value("${app.events.source.file}") jsonFile:String){
  import sparkSession.implicits._

  def getAllBetEvents(): Dataset[BetEvents] ={
    val eventEncoder = Encoders.product[BetEvents]
    val schema = eventEncoder.schema
    val datasetOfEvents =sparkSession.read.option("multiline", "true").schema(schema).json(jsonFile)

    println(datasetOfEvents.printSchema())
    datasetOfEvents.as[BetEvents]

  }

}
