package com.ifat.bdd.final_exam.model.mapping

import java.sql.Timestamp

import scala.beans.BeanProperty

case class EnrichedBetEvents(@BeanProperty eventId: Int, @BeanProperty eventTime: Timestamp, @BeanProperty eventCountry: String,
                             @BeanProperty eventCurrencyCode: String,@BeanProperty  bet: Double, @BeanProperty gameName: String,
                             @BeanProperty win: Double, @BeanProperty onlineTimeSecs: Long, @BeanProperty userId: Int,
                             @BeanProperty  name: String,@BeanProperty  lastName: String, @BeanProperty countryOfOrigin: String,
                             @BeanProperty email: String) extends Serializable {

  @transient
  def isMoreThan(hours: Int): Boolean = {
    onlineTimeSecs > hours * 3600
  }

  //Win/Bet ratio is higher than 1/10.
  @transient
  def betRation(ration: Double): Boolean = {
    win > bet * ration
  }

  @transient
  def isSpecialGameSuffix(gameSuffix: String) = {
    gameName.endsWith(gameSuffix)
  }

  @transient
  def isCountryOfOrigin(country: String): Boolean = {
    countryOfOrigin.equals(country)
  }

  @transient
  def inBetween(timestampStart: Timestamp, timestampEnd: Timestamp): Boolean = {
    eventTime.after(timestampStart) && eventTime.before(timestampEnd)
  }

  @transient
  def bothCountryAndSuffix(countryName: String, gameSuffix: String) = {
    isCountryOfOrigin(countryName) && isSpecialGameSuffix(gameSuffix)
  }

}
