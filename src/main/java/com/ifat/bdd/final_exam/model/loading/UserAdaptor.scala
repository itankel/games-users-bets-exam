package com.ifat.bdd.final_exam.model.loading

import scala.collection.JavaConverters._
import scala.language.implicitConversions

case class UserScala(id:Int, name:String, lastName:String, countryOfOrigin:String, email:String)

object UserAdaptor {
  implicit def toProduct(user: User): UserScala = {
    UserScala(user.getId, user.getName, user.getLastName, user.getCountryOfOrigin, user.getEmail)
  }
  implicit def toProduct1(users: java.util.List[User]): List[UserScala] = {
    users.asScala.toList.map(user =>UserScala(user.getId, user.getName, user.getLastName, user.getCountryOfOrigin, user.getEmail))
  }

}
