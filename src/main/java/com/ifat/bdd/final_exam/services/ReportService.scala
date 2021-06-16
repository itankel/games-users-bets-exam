package com.ifat.bdd.final_exam.services

import java.sql.Timestamp

import com.ifat.bdd.final_exam.model.mapping.{EnrichedBetEvents, StatisticsData, SuspiciousActivity}
import com.ifat.bdd.final_exam.repo.EnrichedBetEventsRepository
import org.springframework.stereotype.Service

import scala.collection.JavaConverters._


@Service
class ReportService(enrichedBetEventsRepository :EnrichedBetEventsRepository,filterService: FilterService
                         ,detectiveService:DetectiveService,statisticsService: StatisticsService) {

  enrichedBetEventsRepository.createEnrichedBetEventsRepositoryDS()
  private val filteredDataSet=filterService.filterByCountryAndGamePrefix()


  def reportOnSuspiciousLocation(startOn: Timestamp, endOn: Timestamp): java.util.List[SuspiciousActivity]={
    detectiveService.checkActivityFromDifferentLocation(filteredDataSet, startOn, endOn).seq.asJava

  }

  def reportOnSuspiciousActivity(startOn: Timestamp, endOn: Timestamp):java.util.List[EnrichedBetEvents]={
    detectiveService.checkSuspiciousActivity(filteredDataSet, startOn, endOn)
  }


  def reportOnAllStatistics( startOn: Timestamp, endOn: Timestamp):java.util.List[StatisticsData]={
    statisticsService.getAllGamesStatistics(filteredDataSet, startOn, endOn)
  }


  def reportOnStatisticsPerGroup(startOn: Timestamp, endOn: Timestamp, gameName:String):java.util.List[StatisticsData]={
    statisticsService.getGamesStatisticsPerGroup(filteredDataSet, startOn, endOn,gameName)
  }


}
