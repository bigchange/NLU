package com.kunyan.text

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks

/**
  * Created by C.J.YOU on 2016/11/29.
  */
object util {

  def topKeyWords(keyWords: mutable.HashMap[String, Float], size: Int) = {

    keyWords.toList.sortBy(_._2).reverse.take(size)

  }

  /**
    * 关键词过滤
    *
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
             if(y._1.contains(x)) {
               isNeed = false
               break.break()
             }
           }
         }
       }

      isNeed

      }

    val filter = filtered.map { x =>
      if(x._1.length >= 3)
        (x._1.substring(0,3), x._2)
      else
        x
    }
    filter

  }

  def sentenceFilter(list:ListBuffer[String], keyWords: List[(String, Float)]) = {

    val minKeyWordNum = keyWords.length - 1
    val sentences = new ListBuffer[String]

    for(item <- list) {

      if(item.length >= 6 && item.length <= 20) {
        var count = 0
        for (key <- keyWords) {
          if(item.contains(key._1)) {
            count += 1
          }
        }
        if(count >= minKeyWordNum)
          sentences.+=(item)
      }
    }

    sentences

  }

  def getBestSentence(lb: ListBuffer[String], keyWords: List[(String, Float)], summary: String): String = {

    val list = lb.filter(_ + "。" != summary)

    if(list.isEmpty) {
      return "无效事件"
    }
    val weights = new ListBuffer[(String,Float)]
    var maxWeight = 0.0.toFloat
    for( item <- list) {
      var weight = 0.0.toFloat
      keyWords.foreach { x =>
        if(item.contains(x._1)) {
          weight += x._2
        }
      }
      if(weight > maxWeight)
        maxWeight = weight
      weights.+=((item, weight))
    }
    // weights.filter(_._2 == maxWeight).foreach(println)
    weights.map(x=> (x._1, x._1.length)).maxBy(_._2)._1
  }

}
