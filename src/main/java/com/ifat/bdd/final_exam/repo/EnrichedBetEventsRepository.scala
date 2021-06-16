package com.ifat.bdd.final_exam.repo

import com.ifat.bdd.final_exam.model.loading.User
import com.ifat.bdd.final_exam.model.mapping.EnrichedBetEvents
import com.ifat.bdd.final_exam.services.UserService
import org.apache.spark.sql.{Dataset, Encoders, SparkSession}
import org.springframework.stereotype.Component


@Component
class EnrichedBetEventsRepository(@transient userService: UserService, @transient sparkSession: SparkSession, @transient eventsRepository: BetEventsRepository) {

  private var enrichedBetEventDataSet: Dataset[EnrichedBetEvents] = null
  private val ID_COL="id"
  private val USERID_COL="userID"
  private val USR_ALIAS="user"

  def getAllEnrichedBetEventsDS(): Dataset[EnrichedBetEvents] = {
    enrichedBetEventDataSet
  }

  def createEnrichedBetEventsRepositoryDS() = {
    import com.ifat.bdd.final_exam.model.loading.UserAdaptor._

    val users: java.util.List[User] = userService.getAllUsers()
    val usersDataset= sparkSession.createDataFrame(users).as(USR_ALIAS)

    val eventsDataset = eventsRepository.getAllBetEvents()

    val enrichedBetEventsEncoder= Encoders.product[EnrichedBetEvents]
    enrichedBetEventDataSet = eventsDataset.join((usersDataset), usersDataset(ID_COL) === eventsDataset(USERID_COL))
      .drop(ID_COL).as[EnrichedBetEvents](enrichedBetEventsEncoder).persist()

    enrichedBetEventDataSet.show(false)
  }
}
