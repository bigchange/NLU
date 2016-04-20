package org.bigchange.nlu

import java.util.Properties

import edu.stanford.nlp.ling.CoreAnnotations.{TextAnnotation, TokensAnnotation, SentencesAnnotation}
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}

/**
  * Created by C.J.YOU on 2016/4/20.
  */
object StanfordCoreNLP {

  def main(args: Array[String]) {
    // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
    val props = new Properties()
    props.setProperty("annotators", "tokenize, ssplit")
    val pipeline = new StanfordCoreNLP(props)
    // read some text in the text variable
    val textCN = "约翰去了中国，他参观了北京。"
    val textEN = "John Doe went to China. There, he visited Beijing."
    // create an empty Annotation just with the given text
    val doc= new Annotation(textCN)
    // run all Annotators on this text
    pipeline.annotate(doc)

    // these are all the sentences in this document
    // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
    val sentences = doc.get(classOf[SentencesAnnotation])
    val iterator = sentences.iterator()
    while(iterator.hasNext){
      // traversing the words in the current sentence
      // a CoreLabel is a CoreMap with additional token-specific methods
      val tokens =  iterator.next().get(classOf[TokensAnnotation])
      val tokenIterator = tokens.iterator()
      while(tokenIterator.hasNext){
        val token = tokenIterator.next()
        // this is the text of the token
        val text = token.get(classOf[TextAnnotation])
        println("token:"+text)
      }
    }


  }

}
