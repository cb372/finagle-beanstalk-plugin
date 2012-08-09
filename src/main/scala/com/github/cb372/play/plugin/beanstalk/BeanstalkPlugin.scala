package com.github.cb372.play.plugin.beanstalk

import play.api._
import play.api.libs.concurrent.{Akka, Promise}

import com.github.cb372.finagle.beanstalk.client.{BeanstalkClient, Job, PutOpts}

/**
 * Provides a Beanstalk client
 */
class BeanstalkPlugin(app: Application) extends Plugin {

  private implicit val implicitApp = app

  private lazy val hosts = app.configuration.getString("beanstalk.hosts").getOrElse("localhost:11300")

  /**
  * provides access to the Beanstalk client
  */           
  lazy val client: BeanstalkClient = BeanstalkClient.build(hosts)

  override def onStart() {
    client
  }

  override def onStop() {
    client.quit() 
  }

  override lazy val enabled = {
    !app.configuration.getString("beanstalkplugin").filter(_ == "disabled").isDefined
  }

  def putJob(job: String): Promise[Int] = Akka.future {
    client.put(job, PutOpts()).get().right.get // No error handling - hope it worked!
  }

  def reserveJob(job: String): Promise[Job[Array[Byte]]] = Akka.future {
    client.reserve().get().right.get // No error handling - hope it worked!
  }
}
