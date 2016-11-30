package com.kunyan.text

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks

/**
  * Created by C.J.YOU on 2016/11/29.
  */
object util {

  /**
    * 关键词过滤
    * @param keyWords
    * @return
    */
  def wordFilter(keyWords: java.util.Map[java.lang.String, java.lang.Float]) = {

    val resHashMap = new mutable.HashMap[String, Float]()

    val entrySet = keyWords.entrySet()
    val iterator = entrySet.iterator()
    while(iterator.hasNext) {
      val entry = iterator.next()
      val key = entry.getKey
      val value = entry.getValue
      if(value >= 0.02)
        resHashMap.put(key, value)
    }
    val keys = resHashMap.keySet
    val filtered = resHashMap.filter { y =>
       var isNeed = true
       if(y._1.length > 2) {
         val break = new Breaks
         break.breakable {
           keys.filter(_.length <= 2).foreach { x =>
             if(y._1.contains(x) && x.length <=2) {
               isNeed = false
               break.break()
             }
           }
         }
       }
      isNeed
      }
    filtered.map { x =>
      if(x._1.length >= 3)
        (x._1.substring(0,3), x._2)
      else
        x
    }

  }

  def sentenceFilter(list: java.util.List[String], keyWords: mutable.HashMap[String, Float]) = {

    val iterator = list.listIterator()
    val sentences = new ListBuffer[String]
    while(iterator.hasNext) {
      val item = iterator.next()
      if(item.length >= 6 && item.length <= 15) {
        sentences.+=(item)
      }
    }
    sentences
  }

  def getBestSentence(list: java.util.List[String], keyWords: mutable.HashMap[String, Float]) = {

    val iterator = list.listIterator()
    val weights = new ListBuffer[(String,Float)]
    var maxWeight = 0.0.toFloat
    while(iterator.hasNext) {
      var weight = 0.0.toFloat
      val item = iterator.next()
      keyWords.foreach { x =>
        if(item.contains(x._1)) {
          weight += x._2
        }
      }
      if(weight > maxWeight)
        maxWeight = weight
      weights.+=((item, weight))
    }
    weights.filter(_._2 == maxWeight).foreach(println)
    weights.filter(_._2 == maxWeight).map(x=> (x._1, x._1.length)).minBy(_._2)._1

  }

}
