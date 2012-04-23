package models

import play.api.Play.current
import java.util.{Date}
import com.novus.salat._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import se.radley.plugin.salat._

case class User(
  id: ObjectId = new ObjectId,
  username: String,
  password: String,
  address: Option[Address] = None,
  added: Date = new Date(),
  updated: Option[Date] = None,
  deleted: Option[Date] = None,
  @Key("company_id")company: Option[ObjectId] = None
)

object User extends ModelCompanion[User, ObjectId] {
  val collection = getCollection("users")
  val dao = new SalatDAO[User, ObjectId](collection = collection) {}

  def findOneByUsername(username: String): Option[User] = findOne(MongoDBObject("username" -> username))
  def findByCountry(country: String) = find(MongoDBObject("address.country" -> country))
}
