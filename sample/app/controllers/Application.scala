package controllers

import play.api._
import play.api.mvc._
import play.api.Play.current

import com.github.cb372.play.plugin.beanstalk.BeanstalkPlugin

object Application extends Controller {

  val beanstalk = current.plugin[BeanstalkPlugin].getOrElse(sys.error("failed to load Beanstalk plugin"))
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def putJob(name: String) = Action {
    val jobIdPromise = beanstalk.putJob(name)
    Async {
      jobIdPromise.map(i => Ok("Put a job with ID : " + i))
    }
  }
  
  def reserveJob = Action {
    val jobPromise = beanstalk.reserveJob("ignore me")
    Async {
      jobPromise.map(j => Ok("Reserved a job : ID = " + j.id + ", data = " + new String(j.data, "UTF-8")))
    }
  }
}
