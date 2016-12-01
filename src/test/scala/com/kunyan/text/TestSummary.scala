package com.kunyan.text

import java.io.{FileOutputStream, PrintWriter}

import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

/**
  * Created by C.J.YOU on 2016/11/29.
  */
class TestSummary  extends FlatSpec with Matchers  {

  "summary" should "pass" in {


    val pw = new PrintWriter(new FileOutputStream("src/test/resources/events-HAN.txt", false),true)

    val text = Source.fromFile("src/test/resources/events.txt").getLines().toList.distinct.zipWithIndex.foreach { case (text,id) =>

      val result = Summary.summary(text)
      val summary = id + 1 + " -- " + result._1
      println(summary)
      println(result._2 + "\n")
      // 写入文件
      pw.write(summary + "\n")
      pw.write("description:"+ result._2 + "\n")
      pw.write("\n")
      pw.flush()

    }
    pw.close()




  }

}
