//package org.bigchange.nlu
//
//import edu.arizona.sista.processors.corenlp.CoreNLPProcessor
//
///**
//  * Created by C.J.YOU on 2016/4/20.
//  */
//object CoreNLPProcessor {
//
//  def main(args: Array[String]) {
//    val proc = new CoreNLPProcessor
//    val textEN = "John Doe went to China. There, he visited Beijing."
//    val textCN = "约翰去了中国，他参观了北京。"
//    val docEN = proc.mkDocument(textEN,keepText = false)
//    val docCN = proc.mkDocument(textCN,keepText= false)
//    // Note that the method called (doc.clear()) clears the internal structures created by the actual
//    // CoreNLP annotators. This saves a lot of memory, so, although it is not strictly necessary, I recommend you call it.
//    docEN.clear()
//    docCN.clear()
//    for(sentence <- docEN.sentences){
//      /**
//        * token：英文实现分词效果
//        * John Doe went to China .
//          There , he visited Beijing .
//        */
//      // println(sentence.words.mkString(":"))
//
//    }
//
//    for (sentence <- docCN.sentences){
//      /**
//        * token: 中文实现分词效果(暂时无法实现) Segmentation standard(中文分词两个标准)  :ctb and pku
//        */
//      println(sentence.words.mkString(" "))
//    }
//
//
//
//  }
//
//}
