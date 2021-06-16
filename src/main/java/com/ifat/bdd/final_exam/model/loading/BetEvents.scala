package com.ifat.bdd.final_exam.model.loading

import java.sql.Timestamp

case class BetEvents(eventId: Int, eventTime: Timestamp, eventCountry: String, eventCurrencyCode: String, userId:
Int, bet: Double, gameName: String, win: Double, onlineTimeSecs: Long) extends Serializable {


  def isMoreThan(hours: Int): Boolean = {
    onlineTimeSecs > hours * 3600
  }

  //Win/Bet ratio is higher than 1/10.
  def betRation(ration: Double): Boolean = {
    win > bet * ration
  }

  def statesDemo() = {
    gameName.endsWith("-demo")
  }


  def inBetween(timestampStart: Timestamp, timestampEnd: Timestamp): Boolean = {
    eventTime.after(timestampStart) && eventTime.before(timestampEnd)
  }

}
