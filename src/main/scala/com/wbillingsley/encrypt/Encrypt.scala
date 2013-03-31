package com.wbillingsley.encrypt

object Encrypt {
 
  import java.security.SecureRandom
  import javax.crypto.SecretKeyFactory
  import javax.crypto.spec.PBEKeySpec
  import scala.util.Try
  
  val keyFactory = Try(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")).toOption
  val random = Try(SecureRandom.getInstance("SHA1PRNG")).toOption
  
  /** Key length in bits. Must be divisible by 24 to Base64-encode with our quick-and-dirty encoder*/
  val derivedKeyLength = 192 

  /** Salt length in bytes. Must be divisible by 3 to Base64-encode with our quick-and-dirty encoder*/
  val saltBytes = 12

  val iterations = 1000
  
  def encrypt(saltB64:String, password:String) = {    
    val salt = Base64.decode(saltB64)    
    val spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength)    
    val r = for (k <- keyFactory) yield Base64.encode(k.generateSecret(spec).getEncoded()) 
    r getOrElse { throw new IllegalStateException("No key factory") }
  } 
    
  def genSaltB64 = {
    val b = Array.ofDim[Byte](saltBytes)
    for (r <- random) r.nextBytes(b)
    Base64.encode(b)
  }
}