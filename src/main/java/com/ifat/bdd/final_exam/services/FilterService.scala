package com.ifat.bdd.final_exam.services

import com.ifat.bdd.final_exam.model.mapping.EnrichedBetEvents
import com.ifat.bdd.final_exam.repo.EnrichedBetEventsRepository
import org.apache.spark.sql.Dataset
import org.springframework.stereotype.Component


@Component
class FilterService(@transient enrichedBetEventsRepository: EnrichedBetEventsRepository) {

  def filterByCountryAndGamePrefix(): Dataset[EnrichedBetEvents] = {
    println("count before filter " + enrichedBetEventsRepository.getAllEnrichedBetEventsDS().count())
    println("count how much left after USA and -demo will be removed " + enrichedBetEventsRepository.getAllEnrichedBetEventsDS().
      filter(!_.bothCountryAndSuffix("USA", "-demo")).count())
    enrichedBetEventsRepository.getAllEnrichedBetEventsDS().filter(!_.bothCountryAndSuffix("USA", "-demo")).persist()
  }


}
