package com.ifat.bdd.final_exam.model.mapping

import scala.beans.BeanProperty

case class StatisticsData(@BeanProperty gameName: String, @BeanProperty minBetAsUSD: Double, @BeanProperty maxBetAsUSD: Double,
                          @BeanProperty avgBetAsUSD: Double, @BeanProperty minWinAsUSD: Double, @BeanProperty maxWinAsUSD: Double,
                          @BeanProperty avgWinAsUSD: Double, @BeanProperty minProfitAsUSD: Double, @BeanProperty maxProfitAsUSD: Double,
                          @BeanProperty avgProfitAsUSD: Double) extends Serializable {
}
